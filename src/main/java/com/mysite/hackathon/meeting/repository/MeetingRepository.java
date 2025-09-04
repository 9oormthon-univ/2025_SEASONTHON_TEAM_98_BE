package com.mysite.hackathon.meeting.repository;

import com.mysite.hackathon.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByDong(String dong);
    List<Meeting> findByCategoryAndDong(String category, String dong);

    /** ✅ 반경 검색 + 카테고리 필터 */
    @Query(value = "SELECT * FROM meetings m " +
            "WHERE ST_Distance_Sphere(POINT(m.location_lng, m.location_lat), POINT(:lng, :lat)) <= :radius " +
            "AND (:category IS NULL OR m.category = :category)",
            nativeQuery = true)
    List<Meeting> findMeetingsWithinRadiusAndCategory(@Param("lat") double lat,
                                                      @Param("lng") double lng,
                                                      @Param("radius") double radius,
                                                      @Param("category") String category);
}
