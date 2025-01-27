// Jimmy Lynch Assignment 13
/*
• 10 Points: Source code contains program header and comments;
 code organized and readable
• 15 Points: new graphics added (background, player, item)
• 15 Points: score added to game, displays on canvas
• 15 Points: “you win” message appears
• 15 Points: new game functionality
• 10 Points: instructions, about, and quit menu items
• 10 Points: design aesthetics (fonts, colors, icons, labels, arrangement, alignment, etc.)
• 10 Points: Grade report completed fully and accurately
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
public class CollectionGameAssignment extends Application 
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
    
    
    Scene mainScene;
    
    GraphicsContext context;
    
    
    int score;
    
    // Creating our kitten object here, so that it's position doesn't reset
     // each time you select "new game"
    SpriteUpdated kitten = new SpriteUpdated(200,400, 100,100, "kitten_new.png");
    
    public void runGame()
    {
        score = 0;
        
        // Do this here if you want it's position to reset at the start of 
         // each game
        //SpriteUpdated kitten = new SpriteUpdated(200,400, 100,100, "kitten.png");
        
        // Instead of drawing them as soon as we make them, we make them and
         // then put into an ArrayList
        // Later on, we loop through that list and draw each one
        // Creating too many balls makes the game lag
        int numBalls = 20;
        ArrayList<SpriteUpdated> ballList = new ArrayList<SpriteUpdated>();
        for (int i = 0; i < numBalls; i++)
        {
            // Different from x and y variables in Sprite class, even though
             // x and y public in Sprite
            // This is because scope is specific to a class 
            double x = 400 * Math.random() + 100;
            double y = 400 * Math.random() + 100;

            SpriteUpdated ball = new SpriteUpdated(x, y, 75, 75, "ball.png");

            
            // Set direction of movement, plus a minimum speed
            ball.distanceX = Math.random() + 0.5;
            ball.distanceY = Math.random() + 0.5;
            
            // Also want to set a random "sign" (+ or -)
             // so that some upjects move to the left or up
            if(Math.random() > 0.50)
            {
                ball.distanceX *= -1;
            }
            if(Math.random() > 0.50)
            {
                ball.distanceY *= -1;
            }
            
            
            ballList.add( ball );
        }
        
        // Running the actual game
        // animation timer redraws the game 60 times per second
        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long nanoSeconds)
            {   
                // clear the canvas
                context.setFill(Color.WHITE);
                context.fillRect(0,0, 600,600);
                // Using a background image now
                Image background = new Image( "background.jpeg" );
                context.drawImage(background, 0,0);
                
                // draw balls
                // size of ball list might change later
                for (int i = 0; i < ballList.size(); i++)
                {
                    // Why do we have to initialize again
                        // I guess using like a copy?
                    SpriteUpdated ball = ballList.get(i);
                    
                    // Move along a straight line specified by distance
                     // values
                    ball.move(ball.distanceX, ball.distanceY);
                    
                    // Also, "wrap" ball around screen (appear on the
                     // opposite side if the ball goes past screen boundaries
                    ball.wrap();
                    
                    
                    ball.draw( context );
                
                    // Why do we draw it and then check if it overlaps
                    // Why don't we just check if it will overlap and
                     // then only draw it if it won't
                    
                    // when the kitten overlaps ball,
                    //    remove the ball from the ball list
                    if (kitten.overlaps(ball)) {
                        ballList.remove(ball);
                        // For some reason, incrementing the score here makes
                         // the score behave really weirdly
                    }
                }
                
                kitten.wrap();
                // draw kitten
                kitten.draw( context );
                
                
                score = numBalls - ballList.size();
                // Draw the score
                context.setFont( new Font("Arial Bold", 48) );
                context.setFill( Color.RED );
                context.fillText( String.valueOf(score), 15, 50);
                
                
                // check for win:
                if (ballList.size() == 0) {
                    context.setFont( new Font("Arial Bold", 76) );
                    context.setFill( Color.FUCHSIA );
                    context.setLineWidth(4);
                    context.setStroke( Color.FORESTGREEN );
                    context.fillText("You Win!", 125, 300);
                    context.strokeText("You Win!", 125, 300);
                }
                 
            }
        };

        timer.start();
        

        // add a key press event listener to move the kitten
        
        // Change value moved for every key press to a different number to
         // make it move faster or slower
        // Value was originally 5, 100 is better
        mainScene.setOnKeyPressed(
            (event) ->
            {
                String key = event.getCode().toString();
                
                if (key.equals("UP"))
                    kitten.move(0,-100);
                    
                if (key.equals("DOWN"))
                    kitten.move(0,+100);
                    
                if (key.equals("LEFT"))
                    kitten.move(-100,0);
                    
                if (key.equals("RIGHT"))
                    kitten.move(+100,0);
            }
        );
    }

    // Application is an abstract class,
    //  requires the method: public void start(Stage s)
    public void start(Stage mainStage) 
    {
        // set the text that appears in the title bar
        mainStage.setTitle("This cat travels at 10,000 meters per second");
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
        
        // The scene is resizeable but the canvas inside is 600x600
        //Scene mainScene = new Scene(root);
        mainScene = new Scene(root);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );

        // custom application code below -------------------

        
        
        
        Canvas canvas = new Canvas(600,600);
        //GraphicsContext context = canvas.getGraphicsContext2D();
        context = canvas.getGraphicsContext2D();
        

        root.setCenter( canvas );

        
        // Turned all of the code that was here into a seperate method so it
         // can be called multiple times from different parts of the program
        runGame();
        
        
        
        // adding menu stuff
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        
        Menu gameMenu = new Menu("Game");
        menuBar.getMenus().add(gameMenu);
        
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem instructItem = new MenuItem("Instructions");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem quitItem = new MenuItem("Quit");
        
        gameMenu.getItems().add(newGameItem);
        gameMenu.getItems().add(instructItem);
        gameMenu.getItems().add(aboutItem);
        gameMenu.getItems().add(quitItem);
        
        // Literally just copying the whole code over again
        // Would probably make more sense to give this program like a runGame() method
        // -Ok yeah that's what I ended up doing
        newGameItem.setOnAction(
            (ActionEvent event) ->
            {
                runGame();     
            }
        );
        
        instructItem.setOnAction(
            (ActionEvent event) ->
            {
                try
                {
                    Alert infoAlert = new Alert( AlertType.INFORMATION );
                 
                    infoAlert.setTitle("Instructions"); 
                    // set title bar text
                    infoAlert.setHeaderText("Steering the cat into a basketball " +
                        "will collect the basketball.\nThe more basketballs " +
                        "you get, the higher your score will be.\nCollect " +
                        "all the basketballs to win!");
                    // set body text
                    infoAlert.setContentText("");
                    
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
                    // set title bar text
                    infoAlert.setHeaderText("Collection Game (with Improvements)");
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
        
        quitItem.setOnAction(
            (ActionEvent event) ->
            {
                mainStage.close();
            }
        );
        
        
        
        
        
        // custom application code above -------------------

        // after adding all content, make the Stage visible
        mainStage.show();
        mainStage.sizeToScene();
    }
}