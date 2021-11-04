package com.example.inmoniliarialuceromoviles.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoniliarialuceromoviles.modelo.Propietario;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token","-1");
        Call<Propietario> prop = ApiClient.getMyApiClient().obtenerPropietario(token);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietarioMutable.postValue(response.body());
                    getEditable().setValue(false);
                }else{
                    Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
      public void editarDatos(Propietario p) {
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token","-1");
        Call<Propietario> prop = ApiClient.getMyApiClient().editarPropietario(token,p);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietarioMutable.postValue(response.body());
                    Toast.makeText(context, "Se edito con exito", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

                Toast.makeText(context, "Ocurrio un error"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
    public void guardarDatos(){
        editable.setValue(true);
        veditar.setValue(View.INVISIBLE);
        vguardar.setValue(View.VISIBLE);

    }

}