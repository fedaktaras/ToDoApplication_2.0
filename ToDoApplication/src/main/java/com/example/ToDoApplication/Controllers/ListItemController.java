package com.example.ToDoApplication.Controllers;
import com.example.ToDoApplication.ListItem;
import com.example.ToDoApplication.Services.ListItemService;
import com.example.ToDoApplication.Services.ListWithItemsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
public class ListItemController {
    @Autowired
    ListItemService listItemService;
    ListWithItemsService listWithItemsService;
    @GetMapping(value = "/list/{id}")
    public ResponseEntity<ListItem> getListItemsById(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.ok(listItemService.getListItemById(id));
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping(value = "/list/{id}")
    public ResponseEntity<ListItem> addNewListItem(@PathVariable("id") long idOfListWithItems, @RequestBody ListItem newListItem) {
        try {
            newListItem = listItemService.addListItem(newListItem, idOfListWithItems);
            return ResponseEntity.ok(newListItem);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping("/list/{ItemId}")
    public ResponseEntity<Void> updateListWithTasksDeleteItem(@Valid @PathVariable("ItemId")Long ItemId){
        try {
            listItemService.deleteListItemById(ItemId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (NullPointerException e) {return ResponseEntity.badRequest().build();}
        catch (NoSuchElementException e) {return ResponseEntity.notFound().build();}
        catch (Exception e){return ResponseEntity.internalServerError().build();}
    }
    @PutMapping("/list/{ItemId}/{ItemIdToShift}/{ListWithItemsId}")
    public ResponseEntity<Void> updateOrderOfListItems(@Valid @PathVariable Long ItemId, @PathVariable Long ItemIdToShift, @PathVariable Long ListWithItemsId){
        try{
            listItemService.placeBeforeListItemId(ItemIdToShift, ItemId, ListWithItemsId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){return ResponseEntity.internalServerError().build();}
    }
}