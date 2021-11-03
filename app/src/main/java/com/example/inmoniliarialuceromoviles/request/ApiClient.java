package com.example.inmoniliarialuceromoviles.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.inmoniliarialuceromoviles.R;
import com.example.inmoniliarialuceromoviles.modelo.Contrato;
import com.example.inmoniliarialuceromoviles.modelo.Inmueble;
import com.example.inmoniliarialuceromoviles.modelo.Inquilino;
import com.example.inmoniliarialuceromoviles.modelo.Pago;
import com.example.inmoniliarialuceromoviles.modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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
   /* private ArrayList<Propietario> propietarios=new ArrayList<>();
    private ArrayList<Inquilino> inquilinos=new ArrayList<>();
    private ArrayList<Inmueble> inmuebles=new ArrayList<>();
    private ArrayList<Contrato> contratos=new ArrayList<>();
    private ArrayList<Pago> pagos=new ArrayList<>();*/

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

        @GET("contrato/{id}")
        Call<Contrato>obtenerContrato(@Header("Authorization") String token, @Path("id") int id);

        @GET("Pagos/{id}")
        Call<List<Pago>>obtenerPagos(@Header("Authorization") String token, @Path("id") int id);

    }



   /*private static Propietario usuarioActual=null;
    private static ApiClient api=null;

    private ApiClient(){
        //Nos conectamos a nuestra "Base de Datos"
        cargaDatos();
    }
    //Método para crear una instancia de ApiClient
    public static ApiClient getApi(){
        if (api==null){
            api=new ApiClient();
        }
        return api;

    }




    //Servicios
    //Para que pueda iniciar sesion
    public Propietario login(String mail, final String password){
        for(Propietario propietario:propietarios){
            if(propietario.getEmail().equals(mail)&&propietario.getContraseña().equals(password)){
                usuarioActual=propietario;
                return propietario;
            }
        }
        return null;
    }


    //Retorna el usuario que inició Sesión
    public Propietario obtenerUsuarioActual(){
        return usuarioActual;
    }

    //Retorna las propiedades del usuario propietario logueado
    public ArrayList<Inmueble> obtnerPropiedades(){
        ArrayList<Inmueble> temp=new ArrayList<>();
        for(Inmueble inmueble:inmuebles){
            if(inmueble.getPropietario().equals(usuarioActual)){
                temp.add(inmueble);
            }
        }
        return temp;
    }

    //Lista de inmuebles con contratos vigentes del Propietario logueado
    public ArrayList<Inmueble> obtenerPropiedadesAlquiladas(){
        ArrayList<Inmueble> temp=new ArrayList<>();
        for(Contrato contrato:contratos){
            if(contrato.getInmueble().getPropietario().equals(usuarioActual)){
                temp.add(contrato.getInmueble());
            }
        }
        return temp;
    }


//Dado un inmueble retorna el contrato activo de dicho inmueble

    public Contrato obtenerContratoVigente(Inmueble inmueble){

        for(Contrato contrato:contratos){
            if(contrato.getInmueble().equals(inmueble)){
                return contrato;
            }
        }
        return null;
    }

    //Dado un inmueble, retorna el inquilino del ultimo contrato activo de ese inmueble.
    public Inquilino obtenerInquilino(Inmueble inmueble){
        for(Contrato contrato:contratos){
            if(contrato.getInmueble().equals(inmueble)){
                return contrato.getInquilino();
            }
        }
        return null;
    }
    //Dado un Contrato, retorna los pagos de dicho contrato
    public ArrayList<Pago> obtenerPagos(Contrato contratoVer){
        ArrayList<Pago> temp=new ArrayList<>();
        for(Contrato contrato:contratos){
            if(contrato.equals(contratoVer)){
                for(Pago pago:pagos){
                    if(pago.getContrato().equals(contrato)){
                        temp.add(pago);
                    }
                }
            }
            break;
        }
        return temp;
    }
    //Actualizar Perfil
    public void actualizarPerfil(Propietario propietario){
        int posición=propietarios.indexOf(propietario);
        if(posición!=-1){
            propietarios.set(posición,propietario);
        }
    }

    //ActualizarInmueble
    public void actualizarInmueble(Inmueble inmueble){
        int posicion=inmuebles.indexOf(inmueble);
        if(posicion!=-1){
            inmuebles.set(posicion,inmueble);
        }
    }

    private void cargaDatos(){

        //Propietarios
        Propietario juan=new Propietario(1,23492012L,"Juan","Perez","juan@mail.com","123","2664553447", R.drawable.juan);
        Propietario sonia=new Propietario(2,17495869L,"Sonia","Lucero","sonia@mail.com","123","266485417",R.drawable.sonia);
        propietarios.add(juan);
        propietarios.add(sonia);

        //Inquilinos
        Inquilino mario=new Inquilino(100,25340691L,"Mario","Luna","Aiello sup.","luna@mail.com","2664253411","Lucero Roberto","2664851422");
        inquilinos.add(mario);

        //Inmuebles
        Inmueble salon=new Inmueble(501,"Colon 340","comercial","salon",2,20000,juan,true,"http://www.secsanluis.com.ar/servicios/salon1.jpg");
        Inmueble casa=new Inmueble(502,"Mitre 800","particular","casa",2,15000,juan,true,"http://www.secsanluis.com.ar/servicios/casa1.jpg");
        Inmueble otraCasa=new Inmueble(503,"Salta 325","particular","casa",3,17000,sonia,true,"http://www.secsanluis.com.ar/servicios/casa2.jpg");
        Inmueble dpto=new Inmueble(504,"Lavalle 450","particular","dpto",2,25000,sonia,true,"http://www.secsanluis.com.ar/servicios/departamento1.jpg");
        Inmueble casita=new Inmueble(505,"Belgrano 218","particular","casa",5,90000,sonia,true,"http://www.secsanluis.com.ar/servicios/casa3.jpg");

        inmuebles.add(salon);
        inmuebles.add(casa);
        inmuebles.add(otraCasa);
        inmuebles.add(dpto);
        inmuebles.add(casita);

        //Contratos
        Contrato uno=new Contrato(701, "05/01/2020","05/01/2021",17000,mario,otraCasa);
        contratos.add(uno);
        //Pagos
        pagos.add(new Pago(900,1,uno,17000,"10/02/2020"));
        pagos.add(new Pago(901,2,uno,17000,"10/03/2020"));
        pagos.add(new Pago(902,3,uno,17000,"10/04/2020"));


    }*/
}
