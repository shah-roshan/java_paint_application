import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *This is the Rectangle class that extends the Geometric oject
 *
 * @author Roshan  Shah, roshanrupeshkumarshah@gmail.com
 */

public class Rectangle extends GeometricObject {

    /**
     * Size  for the rectangle
     */
    private double width = 200, height = 100;

    /**
     * This is a method to return the width of the Rectangle
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * This is a method to set the width of the Rectangle
     * @param width is the width of the Rectangle
     */
    public void setWidth(double width) {
        if (width <= 0) {
            System.out.println("ERROR! Width must be greater than 0. Not changed.");
        } else {
            this.width = width;
        }
    }

    /**
     * This is a method to return the height of the Rectangle
     * @return height
     */
    public double getHeight() {
        return height;
    }
    /**
     * This is a method to set the height of the Rectangle
     * @param height is the width of the Rectangle
     */
    public void setHeight(double height) {
        if (height <= 0) {
            System.out.println("ERROR! Height must be greater than 0. Not changed.");
        } else {
            this.height = height;
        }
    }

    /**
     * This is a constructor for the Rectangle class that takes  the values
     * @param x is the x coordinate
     * @param y is the y coordinate
     * @param fillColor is the fill Color of the object
     * @param lineWidth is the width of the line
     * @param outLine is the color of stroke
     * @param width is the width of the object
     * @param height is the height of the object
     */
    public  Rectangle(double x, double y, Color fillColor, double lineWidth, Color outLine, double width,double height){
        super(x,y,fillColor,lineWidth,outLine);
        this.width=width;
        this.height=height;


    }

    /**
     * Is the draw method for the rectangle
     * @param gc GraphicsContext to draw on
     */
    @Override
    public void draw(GraphicsContext gc) {
        setStyles(gc); // Exercise 1a
        gc.fillRect(getX() , getY() , width, height);
        gc.strokeRect(getX() , getY() , width, height);

    }

    /**
     * Is the method to return the string representation of the object
     * @return toString
     */
    @Override
    public String toString() {
        return "Rectangle{" + "width=" + width + ", height=" + height + '}'
                + "\n-->" + super.toString(); // Exercise 2a
    }

}
