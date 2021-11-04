package com.example.inmoniliarialuceromoviles.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoniliarialuceromoviles.modelo.Inquilino;


public class InquiDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquilino;
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
        Inquilino i = (Inquilino) bundle.getSerializable("inquilino");
        inquilino.setValue(i);

    }
   }


