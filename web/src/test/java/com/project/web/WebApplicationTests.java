package com.project.web;

import com.project.web.services.ParallelogramService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {

	private static final ParallelogramService parallelogram = new ParallelogramService();

//	@Test
//	void isCorrectArea() {
//		double length = 10d;
//		double height = 8d;
//		var area = length * height;
//
//		assert parallelogram.calculateArea(height, length) == area;
//	}

//	@Test
//	void isCorrectPerimeter() {
//		double length = 10d;
//		double height = 8d;
//		var perimeter = 2 * (length + height);
//
//		assert parallelogram.calculatePerimeter(height, length) == perimeter;
//	}

}
