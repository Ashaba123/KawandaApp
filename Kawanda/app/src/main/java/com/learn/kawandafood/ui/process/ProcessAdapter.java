package com.learn.kawandafood.ui.process;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;
import com.learn.kawandafood.data.viewmodel.ProcessViewModel;
import com.learn.kawandafood.data.viewmodel.ProductViewModel;
import com.learn.kawandafood.data.viewmodel.SubProcessViewModel;

import java.util.List;

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ViewHolder> {
    List<Process> processes;

    public ProcessAdapter(List<Process> processes) {
        this.processes = processes;
    }

    public void setData(List<Process> processes) {
        this.processes = processes;
    }

    @NonNull
    @Override
    public ProcessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.process_row, parent, false);
        return new ProcessAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessAdapter.ViewHolder holder, int position) {
        Process process = processes.get(position);
        holder.processName.setText(process.name);

        ProductViewModel productViewModel = new ViewModelProvider((ViewModelStoreOwner) holder.itemView.getContext()).get(ProductViewModel.class);
        productViewModel.getProduct(process.product_id).observe((LifecycleOwner) holder.itemView.getContext(), product -> {
            holder.productName.setText(product.name);
        });

        SubProcessViewModel subProcessViewModel =
                new ViewModelProvider((ViewModelStoreOwner) holder.itemView.getContext()).get(SubProcessViewModel.class);
        subProcessViewModel.countSubProcesses(process.id).observe((LifecycleOwner) holder.itemView.getContext(), integer -> {
            holder.subProcessCount.setText(String.valueOf(integer));
        });


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BrowseSubProcessActivity.class);
            intent.putExtra("id", processes.get(position).id);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return processes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView processName;
        TextView productName;
        TextView subProcessCount;
        ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            processName = itemView.findViewById(R.id.processTitle);
            productName = itemView.findViewById(R.id.product_name);
            subProcessCount = itemView.findViewById(R.id.sub_process_count);
            imgDelete = itemView.findViewById(R.id.img_delete_process);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Process process = processes.get(position);
                    ProcessViewModel processViewModel =
                            new ViewModelProvider((ViewModelStoreOwner) v.getContext()).get(ProcessViewModel.class);
                    processViewModel.deleteProcess(process);
                    notifyDataSetChanged();
                    Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
