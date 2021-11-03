package com.example.inmoniliarialuceromoviles.ui.inmueble;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;

import java.util.ArrayList;
import java.util.List;


public class InmuebleFragment extends Fragment {
    private RecyclerView rvInmueble;
    private InmuebleViewModel inmuebleViewModel;
    private InmuebleAdapter adapter;
  

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        inmuebleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);

        View root = inflater.inflate(R.layout.fragment_inmueble, container, false);
        rvInmueble = root.findViewById(R.id.rvInmueble);


        inmuebleViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rvInmueble.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleAdapter(inmuebles,root, getLayoutInflater());
                rvInmueble.setAdapter(adapter);


            }
        });
        inmuebleViewModel.mostrarInmuebles();
        return root;

    }
}