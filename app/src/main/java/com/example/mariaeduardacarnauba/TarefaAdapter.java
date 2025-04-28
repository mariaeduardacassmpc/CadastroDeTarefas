package com.example.mariaeduardacarnauba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {
    private List<Tarefa> tarefas;

    public TarefaAdapter(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TarefaViewHolder holder, int position) {
        Tarefa tarefa = tarefas.get(position);
        holder.titulo.setText(tarefa.getTitulo());
        holder.descricao.setText(tarefa.getDescricao());

        holder.deleteButton.setOnClickListener(v -> {
            // Remover tarefa da lista e notificar o adapter
            tarefas.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public static class TarefaViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, descricao;
        public Button deleteButton;

        public TarefaViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.editTitulo);
            descricao = itemView.findViewById(R.id.editDescricao);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
