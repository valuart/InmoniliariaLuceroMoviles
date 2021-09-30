package com.example.inmoniliarialuceromoviles.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import java.util.ArrayList;


public class ContratoAdapter extends RecyclerView.Adapter <ContratoAdapter.ViewHolder>{
    private ArrayList<Inmueble> lista;
    private View root;
    private LayoutInflater layoutInflater;

    public ContratoAdapter(ArrayList<Inmueble> inmuebles, View root, LayoutInflater layoutInflater) {
        this.lista = inmuebles;
        this.root = root;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.item_contrato, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.ViewHolder holder, int position) {
        Inmueble i = lista.get(position);
        Glide.with(root.getContext())
                .load(lista.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.foto);
        holder.tvdir.setText(lista.get(position).getDireccion());

        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", i);
                Navigation.findNavController(root).navigate(R.id.conDetalleFragment,bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvdir;
        private ImageView foto;
        private Button ver;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto=itemView.findViewById(R.id.ivInmuCon);
            tvdir=itemView.findViewById(R.id.tvDirCon);
            ver=itemView.findViewById(R.id.btVerCon);
        }
    }
}
