// Jimmy Lynch Assignment 6
/*
• 15/15 Points: Source code contains program header and comments;
 code organized and readable
• 60/60 Points (10 points each): used at least one of each of the following:
 rectangle, oval, polygon, text, color, gradient.
• 15/15 Points: your image displays a high level of creativity and effort (yes, this is subjective)
• 10/10 Points: Grade report completed fully and accurately
*/

import javafx.application.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.beans.value.*;
import javafx.event.*; 
import javafx.animation.*;
import javafx.geometry.*;
import java.io.*;
import java.util.*;

/**
 *   
 *   JavaFX API: https://openjfx.io/javadoc/11/
 */ 
public class CanvasPainting extends Application 
{
    
    public static void main(String[] args) 
    {
        try
        {
            // creates Stage, calls the start method
            launch(args);
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }

    public void start(Stage mainStage) 
    {
        // set the text that appears in the title bar
        mainStage.setTitle("Cool painting");
        mainStage.setResizable(false);

        // layout manager: organize window contents
        // Why VBox and not Pane:
        // Helps to center the canvas in the window without having to 
        // manually set it somewhere
        VBox root = new VBox();
        List<Node> rootList = root.getChildren();

        // add padding/margin around layout manager
        root.setPadding( new Insets(16) );
        // add space between objects in layout manager
        root.setSpacing( 16 );
        // set alignment of objects in layout manager (default: Pos.TOP_LEFT)
        root.setAlignment( Pos.CENTER );
        // set font size of objects
        root.setStyle(  "-fx-font-size: 24;"  );

        // Scene: contains window content
        // parameters: layout manager; width window; height window
        Scene mainScene = new Scene(root, 800, 800);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );

        
        // custom application code below -------------------

        // a Canvas object allows you to draw lines, shapes, text, etc.
        // Canvas needs to be a little bit smaller than the window it's on
        Canvas canvas = new Canvas(700, 700);
        rootList.add(canvas);
        
        // GraphicsContext class has methods to draw on a Canvas object
        GraphicsContext context = canvas.getGraphicsContext2D();
        
        
        
        
        // Use at least one of each of the following:
        // rectangle, oval, polygon, text, color, gradient (linear or radial)
        
        
        // Setting color for drawing filled out objects
        context.setFill( Color.LIGHTSKYBLUE );
        
        // Drawing rectangle:
        // parameters: x,y (top left corner), width, height
        context.fillRect(1, 1, 700, 600);
        
        
        context.setFill( Color.MEDIUMSPRINGGREEN );
        context.fillRect(1, 600, 700, 100);
        
        
        
        // Space
        context.setFill( Color.BLACK );
        context.fillRect(1, 1, 700, 150);
        
        // ( 1-700, 16-150)
        context.setFill( Color.WHITE );
        context.fillRect(30, 30, 1, 1);
        context.fillRect(500, 60, 1, 1);
        context.fillRect(200, 90, 1, 1);
        context.fillRect(650, 110, 1, 1);
        context.fillRect(100, 135, 1, 1);
        context.fillRect(475, 130, 1, 1);
        context.fillRect(600, 30, 1, 1);
        
        
        
        // Making a gradient of the visible spectrum
        // red, orange, yellow, green, cyan, blue, violet
        Stop[] stopArray = {
                new Stop(0.0, Color.RED),
                new Stop(1/6.0, Color.ORANGE),
                new Stop(2/6.0, Color.YELLOW),
                new Stop(3/6.0, Color.GREEN),
                new Stop(4/6.0, Color.CYAN),
                new Stop(5/6.0, Color.BLUE),
                new Stop(1.0, Color.VIOLET)
            };
        // Stretches horizontally over the entire gradient axis
        LinearGradient rainbowGradient =
                new LinearGradient(0, 0, 1, 0,
                        true, CycleMethod.NO_CYCLE, stopArray);
        
        context.setFill( rainbowGradient );
        context.fillRect(1, 1, 700, 15);
        
        
        
        // Making a spaceship with ovals and polygons:
        
        // Making our spaceship's color gray
        context.setFill( Color.GRAY );
        
        // Left/top angle
        double[] xCoordinates = {300, 340, 340, 300};
        double[] yCoordinates = {64,  62, 58, 50};
        context.fillPolygon( xCoordinates, yCoordinates, 4 );
        
        // Right/bottom angle
        double[] x2Coordinates = {300, 340, 340, 300};
        double[] y2Coordinates = {71,  73, 77, 85};
        context.fillPolygon( x2Coordinates, y2Coordinates, 4 );
        
        // Ship center
        context.fillOval(302.5, 59, 32.5, 17);
        
        
        
        
        // custom application code above -------------------

        // after adding all content, make the Stage visible
        mainStage.show();
    }
}