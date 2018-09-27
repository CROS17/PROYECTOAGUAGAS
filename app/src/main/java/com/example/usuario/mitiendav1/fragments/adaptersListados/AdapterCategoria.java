package com.example.usuario.mitiendav1.fragments.adaptersListados;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.mitiendav1.R;
import com.example.usuario.mitiendav1.backend.model.Categoria;

import java.util.ArrayList;

public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder>
        implements View.OnClickListener  {

    ArrayList<Categoria> listaCategoria;
    private View.OnClickListener listener;

    public AdapterCategoria(ArrayList<Categoria> listaPersonajes) {
        this.listaCategoria = listaPersonajes;
    }

    @Override
    public AdapterCategoria.CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_categoria,null,false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

       return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        holder.txtid.setText(String.valueOf(listaCategoria.get(position).getIdcategoria()));
        holder.txtnombre.setText(listaCategoria.get(position).getNombre().toString());
        holder.txtdescripcion.setText(listaCategoria.get(position).getDescripcion());
    }
    @Override
    public int getItemCount() {
        return listaCategoria.size();
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
    public class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView txtid,txtnombre,txtdescripcion;
        public CategoriaViewHolder(View itemView) {
            super(itemView);
            txtid= (TextView) itemView.findViewById(R.id.txtitemidcategoria);
            txtnombre= (TextView) itemView.findViewById(R.id.txtitemnombrecategoria);
            txtdescripcion= (TextView) itemView.findViewById(R.id.txtitemdescripcioncategoria);
        }
    }

}
