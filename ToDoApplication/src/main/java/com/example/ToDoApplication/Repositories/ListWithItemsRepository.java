package com.example.ToDoApplication.Repositories;
import com.example.ToDoApplication.ListWithItems;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ListWithItemsRepository extends JpaRepository<ListWithItems, Long> {
}
