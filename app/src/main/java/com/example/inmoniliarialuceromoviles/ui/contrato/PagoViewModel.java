package com.example.inmoniliarialuceromoviles.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoniliarialuceromoviles.modelo.Contrato;
import com.example.inmoniliarialuceromoviles.modelo.Pago;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

import java.util.ArrayList;

public class PagoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Pago>> pagos;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<ArrayList<Pago>> getPagos() {
        if (pagos == null) {
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }

    public void cargarPagos(Bundle bundle) {
        ApiClient api = ApiClient.getApi();
        Contrato c = (Contrato) bundle.getSerializable("contrato");
        pagos.setValue(api.obtenerPagos(c));

    }
}