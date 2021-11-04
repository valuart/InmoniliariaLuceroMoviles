package com.example.inmoniliarialuceromoviles;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmoniliarialuceromoviles.modelo.Propietario;
import com.example.inmoniliarialuceromoviles.request.ApiClient;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> visible;
    private MutableLiveData<String> error;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }


    public MutableLiveData<Integer> getVisible() {
        if(visible == null){
            visible = new MutableLiveData<>();
        }
        return visible;
    }

    public MutableLiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void inicioSesion(String e, String c){
        Bundle bundle = new Bundle();
        Call<String> token = ApiClient.getMyApiClient().login(e, c);
        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    SharedPreferences sp = ApiClient.conectar(context);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer " + response.body());
                    editor.commit();
                    Call<Propietario> pCall = ApiClient.getMyApiClient().obtenerPropietario("Bearer " + response.body());
                    pCall.enqueue(new Callback<Propietario>() {
                        @Override
                        public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                            if(response.isSuccessful()){
                                bundle.putSerializable("propietario", response.body());
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("propietario", bundle);
                                context.startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<Propietario> call, Throwable t) {

                        }
                    });
                    visible.setValue(View.INVISIBLE);
                } else {
                    visible.setValue(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }

        });
    }

}