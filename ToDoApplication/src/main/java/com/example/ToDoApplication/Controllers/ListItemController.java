package com.example.ToDoApplication.Controllers;
import com.example.ToDoApplication.ListItem;
import com.example.ToDoApplication.Services.ListItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListItemController {
    @Autowired
    ListItemService listItemService;
    @PostMapping(value = "/list/{id}")
    public void addNewListItem(@PathVariable("id") long idOfListWithItems, @RequestBody ListItem newListItem) throws EntityNotFoundException {
        listItemService.addListItem(newListItem, idOfListWithItems);
    }
    @GetMapping(value = "/list/{id}")
    public ListItem getListItemsById(@PathVariable("id") Long id) throws EntityNotFoundException{return listItemService.getListItemById(id);}
    @DeleteMapping(value = "/list/{id}")
    public void deleteListItemById(@PathVariable("id") Long id){listItemService.deleteListItemById(id);}
}
