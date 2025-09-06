package com.mysite.hackathon.security.auth;

import com.mysite.hackathon.local_login.model.User;                 // ✅ 수정
import com.mysite.hackathon.local_login.repository.LocalUserRepository; // ✅ 수정
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

    private final LocalUserRepository userRepository; // ✅ 수정

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

        // ⚠️ 여기서 그냥 oAuth2User 를 리턴하면,
        // DB에 저장한 User 정보가 SecurityContext 에 안 들어가.
        // CustomOAuth2User 를 따로 만들어서 user.getId(), role 같은 걸 넘겨주는 게 좋아.
        return oAuth2User;
    }
}
