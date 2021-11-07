package baseline;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ItemCellFactory implements Callback<ListView<ListItem>, ListCell<ListItem>> {
    @Override
    public ListCell<ListItem> call(ListView<ListItem> param) {
        return new ListCell<>(){
            @Override
            public void updateItem(ListItem item, boolean empty) {
                super.updateItem(item, empty);
                setText(item.getName());
            }
        };
    }
}
