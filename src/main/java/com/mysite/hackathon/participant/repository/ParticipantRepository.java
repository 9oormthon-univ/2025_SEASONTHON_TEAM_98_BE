package com.mysite.hackathon.participant.repository;

import com.mysite.hackathon.participant.entity.MeetingParticipant;
import com.mysite.hackathon.meeting.entity.Meeting;
import com.mysite.hackathon.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<MeetingParticipant, Long> {
    Optional<MeetingParticipant> findByMeetingAndUser(Meeting meeting, User user);

    /** ✅ 모임별 참가자 리스트 조회 */
    List<MeetingParticipant> findAllByMeeting(Meeting meeting);
}
