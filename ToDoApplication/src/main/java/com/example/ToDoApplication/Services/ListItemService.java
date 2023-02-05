package com.example.ToDoApplication.Services;

import com.example.ToDoApplication.ListItem;
import com.example.ToDoApplication.Repositories.ListItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListItemService {
    @Autowired
    ListItemRepository listItemRepository;
    public void addListItem(ListItem listItem){listItemRepository.save(listItem);}
    public ListItem getListItemById(Long id) throws EntityNotFoundException {return listItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);}
    public void deleteById(Long id) {listItemRepository.deleteById(id);}
}