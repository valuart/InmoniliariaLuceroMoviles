package com.example.inmoniliarialuceromoviles.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

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

    public void guardarCambios(boolean b){
        ApiClient api = ApiClient.getApi();
        i.setEstado(b);
        api.actualizarInmueble(i);

    }

}