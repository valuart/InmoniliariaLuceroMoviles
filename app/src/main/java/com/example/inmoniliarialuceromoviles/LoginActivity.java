package com.example.inmoniliarialuceromoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    EditText mail, password;
    Button ingresar;
    TextView error;
    ImageView logo;
    LoginViewModel lvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarVista();
        lvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        lvm.getVisible().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                error.setVisibility(integer);
            }
        });
    }

    private void inicializarVista(){
        mail = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        error = findViewById(R.id.tvError);
        logo = findViewById(R.id.ivLogo);
        logo.setImageResource(R.drawable.logoinmo2);

        ingresar = findViewById(R.id.btIngresar);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvm.inicioSesion(mail.getText().toString(), password.getText().toString());

            }
        });
    }

}