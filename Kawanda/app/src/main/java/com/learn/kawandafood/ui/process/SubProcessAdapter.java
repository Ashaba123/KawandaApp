package com.learn.kawandafood.ui.process;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.SubProcess;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;
import com.learn.kawandafood.data.viewmodel.SubProcessViewModel;

import java.util.List;

public class SubProcessAdapter extends RecyclerView.Adapter<SubProcessAdapter.ViewHolder> {
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
        ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            processName = itemView.findViewById(R.id.sub_process_name);
            imgDelete = itemView.findViewById(R.id.img_delete_sub_process);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    SubProcess subProcess = subProcesses.get(position);
                    SubProcessViewModel subProcessViewModel =
                            new ViewModelProvider((ViewModelStoreOwner) v.getContext()).get(SubProcessViewModel.class);
                    subProcessViewModel.deleteSubProcess(subProcess);
                    Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
}
