package com.mysite.hackathon.meeting.service;

import com.mysite.hackathon.meeting.dto.MeetingRequestDto;
import com.mysite.hackathon.meeting.dto.MeetingResponseDto;
import com.mysite.hackathon.meeting.dto.ParticipantInfoDto;
import com.mysite.hackathon.meeting.dto.ParticipationStatusDto;
import com.mysite.hackathon.meeting.entity.Meeting;
import com.mysite.hackathon.meeting.repository.MeetingRepository;
import com.mysite.hackathon.participant.entity.MeetingParticipant;
import com.mysite.hackathon.participant.repository.ParticipantRepository;
import com.mysite.hackathon.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final ParticipantRepository participantRepository;

    /** ✅ 모임 리스트 조회 */
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

    /** ✅ 모임 상세 조회 */
    public MeetingResponseDto getMeeting(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));
        return MeetingResponseDto.fromEntity(meeting);
    }

    /** ✅ 모임 생성 */
    public MeetingResponseDto createMeeting(MeetingRequestDto dto) {
        Meeting meeting = Meeting.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .locationLat(dto.getLocationLat())
                .locationLng(dto.getLocationLng())
                .address(dto.getAddress())
                .dong(dto.getDong())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .restriction(dto.getRestriction())
                .genderLimit(dto.getGenderLimit())
                .hasAfterparty(dto.getHasAfterparty())
                .build();
        meetingRepository.save(meeting);
        return MeetingResponseDto.fromEntity(meeting);
    }

    /** ✅ 모임 수정 */
    public MeetingResponseDto updateMeeting(Long id, MeetingRequestDto dto) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        meeting.setTitle(dto.getTitle());
        meeting.setDescription(dto.getDescription());
        meeting.setCategory(dto.getCategory());
        meeting.setLocationLat(dto.getLocationLat());
        meeting.setLocationLng(dto.getLocationLng());
        meeting.setAddress(dto.getAddress());
        meeting.setDong(dto.getDong());
        meeting.setStartTime(dto.getStartTime());
        meeting.setEndTime(dto.getEndTime());
        meeting.setRestriction(dto.getRestriction());
        meeting.setGenderLimit(dto.getGenderLimit());
        meeting.setHasAfterparty(dto.getHasAfterparty());

        meetingRepository.save(meeting);
        return MeetingResponseDto.fromEntity(meeting);
    }

    /** ✅ 모임 삭제 */
    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }

    /** ✅ 새 기능: 모임별 참가자 리스트 조회 */
    public List<ParticipantInfoDto> getParticipants(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        List<MeetingParticipant> participants = participantRepository.findAllByMeeting(meeting);

        return participants.stream()
                .map(p -> new ParticipantInfoDto(
                        p.getUser().getId(),
                        p.getUser().getUsername()
                ))
                .collect(Collectors.toList());
    }

    /** ✅ 새 기능: 특정 모임 참여 상태 확인 */
    public ParticipationStatusDto getParticipationStatus(Long meetingId, Long userId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        User user = new User();
        user.setId(userId);

        boolean joined = participantRepository.findByMeetingAndUser(meeting, user).isPresent();

        return new ParticipationStatusDto(
                meetingId,
                userId,
                joined ? "참여중" : "미참여"
        );
    }
}
