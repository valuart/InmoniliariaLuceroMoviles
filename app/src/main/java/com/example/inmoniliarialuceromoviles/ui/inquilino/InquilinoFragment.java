package com.example.inmoniliarialuceromoviles.ui.inquilino;

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

public class InquilinoFragment extends Fragment {
    private RecyclerView rvInquilino;
    private InquilinoViewModel inqViewModel;
    private InquilinoAdapter ina;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        inqViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinoViewModel.class);

        View root = inflater.inflate(R.layout.inquilino_fragment, container, false);
        rvInquilino = root.findViewById(R.id.rvInquilinos);

        inqViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rvInquilino.setLayoutManager(gridLayoutManager);
                ina = new InquilinoAdapter(inmuebles,root,getLayoutInflater());
                rvInquilino.setAdapter(ina);


            }
        });
        inqViewModel.propiedadesAlquiladas();
        return root;


    }



}