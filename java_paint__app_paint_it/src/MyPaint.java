import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.java2d.pipe.OutlineTextRenderer;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * This is a fantastic paint application that lets the users draw different objects , animate, change fill and
 * stroke colors by clicking on them and has undo and clear screen options .It also has option to bring the object to
 * Top or bottom .
 * @author Roshan Shah, roshanrupeshkumarshah@gmail.com
 */
public class MyPaint extends Application {

    /**
     * This is the Canvas
     */
    private Canvas c;
    /**
     * Label to highlight the menu with a different color
     */
    private  Label menu;


    /**
     * This is the Circle button to select the circle shape to be drawn with the mouse
     */
    private static Button circle;
    /**
     * This is the rectangle button to select the rectangle shape to be drawn with the mouse
     */
    private static Button rectangle;
    /**
     * This is the line button to select the line shape to be drawn with the mouse
     */
    private static Button line;
    /**
     * This is the clear button to clear the things on the screen
     */
    private static Button clear;
    /**
     * This is the undo button to remove the last object drawn from the canvas
     */
    private static Button undo;
    /**
     * This is a textfield to input the lineWidth
     */
    private static TextField lineWidth;
    /**
     * This is a color picker that provides us with a drop down menu for picking colors
     */
    private ColorPicker fillPicker;
    /**
     * This is an arrayList of the type GeometricObject
     */
    private ArrayList<GeometricObject> geometricObject = new ArrayList<>();
    /**
     * This is an arrayList of the type GeometricObject
     */
    private ArrayList<GeometricObject> newList = new ArrayList<>();
    /**
     * This is an GraphicsContect to draw on
     */
    private GraphicsContext gc, trans;
    /**
     * This is a color picker that provides us with a drop down menu for picking colors
     */
    private ColorPicker outlinePicker;
    /**
     * This is a button to reset the shape colors
     */
    private Button resetShapeColor;
    /**
     * This is a Label named Fill to point out the fill picker
     */
    private Label fill;
    /**
     * This is a Label named outline to point out the fill picker
     */
    private Label outline;
    /**
     * This is an instance of geometric object
     */
    private GeometricObject g1;
    /**
     * this is an integer variable to keep track of the number of objects
     */
    private int count = 0;
    /**
     * is the staring x coordinate of the object
     */
    private double xs;
    /**
     * is the staring y coordinate of the object
     */
    private double ys;
    /**
     * is the ending x coordinate of the object
     */
    private double xf;
    /**
     * is the ending y coordinate of the object
     */
    private double yf;
    /**
     * This is the shake button which triggers the animate method
     */
    private Button shake;
    /**
     * This button brings the object selected on the screen to top
     */
    private Button toTop;
    /**
     * This button brings the object selected on the screen to bottom
     */
    private Button toBackground;
    /**
     * This is the transparent canvas
     */
    private Canvas top;
    /**
     * This is a variable to control the choice of objects the user wants to draw on screen
     */
    private int choice = 1;
    /**
     * This is a loopControl for the animate method
     */
    private int animateCount = 0;
    /**
     * This is an integer variable to control the movement of objects to top and bottom on canvas
     */
    private int displayControl;
    /**
     * This is an integer varaible to store the index for selected object
     */
    private int swapindex = 0;
    /**
     * This is a geometric object that contains the reference for the object that needs to be moved
     */
    private GeometricObject swap;
    /**
     * This integer variable stores the size of the arrayList
     */
    private int sizeOfArray;
    /**
     * This is a label that points the lineWidth textfield to the user
     */
   private Label widthOfLine;
    /**
     * This label contains all the instructions for the user
     */
   private Label instructions;
   private Label instructionHeading;


    /**
     * This method is mouse event handler that is triggered when the mouse button is pressed
     *
     * @param pressed is the MouseEvent
     */
    private void mousePressed(MouseEvent pressed) {
        undo.setVisible(false);
        double xs = pressed.getX();
        double ys = pressed.getY();
        this.xs = xs;
        this.ys = ys;

    }

