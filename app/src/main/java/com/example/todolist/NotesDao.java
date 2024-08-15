package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao { // Методы для таблицы notes в БД

    @Query("SELECT * FROM notes")//Query - опизание запроса на SQL
    LiveData<List<Note>> getNotes(); // LiveData установки подписки на изменение объектов

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Добавление объекта в БД, в случае если такой объект есть он перезапишется
    void add(Note note);

    @Query("DELETE FROM notes WHERE id = :id") // Удаление объекта из БД где id равен переданному id
    void remove(int id);
}
