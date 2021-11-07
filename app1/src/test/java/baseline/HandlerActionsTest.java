package baseline;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HandlerActionsTest {

    @Test
    void addItem() {
        Items itemsList = new Items();
        HandlerActions ha = new HandlerActions();
        ha.addItem(itemsList);
        assertEquals(1, itemsList.getItemsList().size());
    }

    @Test
    void testAddExpectedItem() {
        Items itemsList = new Items();
        ListItem item = new ListItem();
        HandlerActions ha = new HandlerActions();
        ha.addExistingItem(itemsList, item);
        assertEquals(item, itemsList.getItem(0));
    }

    @Test
    void removeItem() {
        Items itemsList = new Items();
        ListItem item = new ListItem();
        HandlerActions ha = new HandlerActions();
        ha.addExistingItem(itemsList, item);
        ha.removeItem(itemsList, item);
        assertEquals(0, itemsList.getItemsList().size());
    }

    @Test
    void clear() {
        Items itemsList = new Items();
        ListItem item = new ListItem();
        HandlerActions ha = new HandlerActions();
        ha.addExistingItem(itemsList, item);
        ha.addItem(itemsList);
        ha.clear(itemsList);
        assertEquals(0, itemsList.getItemsList().size());
    }

    @Test
    void toggleCompleteBool() {
        ListItem item = new ListItem();
        HandlerActions ha = new HandlerActions();
        ha.toggleCompleteBool(item);
        assertEquals(true, item.getComplete());
    }

    @Test
    void setDueDate() {
        HandlerActions ha = new HandlerActions();
        ListItem item = new ListItem();
        ha.setDueDate(item, LocalDate.parse("2021-11-19"));
        assertEquals("2021-11-19", item.getDue());
    }

    @Test
    void setTitle() {
        ListItem item = new ListItem();
        HandlerActions ha = new HandlerActions();
        ha.setTitle(item, "expectedTitle");
        assertEquals("expectedTitle", item.getName());
    }

    @Test
    void setDetails() {
        ListItem item = new ListItem();
        HandlerActions ha = new HandlerActions();
        ha.setDetails(item, "item details");
        assertEquals("item details", item.getDescription());
    }

    @Test
    void saveListFile(){
        File file = new File("testdata/makeSaveTest.txt");
        Items itemsList = new Items();
        ListItem item = new ListItem();
        item.setDue("2021-11-19");
        item.setComplete(true);
        item.setDescription("item 1 description");
        item.setName("item 1");
        ListItem item2 = new ListItem();
        item2.setDue("2021-11-21");
        item2.setComplete(false);
        item2.setDescription("item 2 description");
        item2.setName("item 1");
        HandlerActions ha = new HandlerActions();
        ha.addExistingItem(itemsList, item);
        ha.addExistingItem(itemsList, item2);
        Items results = new Items();
        ha.saveListFile(itemsList, file);
        ha.loadListFile(results, file);
        for(int i = 0;i<2;i++){
            assertEquals(itemsList.getItem(i).getDescription(), results.getItem(i).getDescription());
            assertEquals(itemsList.getItem(i).getName(), results.getItem(i).getName());
            assertEquals(itemsList.getItem(i).getComplete(), results.getItem(i).getComplete());
            assertEquals(itemsList.getItem(i).getDue(), results.getItem(i).getDue());
        }
    }

    @Test
    void loadListFile(){
        File file = new File("testdata/test_file_1.txt");
        Items results = new Items();
        HandlerActions ha = new HandlerActions();
        ha.loadListFile(results, file);
        Items expected = new Items();
        ListItem item = new ListItem();
        item.setDue("YYYY-MM-DD");
        item.setComplete(false);
        item.setDescription("item 1 description");
        item.setName("item1");
        ListItem item2 = new ListItem();
        item2.setDue("2021-11-10");
        item2.setComplete(true);
        item2.setDescription("item 2 description");
        item2.setName("item2");
        ha.addExistingItem(expected, item);
        ha.addExistingItem(expected, item2);
        for(int i = 0;i<2;i++){
            assertEquals(expected.getItem(i).getDescription(), results.getItem(i).getDescription());
            assertEquals(expected.getItem(i).getName(), results.getItem(i).getName());
            assertEquals(expected.getItem(i).getComplete(), results.getItem(i).getComplete());
            assertEquals(expected.getItem(i).getDue(), results.getItem(i).getDue());
        }
    }
}