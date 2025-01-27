// Jimmy Lynch Assignment 10
/*
• 10/10 Points: Source code contains program header and comments;
 code organized and readable
• 10/10 Points: Grade report completed fully and accurately
• For the remaining 80 points: decide how many points you believe your additions are worth,
list what you have done, and how many points you believe you should earn for each part.

Added features from other stuff we've done
-Changed open button to instead be a menu item in a menu that I created
-Added additional functionality to the menu as well
-Added icons for the menu items and for the application itself
-Added keyboard shortcuts for the menu items
10/10 Points

Added sliders to adjust the y position of the text without having to type in a number
40/40 Points

Made the text automatically center itself horizontally
20/20 Points

Messed around with the layout (very jankily) to make everything fit in the window
10/10 Points
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
public class TextAnnotatorImproved extends Application 
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

    public Image imageData;
    
    // Application is an abstract class,
    //  requires the method: public void start(Stage s)
    public void start(Stage mainStage) 
    {
        // set the text that appears in the title bar
        mainStage.setTitle("Text Annotator Improved");
        mainStage.setResizable(false);

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
        Scene mainScene = new Scene(root, 1200, 600);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );

        
        
        // custom application code below -------------------
        
        
        // Setting the icon for the application
        // Adding the icon, as an Image, directly to mainStage
        mainStage.getIcons().add( new Image("icons/smiley.png") );
        
        
        // Messing around with this stuff
        /*
        root.setCenter(box);
        Canvas canvas = new Canvas(500, 500);
        boxList.add(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.WHITE);
        context.fillRect(0,0, 500, 500);
        */
        Canvas canvas = new Canvas(500, 500);
        root.setCenter(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.WHITE);
        context.fillRect(0,0, 500, 500);
        root.setRight(box);
        
        // ***Makes any text drawn with context (writing text on the canvas)
         // automatically centered horizontally
        // I spent a while figuring out how to (kind of) do it manually but 
         // this does it better with literally one line
        context.setTextAlign(TextAlignment.CENTER);
        
        
        // Adding MenuBar to top of window
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        
        // File menu
        Menu fileMenu = new Menu("File");
        menuBar.getMenus().add(fileMenu);
        
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem quitItem = new MenuItem("Quit");
        
        // Adding an image to each menu item to be used as its icon
        newItem.setGraphic( new ImageView( new Image("icons/page_white.png") ) );
        openItem.setGraphic( new ImageView( new Image("icons/folder.png") ) );
        saveItem.setGraphic( new ImageView( new Image("icons/disk.png") ) );
        quitItem.setGraphic( new ImageView( new Image("icons/cross.png") ) );
        
        fileMenu.getItems().add(newItem);
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(quitItem);
        
        // "About" menu
        Menu otherMenu = new Menu("Other");
        menuBar.getMenus().add(otherMenu);
        
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setGraphic( new ImageView( new Image("icons/information.png") ) );
        otherMenu.getItems().add(aboutItem);
        
        
        // Adding keyboard shortcuts, in the form of key combinations that 
         // activate the actions of the menu items
        
        // Hold CTRL and press n for new
        newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCodeCombination.CONTROL_DOWN));
        // CTRL o for quitItem
        openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCodeCombination.CONTROL_DOWN));
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN));
        quitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.CONTROL_DOWN));
        // CTRL a already has a function when working with text
        aboutItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCodeCombination.CONTROL_DOWN));
        

        // Adding functionality to menuItems
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
        
        openItem.setOnAction(
            (ActionEvent event) ->
            {
                FileChooser openChooser = new FileChooser();
                ExtensionFilter filter = new ExtensionFilter("Image Files", "*.png", "*.jpg" );
                openChooser.getExtensionFilters().add(filter);
                File file = openChooser.showOpenDialog(mainStage);
                
                
                try
                {
                    String url = file.toURI().toURL().toString();
                    
                    //load image and resize to fit within 500x500
                    // but true here means to keep aspect ratio, so final size may not be exactly 500x500
                    imageData = new Image(url, 500, 500, true, true);
                    
                    //resize canvas to match image size
                    canvas.setWidth(imageData.getWidth() );
                    canvas.setHeight(imageData.getHeight() );
                    
                    //throw an exception if file does not contain image data
                    if(imageData.isError() )
                        throw imageData.getException();
                        
                    context.drawImage(imageData, 0,0);
                    
                    
                }
                catch(Exception error)
                {
                    error.printStackTrace();
                }
            }
        );
        
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
                    infoAlert.setHeaderText("Text Annotator App (with Improvements)");
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
        
        // The label is like x: and then the field is what we type into
        // Looking at yField1 and yField2
        
        Label yLabel1 = new Label("Vertically Adjust Top text:");
        Slider ySlider1 = new Slider();
        ySlider1.setMin(25);
        ySlider1.setMax(175);
        //default value that Slider is set to
        ySlider1.setValue(25);
        ySlider1.setShowTickLabels(false);
        ySlider1.setShowTickMarks(false);
        ySlider1.setMajorTickUnit(12);
        
        Label yLabel2 = new Label("Vertically Adjust Bottom text:");
        Slider ySlider2 = new Slider();
        ySlider2.setMin(225);
        ySlider2.setMax(425);
        //default value that Slider is set to
        ySlider2.setValue(425);
        ySlider2.setShowTickLabels(false);
        ySlider2.setShowTickMarks(false);
        ySlider2.setMajorTickUnit(12);
        
        HBox rowS1 = new HBox();
        rowS1.setAlignment(Pos.CENTER);
        rowS1.setSpacing(16);
        rowS1.getChildren().addAll(yLabel1, ySlider1);
        boxList.add( rowS1 );
        
        HBox rowS2 = new HBox();
        rowS2.setAlignment(Pos.CENTER);
        rowS2.setSpacing(16);
        rowS2.getChildren().addAll(yLabel2, ySlider2);
        boxList.add( rowS2 );
        
        //Label xLabel1 = new Label("x:");
        //TextField xField1 = new TextField("0");
        //xField1.setMaxWidth(60);
        //Label yLabel1 = new Label("y:");
        //TextField yField1 = new TextField("0");
        //yField1.setMaxWidth(60);
        Label textLabel1 = new Label("Top text: ");
        TextField textField1 = new TextField(" ");
        
        HBox row1 = new HBox();
        row1.setAlignment(Pos.CENTER);
        row1.setSpacing(16);
        // row1.getChildren().addAll(xLabel1, xField1, yLabel1, yField1, textLabel1, textField1);
        row1.getChildren().addAll(textLabel1, textField1);
        boxList.add(row1);
        
        //Label xLabel2 = new Label("x:");
        //TextField xField2 = new TextField("0");
        //xField2.setMaxWidth(60);
        //Label yLabel2 = new Label("y:");
        //TextField yField2 = new TextField("0");
        //yField2.setMaxWidth(60);
        Label textLabel2 = new Label("Bottom text: ");
        TextField textField2 = new TextField(" ");
        
        HBox row2 = new HBox();
        row2.setAlignment(Pos.CENTER);
        row2.setSpacing(16);
        //row2.getChildren().addAll(xLabel2, xField2, yLabel2, yField2, textLabel2, textField2);
        row2.getChildren().addAll(textLabel2, textField2);
        boxList.add(row2);
        
        ChangeListener<Object> renderListener =
            (property, oldValue, newValue) ->
            {
                //clear the canvas
                context.setFill(Color.WHITE);
                context.fillRect(0,0, 500, 500);
                
                //redraw image
                context.drawImage(imageData, 0,0);
                
                context.setStroke(Color.BLACK);
                context.setLineWidth(2);
                context.setTextBaseline(VPos.TOP);
                context.setFont( new Font ("Impact", 48));
                
                try
                {
                    //double x1 = Double.parseDouble(xField1.getText());
                    //double y1 = Double.parseDouble(yField1.getText());
                    double y1 = ySlider1.getValue();
                    String text1 = textField1.getText();
                    // I wanted the text to automatically center itself as
                     // it gets updated
                    // No idea what the actual way to do this would be, since
                     // you can't really move stuff that's drawn on a canvas 
                     // around like you can with objects in a layout manager
                    // Doing it 'manually'
                    // lol
                    //double x1 = 240 - (9.5 * textField1.getText().length() );
                    
                    // *Ok so it turned out there was a built in way to do 
                     // this after all, whoops
                    double x1 = 245;
                    
                    //draw filled text with outline
                    context.strokeText(text1, x1, y1);
                    context.fillText(text1, x1, y1);
                    
                    //double x2 = Double.parseDouble(xField2.getText());
                    //double y2 = Double.parseDouble(yField2.getText());
                    double y2 = ySlider2.getValue();
                    String text2 = textField2.getText();
                    //double x2 = 240 - (9.5 * textField2.getText().length() );
                    double x2 = 245;
                    
                    //draw filled text with outline
                    context.strokeText(text2, x2, y2);
                    context.fillText(text2, x2, y2);
                    
                    
                }
                catch(Exception error)
                {
                    //intentionally doing nothing...
                    // the exception often happens when changing numbers
                }
            };
        
        //xField1.textProperty().addListener( renderListener);
        //yField1.textProperty().addListener( renderListener);
        ySlider1.valueProperty().addListener( renderListener );
        textField1.textProperty().addListener( renderListener);
        
        //xField2.textProperty().addListener( renderListener);
        //yField2.textProperty().addListener( renderListener);
        ySlider2.valueProperty().addListener( renderListener );
        textField2.textProperty().addListener( renderListener);
        
        // custom application code above -------------------

        
        
        // after adding all content, make the Stage visible
        mainStage.show();
        
        //resize stage(window) to scene contents
        mainStage.sizeToScene();

    }
}