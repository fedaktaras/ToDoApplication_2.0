package com.example.ToDoApplication;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "list_with_items")
public class ListWithItems {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @OneToMany(mappedBy = "listWithItems", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ListItem> listOfListItems;
    public ListWithItems(){}
    public ListWithItems(String title) {this.title = title;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public List<ListItem> getListOfListItems() {return listOfListItems;}
    public void setListOfListItems(List<ListItem> listOfListItems) {this.listOfListItems = listOfListItems;}
}
