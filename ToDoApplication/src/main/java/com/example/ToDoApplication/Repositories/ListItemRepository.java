package com.example.ToDoApplication.Repositories;

import com.example.ToDoApplication.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    @Query("SELECT l.id FROM ListItem l WHERE l.listWithItems.id = :listWithItemsId AND l.next IS NULL")
    Long findLastListItemIdByListWithItems(@Param("listWithItemsId") Long listWithItemsId);

    @Modifying
    @Query("UPDATE ListItem l SET l.next = :next WHERE l.id = :id")
    void updateNextById(@Param("id") Long id, @Param("next") Long next);
}
