package com.example.inmoniliarialuceromoviles.ui.inmueble;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;

import java.util.ArrayList;


public class InmuebleAdapter extends RecyclerView.Adapter <InmuebleAdapter.ViewHolder> {
    private ArrayList<Inmueble> lista;
    private View root;
    private LayoutInflater layoutInflater;

    public InmuebleAdapter(ArrayList<Inmueble> inmuebles, View root, LayoutInflater layoutInflater) {
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
        Glide.with(root.getContext())
                .load(lista.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.foto);
        holder.tvdir.setText(lista.get(position).getDireccion());
        holder.tvPre.setText(lista.get(position).getPrecio()+"");

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