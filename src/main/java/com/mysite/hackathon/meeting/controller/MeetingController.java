package com.mysite.hackathon.meeting.controller;

import com.mysite.hackathon.meeting.dto.MeetingRequestDto;
import com.mysite.hackathon.meeting.dto.MeetingResponseDto;
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

    // ✅ 동 필수, 카테고리 선택적
    @GetMapping
    public ResponseEntity<List<MeetingResponseDto>> getMeetings(
            @RequestParam(required = false) String category,
            @RequestParam String dong
    ) {
        return ResponseEntity.ok(meetingService.getMeetings(category, dong));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingResponseDto> getMeeting(@PathVariable Long id) {
        return ResponseEntity.ok(meetingService.getMeeting(id));
    }

    @PostMapping
    public ResponseEntity<MeetingResponseDto> createMeeting(@RequestBody MeetingRequestDto dto) {
        return ResponseEntity.ok(meetingService.createMeeting(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeetingResponseDto> updateMeeting(
            @PathVariable Long id,
            @RequestBody MeetingRequestDto dto
    ) {
        return ResponseEntity.ok(meetingService.updateMeeting(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.noContent().build();
    }
}
