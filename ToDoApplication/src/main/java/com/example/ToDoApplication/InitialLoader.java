package com.example.ToDoApplication;

import com.example.ToDoApplication.Repositories.ListItemRepository;
import com.example.ToDoApplication.Repositories.ListWithItemsRepository;
import com.example.ToDoApplication.Services.ListItemService;
import com.example.ToDoApplication.Services.ListWithItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                newListItem.setTitle("List item " + j + " in List With Items " + j);
                newListItem.setText("Some description of task in List item " + j + " in List With Items " + j);
                listItemService.addListItem(newListItem, listWithItems.getId());
            }
        }
    }
}
