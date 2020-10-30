import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the Geometric abstract class
 * This is the top level class that has an abstract draw method and
 * all accessor and mutator methods.
 *
 * @author Roshan Shah, roshanrupeshkumarshah@gmail.com
 */
public abstract class GeometricObject {
    /**
     * this is the constructor for the Geometric object
     * it sets the x, y ,fillcolor,lineWidth and Outline for the Geometric objects
     * @param x is the x coordinate of the object
     * @param y is the y coordinate  of the object
     * @param fillColor is the fill color of the object
     * @param lineWidth is the width of line of object
     * @param outLine is the stroke color of the object
     */
    public GeometricObject(double x, double y, Color fillColor,double lineWidth,Color outLine){
    this.x=x;
    this.y=y;
    this.fillColor=fillColor;
    this.lineWidth=lineWidth;
    this.outlineColor=outLine;


}


    private Color outlineColor;
    private double x = 60, y = 100;
    private Color fillColor ;
    private double lineWidth = 5;

    /**
     * This is a method to set color and line width for objects
     *
     * @param gc GraphicsContext to draw on
     */
    public void setStyles(GraphicsContext gc) {

        gc.setStroke(outlineColor);
        gc.setFill(fillColor);
        gc.setLineWidth(lineWidth);
    }

    /**
     * This is a method to return the x coordinate
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * This is a method to set the x values
     * @param x is the x coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * This is a method to return the y coordinate values
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * This is a method to set the y values
     * @param y is the y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * This is a method to return the stroke color
     * @return outlineColor
     */
    public Color getStrokeColor() {
        return outlineColor;
    }

    /**
     * This is a method to set  the stroke color
     * @param strokeColor is the color of stroke of the objects
     */
    public void setStrokeColor(Color strokeColor) {
        this.outlineColor = strokeColor;
    }

    /**
     * This is a method to return the fill color
     * @return fillColor
     */
    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * This is a return method for line Width
     * @return lineWidth
     */
    public double getLineWidth() {
        return lineWidth;
    }

    /**
     * This is a method to set the line width
     * @param lineWidth is the width of the line
     */
    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    /**
     * abstract draw method for geometric  object
     * @param gc GraphicsContext to draw on
     */
    public  abstract void  draw(GraphicsContext gc);

    /**
     * To String representation of the object
     * @return toString
     */
    @Override
    public String toString() {
        return "GeometricObject{" + "x=" + x + ", y=" + y + ", strokeColor=" + outlineColor + ", fillColor=" + fillColor + ", lineWidth=" + lineWidth + '}';
    }

}
