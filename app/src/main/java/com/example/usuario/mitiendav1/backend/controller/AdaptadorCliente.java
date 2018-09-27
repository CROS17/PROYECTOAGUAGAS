package com.example.usuario.mitiendav1.backend.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.example.usuario.mitiendav1.R;
import com.example.usuario.mitiendav1.backend.model.Producto;

import java.util.ArrayList;

public class AdaptadorCliente extends RecyclerView.Adapter<AdaptadorCliente.ViewHolder> {

    private ArrayList<Producto>elementos;
    private Context context;

    public AdaptadorCliente(ArrayList<Producto> elementos, Context context){

        this.elementos = elementos;
        this.context=context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView TxtDes,TxtDetalle;

        public ViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.img);
            TxtDes=(TextView)itemView.findViewById(R.id.txtDesPro);
            TxtDetalle=(TextView)itemView.findViewById(R.id.txtDetalle);
        }
    }

    @Override
    public AdaptadorCliente.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorCliente.ViewHolder holder, int position) {
        Producto lista=elementos.get(position);
        holder.TxtDes.setText(lista.getNombre());
        holder.TxtDetalle.setText(lista.getStock());
        Picasso.with(context).load("ruta de la imagen"+lista.getImg()).resize(400,400).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return elementos.size();
    }


}
