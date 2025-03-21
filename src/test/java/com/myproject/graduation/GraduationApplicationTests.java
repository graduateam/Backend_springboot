package com.myproject.graduation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class GraduationApplicationTests {

	@Test
	@Disabled
	void contextLoads() {
	}

}
