package com.example.ToDoApplication.Controllers;

import com.example.ToDoApplication.ListWithItems;
import com.example.ToDoApplication.Services.ListWithItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListWithItemsController {
    @Autowired
    ListWithItemsService listWithItemsService;
    @PostMapping(value = "/lists")
    public void newListWithTasks(@RequestBody ListWithItems listWithItems){listWithItemsService.addListWithItems(listWithItems);}
    @GetMapping(value = "/lists/{id}")
    public ListWithItems findById(@PathVariable("id") Long idToLookFor){return listWithItemsService.getListWithItemsById(idToLookFor);}
    @DeleteMapping(value = "/lists/{id}")
    public void deleteListItemById(@PathVariable Long id){
        listWithItemsService.deleteListItemByID(id);
    }
}
