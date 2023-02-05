package com.example.ToDoApplication.Services;

import com.example.ToDoApplication.ListWithItems;
import com.example.ToDoApplication.Repositories.ListWithItemsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListWithItemsService {
    @Autowired
    ListWithItemsRepository listWithItemsRepository;
    public void addListWithItems(ListWithItems listWithItems){listWithItemsRepository.save(listWithItems);}
    public ListWithItems getListWithItemsById(Long id){return listWithItemsRepository.findById(id).orElseThrow(EntityNotFoundException::new);}
    public void deleteListItemByID(Long id) {listWithItemsRepository.deleteById(id);}
}
