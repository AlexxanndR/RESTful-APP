package com.project.web.models;

public class Parallelogram {
    private double height;
    private double length;
    private double perimeter;
    private double aree;

    public Parallelogram() { }

    public Parallelogram(double height, double length) {
        this.height = height;
        this.length = length;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) { this.height = height; }

    public double getLength() {
        return length;
    }
    public void setLength(double length) { this.length = length; }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() { return aree; }

    public double CalculPerimeter() {
        return perimeter = 2 * (height + length);
    }

    public double CalculArea() {
        return aree = height * length;
    }

}