    /**
     * This method is a mouse event handler that is triggered when the mouse button is dragged.
     * In order to draw an object , it checks what the choice variable is and everyime gets the current  x and
     * y locations for the object then clears the transparent screen each time and updates the locations and draws on the
     * transparent canvas again
     *
     * @param me is the MouseEvent
     */
    private void mouseDragged(MouseEvent me) {
        double xf = me.getX(), yf = me.getY();
        if (xf<0 || xf>800 || yf<0 || yf>510){
            Alert  alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Please draw within the screen limits");
            alert.showAndWait();
        }


        if (choice == 1) {
            trans.clearRect(0, 0, 800, 510);
            xf = me.getX();
            yf = me.getY();

            try {
                g1 = new Rectangle(xs, ys, fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), (xf - xs), (yf - ys));

                if (xf < xs && yf < ys) {
                    g1 = new Rectangle(xs - (xs - xf), ys - (ys - yf), fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), Math.abs(xf - xs), Math.abs(yf - ys));

                }
                if(!( (xf<0 || xf>800 || yf<0 || yf>510))) {
                    g1.draw(trans);
                }
            } catch (NumberFormatException n) {
                Alert  alert =new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Number Format Exception!");
                alert.setContentText("An integer value expected for Line Width");
                alert.showAndWait();
            }


        }
        if (choice == 2) {

            trans.clearRect(0, 0, 800, 510);

            xf = me.getX();
            yf = me.getY();
            try {


                g1 = new Circle(xs, ys, fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), yf - ys);
                if (xf < xs && yf < ys) {
                    g1 = new Circle(xs, ys, fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), Math.abs(yf - ys));
                }
                if(!( (xf<0 || xf>800 || yf<0 || yf>510))) {
                    g1.draw(trans);
                }
            } catch (NumberFormatException n) {
                Alert  alert =new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Number Format Exception!");
                alert.setContentText("An integer value expected for Line Width");
                alert.showAndWait();
            }


        }

        if (choice == 3) {
            trans.clearRect(0, 0, 800, 510);

            xf = me.getX();
            yf = me.getY();
            try {


                g1 = new Line(xs, ys, fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), xf, yf);
                if(!( (xf<0 || xf>800 || yf<0 || yf>510))) {
                    g1.draw(trans);
                }
            } catch (NumberFormatException n) {
                Alert  alert =new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Number Format Exception!");
                alert.setContentText("An integer value expected for Line Width");
                alert.showAndWait();
            }


        }


    }

    /**
     * This method is a mouse event handler that is triggered when the mouse button is released
     * This method draws the object on the opaque screen and uses the clrscr() and drawIt() methods to avoid code
     * repitition.
     * After drawing an object , it adds the object to the arrayList
     *
     * @param me is the MouseEvent
     */
    private void mouseRealesed(MouseEvent me) {
        if(!( (xf<0 || xf>800 || yf<0 || yf>510))){
        if (displayControl != 1 && displayControl != 2) {

            if (choice == 2) {
                xf = me.getX();
                yf = me.getY();
                try {
                    g1 = new Circle(xs, ys, fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), yf - ys);
                    if (xf < xs && yf < ys) {
                        g1 = new Circle(xs, ys, fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), Math.abs(yf - ys));
                    }

                    xf = yf = 0;
                    geometricObject.add(g1);
                    count++;
                    clrscr();
                    drawIt();
                    trans.clearRect(0, 0, 800, 510);
                    undo.setVisible(true);
                } catch (NumberFormatException n) {

                    Alert  alert =new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Number Format Exception!");
                    alert.setContentText("An integer value expected for Line Width");
                    alert.showAndWait();
                }


            }
            if (choice == 1) {


                xf = me.getX();
                yf = me.getY();
                try {

                    g1 = new Rectangle(xs, ys, fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), (xf - xs), (yf - ys));

                    if (xf < xs && yf < ys) {
                        g1 = new Rectangle(xs - (xs - xf), ys - (ys - yf), fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), Math.abs(xf - xs), Math.abs(yf - ys));

                    }
                    xf = yf = 0;
                    geometricObject.add(g1);
                    count++;
                    clrscr();
                    drawIt();
                    trans.clearRect(0, 0, 800, 510);
                    undo.setVisible(true);
                } catch (NumberFormatException n) {
                    Alert  alert =new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Number Format Exception!");
                    alert.setContentText("An integer value expected for Line Width");
                    alert.showAndWait();
                }

            }

            if (choice == 3) {

                xf = me.getX();
                yf = me.getY();
                try {


                    g1 = new Line(xs, ys, fillPicker.getValue(), Integer.parseInt(lineWidth.getText()), outlinePicker.getValue(), xf, yf);
                    g1.setFillColor(fillPicker.getValue());
                    g1.setStrokeColor(outlinePicker.getValue());

                    xf = yf = 0;
                    geometricObject.add(g1);
                    count++;
                    clrscr();
                    drawIt();
                    trans.clearRect(0, 0, 800, 510);
                    undo.setVisible(true);
                } catch (NumberFormatException n) {
                    Alert  alert =new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Number Format Exception!");
                    alert.setContentText("An integer value expected for Line Width");
                    alert.showAndWait();
                }

            }
            //   geometricObject.add(g1);

        }

    }
    }

    /**
     * This method is mouse event handler that is triggered when the mouse button is clicked
     * Click checks for the state of displayControl and then performs different operations .
     * It changes the fill and outline color of the objects when the displayControl is 0 and when it is 1 or 2,
     * it gets the location of the object clicked and its index and swaps the index values to top or bottom respectively.
     *
     * @param me is the MouseEvent
     */
    private void mouseClicked(MouseEvent me) {

        if (displayControl == 0) {
            for (GeometricObject a : geometricObject) {

                if (a instanceof Rectangle) {

                    if ((me.getX() >= a.getX() && me.getX() <= a.getX() + ((Rectangle) a).getWidth()) && (me.getY() >= a.getY() && a.getY() <= a.getY() + ((Rectangle) a).getHeight())) {
                        if (me.isStillSincePress()) {
                            a.setFillColor(fillPicker.getValue());
                            a.setStrokeColor(outlinePicker.getValue());
                        }
                    }
                } else if (a instanceof Circle) {
                    if ((me.getX() >= a.getX() && me.getX() <= a.getX() + ((Circle) a).getRadius()) && (me.getY() >= a.getY() && me.getY() <= a.getY() + ((Circle) a).getRadius())) {

                        if (me.isStillSincePress()) {
                            a.setFillColor(fillPicker.getValue());
                            a.setStrokeColor(outlinePicker.getValue());
                        }
                    }
                }

            }

        } else {

try{
            for (GeometricObject a : geometricObject) {

                if (a instanceof Rectangle) {
                    if ((me.getX() >= a.getX() && me.getX() <= a.getX() + ((Rectangle) a).getWidth()) && (me.getY() >= a.getY() && a.getY() <= a.getY() + ((Rectangle) a).getHeight())) {
                        if (me.isStillSincePress()) {

                                swapindex = geometricObject.indexOf(a);
                                swap = a;


                        }
                    }
                } else if (a instanceof Circle) {
                    if ((me.getX() >= a.getX() && me.getX() <= a.getX() + ((Circle) a).getRadius()) && (me.getY() >= a.getY() && me.getY() <= a.getY() + ((Circle) a).getRadius())) {

                        if (me.isStillSincePress()) {

                                swapindex = geometricObject.indexOf(a);
                                swap = geometricObject.get(swapindex);

                        }

                    }
                }


            }

            if (displayControl == 1) {

try {
    GeometricObject moveToTop = geometricObject.get(swapindex);

    geometricObject.remove(swapindex);

    geometricObject.add(moveToTop);

}
catch(IndexOutOfBoundsException e){
    Alert  alert =new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Index Out of Bounds Exception!");
    alert.setContentText("Please clear screen and try again");
    alert.showAndWait();
}

            }


            if (displayControl == 2) {
                try {
                    GeometricObject moveToBottom = geometricObject.get(swapindex);
                    geometricObject.remove(swapindex);

                    geometricObject.add(0, moveToBottom);


                }
                catch(IndexOutOfBoundsException e){
                    Alert  alert =new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Index Out of Bounds Exception!");
                    alert.setContentText("Please clear screen and try again");
                    alert.showAndWait();                }

            }



    }
    catch(ArrayIndexOutOfBoundsException e) {
        Alert  alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle(" Array Index Out of Bounds Exception!");
        alert.setContentText("Please clear screen and try again");
        alert.showAndWait();
    }
            displayControl = 0;
        }

        clrscr();
        drawIt();
        undo.setVisible(true);
        swapindex = 0;
        swap = null;



    }

    /**
     * This is a button event handler linked to the clear button and it clears the whole canvas .
     *
     * @param a is the Action Event
     */
    private void clear(ActionEvent a) {




        geometricObject.removeAll(geometricObject);


        trans.clearRect(0, 0, 800, 630);
        // trans.setFill(Color.LIGHTYELLOW);
        // trans.fillRect(0,0,800,630);
        gc.setFill(Color.WHITE);
        trans.setFill(Color.WHITE);
        trans.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 630);





        undo.setVisible(false);
    }

    /**
     * This is a button event handler linked to the undo button .
     * This method removes the last object drawn from the screen
     *
     * @param b is the Action event
     */
    private void undo(ActionEvent b) {
        try {
            geometricObject.remove(count - 1);
            count--;
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, 800, 510);

            for (GeometricObject a : geometricObject) {

                a.draw(gc);
            }
            if (geometricObject.size() < 1) {
                undo.setVisible(false);
            }

    }
    catch(ArrayIndexOutOfBoundsException e){
        Alert  alert =new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Array Index Out of Bounds Exception!");
        alert.setContentText("Please clear screen and try again");
        alert.showAndWait();
        undo.setVisible(false);
    }
        catch(IndexOutOfBoundsException e){
            Alert  alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Index Out of Bounds Exception!");
            alert.setContentText("Please clear screen and try again");
            alert.showAndWait();
            undo.setVisible(false);
        }

    }






    /**
     * This is a method to animate the objects using the pause transition and platform RunLater methods.
     *
     * @param gc GraphicsContext to draw on
     **/
    public void animate(GraphicsContext gc) {
        for (int i = 0; i < geometricObject.size(); i++) {
            newList.add(geometricObject.get(i));
        }


        Platform.runLater(() -> {


            animateCount = 0;
            while (true) {

                for (GeometricObject a : newList) {
                    if (animateCount == 1) {
                        a.setX(a.getX() + 20);
                        a.setY(a.getY() + 20);

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {

                        }

                    }

                    if (animateCount == 2) {
                        a.setX(a.getX() - 40);
                        a.setY(a.getY() - 40);

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {

                        }


                    }

                    if (animateCount == 3) {
                        a.setX(a.getX() + 20);
                        a.setY(a.getY() + 20);

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {

                        }


                    }
animateCount++;



                    PauseTransition wait = new PauseTransition(Duration.seconds(1));
                    wait.setOnFinished((e) -> {
                        clrscr();
                        a.draw(gc);


                    });

                    wait.play();

                }
                if (animateCount >3) {
                    break;
                }


                //  pause(1000 / 60);
            }


        });
        clrscr();
        drawIt();

    }

    /**
     * This is the pause method that contains the Thread.sleep method in order to pause the thread.
     *
     * @param duration is an integer value that is passed to the Thread.sleep method
     */
    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
        }
    }

    /**
     * This is an button event handler linked to the shake button that calls the animate method and plays the animation
     *
     * @param c is the Action Event
     */
    private void shake(ActionEvent c) {
        clrscr();
        Thread t = new Thread(() -> animate(gc));
        t.start();

        animate(gc);
        try {
            drawIt();
        } catch (NullPointerException e) {
            Alert  alert =new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Null Pointer exception!");
            alert.setContentText("Clear screen and Try again");
            alert.showAndWait();

        }
        clrscr();
        drawIt();


    }

    /**
     * This is a method to draw the objects in the array list to the opaque canvas
     */
    private void drawIt() {
        for (GeometricObject a : geometricObject) {
            a.draw(gc);
        }
    }

    /**
     * This is a method to clear the transparent and opaque canvas
     */
    private void clrscr() {
        gc.clearRect(0, 0, 800, 510);
        trans.clearRect(0, 0, 800, 510);
    }

    /**
     * This is a event handler that is linked to the toTop button and sets the displayControl to 1
     *
     * @param a is the Action Event
     */
    private void top(ActionEvent a) {
        displayControl = 1;


    }

    /**
     * This is a event handler that is linked to the toBottom button and sets the displayControl to 2
     *
     * @param a is the Action Event
     */
    private void bottom(ActionEvent a) {
        displayControl = 2;
    }

    /**
     * This is an event handler linked to the Circle button
     * This is a method to control which object is to be drawn . Its sets the choice variable to 2.
     *
     * @param a is the Action Event
     */
    private void circlePressed(ActionEvent a) {
        choice = 2;
    }

    /**
     * This is an event handler linked to the Rectangle button
     * This is a method to control which object is to be drawn . Its sets the choice variable to 1.
     *
     * @param a is the Action Event
     */
    private void rectanglePressed(ActionEvent a) {
        choice = 1;
    }

    /**
     * This is an event handler linked to the Line button
     * This is a method to control which object is to be drawn . Its sets the choice variable to 3.
     *
     * @param a is the Action Event
     */
    private void linePressed(ActionEvent a) {
        choice = 3;
    }


    // TODO: Instance Variables for View Components and Model
    // TODO: Private Event Handlers and Helper Methods

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 665); // set the size here
        stage.setTitle("FX GUI Template"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model


        // 2. Create the GUI components
        menu=new Label("");

        instructions=new Label(
                "\n --> Clear button clears the screen but" +
                "\n the objects can't be retreived after that"
        +"\n -->To top brings object to foreground"
        +"\n -->To Background sends the object to background"+
                "\n Note:Press the button and perform action for each object"+
                "\n The button has to be pressed each time to achieve this"+
                "\n --> To change the color of an object-" +
                "\n 1)Select the fill and outline color " +
        "\n 2)Click inside the object!");
        widthOfLine=new Label("Line Width:");
        toBackground = new Button("To Background");
        toTop = new Button("To Top");
        shake = new Button("Shake it!");
        circle = new Button("Circle");
        rectangle = new Button("Rectangle");
        clear = new Button("Clear");
        undo = new Button("Undo");
        line = new Button("Line");
        fill = new Label("Fill Color");
        outline = new Label("Outline Color");
        lineWidth = new TextField("5");
        instructionHeading=new Label("Instructions");

        fillPicker = new ColorPicker();
        resetShapeColor = new Button("Reset Color");
        outlinePicker = new ColorPicker();
        c = new Canvas(800, 510);
        top = new Canvas(800, 510);




        gc = c.getGraphicsContext2D();
        trans = top.getGraphicsContext2D();


        // gc.setFill(Color.WHITE);
        //gc.fillRect(0,0,800,510);


        // 3. Add components to the root
        root.getChildren().addAll(menu,c, top, circle, rectangle, clear, undo, line, fillPicker, outlinePicker, fill, outline, lineWidth, toBackground, toTop,widthOfLine,instructions,instructionHeading);
        // 4. Configure the components (colors, fonts, size, location)
        fillPicker.setValue(Color.RED);
        outlinePicker.setValue(Color.BLUE);
        circle.relocate(10, 515);

        rectangle.relocate(70, 515);
        undo.setVisible(false);

        line.relocate(150, 515);

        clear.relocate(200, 515);
        undo.relocate(260, 515);
        fillPicker.relocate(0, 585);
        outlinePicker.relocate(130, 585);
        fill.relocate(20, 565);
        fill.setFont(new Font(14));
        outline.relocate(150, 565);
        outline.setFont(new Font(14));
        lineWidth.relocate(320, 555);
        lineWidth.setPrefWidth(80);
        shake.relocate(260, 585);
        toTop.relocate(330, 585);
        toBackground.relocate(390, 585);
        widthOfLine.relocate(255,555);
        lineWidth.setStyle("-fx-background-color:lightblue;-fx-text-fill:darkred");
        instructions.relocate(500,530);
        instructions.setMaxWidth(315);
        instructions.setMaxHeight(170);
        instructions.setFont(new Font(8));
        instructionHeading.relocate(530,520);

        // 5. Add Event Handlers and do final setup
        top.addEventHandler(MouseEvent.MOUSE_PRESSED, this::mousePressed);
        top.addEventHandler(MouseEvent.MOUSE_RELEASED, this::mouseRealesed);
        top.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::mouseDragged);
        top.addEventHandler(MouseEvent.MOUSE_CLICKED, this::mouseClicked);

        shake.setOnAction(this::shake);
        clear.setOnAction(this::clear);
        toTop.setOnAction(this::top);
        toBackground.setOnAction(this::bottom);
        undo.setOnAction(this::undo);
        circle.setOnAction(this::circlePressed);
        rectangle.setOnAction(this::rectanglePressed);
        line.setOnAction(this::linePressed);
        menu.relocate(0,510);
        menu.setPrefSize(800,155);
        menu.setStyle("-fx-background-color:lightyellow");


        // 6. Show the stage
        stage.show();


    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
