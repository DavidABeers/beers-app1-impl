/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 David Beers
 */
package baseline;

import javafx.event.ActionEvent;

public class TodoController {
    public void makeSaveFile(String filename){
        // open a file writer
        // for each list selected
        // write list name
        // for each item
        // write item, description, and due date
    }

    private void openSaveFile(String filename){
        // open a file object
        // open a file scanner
        // while save has a next line
        // put list to list ListView
        // while next line is not another list
        // put items to items listView
    }

    private void saveClicked(ActionEvent event){
        // saves the currently selected to do lists using makeSaveFile
    }

    private void openClicked(ActionEvent event){
        // allow the user to open a previously saved to do list
    }

    private void newListClicked(ActionEvent event){
        // create a new list item in the list view
    }
    private void editListClicked(ActionEvent event){
        // will allow the name of a selected list to be changed
    }
    private void deleteListClicked(ActionEvent event){
        // deletes selected list
    }
    private void newItemClicked(ActionEvent event){
        // creates a new item in a to-do list
    }
    private void deleteItemClicked(ActionEvent event){
        // removes selected item from the to-do list
    }
    private void completeCheckboxClicked(ActionEvent event){
        // set the complete field for the item to true
    }
    private void radioButtonClicked(ActionEvent event){
        // toggle a filter by the field tied to the clicked radio button
        // deselect any previously selected radiobuttons
    }
}
