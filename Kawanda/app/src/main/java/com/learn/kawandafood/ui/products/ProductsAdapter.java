package com.learn.kawandafood.ui.products;

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
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;
import com.learn.kawandafood.data.viewmodel.ProductViewModel;


import java.util.List;

class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    List<Product> products;

    public ProductsAdapter(List<Product> products) {
        this.products = products;
    }

    public void setData(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row, parent, false);
        return new ProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.productName.setText(product.name);
        holder.productQuantity.setText(String.valueOf(product.quantity));
        ClientViewModel clientViewModel = new ViewModelProvider((ViewModelStoreOwner) holder.itemView.getContext()).get(ClientViewModel.class);
        clientViewModel.getById(product.client_id).observe((LifecycleOwner) holder.itemView.getContext(), client -> {
            holder.clientName.setText(client.name);
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productQuantity;
        TextView clientName;
        ImageView imgDelete;
        ImageView imgEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.prdct_name);
            productQuantity = itemView.findViewById(R.id.prdct_quantity);
            clientName = itemView.findViewById(R.id.client_names);
            imgDelete = itemView.findViewById(R.id.img_deleteP);
            imgEdit = itemView.findViewById(R.id.img_edit);




            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //edit product page
                    int position = getAdapterPosition();
                    Product product = products.get(position);
                    Intent intent = new Intent(v.getContext(), EditProductActivity.class);
                    intent.putExtra("id", product.getId());
                    intent.putExtra("client_id", product.getClient_id());
                    v.getContext().startActivity(intent);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Product product = products.get(position);
                    ProductViewModel productViewModel =
                            new ViewModelProvider((ViewModelStoreOwner) v.getContext()).get(ProductViewModel.class);
                    productViewModel.deleteProduct(product);
                    notifyDataSetChanged();
                    Toast.makeText(v.getContext(), "Deleted Product", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
