package com.example.ToDoApplication;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import java.util.List;
@Entity
@Table(name = "list_with_items")
public class ListWithItems {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String title;
    @OneToMany(mappedBy = "listWithItems", cascade = CascadeType.ALL)
    @OrderBy("previous ASC NULLS FIRST")
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ListItem> listOfListItems;
    public ListWithItems(){}
    public ListWithItems(String title) {this.title = title;}
    public ListWithItems(String title, List<ListItem> listOfListItems) {this.title = title; this.listOfListItems = listOfListItems;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public List<ListItem> getListOfListItems() {return listOfListItems;}
    public void setListOfListItems(List<ListItem> listOfListItems) {this.listOfListItems = listOfListItems;}
}