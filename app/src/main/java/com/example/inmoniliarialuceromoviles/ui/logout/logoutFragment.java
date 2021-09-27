package com.example.inmoniliarialuceromoviles.ui.logout;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmoniliarialuceromoviles.R;

public class logoutFragment extends Fragment {


    public static logoutFragment newInstance() {
        return new logoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.logout_fragment, container, false);
        notificacion();
        return root;

    }
    public void notificacion() {
        new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme)
                .setTitle("Cerrar Sesión")
                .setMessage("¿Desea cerrar la Sesión?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.nav_inicio);
                    }
                }).show();
    }
}

