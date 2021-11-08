/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.time.LocalDate;

public class TodoController{
    HandlerActions ha = new HandlerActions();
    Items itemsList = new Items();
    Items completeItemsList = new Items();
    Items incompleteItemsList = new Items();
    ToggleGroup filters = new ToggleGroup();
    private Stage primaryStage;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextArea detailsField;

    @FXML
    ListView<ListItem> itemsListView;

    @FXML
    private Label isComplete;

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

    public void setStage(Stage stage){
        // get the stage because it's needed for file choosers
        primaryStage = stage;
    }

    public TodoController(){
        // I read something that said I needed a public parameter-less constructor, which should be default but just in case
    }

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
        itemsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detailsField.setText(itemsListView.getSelectionModel().getSelectedItem().getDescription());
            titleField.setText(itemsListView.getSelectionModel().getSelectedItem().getName());
            dateField.setValue(LocalDate.parse(itemsListView.getSelectionModel().getSelectedItem().getDue()));
            markComplete.setSelected(itemsListView.getSelectionModel().getSelectedItem().getComplete());
        });

        // I'm not sure how to make this one a lambda
        itemsListView.setCellFactory(new Callback<>() {

            @Override
            public ListCell<ListItem> call(ListView<ListItem> p) {
                // I need this to override the displayed cell names, so SonarLint will just have to chill
                ListCell<ListItem> cell = new ListCell<>() {

                    @Override
                    protected void updateItem(ListItem item, boolean empty) {
                        super.updateItem(item, empty);
                        setText((empty || item == null) ? null : item.getName());
                    }

            };
                return cell;
            }
        });

    }


    @FXML
    private void updateDetails(KeyEvent event){
        // will modify the details of the active item
        ha.setDetails(itemsListView.getSelectionModel().getSelectedItem(), detailsField.getText());
    }

    @FXML
    private void setDate(ActionEvent event){
        // will call a function to set the date of the active item
        LocalDate date = dateField.getValue();
        ha.setDueDate(itemsListView.getSelectionModel().getSelectedItem(), date);
        dateField.getEditor().setText(itemsListView.getSelectionModel().getSelectedItem().getDue());
    }

    @FXML
    private void saveData(ActionEvent event){
        // will set all data in the item to the current data on the panel
        ha.setDetails(itemsListView.getSelectionModel().getSelectedItem(), detailsField.getText());
        ha.setTitle(itemsListView.getSelectionModel().getSelectedItem(), titleField.getText());
        itemsListView.refresh();
    }

    @FXML
    private void updateTitle(ActionEvent event){
        // sets the new title
        ha.setTitle(itemsListView.getSelectionModel().getSelectedItem(), titleField.getText());
    }

    @FXML
    private void toggleComplete(ActionEvent event){
        // changes the complete field of the active item to the opposite of its current state.
        ha.toggleCompleteBool(itemsListView.getSelectionModel().getSelectedItem());
        // moves the item onto its new filtered list
        if(!itemsListView.getSelectionModel().getSelectedItem().getComplete()){
            isComplete.setText("incomplete");
            ha.addExistingItem(incompleteItemsList, itemsListView.getSelectionModel().getSelectedItem());
            ha.removeItem(completeItemsList, itemsListView.getSelectionModel().getSelectedItem());
        }
        else {
            isComplete.setText("complete");
            ha.addExistingItem(completeItemsList, itemsListView.getSelectionModel().getSelectedItem());
            ha.removeItem(incompleteItemsList, itemsListView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void loadSaveFile(ActionEvent event){
        // make a file chooser
        FileChooser openFile = new FileChooser();
        openFile.setTitle("Load Save File");
        // only loads text files
        FileChooser.ExtensionFilter textFiles = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt");
        openFile.getExtensionFilters().add(textFiles);
        File save = openFile.showOpenDialog(primaryStage);
        // clears the itemsList and adds read in items to it
        ha.loadListFile(itemsList, save);
    }

    @FXML
    private void saveCurrentList(ActionEvent event){
        // opens a file chooser
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save Todo List");
        // only saves text files
        FileChooser.ExtensionFilter textFiles = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt");
        fileSaver.getExtensionFilters().add(textFiles);
        // make a file object with the file chooser
        File saveFile = fileSaver.showSaveDialog(primaryStage);
        // saves the open list
        ha.saveListFile(itemsList, saveFile);

    }

    @FXML
    private void eraseList(ActionEvent event){
        // deletes selected list
        ha.clear(itemsList);
    }

    @FXML
    private void makeNewItem(ActionEvent event){
        // creates a new item in a to-do list
        ListItem item = new ListItem();
        ha.addExistingItem(itemsList, item);
        ha.addExistingItem(incompleteItemsList, item);
    }

    @FXML
    private void deleteListItem(ActionEvent event){
        // removes selected item from the to-do list
        ha.removeItem(itemsList, itemsListView.getSelectionModel().getSelectedItem());
        if(!itemsListView.getSelectionModel().getSelectedItem().getComplete()){ // If I unbox this it still yells at me, and I need to be able to toString it elsewhere.
            ha.removeItem(incompleteItemsList, itemsListView.getSelectionModel().getSelectedItem());
        }
        else{
            ha.removeItem(completeItemsList, itemsListView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void showAll(ActionEvent event){
        // disable filter
        itemsListView.setItems(itemsList.getItemsList());
    }
    @FXML
    private void showComplete(ActionEvent event){
        // only show items marked complete
        itemsListView.setItems(completeItemsList.getItemsList());
    }
    @FXML
    private void showIncomplete(ActionEvent event){
        // only show items no marked complete
        itemsListView.setItems(incompleteItemsList.getItemsList());
    }

    @FXML
    private void scroll(ScrollEvent event){
        // will be filled later maybe if I actually need it
    }

    @FXML
    private void getItemDetails(MouseEvent event){
        // will grab detail to display on the pane
        detailsField.setText(itemsListView.getSelectionModel().getSelectedItem().getDescription());
    }
}
