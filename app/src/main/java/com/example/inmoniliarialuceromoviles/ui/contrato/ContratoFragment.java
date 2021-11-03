package com.example.inmoniliarialuceromoviles.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Contrato;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;


import java.util.ArrayList;
import java.util.List;

public class ContratoFragment extends Fragment {
    private RecyclerView rvCon;
    private ContratoViewModel conViewModel;
    private ContratoAdapter ca;
    private TextView msj;


    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        conViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoViewModel.class);

        View root = inflater.inflate(R.layout.contrato_fragment, container, false);

        rvCon = root.findViewById(R.id.rvContratos);


        conViewModel.getContratos().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rvCon.setLayoutManager(gridLayoutManager);
                ca = new ContratoAdapter(contratos,root,getLayoutInflater());
                rvCon.setAdapter(ca);

            }
        });


        conViewModel.propiedadesAlquiladas();
        return root;

    }



}