package com.learn.kawandafood.ui.process;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.SubProcess;

import java.util.List;

public class SubProcessAdapter extends RecyclerView.Adapter<SubProcessAdapter.ViewHolder>  {
    private List<SubProcess> subProcesses;

    public SubProcessAdapter(List<SubProcess> subProcesses) {
        this.subProcesses = subProcesses;
    }

    public void setData(List<SubProcess> subProcesses) {
        this.subProcesses = subProcesses;
    }

    @NonNull
    @Override
    public SubProcessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_process_row, parent, false);
        return new SubProcessAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubProcessAdapter.ViewHolder holder, int position) {
        SubProcess subProcess = subProcesses.get(position);
        holder.processName.setText(subProcess.name);
    }

    @Override
    public int getItemCount() {
        return subProcesses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView processName;
        CheckBox chked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            processName = itemView.findViewById(R.id.sub_process_name);
            chked = itemView.findViewById(R.id.chkbox);
        }


    }
}
