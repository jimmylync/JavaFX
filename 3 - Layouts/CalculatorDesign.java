// Jimmy Lynch - Assignment 3
/*
Grade your homework using the following criteria (partial credit allowed):
• 10/10 Points: Source code contains program header, comments, and grade report;
 code organized and readable
• 60/60 Points Total (3 points each): Created 19 buttons and 1 text field.
• 10/10 Points: Arrangement of UI objects
(aligned; arrangement based on standard calculator layout)
• 10/10 Points: relative button positions do not change when window is resized
• 10/10 Points: Grade report completed fully and accurately
*/

import javafx.application.*;
import javafx.stage.*;
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
 */ 
public class CalculatorDesign extends Application 
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
        mainStage.setTitle("Calculator Design");
        mainStage.setResizable(true);
        
        // layout manager: organize window contents
        GridPane root = new GridPane();
        // do not need to use rootList for GridPane organizer
        // List<Node> rootList = root.getChildren();
        
        // Scene: contains window content
        // parameters: layout manager; width window; height window
        Scene mainScene = new Scene(root, 285, 435);
        //Scene mainScene = new Scene(root, 300, 450);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );
        
        
        // Messing with the font size
        // root.setStyle( "-fx-font-size: 24;" );
        root.setStyle( "-fx-font-size: 28;" );
        
        // to add space between rows and columns
        root.setHgap( 16 ); // between horizontal objects
        root.setVgap( 16 ); // between vertical objects
        
        
        // debug setting that lets you actually see the grid
        // lets you see that rows and columns not being used get like compressed
        // can also see how the Vgap and Hgap's add spaces too
        //root.setGridLinesVisible(true);
        
        
        
        TextField resultsField = new TextField();
        // this TextField is just for displaying stuff
        resultsField.setEditable(false);
        
        // Usually we use Pane as layout for program, so it would be like
        // nameField.setLayoutX() and Y
        // Need to figure out how to format a TextField with GridPane
        
        // Makes it not wide enough to mess with the columns
        // Issue then is that it's not wide enough
        // This method ended up being really useful anyway
        //resultsField.setPrefColumnCount(1);
        
        // Adding this makes the formatting a lot neater on the right
        // The parameter doesn't even need to be 4 for some reason
        // Seems to work from [0, 9]
            // 10 also works, but [0, 9] slightly better
        resultsField.setPrefColumnCount(4);
        // Default value for prefColumnCount appears to be 12 for some 
        // reason
        // System.out.println( resultsField.getPrefColumnCount() );
        
        // Using additional parameters for this method allows us to 
        // change how many columns and rows it spans
        // Allows us to make the TextField wider without messing up the
        // columns
        root.add(resultsField, 0, 0, 4, 1);
        
        
        
        Button button0 = new Button("0");
        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button buttonPlus = new Button("+");
        Button buttonEquals = new Button("=");
        Button buttonDecimalPoint = new Button(".");
        Button buttonClear = new Button("C");
        Button buttonAllClear = new Button("AC");
        // Use Unicode characters for the minus, times, divides, and 
        // delete keys
        // Need to add backslash and "u" in front of unicode numbers to
        // do unicode characters
        Button buttonMinus = new Button("\u2212");
        Button buttonTimes = new Button("\u00D7");
        Button buttonDivides = new Button("\u00F7");
        // 2421, 232B
        Button buttonDelete = new Button("\u2421");
        
        
        // parameters: object, column # (X), row # (Y)
        
        // first row
        root.add(buttonAllClear, 0, 1);
        root.add(buttonClear, 1, 1);
        root.add(buttonDelete, 2, 1);
        root.add(buttonDivides, 3, 1);
        
        // second row
        root.add(button7, 0, 2);
        root.add(button8, 1, 2);
        root.add(button9, 2, 2);
        root.add(buttonTimes, 3, 2);
        
        // third row
        root.add(button4, 0, 3);
        root.add(button5, 1, 3);
        root.add(button6, 2, 3);
        root.add(buttonMinus, 3, 3);
        
        // fourth row
        root.add(button1, 0, 4);
        root.add(button2, 1, 4);
        root.add(button3, 2, 4);
        root.add(buttonPlus, 3, 4);
        
        // fifth row
        // Leaving the bottom left space blank
        // Maybe if later on we needed to add like a key for the negative
        // sign or something we could do that
        root.add(button0, 1, 5);
        root.add(buttonDecimalPoint, 2, 5);
        root.add(buttonEquals, 3, 5);
        
        
        // after adding all content, make the Stage visible
        mainStage.show();
    }
}