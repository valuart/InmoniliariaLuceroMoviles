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
import com.example.inmoniliarialuceromoviles.modelo.Pago;
import com.example.inmoniliarialuceromoviles.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {
  private Context context;
  private MutableLiveData<List<Pago>> pagos;

  public PagoViewModel(@NonNull Application application) {
     super(application);
     context = application.getApplicationContext();
 }

 public MutableLiveData<List<Pago>> getPagos() {
     if (pagos == null) {
         pagos = new MutableLiveData<>();
     }
     return pagos;
 }

 public void cargarPagos(Bundle bundle) {
     //ApiClient api = ApiClient.getApi();
     Contrato c = (Contrato) bundle.getSerializable("pagos");
    // pagos.setValue(api.obtenerPagos(c));
     SharedPreferences sp = ApiClient.conectar(context);
     String token = sp.getString("token","-1");
     Call<List<Pago>> p = ApiClient.getMyApiClient().obtenerPagos(token, c.getId());
     p.enqueue(new Callback<List<Pago>>() {
         @Override
         public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
             if(response.isSuccessful()){
                     pagos.postValue(response.body());
             }
         }

         @Override
         public void onFailure(Call<List<Pago>> call, Throwable t) {
             Toast.makeText(context, "Ocurrio un error"+t.getMessage(), Toast.LENGTH_LONG).show();
         }
     });


 }
}