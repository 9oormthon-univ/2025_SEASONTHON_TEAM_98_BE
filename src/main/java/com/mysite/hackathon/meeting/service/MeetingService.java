package com.mysite.hackathon.meeting.service;

import com.mysite.hackathon.global.exception.MeetingNotFoundException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final ParticipantRepository participantRepository;

    /** ✅ 동 + 카테고리 기반 모임 리스트 조회 */
    @Transactional(readOnly = true)
    public List<MeetingResponseDto> getMeetings(String category, String dong) {
        List<Meeting> meetings = (category == null)
                ? meetingRepository.findByDong(dong)
                : meetingRepository.findByCategoryAndDong(category, dong);

        return meetings.stream()
                .map(m -> MeetingResponseDto.fromEntity(m,
                        participantRepository.findAllByMeeting(m).size()))
                .collect(Collectors.toList());
    }

    /** ✅ 모임 상세 조회 */
    @Transactional(readOnly = true)
    public MeetingResponseDto getMeeting(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new MeetingNotFoundException(id));

        int currentParticipants = participantRepository.findAllByMeeting(meeting).size();
        return MeetingResponseDto.fromEntity(meeting, currentParticipants);
    }

    /** ✅ 모임 생성 */
    @Transactional
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
                .maxParticipants(dto.getMaxParticipants())
                .build();

        meetingRepository.save(meeting);
        return MeetingResponseDto.fromEntity(meeting, 0);
    }

    /** ✅ 모임 수정 */
    @Transactional
    public MeetingResponseDto updateMeeting(Long id, MeetingRequestDto dto) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new MeetingNotFoundException(id));

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
        meeting.setMaxParticipants(dto.getMaxParticipants());

        meetingRepository.save(meeting);

        int currentParticipants = participantRepository.findAllByMeeting(meeting).size();
        return MeetingResponseDto.fromEntity(meeting, currentParticipants);
    }

    /** ✅ 모임 삭제 */
    @Transactional
    public void deleteMeeting(Long id) {
        if (!meetingRepository.existsById(id)) {
            throw new MeetingNotFoundException(id);
        }
        meetingRepository.deleteById(id);
    }

    /** ✅ 모임별 참가자 리스트 조회 */
    @Transactional(readOnly = true)
    public List<ParticipantInfoDto> getParticipants(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new MeetingNotFoundException(meetingId));

        List<MeetingParticipant> participants = participantRepository.findAllByMeeting(meeting);

        return participants.stream()
                .map(p -> new ParticipantInfoDto(
                        p.getUser().getId(),
                        p.getUser().getUsername()
                ))
                .collect(Collectors.toList());
    }

    /** ✅ 특정 모임 참여 상태 확인 */
    @Transactional(readOnly = true)
    public ParticipationStatusDto getParticipationStatus(Long meetingId, Long userId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new MeetingNotFoundException(meetingId));

        User user = new User();
        user.setId(userId);

        boolean joined = participantRepository.findByMeetingAndUser(meeting, user).isPresent();

        return new ParticipationStatusDto(
                meetingId,
                userId,
                joined ? "참여중" : "미참여"
        );
    }

    /** ✅ 내 주변 모임 조회 (반경 + 카테고리) */
    @Transactional(readOnly = true)
    public List<MeetingResponseDto> getNearbyMeetings(double lat, double lng, double radiusKm, String category) {
        double radius = radiusKm * 1000; // km → m 변환
        List<Meeting> meetings = meetingRepository.findMeetingsWithinRadiusAndCategory(lat, lng, radius, category);

        return meetings.stream()
                .map(m -> MeetingResponseDto.fromEntity(m,
                        participantRepository.findAllByMeeting(m).size()))
                .collect(Collectors.toList());
    }
}
