// Jimmy Lynch - Assignment 2
/*
Grade your homework using the following criteria (partial credit allowed):
• 10/10 Points: Source code contains program header and comments; code organized and
readable
• 40/40 Points (10 each): Created two text fields (numerator, denominator), a button,
and a label
• 10/10 Points: Arrangement of UI objects (aligned)
• 10/10 Points: correct text displays in label (e.g. 3/4 = 0.75, 1/3 = 0.333333333333,
100/25 = 4.0)
• 10/10 Points: if a non-number is entered in either text field, a try-catch block handles
the exception and displays an appropriate message in that text field (e.g. “must enter
an integer”)
• 10/10 Points: if zero is entered in the denominator text field, an appropriate error
message displays in the text field (e.g. “can not divide by zero”)
• 10/10 Points: Grade report completed fully and accurately
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.util.List;


import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;


/**
 *   
 */ 
public class FracToDecimConverter extends Application 
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
        mainStage.setTitle("Fraction to Decimal Converter");
        mainStage.setResizable(false);
        
        // layout manager: organize window contents
        // We usually name it rootList
        Pane root = new Pane();
        List<Node> rootList = root.getChildren();
        
        // Scene: contains window content
        // parameters: layout manager; width window; height window
        Scene mainScene = new Scene(root, 500, 500);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );
        
        
        
        // Label for result to be displayed on the screen
        Label outputLabel = new Label("Result displayed here");
        outputLabel.setLayoutX(50);
        outputLabel.setLayoutY(200);
        rootList.add(outputLabel);
        
        
        // TextFields for user to input numerator and denominator
        TextField numeratorField = new TextField("Enter the numerator");
        numeratorField.setLayoutX(50);
        numeratorField.setLayoutY(50);
        rootList.add(numeratorField);
                
        TextField denominatorField = new TextField("Enter the denominator");
        denominatorField.setLayoutX(50);
        denominatorField.setLayoutY(100);
        rootList.add(denominatorField);
        
        
        // Create a button on screen that displays 'Convert fraction to decimal'
        Button convertButton = new Button("Convert fraction to decimal");
        convertButton.setLayoutX(250);
        convertButton.setLayoutY(75);
        rootList.add(convertButton);
        
        
        convertButton.setOnAction( (ActionEvent event) -> 
            {    
                // The assignment was updated where now we need to accept 
                // all numbers, not just integers
                
                // Reading user inputs
                // TextField method getText() returns a String-still need to 
                // convert to an actual numerical data type
                String numeratorString = numeratorField.getText();
                String denominatorString = denominatorField.getText();
                
                
                // Converting user inputs to numbers:
                /* 
                Accounting for when user inputs can't be converted to numbers
                -Using try and catch blocks to handle exceptions and prompt 
                the user to enter different inputs
                -In addition to this, need to make sure that the program 
                doesn't take invalid inputs entered by the user and try to
                display them "converted" into decimals
                    -otherwise, displays nonsense results when invalid inputs
                    are given
                Also accounting for zero being entered as a denominator
                */
                boolean isValid = true;
                /* 
                Program won't let me compile unless I declare and 
                initialize the numerator and denominator variables outside of
                the try blocks
                Because of this, I'm declaring and initializing them using
                dummy values
                -Another workaround would be to declare but not initialize 
                 numerator and denominator outside of the try and catch blocks
                 and then to initialize them to dummy values in the catch blocks
                */
                // int numerator = 54021;
                double numerator = 1;
                try
                {
                    // Converting strings to doubles
                    // numerator = Integer.parseInt( numeratorString );
                    numerator = Double.parseDouble( numeratorString );
                }
                catch (NumberFormatException error)
                {
                    // Can use setText() for a textField, just like you can 
                    // with Labels
                    numeratorField.setText("Must enter a number");
                    isValid = false;
                }
                
                // int denominator = 1206;
                double denominator = 1;
                try
                {
                    // denominator = Int.parseInt( denominatorString );
                    denominator = Double.parseDouble( denominatorString );
                }
                catch (NumberFormatException error)
                {
                    denominatorField.setText("Must enter a number");
                    isValid = false;
                }
                // Making sure 0 isn't inputted as a denominator
                /* 
                Can't use try and catch blocks to do this because in Java, 
                division by zero doesn't actually throw an exception 
                unless it's with integer division, and we're already going 
                out of our way to avoid doing integer division
                */
                if ( denominator == 0 ) {
                    denominatorField.setText("Can not divide by zero");
                    isValid = false;
                }
                
                
                // Checking to make sure user input valid
                // When the user gives invalid inputs, don't want these inputs
                // to be passed to our result and displayed - nonsense results
                if ( isValid == true )
                {
                    // Casting to double to make sure we're doing actual 
                    // division, not rounded integer division
                    double decimal = ((double) numerator) / denominator;
                    outputLabel.setText (numerator + "/" + denominator + " = " + decimal);
                }
                // When an input is invalid, need to "clear" displayed result
                // so that any previous result won't still be displayed as a
                // "result" to a different, invalid, set of inputs
                else {
                    outputLabel.setText("Result displayed here");
                }
            }
        );
        
        
        // after adding all content, make the Stage visible
        mainStage.show();
    }
}