package com.example.inmoniliarialuceromoviles.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.inmoniliarialuceromoviles.modelo.Contrato;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.modelo.Pago;
import com.example.inmoniliarialuceromoviles.modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {
    public static final String UrlBase="https://192.168.0.101:45457/api/";
    private static PostInterface postInterface;
    private static SharedPreferences sp;

    public static SharedPreferences conectar(Context context){
        if (sp==null){
            sp = context.getSharedPreferences("token.dat",0);
        }
        return  sp;
    }

    public static PostInterface getMyApiClient(){

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(UrlBase)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        postInterface=retrofit.create(PostInterface.class);

        return postInterface;
    }

    public interface PostInterface {

        @FormUrlEncoded
        @POST("Propietario/login")
        Call<String> login(@Field("Email") String email, @Field("Clave") String clave);

        @GET("Propietario/obtenerPropietario")
        Call<Propietario> obtenerPropietario(@Header("Authorization") String token);

        @PUT("Propietario/editarPropietario")
        Call<Propietario> editarPropietario(@Header("Authorization") String token, @Body Propietario propietario);

        @GET("Inmueble")
        Call<List<Inmueble>> obtenerPropiedades(@Header("Authorization") String token);

        @PUT("Inmueble/{id}")
        Call<Inmueble> actualizarEstado(@Header("Authorization") String token, @Path("id") int id);

        @GET("Contrato")
        Call<List<Contrato>> obtenerInmueblesAlquilados(@Header("Authorization") String token);

        @GET("Pagos/{id}")
        Call<List<Pago>>obtenerPagos(@Header("Authorization") String token, @Path("id") int id);

    }
}
