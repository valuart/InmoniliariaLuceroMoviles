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

import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;


import java.util.ArrayList;

public class ContratoFragment extends Fragment {
    private RecyclerView rvCon;
    private ContratoViewModel conViewModel;
    private ContratoAdapter ca;


    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        conViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoViewModel.class);

        View root = inflater.inflate(R.layout.inquilino_fragment, container, false);

        rvCon = root.findViewById(R.id.rvContratos);

        conViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rvCon.setLayoutManager(gridLayoutManager);
                ca = new ContratoAdapter(inmuebles,root,getLayoutInflater());
                rvCon.setAdapter(ca);


            }
        });
        conViewModel.propiedadesAlquiladas();
        return root;

    }



}