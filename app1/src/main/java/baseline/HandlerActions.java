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

    public void addItem(Items itemsList){
            ListItem item = new ListItem();
            itemsList.addItem(item);
    }

    public void addExistingItem(Items itemsList, ListItem item){
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

    private void writeToSave(File file, Items itemsList){
        // helper function for saveListFile, makes and writes the save file
        try{
            if(file.createNewFile()){
                // I did close the resource
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

    public void saveListFile(Items itemsList, File saveFile){

        writeToSave(saveFile, itemsList);
    }

    private void readSaveFile(File file, Items itemsList){
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
    public void loadListFile(Items itemsList, File save){

        // replace itemsList with the loaded list
        readSaveFile(save, itemsList);
    }

}
