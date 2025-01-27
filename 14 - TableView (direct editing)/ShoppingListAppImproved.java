// Jimmy Lynch Assignment 14
/*
• 10/10 Points: Source code contains program header and comments;
 code organized and readable
• 10/10 Points: application contains menu with about/help and quit menu items
• 20/20 Points: “Add New Item” button functions as described
• 20/20 Points: “Calculate Total Cost” button functions as stated
• 20/20 Points: “Save to file” menu item option writes all table data and total price to text file
• 10/10 Points: design aesthetics (fonts, colors, icons, labels, arrangement, alignment, etc.)
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

// new imports
import javafx.collections.*;
import javafx.scene.control.cell.*;

// another new import
import javafx.scene.control.TableColumn.*;
import javafx.util.converter.*;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;

/**
 *   
 *   JavaFX API: https://openjfx.io/javadoc/11/
 */ 
public class ShoppingListAppImproved extends Application 
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
        mainStage.setTitle("Shopping List App");
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
        Scene mainScene = new Scene(root);
        // attach/display Scene on Stage (window)
        mainStage.setScene( mainScene );

        // custom application code below -------------------

        // TableView class displays data from a "data class" object
        //   arranged in an Excel-like (spreadsheet-like) table
        // each cell is a value - all the little boxes in the table
        TableView<Item> table = new TableView<Item>();

        // table displays a list of data, one item per row.
        // ObservableList is a List with a listener built in;
        //   whenever list changes, the table automatically updates.
        ObservableList<Item> dataList = FXCollections.observableArrayList();

        // link the table to the list, so when list changes, table auto updates
        table.setItems( dataList );
        
        
        // add some data to the list
        //dataList.add( new Item("Apples", 0.75, 6) );
        

        // configure table columns:
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setMinWidth( 120 );
        // Need to specify how the cells in the column will be populated
        nameColumn.setCellValueFactory( 
            new PropertyValueFactory<Item, String>("name") );

        TableColumn priceColumn = new TableColumn("Price");
        priceColumn.setMinWidth( 120 );
        priceColumn.setCellValueFactory(
            new PropertyValueFactory<Item, Double>("price") );
            
        TableColumn quantityColumn = new TableColumn("Quantity");
        quantityColumn.setMinWidth( 120 );
        quantityColumn.setCellValueFactory(
            new PropertyValueFactory<Item, Integer>("quantity") );


        // add columns to table display
        table.getColumns().addAll( nameColumn, priceColumn, quantityColumn );

        // test hiding extra columns
        // table.setMaxWidth(240);

        // make table able to edit the list!
        table.setEditable(true);

        // when user double clicks on table cell, a TextField appears
        // need to do separately for each column
        // Tables use their own special type of text fields
        // Did setCellValueFactory() earlier, now also doing setCellFactory()
        nameColumn.setCellFactory( TextFieldTableCell.forTableColumn() );

        
        // Using an EventHandler
            // setOnEditCommit kind of like a ChangeListener I think
        // to change data in the list as well:
        //  edit commit: is what happens after you press "enter" when changing data
        nameColumn.setOnEditCommit(
            new EventHandler< CellEditEvent<Item,String> >()
            {
                public void handle(CellEditEvent<Item,String> eventData)
                {
                    // Accessing the row with its current values
                    Item data = eventData.getRowValue();

                    // update the data in the list
                    String newName = eventData.getNewValue();
                    data.setName( newName );
                }
            }
        );

        // add code that allows us to update price column;
        //   also need to convert String (from TextField) into a number 

        // add optional parameter: number <-> string converter
        priceColumn.setCellFactory( 
            TextFieldTableCell.forTableColumn( new DoubleStringConverter() ) );

        // also need to change data in the underlying list
        priceColumn.setOnEditCommit(
            new EventHandler< CellEditEvent<Item,Double> >()
            {
                public void handle(CellEditEvent<Item,Double> eventData)
                {
                    Item data = eventData.getRowValue();

                    Double p = eventData.getNewValue();
                    data.setPrice(p);
                }
            }

        );

        // Doing integer instead of double for this one
        // add optional parameter: number <-> string converter
        quantityColumn.setCellFactory( 
            TextFieldTableCell.forTableColumn( new IntegerStringConverter() ) );

        // also need to change data in the underlying list
        quantityColumn.setOnEditCommit(
            new EventHandler< CellEditEvent<Item,Integer> >()
            {
                public void handle(CellEditEvent<Item,Integer> eventData)
                {
                    Item data = eventData.getRowValue();

                    Integer q = eventData.getNewValue();
                    data.setQuantity(q);
                }
            }

        );

        // add it to the scene
        root.setCenter(box);
        box.getChildren().add(table);
        
        
        
        Button newItemButton = new Button("Add new item");
        Button totalCostButton = new Button("Calculate total cost");
        box.getChildren().add(newItemButton);
        box.getChildren().add(totalCostButton);
        
        Label totalCostLabel = new Label("Total cost:");
        box.getChildren().add( totalCostLabel );
        
        newItemButton.setOnAction(
            (ActionEvent event) ->
            {
                 // special alert box containing TextField:
                 TextInputDialog nameAlert = new TextInputDialog();
                 nameAlert.setTitle("New Item");
                 nameAlert.setHeaderText("What item would you like to add?");
                 //textDialog.setContentText("What item would you like to add?");
                 
                 // output variable may or may not contain String data.
                 Optional<String> output = nameAlert.showAndWait();
                 String itemName;
                 if ( output.isPresent() )
                 {
                     // this is the data that was entered
                     itemName = output.get();
                 }
                 else // no data entered
                 {
                     //System.out.println("No data was entered.");
                     return; // this exits the method immediately.
                 }
                 
                 
                 TextInputDialog priceAlert = new TextInputDialog();
                 priceAlert.setTitle("New Item");
                 priceAlert.setHeaderText("How much does it cost?");
                 
                 // output variable may or may not contain String data.
                 Optional<String> output2 = priceAlert.showAndWait();
                 double itemPrice;
                 if ( output2.isPresent() )
                 {
                     // this is the data that was entered
                     itemPrice = Double.parseDouble (output2.get() );
                 }
                 else // no data entered
                 {
                     return;
                 }
                 
                 
                 TextInputDialog quantityAlert = new TextInputDialog();
                 quantityAlert.setTitle("New Item");
                 quantityAlert.setHeaderText("How many?");
                 
                 // output variable may or may not contain String data.
                 Optional<String> output3 = quantityAlert.showAndWait();
                 int itemQuantity;
                 if ( output3.isPresent() )
                 {
                     // this is the data that was entered
                     itemQuantity = Integer.parseInt(output3.get() );
                 }
                 else // no data entered
                 {
                     return;
                 }
                 
                 // String, double, int
                 dataList.add( new Item(itemName, itemPrice, itemQuantity) );
                 
            }
        );
        
        
        totalCostButton.setOnAction(
            (ActionEvent event) ->
            {
                // sum of price * quantity for the list elements
                
                double totalCost = 0;
                
                // Need to access the values in the TableColumn priceColumn
                // Looping through the ObservableList that our TableView displays
                for (int i = 0; i < dataList.size(); i++){
                    Item currentItem = dataList.get(i);
                    totalCost += currentItem.getPrice() * currentItem.getQuantity();
                }
                
                totalCostLabel.setText("Total cost:    " + totalCost);
            }
        );
        
        // application contains menu with about/help and quit menu items
        
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        
        
        Menu fileMenu = new Menu("File");
        menuBar.getMenus().add(fileMenu);
        
        MenuItem saveItem = new MenuItem("Save to file");
        MenuItem quitItem = new MenuItem("Quit");
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(quitItem);
        
        
        Menu otherMenu = new Menu("Other");
        menuBar.getMenus().add(otherMenu);
        
        MenuItem aboutItem = new MenuItem("About");
        otherMenu.getItems().add(aboutItem);
        
        
        saveItem.setGraphic( new ImageView( new Image("icons/disk.png") ) );
        quitItem.setGraphic( new ImageView( new Image("icons/cross.png") ) );

        aboutItem.setGraphic( new ImageView( new Image("icons/information.png") ) );

        
        saveItem.setOnAction(
            (ActionEvent event) -> {
            try {
                
                FileChooser saveChooser = new FileChooser();
                // can force a file extension by using "ExtensionFilter" --
                //   specifies the types of files that can be opened/saved
                ExtensionFilter filter = new ExtensionFilter("Image Files", "*.txt");
                saveChooser.getExtensionFilters().add( filter );

                File textFile = saveChooser.showSaveDialog(mainStage);
                
                
                if(textFile == null)
                {
                    Alert fileAlert = new Alert(AlertType.ERROR);
                    fileAlert.setContentText("No file selected");
                    fileAlert.showAndWait();
                }
                else
                {
                    PrintWriter printFile = new PrintWriter(textFile);
                    
                    String data = "Name  Price  Quantity\n";
                    // Need to loop through our ObservableList again
                    for (int i = 0; i < dataList.size(); i++){
                        Item currentItem = dataList.get(i);
                        // Using the Item toString() method to format
                        data += currentItem.toString() + "\n";
                    }
                    // Wait we also need to find the total cost again
                    double totalCost = 0;
                    for (int i = 0; i < dataList.size(); i++){
                        Item currentItem = dataList.get(i);
                        totalCost += currentItem.getPrice() * currentItem.getQuantity();
                    }
                    
                    data += "Total price: " + totalCost;
                    
                    printFile.println(data);
                    printFile.close();
                }
                
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
        
        aboutItem.setOnAction(
            (ActionEvent event) ->
            {
                try
                {
                    Alert infoAlert = new Alert( AlertType.INFORMATION );
                 
                    infoAlert.setTitle("About"); 
                    // set title bar text
                    infoAlert.setHeaderText("Shopping list App (with Improvements)");
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
        mainStage.sizeToScene();
    }
}