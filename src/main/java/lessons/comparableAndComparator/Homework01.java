package lessons.comparableAndComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Homework01 {
    public static void main(String[] args) {
        List<Rectangle> rectangles = Arrays.asList(
            new Rectangle("rect 20x20",20, 20),
            new Rectangle("rect 10x10", 10, 10),
            new Rectangle("rect 35x10",35, 10)
        );

        System.out.println("Unsort");
        for (Rectangle rectangle : rectangles) {
            System.out.println(rectangle.getName());
        }

        rectangles.sort(new squareComp());
        System.out.println("Square sort");
        for (Rectangle rectangle : rectangles) {
            System.out.println(rectangle.getName());
        }

        rectangles.sort(new perimeterComp());
        System.out.println("Perimeter sort");
        for (Rectangle rectangle : rectangles) {
            System.out.println(rectangle.getName());
        }
    }
}
 class Rectangle {
    private String name;
    private int width;
    private int height;

    Rectangle(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

     public String getName() {
         return name;
     }

     public int getWidth() {
         return width;
     }

     public int getHeight() {
         return height;
     }
 }

 class squareComp implements Comparator<Rectangle> {
    public int compare(Rectangle a, Rectangle b) {
       int squareA = a.getWidth() * a.getHeight();
       int squareB= b.getWidth() * b.getHeight();
       return squareA > squareB ? 1 : squareA < squareB ? -1 : 0;
    }
 }

 class perimeterComp implements Comparator<Rectangle> {
    public int compare(Rectangle a, Rectangle b) {
       int perimeterA = (a.getWidth() + a.getHeight()) * 2;
       int perimeterB= (b.getWidth() + b.getHeight()) * 2;
       return perimeterA > perimeterB ? 1 : perimeterA < perimeterB ? -1 : 0;
    }
 }