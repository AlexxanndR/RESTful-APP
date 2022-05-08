package com.project.web.IO;

public class OutputParams {
    private double perimeter;
    private double area;

    public OutputParams() { };

    public OutputParams(double perimeter, double area) {
        this.perimeter = perimeter;
        this.area = area;
    }

    public double getPerimeter() { return perimeter; }
    public void setPerimeter(double perimeter) { this.perimeter = perimeter; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    @Override
    public String toString() {
        return "OutputParams{" +
                "perimeter=" + perimeter +
                ", area=" + area +
                '}';
    }
}
