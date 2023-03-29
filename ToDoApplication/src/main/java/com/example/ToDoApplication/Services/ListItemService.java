package com.example.ToDoApplication.Services;
import com.example.ToDoApplication.ListItem;
import com.example.ToDoApplication.ListWithItems;
import com.example.ToDoApplication.Repositories.ListItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListItemService {
    @Autowired
    ListItemRepository listItemRepository;
    @Autowired
    ListWithItemsService listWithItemsService;
    @Transactional
    public void addListItem(ListItem listItem, Long idOfListWithItems){
        ListWithItems listWithItemsToAddTo  =  listWithItemsService.getListWithItemsById(idOfListWithItems); //EntityNotFoundException can be here.
        listItem.setListWithItems(listWithItemsToAddTo);
        Long currentLastFutureNotLast = listItemRepository.findLastListItemIdByListWithItems(idOfListWithItems);
        listItem.setPrevious(currentLastFutureNotLast);
        listItemRepository.saveAndFlush(listItem);
        if(currentLastFutureNotLast!=null){
        listItemRepository.updateNextById(currentLastFutureNotLast,listItem.getItemID());
        }
    }
    public ListItem getListItemById(Long id) throws EntityNotFoundException {return listItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);}
    public void updateListItem(ListItem listItem){listItemRepository.save(listItem);}
    @Transactional
    public boolean deleteListItemById(Long id) {
        try {
            listItemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
