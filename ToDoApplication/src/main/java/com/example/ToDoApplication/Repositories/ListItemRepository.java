package com.example.ToDoApplication.Repositories;

import com.example.ToDoApplication.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
}
