package com.example.mariaeduardacarnauba;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class ListarTarefas extends AppCompatActivity {
    private TarefaDao tarefaDao;
    private RecyclerView recyclerView;
    private TarefaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_tarefa);

        // Inicialização do RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configuração do banco de dados com Room
        TarefaDatabase db = Room.databaseBuilder(getApplicationContext(),
                        TarefaDatabase.class,
                        "tarefa-database")
                .allowMainThreadQueries()
                .build();

        tarefaDao = db.tarefaDao();

        // Obter todas as tarefas do banco de dados
        List<Tarefa> tarefas = tarefaDao.getAllTarefas();

        if (tarefas.isEmpty()) {
            Toast.makeText(this, "Nenhuma tarefa cadastrada.", Toast.LENGTH_SHORT).show();
        }

        // Criar e configurar o adaptador para o RecyclerView
        adapter = new TarefaAdapter(tarefas);
        recyclerView.setAdapter(adapter);
    }
}
