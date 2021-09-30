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
import com.example.inmoniliarialuceromoviles.modelo.Pago;

import java.util.ArrayList;

public class PagoFragment extends Fragment {
    private RecyclerView rvPagos;
    private PagoAdapter pa;
    private PagoViewModel pViewModel;

    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        pViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagoViewModel.class);

        View root = inflater.inflate(R.layout.pago_fragment, container, false);

        rvPagos = root.findViewById(R.id.rvPagos);

        pViewModel.getPagos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rvPagos.setLayoutManager(gridLayoutManager);
                pa = new PagoAdapter(pagos, root, getLayoutInflater());
                rvPagos.setAdapter(pa);


            }
        });
        pViewModel.cargarPagos(getArguments());
        return root;
    }
}