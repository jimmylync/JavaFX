// Jimmy Lynch Assignment 7
/*
• 10/10 Points: Source code contains program header and comments;
 code organized and readable
• 60/60 Points Total (12 points each): added the New, Open, Save, Quit, and About menu items,
 and removed the corresponding buttons
• 10/10 Points: ten colored buttons present that set the color used for painting
• 10/10 Points: code for creating color buttons implemented using arrays and loops
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
public class SimplePaintImproved extends Application 
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
        mainStage.setTitle("Simple Paint");
        mainStage.setResizable(false);

        // layout manager: organize window contents
        // Using a BorderPane for this one
        BorderPane root = new BorderPane();
        // List<Node> rootList = root.getChildren();

        // add padding/margin around layout manager
        // root.setPadding( new Insets(16) );
        // add space between objects in layout manager
        // root.setSpacing( 16 );
        // set alignment of objects in layout manager (default: Pos.TOP_LEFT)
        // root.setAlignment( Pos.CENTER );
        // set font size of objects
        root.setStyle(  "-fx-font-size: 18;"  );

        // Scene: contains window content
        // parameters: layout manager; width window; height window
        Scene mainScene = new Scene(root, 600, 600);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );

        
        
        
        // custom application code below -------------------

        Canvas canvas = new Canvas(400, 400);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.setCenter( canvas );

        // draw a large white rectangle to clear canvas drawing area
        context.setFill( Color.WHITE );
        context.fillRect(0,0, 500,500);
        // default drawing color is black
        context.setFill( Color.BLACK );

        canvas.setOnMousePressed(
            (MouseEvent event) ->
            {
                double x = event.getX();
                double y = event.getY();
                // Doing the -5 because actual coordinates are different from
                // what we're getting?
                context.fillOval( x-5, y-5, 10, 10 );
            }
        );
        
        
        // mouse drag event: mouse moves while button pressed
        canvas.setOnMouseDragged(
            (MouseEvent event) ->
            {
                double x = event.getX();
                double y = event.getY();
                context.fillOval( x-5, y-5, 10, 10 );
            }
        );
        
        
        
        
        // Using a loop to implement buttons using arrays of colors:
        
        VBox buttonBox = new VBox();
        root.setLeft( buttonBox );
        
        // Arrays of colors from class:
        String[] colorNames = {"black", "gray", "white", "brown", 
                "red", "orange", "yellow", "green", "blue", "purple"  };

        Color[] colorObjects = {Color.BLACK, Color.GRAY, Color.WHITE, Color.BROWN,
                Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE};
        
        // There's probably a more automated way of doing this:
        Button[] buttons = {new Button("   "), new Button("   "), 
            new Button("   "), new Button("   "), new Button("   "), 
            new Button("   "), new Button("   "), new Button("   "), 
            new Button("   "), new Button("   ") };
        
        for (int i = 0; i < 10; i++)
        {
            buttons[i].setStyle("-fx-base: " + colorNames[i] );
            
            // Using colorObjects[i] inside the lambda doesn't work - idk why
            // Instead, storing the value of colorObjects at index i in a temp
             // variable so it can be accessed from inside the lambda function
            Color storeColor = colorObjects[i];
            buttons[i].setOnAction(
                (ActionEvent event) -> context.setFill( storeColor )  
            );
            
            buttonBox.getChildren().add( buttons[i] );
        }
        
        
        
        
        // Previously: Creating buttons for New, Open, Save, and Quit functionality
        // (Now being done through a menu instead)
        
        // adding menu stuff
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        
        Menu fileMenu = new Menu("File");
        menuBar.getMenus().add(fileMenu);
        
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem quitItem = new MenuItem("Quit");
        
        fileMenu.getItems().add(newItem);
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(quitItem);
        
        
        Menu otherMenu = new Menu("Other");
        menuBar.getMenus().add(otherMenu);
        
        MenuItem aboutItem = new MenuItem("About");
        otherMenu.getItems().add(aboutItem);
        
        
        // Taking the exact functionality that we had for the buttons and
         // using them with menuItems instead
        
        //newButton.setOnAction(
        newItem.setOnAction(
            (ActionEvent event) ->
            {
                // To clear drawing, just drawing over it in all white
                // draw a large white rectangle to clear canvas drawing area
                context.setFill( Color.WHITE );
                context.fillRect(0,0, 500,500);
                // reset drawing color to black
                context.setFill( Color.BLACK );
            }
        );
        
        //openButton.setOnAction(
        openItem.setOnAction(
            (ActionEvent event) ->
            {
                FileChooser openChooser = new FileChooser();
                ExtensionFilter filter = new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
                openChooser.getExtensionFilters().add(filter);
                File file = openChooser.showOpenDialog(mainStage);

                // TODO: Alert user when handle when "cancel" is clicked / file is null

                try
                {
                    // URL = "universal resource locator"
                    String url = file.toURI().toURL().toString();

                    Image imageData = new Image( url );
                    // throw an exception if file does not contain image data
                    if ( imageData.isError() )
                        throw imageData.getException();

                    context.drawImage(imageData, 0,0);
                }
                catch (Exception error)
                {
                    error.printStackTrace();
                    // TODO: replace above line of code
                    // with Alert for user about non-image file error
                }
            }
        );
        
        //saveButton.setOnAction(
        saveItem.setOnAction(
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

        //quitButton.setOnAction(
        quitItem.setOnAction(
            (ActionEvent event) ->
            {
                mainStage.close();
            }
        );

        
        aboutItem.setOnAction(
            (ActionEvent event) ->
            {
                try
                {
                    Alert infoAlert = new Alert( AlertType.INFORMATION );
                 
                    infoAlert.setTitle("About"); 
                    // set title bar text
                    infoAlert.setHeaderText("Simple Paint Program (with Improvements)");
                    // set body text
                    infoAlert.setContentText("Developed by Jimmy Lynch");
                    
                    // display the Alert window
                    infoAlert.showAndWait();
                }
                catch(Exception error)
                {
                    error.printStackTrace();
                    System.exit(0);
                }
            }
        );
        
        

        
        // custom application code above -------------------
        // after adding all content, make the Stage visible
        mainStage.show();
    }
}