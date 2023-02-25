package com.example.ToDoApplication;
import org.jetbrains.annotations.NotNull;
public class ListWithItemsUpdateTitleDTO {
    @NotNull
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
