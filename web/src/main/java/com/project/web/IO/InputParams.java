package com.project.web.IO;

public class InputParams {
    private double height;
    private double length;

    public InputParams() { }

    public InputParams(double height, double length) {
        this.height = height;
        this.length = length;
    }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }

    @Override
    public String toString() {
        return "InputParams{" +
                "height=" + height +
                ", length=" + length +
                '}';
    }
}
