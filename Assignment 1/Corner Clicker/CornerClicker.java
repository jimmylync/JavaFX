// Jimmy Lynch - Assignment 1
/*
 Grade your homework using the following criteria (partial credit allowed):
• 10/10 Points: Source code contains program header and comments; code organized and
readable; does not include extraneous code, import statements, etc.
• 10/10 Points: created Label in center of screen
• 10/10 Points: text appears in Label when mouse is clicked
• 60/60 Points (4 cases, worth 15 points each): correct text appears in Label when
clicking in each corner
• 10/10 Points: Grade report completed fully and accurately
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


import java.util.List;



public class CornerClicker extends Application
{
    //run the application
    public static void main(String[] args)
    {
        try
        {
            //create the stage, calls the start method
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
        //set the text that appears in the title bar
        mainStage.setTitle("Input Event Handers");
        mainStage.setResizable(false);
        
        //layout manager: organize window contents
        Pane root = new Pane();
        List<Node> rootList = root.getChildren();
        
        //scene: contain window elements
        //parameters: layout manager, width of window,
        //height of window
        Scene mainScene = new Scene(root, 300, 300);
        //attach/display scene on stage
        mainStage.setScene(mainScene);
        
        //create objects to be displayed
        Label messageLabel = new Label("Click on a quadrant");
        messageLabel.setLayoutX(85);
        messageLabel.setLayoutY(130);
        rootList.add(messageLabel);
        
       
        //run code when a mouse button is pressed
        //activate anywhere on the scene
        mainScene.setOnMousePressed(
            new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent event)
                {
                    // range of pixels in window is [0.0, 300) for both x and y
                    // [0,149] is left/up, [150, 300) is right/down 
                    
                    // longer text-push further to the left to keep in center
                    messageLabel.setLayoutX(45);
                    
                    // left side
                    if ( event.getX() <= 149 ) 
                    {
                        if ( event.getY() <= 149 )
                        {
                            // top left
                            messageLabel.setText("You clicked on the top-left quadrant");
                        }
                        else
                        {
                            // bottom left 
                            messageLabel.setText("You clicked on the bottom-left quadrant");
                        }
                    }
                    // right side
                    else if ( event.getY() <= 149 ) 
                    {
                        // top right
                        messageLabel.setText("You clicked on the top-right quadrant");
                    }
                    else
                    {
                        // bottom right
                        messageLabel.setText("You clicked on the bottom-right quadrant");
                    }
                    
                }
            }
        );
        
        //after adding all content, make the stage visible
        mainStage.show();
    }
        
}
