package com.example.inmoniliarialuceromoviles.ui.inquilino;

import android.content.Context;
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
import com.example.inmoniliarialuceromoviles.modelo.Contrato;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.modelo.Inquilino;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder>  {
    private List<Contrato> lista;
    private View root;
    private LayoutInflater layoutInflater;
    private Context context;

    public InquilinoAdapter(List<Contrato> contrato, View root, LayoutInflater layoutInflater) {
        this.lista = contrato;
        this.root = root;
        this.layoutInflater = layoutInflater;

    }
    @NonNull
    @Override
    public InquilinoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.item_inquilino, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.ViewHolder holder, int position) {
        Contrato i = lista.get(position);
        Inmueble inmu = i.getInmueble();
        Inquilino inqu = i.getInquilino();
        holder.tvdir.setText(inmu.getDireccion());
        Glide.with(context)
                .load("https://192.168.0.101:45457"+inmu.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.foto);


        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilino", inqu);
                Navigation.findNavController(root).navigate(R.id.inquiDetalleFragment,bundle);

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
            foto=itemView.findViewById(R.id.ivImagenInmu);
            tvdir=itemView.findViewById(R.id.tvDir);
            ver=itemView.findViewById(R.id.btVer);

        }
    }

}
