package com.example.inmoniliarialuceromoviles.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.example.inmoniliarialuceromoviles.modelo.Contrato;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Contrato>> contratos;
    private Context context;


    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public MutableLiveData<List<Contrato>> getContratos() {
        if (contratos == null) {
            contratos = new MutableLiveData<>();
        }
        return contratos;

    }

    public void propiedadesAlquiladas() {
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token","-1");

        Call<List<Contrato>> con = ApiClient.getMyApiClient().obtenerInmueblesAlquilados(token);
       con.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()) {
                    contratos.postValue(response.body());
                  }
                }
                @Override
                public void onFailure(Call<List<Contrato>> call, Throwable t) {
                    Toast.makeText(context, "Ocurrio un error" + t.getMessage(), Toast.LENGTH_LONG).show();
                  }
               });
            }

    }

