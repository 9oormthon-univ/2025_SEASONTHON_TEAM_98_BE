package com.mysite.hackathon.participant.repository;

import com.mysite.hackathon.participant.entity.MeetingParticipant;
import com.mysite.hackathon.meeting.entity.Meeting;
import com.mysite.hackathon.local_login.model.User; // ✅ 수정
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<MeetingParticipant, Long> {
    Optional<MeetingParticipant> findByMeetingAndUser(Meeting meeting, User user);

    // ✅ userId 기반 조회 메서드 추가
    Optional<MeetingParticipant> findByMeetingAndUser_Id(Meeting meeting, Long userId);

    /** ✅ 모임별 참가자 리스트 조회 */
    List<MeetingParticipant> findAllByMeeting(Meeting meeting);
}
