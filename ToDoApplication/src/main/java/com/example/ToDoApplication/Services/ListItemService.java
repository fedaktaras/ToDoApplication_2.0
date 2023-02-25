package com.example.ToDoApplication.Services;

import com.example.ToDoApplication.ListItem;
import com.example.ToDoApplication.Repositories.ListItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListItemService {
    @Autowired
    ListItemRepository listItemRepository;
    public void addListItem(ListItem listItem){listItemRepository.save(listItem);}
    public ListItem getListItemById(Long id) throws EntityNotFoundException {return listItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);}
    public void updateListItem(ListItem listItem){listItemRepository.save(listItem);}
    public boolean deleteListItemById(Long id) {
        try {
            listItemRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
