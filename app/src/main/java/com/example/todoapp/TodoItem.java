package com.example.todoapp;

public class TodoItem {
    private String name = "";
    private String dueDate = "";
    private String dueTime = "";

    public TodoItem(String name, String dueDate, String dueTime) {
        if (name != null){
            this.name = name;
        }
        if (dueDate != null) {
            this.dueDate = dueDate;
        }
        if (dueTime != null) {
            this.dueTime = dueTime;
        }
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDueTime() {
        return dueTime;
    }
}
