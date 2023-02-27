package com.example.ToDoApplication.Services;
import com.example.ToDoApplication.ListWithItems;
import com.example.ToDoApplication.Repositories.ListWithItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListWithItemsService {
    @Autowired
    ListWithItemsRepository listWithItemsRepository;
    public ListWithItems addListWithItemsAndFlush(ListWithItems listWithItems){
        listWithItemsRepository.saveAndFlush(listWithItems);
    return listWithItems;
    }
    @Transactional(readOnly = true)
    public ListWithItems getListWithItemsById(Long id){return listWithItemsRepository.findById(id).get();}
    @Transactional
    public void deleteListWithItemsByID(Long id) {listWithItemsRepository.deleteById(id);}
    public ListWithItems updateListWithItemsAndFlush(ListWithItems listWithItems) {
        return listWithItemsRepository.saveAndFlush(listWithItems);
    }
}
