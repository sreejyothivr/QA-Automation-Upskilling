import JavaAssignments.Circle;
import JavaAssignments.Rectangle;
import JavaAssignments.Shape;

public class ShapeTest {

    public static void main(String[] args) {

        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        System.out.printf("Circle Area: %.2f%n", circle.calculateArea());
        System.out.printf("Rectangle Area: %.0f%n", rectangle.calculateArea());
    }

}
