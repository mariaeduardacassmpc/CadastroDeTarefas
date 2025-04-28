package com.example.mariaeduardacarnauba;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Dao;
import java.util.List;

@Dao
public interface TarefaDao {
    @Insert
    void insert(Tarefa tarefa);

    @Query("SELECT * FROM Tarefa")
    List<Tarefa> getAllTarefas();

    @Delete
    void delete(Tarefa tarefa);
}