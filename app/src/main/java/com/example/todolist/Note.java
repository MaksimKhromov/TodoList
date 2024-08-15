package com.example.todolist;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes") // Создание таблицы
public class Note {
    @PrimaryKey(autoGenerate = true) // Автогенирация уникального ключа для id
    private int id;
    private String text;
    private int priority;

    public Note(int id, String text, int priority) {
        this.id = id;
        this.text = text;
        this.priority = priority;
    }
    @Ignore
    public Note(String text, int priority) {
        this.text = text;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", priority=" + priority +
                '}';
    }
}
