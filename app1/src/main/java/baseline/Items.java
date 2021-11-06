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
    public ListItem getItem(ListItem item){
        return itemsList.get(itemsList.indexOf(item));
    }
}
