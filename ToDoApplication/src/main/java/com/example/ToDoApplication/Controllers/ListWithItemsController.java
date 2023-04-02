package com.example.ToDoApplication.Controllers;
import com.example.ToDoApplication.ListWithItems;
import com.example.ToDoApplication.ListWithItemsUpdateTitleDTO;
import com.example.ToDoApplication.Services.ListItemService;
import com.example.ToDoApplication.Services.ListWithItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@RestController
public class ListWithItemsController {
    @Autowired
    ListWithItemsService listWithItemsService;
    @Autowired
    ListItemService listItemService;
    @GetMapping(value = "/lists")
    public ResponseEntity<List<ListWithItems>> getAll(){
        try {return ResponseEntity.ok(listWithItemsService.getAllListWithItems());}
        catch (Exception e){return ResponseEntity.internalServerError().build();}
    }
    @GetMapping(value = "/lists/{id}")
    @ResponseBody
    public ResponseEntity<ListWithItems> findById(@PathVariable("id") Long idToLookFor){
        try {
            Optional<ListWithItems> listWithItems = listWithItemsService.getListWithItemsById(idToLookFor);
            return listWithItems.isPresent() ? ResponseEntity.ok(listWithItems.get()) : ResponseEntity.notFound().build();
        } catch (Exception e){return ResponseEntity.internalServerError().build();}
    }
    @PostMapping(value = "/lists")
    public ResponseEntity<ListWithItems> newListWithTasks(@Valid @RequestBody ListWithItems listWithItems){
        try {
            ListWithItems validatedListWithItems = listWithItemsService.addListWithItemsAndFlush(listWithItems);
            return new ResponseEntity<>(validatedListWithItems, HttpStatus.CREATED);
        }
        catch (Exception e){return ResponseEntity.internalServerError().build();}
    }
    @PutMapping("/lists/{id}") // Only title may be changed so DTO is used.
    public ResponseEntity<ListWithItems> updateListWithTasksTitle(@Valid @RequestBody ListWithItemsUpdateTitleDTO listWithItemsToUpdateDTO, @PathVariable("id")Long id){
        try{
            ListWithItems listWithItemsToUpdate = listWithItemsService.getListWithItemsById(id).get();
            listWithItemsToUpdate.setTitle(listWithItemsToUpdateDTO.getTitle());
            listWithItemsService.updateListWithItemsAndFlush(listWithItemsToUpdate);
            return ResponseEntity.ok(listWithItemsToUpdate);
        }
        catch (NullPointerException e) {return ResponseEntity.badRequest().build();}
        catch (NoSuchElementException e) {return ResponseEntity.notFound().build();}
        catch (Exception e){return ResponseEntity.internalServerError().build();}
    }
    @DeleteMapping(value = "/lists/{id}")
    public ResponseEntity<Void> deleteListWithItemById(@PathVariable Long id){
        try {
            boolean isDeleted = listWithItemsService.deleteListWithItemsByID(id);
            return isDeleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}