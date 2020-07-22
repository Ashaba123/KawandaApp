package com.learn.kawandafood.ui.products;

import android.content.Intent;
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
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.viewmodel.ProductViewModel;
import com.learn.kawandafood.ui.MainActivity;
import com.learn.kawandafood.ui.process.ProcessActivity;


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
        holder.productRawMaterial.setText(product.raw_material);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productQuantity;
        TextView productRawMaterial;
        ImageView imgDelete;
        ImageView imgEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.prdct_name);
            productQuantity = itemView.findViewById(R.id.prdct_quantity);
            productRawMaterial = itemView.findViewById(R.id.prdct_raw_material);
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
                    Toast.makeText(v.getContext(), "Deleted Product", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
