/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private ToggleButton markComplete;

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



        // event listener to show the selected item's information on the pane
        itemsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ListItem>() {
            @Override
            public void changed(ObservableValue<? extends ListItem> observable, ListItem oldValue, ListItem newValue) {
                detailsField.setText(itemsListView.getSelectionModel().getSelectedItem().getDescription());
                titleField.setText(itemsListView.getSelectionModel().getSelectedItem().getName());
                dateField.setValue(LocalDate.parse(itemsListView.getSelectionModel().getSelectedItem().getDue()));
                markComplete.setSelected(itemsListView.getSelectionModel().getSelectedItem().getComplete());
            }
        });


    }

    @FXML
    void updateDetails(ActionEvent event){
        // will modify the details of the active item
        ha.setDetails(itemsListView.getSelectionModel().getSelectedItem(), detailsField.getText());
    }

    @FXML
    void setDate(ActionEvent event){
        // will call a function to set the date of the active item
        LocalDate date = dateField.getValue();
        ha.setDueDate(itemsListView.getSelectionModel().getSelectedItem(), date);
        dateField.getEditor().setText(itemsListView.getSelectionModel().getSelectedItem().getDue());
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

    @FXML
    void loadSaveFile(ActionEvent event){
        // clears the itemsList and adds read in items to it
        ha.loadListFile(itemsList);
    }

    @FXML
    void saveCurrentList(ActionEvent event){
        // saves the open list
        ha.saveListFile(itemsList);

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
        detailsField.setText(itemsListView.getSelectionModel().getSelectedItem().getDescription());
    }
}
