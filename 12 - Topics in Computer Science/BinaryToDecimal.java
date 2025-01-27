// Jimmy Lynch Assignment 12
/*
• 10/10 Points: Source code contains program header and comments;
 code organized and readable
• 20/20 Points: application contains explanation of converting binary to decimal
• 20/20 Points: user can enter 4 binary digits as described above
• 20/20 Points: change listener displays correct decimal number automatically
• 20/20 Points: app design aesthetics (fonts, colors, icons, labels, arrangement, menus, etc.)
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
public class BinaryToDecimal extends Application 
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
        mainStage.setTitle("Binary Number Explorer");
        mainStage.setResizable(false);
        /*
        // layout manager: organize window contents
        // ***What if we get rid of the borderpane and just make vbox our root
        VBox box = new VBox();
        box.setStyle(  "-fx-font-size: 18;"  );
        
        // May want to use a Box to add multiple items to a region of the screen
        //VBox box = new VBox();
        // add padding/margin around area
        box.setPadding( new Insets(16) );
        // add space between objects
        box.setSpacing( 16 );
        // set alignment of objects (default: Pos.TOP_LEFT)
        //box.setAlignment( Pos.CENTER );
        // Box objects store contents in a list
        List<Node> boxList = box.getChildren();
        // if you choose to use this, add it to one of the BorderPane regions
        
        // ***Changed from root to box for mainScene
        // Scene: contains window content
        // parameters: layout manager; width window; height window
        Scene mainScene = new Scene(box, 600, 250);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );
        */
       
        // Going back to the default template:
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
        Scene mainScene = new Scene(root, 600, 250);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );
        
        // custom application code below -------------------

        
        // Adding the vbox to the borderpane(our root)
        root.setCenter(box);
        // It's kind of redundant to have a borderpane as our root when we're 
         // only adding stuff to the vbox
        // Ended up just going back and getting rid of the borderpane
        // ***Update - I went back in to add it the borderpane again so that I 
         // could format my button
        
        
        
        // Choosing whether to have everything at the top or in the center
        //box.setAlignment( Pos.TOP_CENTER );
        box.setAlignment( Pos.CENTER );
        
        
        
        Label label1 = new Label("Select values to make a 4-digit binary number: ");
        boxList.add( label1 );
        
        
        
        // Instead of formatting like we did on day 16 (where we made “rows”
         // as hboxes and then adding to the vbox),
         // what if we did it like how we did it on day 13 where we made a 
         // grid pane and added rows to it using pane.addRow(), then added 
         // the pane to the VBox
        GridPane pane = new GridPane();
        //add same space ("gap") between pane elements, vertically and horizontally
        pane.setHgap(8);
        // Messing with gridpane Vgap to make it match the spacing of the vbox
        //pane.setVgap(8);
        pane.setVgap(16);
        // *Comment this out to align the grid at the left instead of the center
        pane.setAlignment( Pos.CENTER );
                
        
        Label label2 = new Label("Bits (binary digits):");
        
        
        // Idk if you can make a ComboBox with ints
        // Making it with strings and then converting later
        // This is the bit all the way at the right
        ComboBox<String> bit0Chooser = new ComboBox<String>();
        bit0Chooser.getItems().addAll("0", "1");
        //default value in ComboBox drop down list
        bit0Chooser.setValue("0");
        
        ComboBox<String> bit1Chooser = new ComboBox<String>();
        bit1Chooser.getItems().addAll("0", "1");
        bit1Chooser.setValue("0");
        
        ComboBox<String> bit2Chooser = new ComboBox<String>();
        bit2Chooser.getItems().addAll("0", "1");
        bit2Chooser.setValue("0");
        
        ComboBox<String> bit3Chooser = new ComboBox<String>();
        bit3Chooser.getItems().addAll("0", "1");
        bit3Chooser.setValue("0");
        
        
        // Bit 0 is the rightmost bit
        pane.addRow(1, label2, bit3Chooser, bit2Chooser, bit1Chooser, bit0Chooser);
        
        
        //boxList.add(pane);
        
        
        
        Label label3 = new Label("Converted to decimal:     ");
        Label label4 = new Label();
        //boxList.add( label3 );     
        
        
        pane.addRow(3, label3, label4);
        boxList.add(pane);
        
        
                
        EventHandler<ActionEvent> renderHandler =
            (event ->
            {
                int bit0 = Integer.parseInt( bit0Chooser.getValue() );
                int bit1 = Integer.parseInt( bit1Chooser.getValue() );
                int bit2 = Integer.parseInt( bit2Chooser.getValue() );
                int bit3 = Integer.parseInt( bit3Chooser.getValue() );
                
                //double decimal_num = (bit0 * Math.pow(2, 0)) + (bit1 * Math.pow(2, 1)) + (bit2 * Math.pow(2, 2)) + (bit3 * Math.pow(2, 3));
                
                int decimal_num = bit0 * 1 + bit1 * 2 + bit2 * 4 + bit3 * 8;
                
                label4.setText("" + decimal_num);
            }
        );
        
        bit0Chooser.setOnAction( renderHandler );
        bit1Chooser.setOnAction( renderHandler );
        bit2Chooser.setOnAction( renderHandler );
        bit3Chooser.setOnAction( renderHandler );
        
        
        
        
        Button aboutButton = new Button();
        aboutButton.setGraphic( new ImageView( new Image("icons/information.png") ) );
        
        root.setTop(aboutButton);
        
        
        aboutButton.setOnAction(
            (ActionEvent event) ->
            {
                try
                {
                    Alert infoAlert = new Alert( AlertType.INFORMATION );
                 
                    infoAlert.setTitle("Short explanation"); 
                    // set title bar text
                    infoAlert.setHeaderText("To convert from binary to decimal, use the Positional Notation Method:");
                    // set body text
                    infoAlert.setContentText("https://www.cuemath.com/numbers/binary-to-decimal/" + 
                        "\n" + "- 'Multiply each digit starting from the rightmost digit by the powers of 2'." + 
                        "\n" + "- 'The sum of all these values obtained for each digit gives the equivalent value of the given binary number in the decimal system'.");
                    
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