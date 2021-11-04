package com.example.inmoniliarialuceromoviles.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Inquilino;

public class InquiDetalleFragment extends Fragment {
    private TextView id, nombre, apellido, dni, correo, telefono;
    private InquiDetalleViewModel indeVM;

    public static InquiDetalleFragment newInstance() {
        return new InquiDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        indeVM= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquiDetalleViewModel.class);

        View root = inflater.inflate(R.layout.inqui_detalle_fragment, container, false);

        inicializar(root);

        indeVM.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                //id.setText(inquilino.getId() + "");
                nombre.setText(inquilino.getNombre());
                apellido.setText(inquilino.getApellido());
                dni.setText(inquilino.getDni()+"");
                telefono.setText(inquilino.getTelefono());
                correo.setText(inquilino.getEmail());
            }
        });

        indeVM.cargarInquilino(getArguments());
        return root;

    }

    public void inicializar(View view){
        id = view.findViewById(R.id.tvCo);
        dni = view.findViewById(R.id.tvDNI);
        nombre = view.findViewById(R.id.tvNombre);
        apellido = view.findViewById(R.id.tvApellido);
        telefono = view.findViewById(R.id.tvTelefono);
        correo = view.findViewById(R.id.tvEmail);
        //nombreGarante = view.findViewById(R.id.tvGarante);
        //telefonoGarante = view.findViewById(R.id.tvTelefonoGarante);

    }

}