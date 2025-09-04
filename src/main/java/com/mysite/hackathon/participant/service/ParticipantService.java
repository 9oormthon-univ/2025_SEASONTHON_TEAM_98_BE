package com.mysite.hackathon.participant.service;

import com.mysite.hackathon.global.exception.AlreadyJoinedException;
import com.mysite.hackathon.global.exception.MaxParticipantsExceededException;
import com.mysite.hackathon.global.exception.ParticipationNotFoundException;
import com.mysite.hackathon.participant.entity.MeetingParticipant;
import com.mysite.hackathon.participant.repository.ParticipantRepository;
import com.mysite.hackathon.meeting.entity.Meeting;
import com.mysite.hackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    @Transactional
    public MeetingParticipant joinMeeting(Meeting meeting, User user) {
        boolean alreadyJoined = participantRepository.findByMeetingAndUser(meeting, user).isPresent();
        if (alreadyJoined) throw new AlreadyJoinedException();

        int currentCount = participantRepository.findAllByMeeting(meeting).size();
        if (meeting.getMaxParticipants() != null && currentCount >= meeting.getMaxParticipants()) {
            throw new MaxParticipantsExceededException();
        }

        MeetingParticipant participant = MeetingParticipant.builder()
                .meeting(meeting)
                .user(user)
                .joinedAt(LocalDateTime.now())
                .build();

        return participantRepository.save(participant);
    }

    @Transactional
    public void cancelJoin(Meeting meeting, User user) {
        MeetingParticipant participant = participantRepository.findByMeetingAndUser(meeting, user)
                .orElseThrow(ParticipationNotFoundException::new);
        participantRepository.delete(participant);
    }
}
