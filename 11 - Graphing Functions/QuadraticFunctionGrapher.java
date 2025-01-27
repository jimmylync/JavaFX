// Jimmy Lynch Assignment 11
/*
• 10/10 Points: Source code contains program header and comments;
 code organized and readable
• 15/15 Points: Sliders exist for coefficients a, b, c; labels appear next to sliders
• 15/15 Points: ChangeListener activates when any coefficient is changed
• 15/15 Points: equation displayed on Canvas; exponent rendered correctly
 (should display as “x2”, do NOT use the notation “x^2”)
• 15/15 Points: function graphs correctly
• 20/20 Points: canvas image can be saved to file (activate via Button or MenuItem)
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

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;


/**
 *   
 *   JavaFX API: https://openjfx.io/javadoc/11/
 */ 
public class QuadraticFunctionGrapher extends Application 
{
    // run the application
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

    // Application is an abstract class,
    //  requires the method: public void start(Stage s)
    public void start(Stage mainStage) 
    {
        // set the text that appears in the title bar
        mainStage.setTitle("Quadratic Function Grapher");
        mainStage.setResizable(true);

        // layout manager: organize window contents
        BorderPane root = new BorderPane();

        // set font size of objects
        root.setStyle(  "-fx-font-size: 18;"  );

        // May want to use a Box to add multiple items to a region of the screen
        VBox box = new VBox();
        // add padding/margin around area
        box.setPadding( new Insets(16) );
        // add space between objects
        box.setSpacing( 16 );
        // set alignment of objects (default: Pos.TOP_LEFT)
        box.setAlignment( Pos.CENTER );
        // Box objects store contents in a list
        List<Node> boxList = box.getChildren();
        // if you choose to use this, add it to one of the BorderPane regions
        
        // Scene: contains window content
        // parameters: layout manager; width window; height window
        // *Not setting the size because we're doing size to scene at the
         // bottom of the code
        //Scene mainScene = new Scene(root, 600, 600)
        Scene mainScene = new Scene(root);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );

        
        
        // custom application code below -------------------

        
        Button saveButton = new Button("Save");
        root.setTop( saveButton );
        
        
        root.setCenter( box );
        
        Label label1 = new Label("Quadratic function:");
        boxList.add( label1 );
        
        // Using Unicode to display exponents
        // https://en.wikipedia.org/wiki/Unicode_subscripts_and_superscripts
        Label label2 = new Label("y = ax\u00B2 + bx + c");
        boxList.add( label2 );
        
        
        Label label3 = new Label("a:");
        Slider aSlider = new Slider();
        aSlider.setMinWidth( 300 );
        aSlider.setMin(-3);
        aSlider.setMax( 3);
        aSlider.setValue(1);
        aSlider.setShowTickMarks(true);
        aSlider.setShowTickLabels(true);
        aSlider.setMajorTickUnit(1);
        
        // Not adding label3 or aSlider directly to the VBox
        // Adding them to an HBox ("row") and then adding that row to the VBOX
        HBox row3 = new HBox();
        row3.setSpacing( 16 );
        row3.setAlignment( Pos.CENTER );
        row3.getChildren().addAll( label3, aSlider );
        boxList.add( row3 );
        
        
        Label label4 = new Label("b:");
        Slider bSlider = new Slider();
        bSlider.setMinWidth( 300 );
        bSlider.setMin(-3);
        bSlider.setMax(3);
        bSlider.setValue(1);
        bSlider.setShowTickMarks(true);
        bSlider.setShowTickLabels(true);
        bSlider.setMajorTickUnit(1);
        
        HBox row4 = new HBox();
        row4.setSpacing( 16 );
        row4.setAlignment( Pos.CENTER );
        row4.getChildren().addAll( label4, bSlider );
        boxList.add( row4 ); 
        
        
        // Still need to add functionality
        Label label5 = new Label("c:");
        Slider cSlider = new Slider();
        cSlider.setMinWidth( 300 );
        cSlider.setMin(-3);
        cSlider.setMax(3);
        cSlider.setValue(1);
        cSlider.setShowTickMarks(true);
        cSlider.setShowTickLabels(true);
        cSlider.setMajorTickUnit(1);
        
        HBox row5 = new HBox();
        row5.setSpacing( 16 );
        row5.setAlignment( Pos.CENTER );
        row5.getChildren().addAll( label5, cSlider );
        boxList.add( row5 );
        
        
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill( Color.WHITE );
        context.fillRect(0,0, 400,400);
        boxList.add(canvas);
        
