package com.example.ToDoApplication;

import jakarta.persistence.*;

@Entity
@Table(name = "ListItem")
public class ListItem {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String text;

    public ListItem() {}
    public ListItem(String title, String text) {
        this.title = title;
        this.text = text;
    }
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "list_with_items_id", nullable = false)
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
