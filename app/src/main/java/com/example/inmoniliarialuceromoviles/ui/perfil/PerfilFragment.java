package com.example.inmoniliarialuceromoviles.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Propietario;


public class PerfilFragment extends Fragment {
    private PerfilViewModel pViewModel;
    private EditText name, lastName, document, mail, pass, phone;
    private TextView id;
    private Button editar, guardar;
    private ImageView avatarP;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        pViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        View vistaPerfil = inflater.inflate(R.layout.fragment_perfil, container, false);

        inicializar(vistaPerfil);

        //observer mutable Propietario
        pViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {

            @Override
            public void onChanged(Propietario propietario) {

                id.setText(propietario.getId()+"");
                document.setText(propietario.getDni().toString());
                name.setText(propietario.getNombre());
                lastName.setText(propietario.getApellido());
                mail.setText(propietario.getEmail());
                pass.setText(propietario.getClave());
                phone.setText(propietario.getTelefono());
                Glide.with(vistaPerfil.getContext())
                        .load("https://192.168.0.101:45457"+propietario.getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(avatarP);

            }
        });

        pViewModel.getEditable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                document.setEnabled(aBoolean);
                name.setEnabled(aBoolean);
                lastName.setEnabled(aBoolean);
                mail.setEnabled(aBoolean);
                pass.setEnabled(aBoolean);
                phone.setEnabled(aBoolean);

            }
        });

        pViewModel.getVeditar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                editar.setVisibility(integer);
            }
        });

        pViewModel.getVguardar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                guardar.setVisibility(integer);
            }
        });

        pViewModel.obtenerUsuarioActual();
        return vistaPerfil;
    }


    private void inicializar(View vistaPerfil) {
        //id = vistaPerfil.findViewById(R.id.tvId);
        document = vistaPerfil.findViewById(R.id.etDocument);
        name = vistaPerfil.findViewById(R.id.etName);
        lastName = vistaPerfil.findViewById(R.id.etLastName);
        mail = vistaPerfil.findViewById(R.id.etCorreo);
        phone = vistaPerfil.findViewById(R.id.etPhone);
        avatarP = vistaPerfil.findViewById(R.id.ivFotoP);
        editar = vistaPerfil.findViewById(R.id.btEditar);
        guardar = vistaPerfil.findViewById(R.id.btGuardar);

        //editar habilita la edicion
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pViewModel.guardarDatos();

            }
        });

        //guarda cambios y deshabilita la edicion
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario p = new Propietario();
                p.setId(Integer.parseInt(id.getText().toString()));
                p.setNombre(name.getText().toString());
                p.setApellido(lastName.getText().toString());
                p.setDni(document.getText().toString());
                p.setEmail(mail.getText().toString());
                p.setClave(pass.getText().toString());
                p.setTelefono(phone.getText().toString());

                pViewModel.editarDatos(p);

            }
        });

    }
}