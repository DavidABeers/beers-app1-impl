/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 David Beers
 */

package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Items {
    private ObservableList<ListItem> itemsList = FXCollections.observableArrayList();

    public void addItem(ListItem item){
        itemsList.add(item);
    }
    public void removeItem(ListItem item){
        itemsList.remove(item);
    }
    public void clearList(){
        itemsList.clear();
    }
    public ListItem getItem(int index){
        return itemsList.get(index);
    }
    public ObservableList<ListItem> getItemsList(){
        return this.itemsList;
    }
}
