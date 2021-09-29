package com.example.inmoniliarialuceromoviles.ui.inquilino;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

import java.util.ArrayList;

public class InquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;


    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public MutableLiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmuebles == null) {
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;

    }

    public void propiedadesAlquiladas() {
        ApiClient api= ApiClient.getApi();
        inmuebles.setValue(api.obtenerPropiedadesAlquiladas());

    }
}