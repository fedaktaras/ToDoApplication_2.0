package com.example.ToDoApplication.Controllers;
import com.example.ToDoApplication.ListItem;
import com.example.ToDoApplication.ListWithItems;
import com.example.ToDoApplication.ListWithItemsUpdateTitleDTO;
import com.example.ToDoApplication.Services.ListItemService;
import com.example.ToDoApplication.Services.ListWithItemsService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class ListWithItemsController {
    @Autowired
    ListWithItemsService listWithItemsService;
    @Autowired
    ListItemService listItemService;
    @PostMapping(value = "/lists")
    public ResponseEntity<ListWithItems> newListWithTasks(@Valid @RequestBody ListWithItems listWithItems){
        try {
            ListWithItems validatedListWithItems = listWithItemsService.addListWithItemsAndFlush(listWithItems);
            return new ResponseEntity<>(validatedListWithItems, HttpStatus.CREATED);
        }
        catch (Exception e){return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
    }
    @GetMapping(value = "/lists/{id}")
    @ResponseBody
    public ListWithItems findById(@PathVariable("id") Long idToLookFor){
        ListWithItems listWithItems =  listWithItemsService.getListWithItemsById(idToLookFor);
        return listWithItems;
    }

    @PutMapping("/lists/{id}")
    public ResponseEntity<ListWithItems> updateListWithTasksTitle(@Valid @RequestBody ListWithItemsUpdateTitleDTO listWithItemsToUpdateDTO, @PathVariable("id")Long id){
        ListWithItems listWithItemsToUpdate = listWithItemsService.getListWithItemsById(id);
        listWithItemsToUpdate.setTitle(listWithItemsToUpdateDTO.getTitle());
        listWithItemsService.updateListWithItemsAndFlush(listWithItemsToUpdate);
        return ResponseEntity.ok(listWithItemsToUpdate);
    }
    @DeleteMapping("/lists/{ListId}/{ItemId}")
    public ResponseEntity<ListWithItems> updateListWithTasksDeleteItem(@Valid @PathVariable("ListId")Long ListId, @PathVariable("ItemId")Long ItemId){

        listItemService.deleteListItemById(ItemId);
        ListWithItems listWithItems =  listWithItemsService.getListWithItemsById(ListId);
        //Hibernate.initialize(listWithItems);
        return ResponseEntity.ok(listWithItems);
    }

    @DeleteMapping(value = "/lists/{id}")
    public void deleteListWithItemById(@PathVariable Long id){listWithItemsService.deleteListWithItemsByID(id);}
}
