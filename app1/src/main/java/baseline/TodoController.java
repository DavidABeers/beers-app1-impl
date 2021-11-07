/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;


public class TodoController{
    HandlerActions ha = new HandlerActions();
    Items itemsList = new Items();
    ToggleGroup filters = new ToggleGroup();

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
    DatePicker dateField;

    @FXML
    private TextArea detailsField;

    @FXML
    private ScrollPane listScroller;

    @FXML
    ListView<ListItem> itemsListView;


    @FXML
    private TextField titleField;

    @FXML
    private RadioButton radioAll;
    @FXML
    private RadioButton radioComplete;
    @FXML
    private RadioButton radioIncomplete;

    public void initialize(){
        // assign the list in Items to itemsListView
        itemsListView.setItems(itemsList.getItemsList());
        // set a character limit for the description
        detailsField.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().length() <= 256 ? change : null));
        // put the radio buttons in a toggle group, I couldn't find a way to do this in scene builder
        radioAll.setToggleGroup(filters);
        radioComplete.setToggleGroup(filters);
        radioIncomplete.setToggleGroup(filters);

        /*itemsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ListItem>() {
            @Override
            public void changed(ObservableValue<? extends ListItem> observable, ListItem oldValue, ListItem newValue) {

            }
        });*/
    }

    @FXML
    void updateDetails(ActionEvent event){
        // will modify the details of the active item
    }

    @FXML
    void setDate(ActionEvent event){
        // will call a function to set the date of the active item
        LocalDate date = dateField.getValue();
        ha.setDueDate(itemsListView.getSelectionModel().getSelectedItem(), date);
    }

    @FXML
    void saveData(ActionEvent event){
        // will set all data in the item to the current data on the panel
        ha.setDetails(itemsListView.getSelectionModel().getSelectedItem(), detailsField.getText());
    }

    @FXML
    void updateTitle(ActionEvent event){
        // sets the new title
        ha.setTitle(itemsListView.getSelectionModel().getSelectedItem(), titleField.getText());
    }

    @FXML
    void toggleComplete(ActionEvent event){
        // changes the complete field of the active item to the opposite of its current state.
        ha.toggleCompleteBool(itemsListView.getSelectionModel().getSelectedItem());
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
        ha.clear(itemsList);
    }

    @FXML
    void makeNewItem(ActionEvent event){
        // creates a new item in a to-do list
        ha.addItem(itemsList);
    }

    @FXML
    void deleteListItem(ActionEvent event){
        // removes selected item from the to-do list
        ha.removeItem(itemsList, itemsListView.getSelectionModel().getSelectedItem());
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
