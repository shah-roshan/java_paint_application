import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *This is the Line class that extends the Geometric object
 *
 * @author Roshan  Shah, roshanrupeshkumarshah@gmail.com
 */
public class Line extends GeometricObject {

    /**
     * ending coordinates for the Line
     */
   private double endx,endy;
    /**
     * color options for the Line
     */
    private Color fillColor, OutlineColor;




    /**
     * This is a constructor for the Rectangle class that takes  the values
     * @param x is the starting x coordinate
     * @param y is the  starting y coordinate
     * @param fillColor is the fill Color of the object
     * @param lineWidth is the width of the line
     * @param outLine is the color of stroke
     * @param endx is the ending x coordinate of the object
     * @param endy is the ending y coordinate of the object
     */
    public Line(double x, double y, Color fillColor, double lineWidth, Color outLine,double endx,double endy){
        super(x,y,fillColor,lineWidth,outLine);
        this.endx=endx;
        this.endy=endy;
        this.fillColor=fillColor;
        this.OutlineColor=outLine;



    }

    /**
     * This method returns the ending x coordinate of the line
     * @return endx
     */
    public double getEndx(){
        return endx;
}

    /**
     * This method returns the ending y coordinate of the line
     * @return endy
     */
    public double getEndy(){
        return endy;
    }
    /**
     * Is the draw method for the Line
     * @param gc GraphicsContext to draw on
     */
    @Override
    public void draw(GraphicsContext gc) {
        setStyles(gc); // Exercise 1a

       gc.strokeLine(getX(),getY(),getEndx(),getEndy());
    }
    /**
     * Is the method to return the string representation of the object
     * @return toString
     */
    @Override
    public String toString() {
        return " "
                + "\n-->" + super.toString(); // Exercise 2a
    }

}
