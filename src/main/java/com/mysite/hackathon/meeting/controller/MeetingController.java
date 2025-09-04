package com.mysite.hackathon.meeting.controller;

import com.mysite.hackathon.meeting.dto.MeetingRequestDto;
import com.mysite.hackathon.meeting.dto.MeetingResponseDto;
import com.mysite.hackathon.meeting.dto.ParticipantInfoDto;
import com.mysite.hackathon.meeting.dto.ParticipationStatusDto;
import com.mysite.hackathon.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;

    /** ✅ 모임 리스트 조회 (동 필수, 카테고리 선택적) */
    @GetMapping
    public ResponseEntity<List<MeetingResponseDto>> getMeetings(
            @RequestParam(required = false) String category,
            @RequestParam String dong
    ) {
        return ResponseEntity.ok(meetingService.getMeetings(category, dong));
    }

    /** ✅ 모임 상세 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<MeetingResponseDto> getMeeting(@PathVariable Long id) {
        return ResponseEntity.ok(meetingService.getMeeting(id));
    }

    /** ✅ 모임 생성 */
    @PostMapping
    public ResponseEntity<MeetingResponseDto> createMeeting(@RequestBody MeetingRequestDto dto) {
        return ResponseEntity.ok(meetingService.createMeeting(dto));
    }

    /** ✅ 모임 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<MeetingResponseDto> updateMeeting(
            @PathVariable Long id,
            @RequestBody MeetingRequestDto dto
    ) {
        return ResponseEntity.ok(meetingService.updateMeeting(id, dto));
    }

    /** ✅ 모임 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.noContent().build();
    }

    /** ✅ 새 기능: 모임별 참가자 리스트 조회 */
    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantInfoDto>> getParticipants(@PathVariable Long id) {
        return ResponseEntity.ok(meetingService.getParticipants(id));
    }

    /** ✅ 새 기능: 특정 모임 참여 상태 확인 */
    @GetMapping("/{id}/status")
    public ResponseEntity<ParticipationStatusDto> getParticipationStatus(
            @PathVariable Long id,
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(meetingService.getParticipationStatus(id, userId));
    }
}
