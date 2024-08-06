package com.example.todolist;

import java.util.ArrayList;

public class DataBase {

    private final ArrayList<Note> notes = new ArrayList<>();

    private static DataBase instance = null;

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    private DataBase() {}

    public void add(Note note) {
        notes.add(note);
    }

    public void remove(int id) {
        notes.removeIf(note -> note.getId() == id);
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }
}
