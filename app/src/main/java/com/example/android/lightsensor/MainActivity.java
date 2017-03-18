package com.example.android.lightsensor;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView tViewLight = (TextView) findViewById(R.id.ligtTextView);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.viewContainer);

        float lux = event.values[0];


        Float lumens = lux;

        if(lumens != null){
            tViewLight.setText(String.valueOf(lumens));
        } else {
            tViewLight.setText("Ocurrió un un error con el sensor de luz");
        }

        /**
         * hacemos caber el valor de luminosidad en el círculo
         * manteniendo el tamaño de texto acorde
         */
        if(lumens > 9999.9) {
            tViewLight.setTextSize(20);
        } else if(lumens > 99.9){
            tViewLight.setTextSize(30);
        } else {
            tViewLight.setTextSize(48);
        }

        int lightValue = Math.round(lumens);

        /**
         * Ajustamos el valor de color de la pantalla según luminosidad
         */
        if (lightValue > 85000) {
            layout.setBackgroundColor(getResources().getColor(R.color.bg14));
        } else if (lightValue > 70000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg13));
        } else if (lightValue > 50000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg12));
        } else if (lightValue > 30000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg11));
        } else if (lightValue > 20000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg10));
        } else if (lightValue > 15000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg9));
        } else if (lightValue > 10000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg8));
        } else if (lightValue > 7000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg7));
        } else if (lightValue > 5000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg6));
        } else if (lightValue > 3000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg5));
        } else if (lightValue > 2000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg4));
        } else if (lightValue > 1000){
            layout.setBackgroundColor(getResources().getColor(R.color.bg3));
        } else if (lightValue > 500){
            layout.setBackgroundColor(getResources().getColor(R.color.bg2));
        } else if (lightValue > 200){
            layout.setBackgroundColor(getResources().getColor(R.color.bg1));
        } else {
            layout.setBackgroundColor(getResources().getColor(R.color.bg0));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
