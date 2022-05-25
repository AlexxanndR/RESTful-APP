package com.project.web.models;

public class ParallelogramView {
    private static double length;
    private static double height;
    private static double perimeter;
    private static double area;

    ParallelogramView() {
        ParallelogramView.length = 0;
        ParallelogramView.height = 0;
        ParallelogramView.perimeter = 0;
        ParallelogramView.area = 0;
    }

    public static double getHeight() {
        return ParallelogramView.height;
    }
    public static void setHeight(double height) { ParallelogramView.height = height; }

    public static double getLength() {
        return ParallelogramView.length;
    }
    public static void setLength(double length) { ParallelogramView.length = length; }

    public static double getPerimeter() {
        return ParallelogramView.perimeter;
    }
    public static void setPerimeter(double perimeter) { ParallelogramView.perimeter = perimeter; }

    public static double getArea() { return ParallelogramView.area; }
    public static void setArea(double area) { ParallelogramView.area = area; }
}