        // set up a listener that graphs the function y = ax^2 + bx + c
        ChangeListener<Number> listener =
            (property, oldValue, newValue) ->
            {
                // to graph a function:
                
                // clear the canvas: draw blank rectangle
                context.setFill( Color.WHITE );
                context.fillRect(0,0, 400,400);
                
                // draw axis lines 
                context.setStroke( Color.BLACK );
                context.setLineWidth( 2 );
                // draw x axis
                context.strokeLine(0,200, 400,200);
                // draw y axis
                context.strokeLine(200,0,  200,400);
                
                // draw points on the graph of y = ax^2 + bx + c
                
                // get a, b, and values from sliders
                double a = aSlider.getValue();
                double b = bSlider.getValue();
                double c = cSlider.getValue();
                
                // make graph color different than axis color
                context.setFill( Color.BLUE );
                
                // Looping through every value of x in a range to find y for
                 // each x
                // Not actually continuous, just doing at really small 
                 // discrete increments (.01) so that it looks continuous
                for (double xMath = -4; xMath <= 4; xMath += 0.01)
                {
                    // y = ax^2 + bx + c
                    double yMath = a*(Math.pow(xMath, 2)) + b*(xMath) + c;
                    //double yMath = a*(xMath * xMath) + b*(xMath) + c;
                    
                    // Stuff to convert math coordinates to canvas 
                     // coordinates:
                    // I think x ends up going from [0, 400]
                    // I guess we do it this way so that our loop isn't 
                     // specific to the size of our canvas
                    double xCanvas = 50 * (xMath + 4);
                    double yCanvas = 400 - (50 * (yMath + 4));
                    // Drawing the line by drawing a bunch of tiny points
                     // along the x and y coordinates (converted to match canvas)
                    context.fillOval(xCanvas, yCanvas, 4,4);
                }
                
                // Displaying what equation we're graphing:
                
                // Round values to two decimal places so that we can display 
                 // in text without having a ton of decimal places
                double aRound = Math.round( a * 100.0 ) / 100.0;
                double bRound = Math.round( b * 100.0 ) / 100.0;
                double cRound = Math.round( c * 100.0 ) / 100.0;
                
                // create string containing the equation
                // *Idk how to write x^2
                 // Is there a way to do this in a String?
                // Formatted string?
                String equation = "y = (" + aRound + ")x\u00B2 + (" +  bRound + ")x + " + cRound;
                
                // draw text on canvas
                context.setLineWidth( 3 );
                context.setStroke( Color.BLACK );
                context.setFill( Color.WHITE );
                context.setFont( new Font("Arial Bold", 16) );
                // Needed to move to the left a little bit
                context.strokeText( equation, 185, 390 );
                context.fillText( equation, 185, 390 );
                
                
            };
            
        // when a slider changes, run the above code
        aSlider.valueProperty().addListener( listener );
        bSlider.valueProperty().addListener( listener );
        cSlider.valueProperty().addListener( listener );
    
        
        
        // Functionality for save button
        saveButton.setOnAction(
            (ActionEvent event) ->
            {
                // code is copied from "ColorViewer" example Java file.

                FileChooser saveChooser = new FileChooser();
                // can force a file extension by using "ExtensionFilter" --
                //   specifies the types of files that can be opened/saved
                ExtensionFilter filter = new ExtensionFilter("Image Files", "*.png"); // save as png - highest quality
                saveChooser.getExtensionFilters().add( filter );

                File saveFile = saveChooser.showSaveDialog(mainStage);

                try
                {
                    // create an "Image" object; stores pixels from canvas
                    Image tempImage = canvas.snapshot(null, null);

                    // BufferedImage: Image with methods for reading and writing pixel data
                    BufferedImage buffImage = SwingFXUtils.fromFXImage( tempImage, null );

                    // output image data to a file
                    ImageIO.write( buffImage, "png", saveFile );

                }
                catch (Exception error)
                {
                    error.printStackTrace();
                }
            }
        );
        
        
        
        // custom application code above -------------------        
        
        
        // after adding all content, make the Stage visible
        mainStage.show();
        
        // resizes the window to fit the elements in the scene
        // *This is why we didn't set a size when making our Scene
        mainStage.sizeToScene();
        
        
    }
}