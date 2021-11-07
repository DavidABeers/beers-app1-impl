/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 David Beers
 */

package baseline;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class HandlerActions {
    private Stage primaryStage;

    public void setStage(Stage stage){
        primaryStage = stage;
    }

    public void addItem(Items itemsList){
            ListItem item = new ListItem();
            itemsList.addItem(item);
    }

    public void removeItem(Items itemsList, ListItem item){
        itemsList.removeItem(item);
    }

    public void clear(Items itemsList){
        itemsList.clearList();
    }

    public void toggleCompleteBool(ListItem item){
        item.setComplete(!item.getComplete());
    }

    public void setDueDate(ListItem item, LocalDate date){
        item.setDue(date.toString());
    }

    public void setTitle(ListItem item, String title){
        item.setName(title);
    }

    public void setDetails(ListItem item, String details){
        item.setDescription(details);
    }

    public void writeToSave(File file, Items itemsList){
        // helper function for saveListFile, makes and writes the save file
        try{
            if(file.createNewFile()){
                FileWriter writer = new FileWriter(file);
                for(ListItem item: itemsList.getItemsList()){
                    writer.write(item.getName() +"\n");
                    writer.write(item.getDue() +"\n");
                    writer.write(item.getDescription() +"\n");
                    writer.write(item.getComplete().toString() +"\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveListFile(Items itemsList){
        // opens a file chooser
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save Todo List");
        // make a file object with the file chooser
        File saveFile = fileSaver.showSaveDialog(primaryStage);
        writeToSave(saveFile, itemsList);
    }

    public void readSaveFile(File file, Items itemsList){
        // helper for loadListFile
        try {
            Scanner saveReader = new Scanner(file);
            // clears the current open list
            itemsList.clearList();
            while(saveReader.hasNextLine()){
                // read and store all the data for an item
                ListItem item = new ListItem();
                item.setName(saveReader.nextLine());
                item.setDue(saveReader.nextLine());
                item.setDescription(saveReader.nextLine());
                if(saveReader.nextLine().equals("true")){
                    item.setComplete(true);
                }
                // put each item into the now clean list
                itemsList.addItem(item);
            }
            saveReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void loadListFile(Items itemsList){
        FileChooser openFile = new FileChooser();
        openFile.setTitle("Load Save File");
        File save = openFile.showOpenDialog(primaryStage);
        readSaveFile(save, itemsList);
    }

}
