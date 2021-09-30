package com.example.inmoniliarialuceromoviles.ui.inmueble;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;


public class InmuDetalleFragment extends Fragment {
    private ImageView fotoInm;
    private TextView id, direccion, uso, ambientes, tipo, precio;
    private CheckBox estado;

    private InmuDetalleViewModel mViewModel;

    public static InmuDetalleFragment newInstance() {
        return new InmuDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(InmuDetalleViewModel.class);

        View view = inflater.inflate(R.layout.inmu_detalle_fragment, container, false);
        inicializar(view);

        mViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                id.setText(inmueble.getIdInmueble() + "");
                direccion.setText(inmueble.getDireccion());
                uso.setText(inmueble.getUso());
                tipo.setText(inmueble.getTipo());
                ambientes.setText(inmueble.getAmbientes() + "");
                precio.setText(String.valueOf(inmueble.getPrecio()));
                estado.setChecked(inmueble.isEstado());
                estado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewModel.guardarCambios(estado.isChecked());
                    }
                });
                Glide.with(getContext())
                        .load(inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(fotoInm);

            }


        });
        mViewModel.setInmueble(getArguments());
        return view;
    }

     private void inicializar(View view){
        id = view.findViewById(R.id.tvCodigo);
        direccion = view.findViewById(R.id.tvDire);
        uso = view.findViewById(R.id.tvUso);
        tipo = view.findViewById(R.id.tvTipo);
        ambientes = view.findViewById(R.id.tvAmbiente);
        precio = view.findViewById(R.id.tvPre);
        estado = view.findViewById(R.id.cbEstado);
        fotoInm = view.findViewById(R.id.ivfotoInmu);

    }




}