package com.example.inmoniliarialuceromoviles.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoniliarialuceromoviles.modelo.Propietario;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<Integer> veditar;
    private MutableLiveData<Integer> vguardar;
    private MutableLiveData<Boolean> editable;

    Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<Propietario> getPropietarioMutable() {
        if (propietarioMutable == null) {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public MutableLiveData<Integer> getVeditar() {
        if (veditar == null) {
            veditar = new MutableLiveData<>();
        }
        return veditar;
    }

    public MutableLiveData<Integer> getVguardar() {
        if (vguardar == null) {
            vguardar = new MutableLiveData<>();
        }
        return vguardar;
    }

    public MutableLiveData<Boolean> getEditable() {
        if (editable == null) {
            editable = new MutableLiveData<>();
        }
        return editable;
    }

    //usuario Logueado
    public void obtenerUsuarioActual() {
        ApiClient api = ApiClient.getApi();
        Propietario p = api.obtenerUsuarioActual();
        propietarioMutable.setValue(p);

    }

    public void editarDatos(Propietario p){
        ApiClient api = ApiClient.getApi();
        api.actualizarPerfil(p);
        veditar.setValue(View.VISIBLE);
        vguardar.setValue(View.INVISIBLE);
    }

    public void guardarDatos(){
        editable.setValue(true);
        veditar.setValue(View.INVISIBLE);
        vguardar.setValue(View.VISIBLE);

    }


}