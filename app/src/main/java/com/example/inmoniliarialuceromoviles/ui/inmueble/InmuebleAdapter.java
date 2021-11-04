package com.example.inmoniliarialuceromoviles.ui.inmueble;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class InmuebleAdapter extends RecyclerView.Adapter <InmuebleAdapter.ViewHolder> {
    private List<Inmueble> lista;
    private View root;
    private LayoutInflater layoutInflater;
    private Context context;

    public InmuebleAdapter(List<Inmueble> inmuebles, View root, LayoutInflater layoutInflater) {
        this.lista = inmuebles;
        this.root = root;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    //Referenciar a la vista item y pasarsela al viewholder
    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
        Inmueble i = lista.get(position);
        Glide.with(context)
                .load("https://192.168.0.101:45457"+i.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.foto);
        holder.tvdir.setText(i.getDireccion());
        holder.tvPre.setText(i.getPrecio()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", i);
                Navigation.findNavController(root).navigate(R.id.inmuDetalleFragment,bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvdir;
        private TextView tvPre;
        private ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvdir=itemView.findViewById(R.id.tvDirec);
            tvPre=itemView.findViewById(R.id.tvPrecio);
            foto=itemView.findViewById(R.id.ivFoto);
        }
    }
}