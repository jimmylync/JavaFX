// Jimmy Lynch - Assignment 5 
/*
• 20/20 Points: Source code contains program header and comments;
 code organized and readable.
• 36/36 Points (2 points each, 18 total):
 Correctly added MenuBar, Menus, and MenuItems, with correct text/nesting.
• 10/10 Points: Label appears in center of window.
• 24/24 Points (2 points each, 12 total): Clicking on a MenuItem displays corresponding text
 in the label in the center of the window.
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


public class LibraryMenu extends Application
{
    public static void main(String[] args)
    {
        try{
            launch(args);
        }
        catch(Exception error){
            error.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }
    
    
    public void start(Stage mainStage)
    {
        mainStage.setTitle("Menu Examples");
        mainStage.setResizable(false);
        
        // layout manager
        BorderPane root = new BorderPane();
        
        root.setStyle ("-fx-font-size: 24;");
        
        // scene contains the window content
        Scene mainScene = new Scene(root, 600, 600);
        mainStage.setScene(mainScene);
        
        
        // custom application code below -------------------
        
        // MenuBar contains Menu objects
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        
        
        // First layer - add directly to menuBar
        
        // For MenuBars, use getMenus()
        Menu menuSearch = new Menu("Search");
        menuBar.getMenus().add( menuSearch );
        
        Menu menuLibraryInfo = new Menu("Library Information");
        menuBar.getMenus().add( menuLibraryInfo );
        
        
        // Second layer - add to things in first layer
        
        // For Menus, use getItems()
        // Adding to Search
        Menu subMenuBooks = new Menu("Books");
        menuSearch.getItems().add( subMenuBooks );
        Menu subMenuMovies = new Menu("Movies");
        menuSearch.getItems().add( subMenuMovies );
        Menu subMenuMusic = new Menu("Music");
        menuSearch.getItems().add( subMenuMusic );
        
        // Adding to Library Information
        MenuItem itemHoursLocation = new MenuItem("Hours and Location");
        menuLibraryInfo.getItems().add( itemHoursLocation );
        MenuItem itemEmployment = new MenuItem("Employment Opportunities");
        menuLibraryInfo.getItems().add( itemEmployment );
        
        
        // Third layer - add to things in second layer
        
        // Adding to Books
        MenuItem itemBookTitle = new MenuItem("Title");
        subMenuBooks.getItems().add( itemBookTitle );
        MenuItem itemAuthor = new MenuItem("Author");
        subMenuBooks.getItems().add( itemAuthor );
        MenuItem itemSubject = new MenuItem("Subject");
        subMenuBooks.getItems().add( itemSubject );
        MenuItem itemCallNumber = new MenuItem("Call Number");
        subMenuBooks.getItems().add( itemCallNumber );
        
        // Adding to Movies
        MenuItem itemMovieTitle = new MenuItem("Title");
        subMenuMovies.getItems().add( itemMovieTitle );
        MenuItem itemActor = new MenuItem("Actor");
        subMenuMovies.getItems().add( itemActor );
        MenuItem itemDirector = new MenuItem("Director");
        subMenuMovies.getItems().add( itemDirector);
        
        // Adding to Music
        MenuItem itemArtistBand = new MenuItem("Artist/Band");
        subMenuMusic.getItems().add( itemArtistBand );
        MenuItem itemAlbum = new MenuItem("Album");
        subMenuMusic.getItems().add( itemAlbum );
        MenuItem itemSong = new MenuItem("Song");
        subMenuMusic.getItems().add( itemSong );
        
         
        // Item selection label
        // This is what we're going to update based on what is selected
        Label selectLabel = new Label("Please select an item.");
        root.setCenter(selectLabel);
        
        
        // Updating label based on which menu item selected
        // Do I not need to use try-catch blocks for this?
            // -I think we only use try catch blocks inside setOnAction() 
            // when messing with file stuff
        itemHoursLocation.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Hours and Location")
        );
        itemEmployment.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Employment Opportunities")
        );
        itemBookTitle.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Title")
        );
        itemAuthor.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Author")
        );
        itemSubject.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Subject")
        );
        itemCallNumber.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Call Number")
        );
        itemMovieTitle.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Title")
        );
        itemActor.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Actor")
        );
        itemDirector.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Director")
        );
        itemArtistBand.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Artist/Band")
        );
        itemAlbum.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Album")
        );
        itemSong.setOnAction( (ActionEvent event) ->
            selectLabel.setText ("You selected Song")
        );
        
        
        // custom application code above -------------------
        
        mainStage.show();
    }
    
    
}

