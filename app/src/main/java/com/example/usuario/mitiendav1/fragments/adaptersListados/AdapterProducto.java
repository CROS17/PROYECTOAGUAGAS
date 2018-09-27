package com.example.usuario.mitiendav1.fragments.adaptersListados;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.mitiendav1.R;
import com.example.usuario.mitiendav1.backend.model.Producto;

import java.util.ArrayList;

public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ProductoViewHolder>
        implements View.OnClickListener{
    ArrayList<Producto> listaProducto;

    private View.OnClickListener listener;

    public AdapterProducto(ArrayList<Producto> listaPersonajes) {
        this.listaProducto = listaPersonajes;
    }
    @Override
    public AdapterProducto.ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_producto,null,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaProducto.get(position).getIdProducto()));
        holder.txtcategoria.setText(String.valueOf(listaProducto.get(position).getNombrecategoria()));
        holder.txtnombre.setText(listaProducto.get(position).getNombre().toString());
        holder.txtpv.setText(String.valueOf(listaProducto.get(position).getPrecioVenta()));
        holder.txtpc.setText(String.valueOf(listaProducto.get(position).getPrecioCompra()));
        holder.txtstock.setText(String.valueOf(listaProducto.get(position).getStock()));
    }
    @Override
    public int getItemCount() {
        return listaProducto.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }
    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView txtid,txtcategoria,txtnombre,txtpv,txtpc,txtstock;
        public ProductoViewHolder(View itemView) {
            super(itemView);
            txtid= (TextView) itemView.findViewById(R.id.txtitemidproducto);
            txtcategoria= (TextView) itemView.findViewById(R.id.txtitemcategoriaproducto);
            txtnombre= (TextView) itemView.findViewById(R.id.txtitemnombreproducto);
            txtpv= (TextView) itemView.findViewById(R.id.txtitempvproducto);
            txtpc= (TextView) itemView.findViewById(R.id.txtitempcproducto);
            txtstock= (TextView) itemView.findViewById(R.id.txtitemstockproducto);
        }
    }
}
