package com.example.ToDoApplication;
import com.example.ToDoApplication.Services.ListItemService;
import com.example.ToDoApplication.Services.ListWithItemsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class InitialLoader implements CommandLineRunner {
    private final ListItemService listItemService;
    private final ListWithItemsService listWithItemsService;
    public InitialLoader(ListItemService listItemService, ListWithItemsService listWithItemsService) {
        this.listItemService = listItemService;
        this.listWithItemsService = listWithItemsService;
    }
    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 5; i++) {
            ListWithItems listWithItems = listWithItemsService.addListWithItemsAndFlush(new ListWithItems("List with items " + i));
            for (int j = 1; j <= 5; j++) {
                ListItem newListItem = new ListItem();
                newListItem.setTitle("List item " + j + " in List With Items " + i);
                newListItem.setText("Some description of task in List item " + j + " in List With Items " + i + ".");
                listItemService.addListItem(newListItem, listWithItems.getId());
            }
        }
    }
}