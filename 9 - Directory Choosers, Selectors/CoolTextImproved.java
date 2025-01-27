// Jimmy Lynch Assignment 9
/*
● 10/10 Points: Source code contains program header and comments;
code organized and readable
● 20/20 Points: MenuBar, Menus, and MenuItems added, contain icons and accelerators
● 20/20 Points: Save functionality works, uses FileChooser, filter set to save as *.png file
● 15/15 Points: Quit functionality works
● 20/20 Points: Instructions and About MenuItems display corresponding Alert windows
● 15/15 Points: Grade report completed fully and accurately
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
public class CoolTextImproved extends Application 
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
        mainStage.setTitle("Cool Text");
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
        Scene mainScene = new Scene(root, 600, 600);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );

        // custom application code below -------------------

        
        
        //row 1 in app
        Label label1 = new Label("Text: ");
        TextField field = new TextField("Sample Text");
        
        
        //row 2 in app
        Label label2 = new Label("Font Name: ");
        ComboBox<String> fontNameChooser = new ComboBox<String>();
        List<String> fontNameList = Font.getFontNames();
        for (int i = 0; i < fontNameList.size(); i++)
        {
            String fontName = fontNameList.get(i);
            fontNameChooser.getItems().add(fontName);
            
        }
        //default value in ComboBox drop down list
        fontNameChooser.setValue("Arial");
        
        //row 3 in app
        Label label3 = new Label("Font Size: ");
        Slider fontSizeSlider = new Slider();
        fontSizeSlider.setMin(12);
        fontSizeSlider.setMax(96);
        //default value that Slider is set to
        fontSizeSlider.setValue(24);
        fontSizeSlider.setShowTickLabels(true);
        fontSizeSlider.setShowTickMarks(true);
        fontSizeSlider.setMajorTickUnit(12);
        
        
        //row 4 in app
        Label label4 = new Label("Font Color: ");
        ColorPicker fontColorPicker = new ColorPicker();
        //default in ColorPicker
        fontColorPicker.setValue(Color.PINK);
        
        
        //last row: canvas to display text
        Canvas canvas = new Canvas(500, 100);
        
        
        // Adjusting alignment of VBox and then adding to our BorderPane
        box.setAlignment( Pos.TOP_CENTER );
        root.setCenter(box);
        
        GridPane pane = new GridPane();
        //add same space ("gap") between pane elements, vertically and horizontally
        pane.setHgap(8);
        pane.setVgap(8);
        // Wait GridPane has a method that lets you add entire rows all at
         // once?
        // This seems like so useful
        // Think of like the calculator program
        pane.addRow(0, label1, field);
        pane.addRow(1, label2, fontNameChooser);
        pane.addRow(2, label3, fontSizeSlider);
        pane.addRow(3, label4, fontColorPicker);
        
        boxList.add(pane);
        
        boxList.add(canvas);
        
        
        GraphicsContext context = canvas.getGraphicsContext2D();
        //clear the canvas
        context.setFill(Color.WHITE);
        context.fillRect(0,0, 500,100);
        
        //normally, text location is specified from bottom left corner
         // change coordinate system to top left corner
        context.setTextBaseline(VPos.TOP);
        
        EventHandler<ActionEvent> renderHandler =
            (event ->
            {
                // Clear the canvas
                context.setFill(Color.WHITE);
                context.fillRect(0,0, 500,100);
                
                // Set font and color based on the GUI control values
                context.setFont( new Font( fontNameChooser.getValue(), fontSizeSlider.getValue() ) );
                context.setFill( fontColorPicker.getValue() );
                
                // Draw filled text
                context.fillText(field.getText(), 4,4);
                context.strokeText( field.getText(), 4,4);
            }
        );
        
        // Instead of EventHandler<ActionEvent>, we're doing ChangeListener<Object>
        ChangeListener<Object> renderListener =
            (ov,OldValue, NewValue) ->
            {
                // Clear the canvas
                context.setFill(Color.WHITE);
                context.fillRect(0,0, 500,100);
                
                // Set font and color based on the GUI control values
                context.setFont( new Font( fontNameChooser.getValue(), fontSizeSlider.getValue() ) );
                context.setFill( fontColorPicker.getValue() );
                
                // Draw filled text
                context.fillText(field.getText(), 4,4);
                context.strokeText( field.getText(), 4,4);
            };
        
        // Using event handler for some of these, changelistener for others
        field.textProperty().addListener( renderListener );
        fontNameChooser.setOnAction( renderHandler );
        fontSizeSlider.valueProperty().addListener( renderListener );
        fontColorPicker.setOnAction( renderHandler );
        
        
        field.setText("Hello world");
        
        
        
        // Adding menu features:
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        
        
        Menu fileMenu = new Menu("File");
        menuBar.getMenus().add(fileMenu);
        
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setGraphic( new ImageView( new Image("icons/disk.png") ) );
        fileMenu.getItems().add(saveItem);
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setGraphic( new ImageView( new Image("icons/cross.png") ) );
        fileMenu.getItems().add(quitItem);
        
        Menu otherMenu = new Menu("Help");
        menuBar.getMenus().add(otherMenu);
        
        MenuItem instructItem = new MenuItem("Instructions");
        instructItem.setGraphic( new ImageView( new Image("icons/lightbulb.png") ) );
        otherMenu.getItems().add(instructItem);
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setGraphic( new ImageView( new Image("icons/information.png") ) );
        otherMenu.getItems().add(aboutItem);
        
    
        // Adding keyboard shortcuts, in the form of key combinations that 
         // activate the actions of the menu items
         
        // Hold CTRL and press s for saveItem
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN));
        // CTRL q for quitItem
        quitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.CONTROL_DOWN));
        // CTRL i for instructItem
        instructItem.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCodeCombination.CONTROL_DOWN));
        // CTRL a for aboutItem
        aboutItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCodeCombination.CONTROL_DOWN));
        
        
        // Adding functionality for menu items:
        
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
        
        instructItem.setOnAction(
            (ActionEvent event) ->
            {
                try
                {
                    Alert infoAlert = new Alert( AlertType.INFORMATION );
                    
                    infoAlert.setTitle("Instructions"); 
                    
                    infoAlert.setHeaderText("How to use the Cool Text App");
                    
                    infoAlert.setContentText("Type whatever you want into " +
                        "the text box. As you choose different fonts, " +
                        "increase or decrease the font size, change the " +
                        "font color, or change the text itself, the produced " +                   
                        "text will update automatically. Go to 'File' and " +
                        "'Save' in the menu to save your finished text as a png");
                    
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
        
        aboutItem.setOnAction(
            (ActionEvent event) ->
            {
                try
                {
                    Alert infoAlert = new Alert( AlertType.INFORMATION );
                 
                    infoAlert.setTitle("About"); 
                    
                    infoAlert.setHeaderText("Cool Text App (with Improvements)");
                    
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