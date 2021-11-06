/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class TodoController{
    Items itemsList = new Items();

    public TodoController(){

    }

    @FXML
    private Button saveChanges;

    @FXML
    private MenuItem loadList;

    @FXML
    private MenuItem saveList;

    @FXML
    private MenuItem newListItem;

    @FXML
    private MenuItem removeListItem;

    @FXML
    private MenuItem clearList;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextArea detailsField;

    @FXML
    private ScrollPane listScroller;

    @FXML
    private ListView<ListItem> itemsListView;

    @FXML
    private TextField titleField;

    @FXML
    private RadioButton radioAll;
    @FXML
    private RadioButton radioComplete;
    @FXML
    private RadioButton radioIncomplete;

    @FXML
    void updateDetails(ActionEvent event){
        // will modify the details of the active item
    }

    @FXML
    void setDate(ActionEvent event){
        // will call a function to set the date of the active item
    }

    @FXML
    void saveData(ActionEvent event){
        // will set all data in the item to the current data on the panel
    }

    @FXML
    void updateTitle(ActionEvent event){
        // sets the new title
    }

    @FXML
    void toggleComplete(ActionEvent event){
        // changes the complete field of the active item to the opposite of its current state.
    }

    public void makeSaveFile(String filename){
        // open a file writer
        // for each list selected
        // write list name
        // for each item
        // write item, description, and due date
    }

    @FXML
    void loadSaveFile(ActionEvent event){
        // open a file object
        // open a file scanner
        // while save has a next line
        // put list to list ListView
        // while next line is not another list
        // put items to items listView
    }

    @FXML
    void saveCurrentList(ActionEvent event){
        // saves the currently selected to do lists using makeSaveFile
    }

    @FXML
    void eraseList(ActionEvent event){
        // deletes selected list
    }

    @FXML
    void makeNewItem(ActionEvent event){
        // creates a new item in a to-do list
        newListItem.setOnAction(event1 -> {
            ListItem item = new ListItem();
            itemsList.addItem(item);
        });
    }

    @FXML
    void deleteListItem(ActionEvent event){
        // removes selected item from the to-do list
    }
    private void completeCheckboxClicked(ActionEvent event){
        // set the complete field for the item to true
    }
    @FXML
    void showAll(ActionEvent event){
        // disable filter
    }
    @FXML
    void showComplete(ActionEvent event){
        // only show items marked complete
    }
    @FXML
    void showIncomplete(ActionEvent event){
        // only show items no marked complete
    }

    @FXML
    private void scroll(ActionEvent event){
        // will be filled later maybe if I actually need it
    }

    @FXML
    void getItemDetails(ActionEvent event){
        // will grab detail to display on the pane
    }
}
