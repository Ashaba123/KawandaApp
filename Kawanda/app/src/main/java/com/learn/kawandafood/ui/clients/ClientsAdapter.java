package com.learn.kawandafood.ui.clients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {

    private List<Client> clients;

    public ClientsAdapter(List<Client> clients) {
        this.clients = clients;
    }
    public  void  setData(List<Client> clients){this.clients =clients;}

    @NonNull
    @Override
    public ClientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsAdapter.ViewHolder holder, int position) {
        Client client  = clients.get(position);

        holder.clientName.setText(client.name);;
        holder.clientNumber.setText(client.phonenumber);


    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView clientName;
        TextView clientNumber;
        ImageView imgDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.client_name);
            clientNumber = itemView.findViewById(R.id.client_number);
            imgDelete = itemView.findViewById(R.id.img_delete);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Client  client= clients.get(position);
                    ClientViewModel clientViewModel =
                            new ViewModelProvider((ViewModelStoreOwner) v.getContext()).get(ClientViewModel.class);
                    clientViewModel.deleteClient(client);
                    Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
}
