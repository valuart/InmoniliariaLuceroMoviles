package com.example.inmoniliarialuceromoviles.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoniliarialuceromoviles.modelo.Contrato;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ConDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contrato;
    private Context context;
    private Contrato i;


    public ConDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<Contrato> getContrato() {
        if (contrato == null) {
            contrato = new MutableLiveData<>();
        }
        return contrato;
    }

    public void setInmueble(Bundle bundle) {
        // ApiClient api = ApiClient.getApi();
        i = (Contrato) bundle.getSerializable("contrato");
        contrato.setValue(i);
    }
}