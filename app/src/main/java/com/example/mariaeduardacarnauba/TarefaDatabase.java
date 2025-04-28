package com.example.mariaeduardacarnauba;

import androidx.room.RoomDatabase;
import androidx.room.Room;
import android.content.Context;
import androidx.room.Database;


@Database(entities = {Tarefa.class}, version = 1)
public abstract class TarefaDatabase extends RoomDatabase {
    public abstract TarefaDao tarefaDao();
    private static TarefaDatabase instance;

    public static synchronized TarefaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TarefaDatabase.class,
                            "tarefa-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
