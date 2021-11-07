/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 David Beers
 */

package baseline;

import java.time.LocalDate;

public class HandlerActions {

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




}
