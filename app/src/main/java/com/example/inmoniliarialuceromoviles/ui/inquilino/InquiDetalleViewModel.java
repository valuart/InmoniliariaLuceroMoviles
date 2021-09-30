package com.example.inmoniliarialuceromoviles.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.modelo.Inquilino;
import com.example.inmoniliarialuceromoviles.request.ApiClient;


public class InquiDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquilino;
    private Inquilino i;
    private Context context;


    public InquiDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<Inquilino> getInquilino() {
        if(inquilino == null) {
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }

    public void cargarInquilino(Bundle bundle) {

        Inmueble i = (Inmueble) bundle.get("inmueble");
        ApiClient api= ApiClient.getApi();
        inquilino.setValue(api.obtenerInquilino(i));



    }


}