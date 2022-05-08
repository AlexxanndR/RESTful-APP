package com.project.web.models;

public class Parallelogram {
    private static double length;
    private static double height;
    private static double perimeter;
    private static double area;

    Parallelogram() {
        Parallelogram.length = 0;
        Parallelogram.height = 0;
        Parallelogram.perimeter = 0;
        Parallelogram.area = 0;
    }

    public static double getHeight() {
        return Parallelogram.height;
    }
    public static void setHeight(double height) { Parallelogram.height = height; }

    public static double getLength() {
        return Parallelogram.length;
    }
    public static void setLength(double length) { Parallelogram.length = length; }

    public static double getPerimeter() {
        return Parallelogram.perimeter;
    }
    public static void setPerimeter(double perimeter) { Parallelogram.perimeter = perimeter; }

    public static double getArea() { return Parallelogram.area; }
    public static void setArea(double area) { Parallelogram.area = area; }
}
