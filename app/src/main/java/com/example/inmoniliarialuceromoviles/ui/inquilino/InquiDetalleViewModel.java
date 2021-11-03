package com.example.inmoniliarialuceromoviles.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoniliarialuceromoviles.modelo.Contrato;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.modelo.Inquilino;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InquiDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquilino;
    private Inquilino inquil;
    private Inmueble in;
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

        Inmueble i = (Inmueble) bundle.get("inquilino");
        inquilino.setValue(inquil);

    }

    }


