// Jimmy Lynch Assignment 4
/*
Grade your homework using the following criteria (partial credit allowed):
• 12/12 Points: Source code contains program header and comments;
    code organized and readable
• 18/18 Points Each (2 points each, 9 total): Created 4 labels, 4 text fields, and 1 button
• 20/20 Points: Label/TextField/Button objects aligned as described, contain relevant text
• 20/20 points (5 points each, 4 total): Alert box appears on button click if any fields are blank,
    and describe the missing data
    (e.g. if the text field for location is empty, display “Please enter a location for this event.”)
• 10/10 Points: FileChooser appears on button click only when all text fields have data
• 10/10 Points: Data written to text file in format shown above
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



public class EventReminderCreator extends Application
{
    public static void main (String args[])
    {
    
        try
        {
            launch(args);
        }
        catch(Exception error)
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
        mainStage.setTitle("Event Reminder Creator");
        mainStage.setResizable(false);
        
        Pane root = new Pane();
        List<Node> rootList = root.getChildren();
        
        root.setStyle( "-fx-font-size: 16;" );
        
        
        Scene mainScene = new Scene(root, 400, 400);
        mainStage.setScene(mainScene);
        
        
        
        // Labels and corresponding text fields:
        // Probably would've been easier to format this if I used VBox and HBoxes
        Label eventNameLabel = new Label("Event Name:");
        eventNameLabel.setLayoutX(25);
        eventNameLabel.setLayoutY(25);
        rootList.add(eventNameLabel);
        TextField eventNameField = new TextField();
        eventNameField.setLayoutX(150);
        eventNameField.setLayoutY(25);
        rootList.add(eventNameField);
        
        
        Label dateTimeLabel = new Label("Date and Time:");
        dateTimeLabel.setLayoutX(25);
        dateTimeLabel.setLayoutY(75);
        rootList.add(dateTimeLabel);
        TextField dateTimeField = new TextField();
        dateTimeField.setLayoutX(150);
        dateTimeField.setLayoutY(75);
        rootList.add(dateTimeField);
        
        
        Label locationLabel = new Label("Location:");
        locationLabel.setLayoutX(25);
        locationLabel.setLayoutY(125);
        rootList.add(locationLabel);
        TextField locationField = new TextField();
        locationField.setLayoutX(150);
        locationField.setLayoutY(125);
        rootList.add(locationField);
        
        
        Label descriptionLabel = new Label("Description");
        descriptionLabel.setLayoutX(25);
        descriptionLabel.setLayoutY(175);
        rootList.add(descriptionLabel);
        TextField descriptionField = new TextField();
        descriptionField.setLayoutX(150);
        descriptionField.setLayoutY(175);
        rootList.add(descriptionField);
        
        
        
        Button createReminderButton = new Button("Create Reminder");
        createReminderButton.setLayoutX(100);
        createReminderButton.setLayoutY(250);
        rootList.add(createReminderButton);
        
        createReminderButton.setOnAction(
        (ActionEvent event) -> {
            
            // if eventNameField doesn't have text
                // do alert
            // else if dateTimeField doesn't have text
                // do alert
            // else if locationField doesn't have text
                // do alert
            // else if descriptionField doesn't have text
                // do alert
            // else
                // choose and write to a file
            
            // There's probably a better way of checking if a TextField is 
            // empty but idk what
            if ( eventNameField.getText().equals("") ){
                Alert alert1 = new Alert(AlertType.ERROR);
                alert1.setContentText("Please enter a name for this event.");
                alert1.showAndWait();
            }
            else if ( dateTimeField.getText().equals("") ){
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setContentText("Please enter a date and time for this event.");
                alert2.showAndWait();
            }
            else if ( locationField.getText().equals("") ){
                Alert alert3 = new Alert(AlertType.ERROR);
                alert3.setContentText("Please enter a location for this event.");
                alert3.showAndWait();
            }
            else if ( descriptionField.getText().equals("") ){
                Alert alert4 = new Alert(AlertType.ERROR);
                alert4.setContentText("Please enter a description for this event.");
                alert4.showAndWait();
            }
            // Only if all of the TextFields contain text:
            else {
                try
                {
                    
                    FileChooser fileChooser = new FileChooser();
                    
                    // note: showSaveDialog creates a brand new file
                    // note: showOpenDialog opens a pre-existing file
                    File textFile = fileChooser.showSaveDialog(mainStage);
                    
                    // Idk if this part actually does anything-using parts 
                    // of code written during class
                    if(textFile == null)
                    {
                        Alert fileAlert = new Alert(AlertType.ERROR);
                        fileAlert.setContentText("No file selected");
                        fileAlert.showAndWait();
                    }
                    // Writes to the file
                    else
                    {
                        PrintWriter printFile = new PrintWriter(textFile);
                        
                        String eventName = eventNameField.getText();
                        String dateTime = dateTimeField.getText();
                        String location = locationField.getText();
                        String description = descriptionField.getText();
                        
                        printFile.println("Event Name: " + eventName);
                        printFile.println("Date and Time: " + dateTime);
                        printFile.println("Location: " + location);
                        printFile.println("Description: " + description);
                        
                        printFile.close();
                    }
                }
                catch(Exception error)
                {
                    error.printStackTrace();
                    System.exit(0);
                }
            }
        }
        );
        
        
        mainStage.show();
        
    }
    
}