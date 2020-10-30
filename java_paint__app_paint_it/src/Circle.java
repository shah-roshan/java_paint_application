import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *This is the circle class that extends the Geometric object
 *
 * @author Roshan  Shah, roshanrupeshkumarshah@gmail.com
 */
public class Circle extends GeometricObject {

    /**
     * radius value for the circle
     */
    private double radius = 50;

    /**
     * This is a method to return the radius for the circle
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * This is a method to set the radius of the circle
     * @param radius is the radius of the circle
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            System.out.println("ERROR! Radius must be greater than 0. Not changed.");
        } else {
            this.radius = radius;
        }
    }

    /**
     * This is a constructor for the Circle class that takes  the values
     * @param x is the x coordinate
     * @param y is the y coordinate
     * @param fillColor is the fill Color of the object
     * @param lineWidth is the width of the line
     * @param outLine is the color of stroke
     * @param radius is the radius of the object
     *
     */
    public Circle(double x, double y, Color fillColor, double lineWidth, Color outLine,double radius){
        super(x,y,fillColor,lineWidth,outLine);
        this.radius=radius;


    }


    /**
     * Is the draw method for the Circle
     * @param gc GraphicsContext to draw on
     */
    @Override
    public void draw(GraphicsContext gc) {
        setStyles(gc); // Exercise 1a
        gc.fillOval(getX()-radius/2, getY()-radius/2 , radius , radius );
        gc.strokeOval(getX()-radius/2, getY()-radius/2 , radius , radius );
    }

    /**
     * Is the method to return the string representation of the object
     * @return toString
     */
    @Override
    public String toString() {
        return "Circle{" + "radius=" + radius + '}'
                + "\n-->" + super.toString(); // Exercise 2a
    }

}
