package com.mysite.hackathon.participant.controller;

import com.mysite.hackathon.participant.dto.ParticipantRequestDto;
import com.mysite.hackathon.participant.dto.ParticipantResponseDto;
import com.mysite.hackathon.participant.entity.MeetingParticipant;
import com.mysite.hackathon.participant.service.ParticipantService;
import com.mysite.hackathon.meeting.entity.Meeting;
import com.mysite.hackathon.meeting.repository.MeetingRepository;
import com.mysite.hackathon.user.entity.User;
import com.mysite.hackathon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meetings/{meetingId}")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;

    @PostMapping("/join")
    public ResponseEntity<ParticipantResponseDto> joinMeeting(
            @PathVariable Long meetingId,
            @RequestBody ParticipantRequestDto request
    ) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("모임을 찾을 수 없습니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        MeetingParticipant participant = participantService.joinMeeting(meeting, user);
        return ResponseEntity.ok(ParticipantResponseDto.fromEntity(participant));
    }

    @DeleteMapping("/join")
    public ResponseEntity<String> cancelJoin(
            @PathVariable Long meetingId,
            @RequestBody ParticipantRequestDto request
    ) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("모임을 찾을 수 없습니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        participantService.cancelJoin(meeting, user);
        return ResponseEntity.ok("모임 참여 취소");
    }
}
