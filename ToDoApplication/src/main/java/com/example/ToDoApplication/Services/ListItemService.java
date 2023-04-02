package com.example.ToDoApplication.Services;
import com.example.ToDoApplication.ListItem;
import com.example.ToDoApplication.ListWithItems;
import com.example.ToDoApplication.Repositories.ListItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Service
public class ListItemService {
    @Autowired
    ListItemRepository listItemRepository;
    @Autowired
    ListWithItemsService listWithItemsService;
    @Transactional
    public ListItem addListItem(ListItem listItem, Long idOfListWithItems) throws EntityNotFoundException{
        ListWithItems listWithItemsToAddTo  =  listWithItemsService.getListWithItemsById(idOfListWithItems).get();
        listItem.setListWithItems(listWithItemsToAddTo);
        Long currentLastFutureNotLast = listItemRepository.findLastListItemIdByListWithItems(idOfListWithItems);
        listItem.setPrevious(currentLastFutureNotLast);
        listItemRepository.saveAndFlush(listItem);
        if(currentLastFutureNotLast!=null){
            listItemRepository.updateNextById(currentLastFutureNotLast,listItem.getItemID());
        }
        return listItem;
    }
    public ListItem getListItemById(Long id) throws EntityNotFoundException {return listItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);}
    public void updateListItem(ListItem listItem){listItemRepository.save(listItem);}
    @Transactional
    public boolean deleteListItemById(Long id) throws NoSuchElementException {
            ListItem listItem = listItemRepository.findById(id).get();
            Long prev = listItem.getPrevious();
            Long next = listItem.getNext();
            listItemRepository.deleteById(id);
            listItemRepository.updateNextById(prev, next);
            listItemRepository.updatePreviousById(next, prev);
            return true;
    }
    @Transactional
    public void placeBeforeListItemId(Long idOfListItemToShift, Long listItemId, Long listWithItemsId) throws NoSuchElementException {
        ListItem listItem = cutOutListItemById(listItemId);
        ListWithItems listWithItems = listWithItemsService.getListWithItemsById(listWithItemsId).get();  //Exception expected if no such element in database
        listItem.setListWithItems(listWithItems);
        if (idOfListItemToShift ==null){
            listItem.setPrevious(null);
            listItem.setNext(null);
            listItemRepository.save(listItem);
            return;
        }
        ListItem listItemToShift = listItemRepository.findById(idOfListItemToShift).get(); //Exception expected if no such element in database
        Long previousId = listItemToShift.getPrevious();
        if(previousId != null){
            ListItem previousListItem = listItemRepository.findById(previousId).get(); //Exception expected if no such element in database
            listItemRepository.updateNextById(previousId, listItem.getItemID());
        }
        listItemRepository.updatePreviousById(listItemToShift.getItemID(), listItem.getItemID());
        listItemRepository.updatePreviousById(listItem.getItemID(), previousId);
        listItemRepository.updateNextById(listItem.getItemID(), listItemToShift.getItemID());
        return;
    }
    public ListItem cutOutListItemById(Long id) throws NoSuchElementException {
        ListItem listItem = listItemRepository.findById(id).get();//Exception expected if no such element in database
        Long prev = listItem.getPrevious();
        Long next = listItem.getNext();
        listItemRepository.updateNextById(prev, next);
        listItemRepository.updatePreviousById(next, prev);
        return listItem;
    }
}
