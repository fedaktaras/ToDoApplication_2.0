package com.example.ToDoApplication.Services;
import com.example.ToDoApplication.ListWithItems;
import com.example.ToDoApplication.Repositories.ListWithItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class ListWithItemsService {
    @Autowired
    ListWithItemsRepository listWithItemsRepository;
    public ListWithItems addListWithItemsAndFlush(ListWithItems listWithItems){
        listWithItems = listWithItemsRepository.saveAndFlush(listWithItems);
        return listWithItems;
    }
    @Transactional(readOnly = true)
    public Optional<ListWithItems> getListWithItemsById(Long id){return listWithItemsRepository.findById(id);}
    public boolean deleteListWithItemsByID(Long id) {
        Optional<ListWithItems> optionalListWithItems = listWithItemsRepository.findById(id);
        if (optionalListWithItems.isPresent()) {
            listWithItemsRepository.deleteById(id);
            return true;
        } else return false;
    }
    public ListWithItems updateListWithItemsAndFlush(ListWithItems listWithItems) {return listWithItemsRepository.saveAndFlush(listWithItems);}
    public List<ListWithItems> getAllListWithItems() {
        return listWithItemsRepository.findAll(Sort.by("id"));
    }
}
