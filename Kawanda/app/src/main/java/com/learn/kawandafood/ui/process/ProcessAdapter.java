package com.learn.kawandafood.ui.process;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Process;


import java.util.List;

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ViewHolder> {
    private List<Process> processes;

    public ProcessAdapter(List<Process> processes) {
        this.processes = processes;
    }

    public void setData(List<Process> processes) {
        this.processes = processes;
    }

    @NonNull
    @Override
    public ProcessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group, parent, false);
        return new ProcessAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessAdapter.ViewHolder holder, int position) {
        Process process = processes.get(position);
        holder.processName.setText(process.name);
    }

    @Override
    public int getItemCount() {
        return processes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView processName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            processName = itemView.findViewById(R.id.processTitle);
        }


    }

}
