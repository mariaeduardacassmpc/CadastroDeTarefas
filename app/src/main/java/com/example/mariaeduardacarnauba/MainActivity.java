package com.example.mariaeduardacarnauba;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private TarefaDao tarefaDao;
    private EditText editTitulo, editDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos campos de entrada
        editTitulo = findViewById(R.id.editTitulo);
        editDescricao = findViewById(R.id.editDescricao);

        // Botões de interação
        Button btnVisualizar = findViewById(R.id.buttonVisualizar);
        Button btnCadastrar = findViewById(R.id.buttonCadastrar);

        // Configuração do banco de dados com Room
        TarefaDatabase db = Room.databaseBuilder(getApplicationContext(),
                        TarefaDatabase.class,
                        "tarefa-database")
                .allowMainThreadQueries()
                .build();

        tarefaDao = db.tarefaDao();

        // Ação para cadastrar a tarefa
        btnCadastrar.setOnClickListener(v -> {
            String titulo = editTitulo.getText().toString();
            String descricao = editDescricao.getText().toString();

            Log.d("MainActivity", "Titulo: " + titulo + ", Descricao: " + descricao);

            if (!titulo.isEmpty() && !descricao.isEmpty()) {
                Tarefa tarefa = new Tarefa(titulo, descricao);
                tarefaDao.insert(tarefa);  // Inserir a tarefa no banco de dados

                // Mensagem de sucesso
                Toast.makeText(MainActivity.this, "Tarefa cadastrada.", Toast.LENGTH_SHORT).show();

                // Limpar os campos após cadastrar
                editTitulo.setText("");
                editDescricao.setText("");

                // Navegar para a tela de listagem de tarefas
                startActivity(new Intent(MainActivity.this, ListarTarefas.class));
                finish();  // Fechar a MainActivity para evitar voltar ao formulário
            } else {
                Log.d("MainActivity", "Campos obrigatórios vazios.");
                Toast.makeText(MainActivity.this, "Preencha os campos obrigatórios.", Toast.LENGTH_SHORT).show();
            }
        });

        // Ação para visualizar as tarefas cadastradas
        btnVisualizar.setOnClickListener(v -> {
            // Navegar para a tela de listagem de tarefas
            startActivity(new Intent(MainActivity.this, ListarTarefas.class));
        });
    }
}
