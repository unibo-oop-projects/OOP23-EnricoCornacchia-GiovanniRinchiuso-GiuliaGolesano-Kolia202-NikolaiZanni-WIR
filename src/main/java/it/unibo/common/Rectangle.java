package it.unibo.common;

public class Rectangle {
    private double x;
    private double y;
    private final double width;
    private final double height;

    public Rectangle(double x, double y, double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public boolean intersects(Rectangle other) {
        // This is a simple intersection check assuming rectangles are axis-aligned and origin is at the top-left
        // You may need to adjust this depending on your coordinate system and rectangle representation
        return this.width > other.width && this.height > other.height;
    }
}