package com.mysite.hackathon.meeting.repository;

import com.mysite.hackathon.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByDong(String dong);
    List<Meeting> findByCategoryAndDong(String category, String dong);
}
