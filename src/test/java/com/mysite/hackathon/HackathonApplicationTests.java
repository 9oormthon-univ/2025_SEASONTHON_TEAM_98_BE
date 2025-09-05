package com.mysite.hackathon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.mysite.hackathon.home_page.HackathonApplication;

@SpringBootTest(classes = HackathonApplication.class)
class HackathonApplicationTests {

	@Test
	void contextLoads() {
		// Spring Boot context가 제대로 로드되는지만 확인하는 기본 테스트
	}
}
