package com.mysite.hackathon.security.auth;

import com.mysite.hackathon.user.entity.User;
import com.mysite.hackathon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unchecked")
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String oauth2Id = null;
        String email = null;
        String name = null;

        if (registrationId.equals("google")) {
            oauth2Id = oAuth2User.getAttribute("sub");
            email = oAuth2User.getAttribute("email");
            name = oAuth2User.getAttribute("name");
        } else if (registrationId.equals("kakao")) {
            oauth2Id = String.valueOf(oAuth2User.getAttribute("id"));
            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");

            if (kakaoAccount != null) {
                if (kakaoAccount.containsKey("email")) {
                    email = (String) kakaoAccount.get("email");
                }
                Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
                if (profile != null && profile.containsKey("nickname")) {
                    name = (String) profile.get("nickname");
                }
            }
        }

        Optional<User> existingUser = userRepository.findByOauth2IdAndProvider(oauth2Id, registrationId);
        User user;

        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            user = User.builder()
                    .oauth2Id(oauth2Id)
                    .provider(registrationId)
                    .email(email)
                    .name(name)
                    .build();
        }
        userRepository.save(user);

        return oAuth2User;
    }
}
