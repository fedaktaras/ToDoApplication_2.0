package com.example.ToDoApplication;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "list_item")
public class ListItem {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String title;
    private String text;
    public ListItem() {}
    public ListItem(String title, String text) {
        this.title = title;
        this.text = text;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "list_with_items_id")
    @JsonBackReference
    private ListWithItems listWithItems;
    public Long getItemID() {return id;}
    public void setItemID(Long itemID) {this.id = itemID;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public ListWithItems getListWithItems() {return listWithItems;}
    public void setListWithItems(ListWithItems listWithItems) {this.listWithItems = listWithItems;}
}
