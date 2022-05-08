package com.project.web.services;

import com.project.web.IO.InputParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParallelogramServiceTest {
    private static final ParallelogramService parallelogramService = new ParallelogramService();

    @Test
    void testCalculatePerimeter() {
        double length = 10d;
        double height = 8d;
        var perimeter = 2 * (length + height);
        assert parallelogramService.calculatePerimeter(new InputParams(height, length)) == perimeter;
    }

    @Test
    void testCalculateArea() {
        double length = 10d;
        double height = 8d;
        var area = length * height;
        assert parallelogramService.calculateArea(new InputParams(height, length)) == area;
    }
}