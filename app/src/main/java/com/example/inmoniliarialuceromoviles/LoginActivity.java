package com.example.inmoniliarialuceromoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {
    EditText mail, password;
    Button ingresar;
    TextView error;
    ImageView logo;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private String phoneNumber = "2664703132";
    LoginViewModel lvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) ;

        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1004);

        inicializarVista();
        lvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        lvm.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
        lvm.getVisible().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                error.setVisibility(integer);
            }
        });
    }

    private void inicializarVista(){
        mail = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        error = findViewById(R.id.tvError);
        logo = findViewById(R.id.ivLogo);
        logo.setImageResource(R.drawable.logoinmo2);

        ingresar = findViewById(R.id.btIngresar);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvm.inicioSesion(mail.getText().toString(), password.getText().toString());

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if(mySensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > 100){
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float move = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (move > SHAKE_THRESHOLD) {
                    llamar();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onPause() {
        super.onPause();
        //anular el registro
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //registrar nuevamente
        senSensorManager.registerListener(this,senAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void llamar(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + phoneNumber));
        startActivity(intent);
    }

}