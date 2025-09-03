package com.mysite.hackathon.meeting.service;

import com.mysite.hackathon.meeting.dto.MeetingRequestDto;
import com.mysite.hackathon.meeting.dto.MeetingResponseDto;
import com.mysite.hackathon.meeting.entity.Meeting;
import com.mysite.hackathon.meeting.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;

    // ✅ 동(dong) 기준 + 카테고리 조건 조회
    public List<MeetingResponseDto> getMeetings(String category, String dong) {
        List<Meeting> meetings;

        if (category == null) {
            meetings = meetingRepository.findByDong(dong);
        } else {
            meetings = meetingRepository.findByCategoryAndDong(category, dong);
        }

        return meetings.stream()
                .map(MeetingResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public MeetingResponseDto getMeeting(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));
        return MeetingResponseDto.fromEntity(meeting);
    }

    public MeetingResponseDto createMeeting(MeetingRequestDto dto) {
        Meeting meeting = Meeting.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .locationLat(dto.getLocationLat())
                .locationLng(dto.getLocationLng())
                .address(dto.getAddress())
                .dong(dto.getDong())   // ✅ DTO에서 동 입력받아 저장
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .restriction(dto.getRestriction())
                .genderLimit(dto.getGenderLimit())
                .hasAfterparty(dto.getHasAfterparty())
                .build();

        meetingRepository.save(meeting);
        return MeetingResponseDto.fromEntity(meeting);
    }

    public MeetingResponseDto updateMeeting(Long id, MeetingRequestDto dto) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        meeting.setTitle(dto.getTitle());
        meeting.setDescription(dto.getDescription());
        meeting.setCategory(dto.getCategory());
        meeting.setLocationLat(dto.getLocationLat());
        meeting.setLocationLng(dto.getLocationLng());
        meeting.setAddress(dto.getAddress());
        meeting.setDong(dto.getDong()); // ✅ 수정 시에도 동 반영
        meeting.setStartTime(dto.getStartTime());
        meeting.setEndTime(dto.getEndTime());
        meeting.setRestriction(dto.getRestriction());
        meeting.setGenderLimit(dto.getGenderLimit());
        meeting.setHasAfterparty(dto.getHasAfterparty());

        meetingRepository.save(meeting);
        return MeetingResponseDto.fromEntity(meeting);
    }

    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }
}
