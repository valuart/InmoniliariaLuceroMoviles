package com.example.inmoniliarialuceromoviles.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.request.ApiClient;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> inmuebles;
    private Inmueble i;
    private Context context;


    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public MutableLiveData<List<Inmueble>> getInmuebles() {
        if (inmuebles == null) {
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;

    }

    public void mostrarInmuebles() {
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token", "-1");
        Call<List<Inmueble>> inm = ApiClient.getMyApiClient().obtenerPropiedades(token);
        inm.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    inmuebles.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });


        /*ApiClient api = ApiClient.getApi();
        inmuebles.setValue(api.obtnerPropiedades());*/

    }

}