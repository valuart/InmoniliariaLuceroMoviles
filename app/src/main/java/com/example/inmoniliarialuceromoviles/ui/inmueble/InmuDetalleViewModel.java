package com.example.inmoniliarialuceromoviles.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmueble;
    private Inmueble i;
    private Context context;


    public InmuDetalleViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();

    }

    public LiveData<Inmueble> getInmueble() {
        if (inmueble == null) {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void setInmueble(Bundle bundle){
        i = (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(i);
    }

    public void guardarCambios(boolean c){
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token","-1");
        Call<Inmueble> inm = ApiClient.getMyApiClient().actualizarEstado(token, i.getIdInmueble());
        inm.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){

                    Toast.makeText(context, "Se actualizo con exito", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

}

