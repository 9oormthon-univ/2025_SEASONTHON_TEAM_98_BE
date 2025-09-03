package com.mysite.hackathon.participant.service;

import com.mysite.hackathon.participant.entity.MeetingParticipant;
import com.mysite.hackathon.participant.repository.ParticipantRepository;
import com.mysite.hackathon.meeting.entity.Meeting;
import com.mysite.hackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public MeetingParticipant joinMeeting(Meeting meeting, User user) {
        boolean alreadyJoined = participantRepository.findByMeetingAndUser(meeting, user).isPresent();
        if (alreadyJoined) throw new RuntimeException("이미 참여한 모임입니다.");

        MeetingParticipant participant = MeetingParticipant.builder()
                .meeting(meeting)
                .user(user)
                .joinedAt(LocalDateTime.now())
                .build();

        return participantRepository.save(participant); // ✅ 반환
    }

    public void cancelJoin(Meeting meeting, User user) {
        MeetingParticipant participant = participantRepository.findByMeetingAndUser(meeting, user)
                .orElseThrow(() -> new RuntimeException("참여 기록이 없습니다."));
        participantRepository.delete(participant);
    }
}
