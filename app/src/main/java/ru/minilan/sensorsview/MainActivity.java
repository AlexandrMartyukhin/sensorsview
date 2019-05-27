package ru.minilan.sensorsview;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView textViewTemp;
    private TextView textViewHumidity;
    private TextView textViewLight;

    private TextView textViewSensorsList;
    private List<Sensor> sensorList;
    private Sensor sensorTemp;
    private Sensor sensorHumidity;
    private Sensor sensorLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTemp = findViewById(R.id.textViewTemp);
        textViewHumidity = findViewById(R.id.textViewHumidity);
        textViewSensorsList = findViewById(R.id.textViewSensorsList);
        textViewLight = findViewById(R.id.textViewLight);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (sensorTemp == null) {
            Toast.makeText(this, "There is no temperature sensor!", Toast.LENGTH_LONG).show();
        }
        if (sensorHumidity == null) {
            Toast.makeText(this,"There is no Humidity sensor",Toast.LENGTH_LONG).show();
        }
        if (sensorLight == null) {
            Toast.makeText(this,"There is no light sensor",Toast.LENGTH_LONG).show();
        }
        sensorManager.registerListener(listenerLight, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listenerTemp, sensorTemp, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listenerHumidity, sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);

        showSensors();
    }

    private void showSensors() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------------------------------------\n").append("Sensor List:\n");
        int i = 1;
        for (Sensor sensor : sensorList) {
            sb.append("- " + i + " -------------------------------------------------------------\n");
            sb.append("Sensor name: ").append(sensor.getName().toUpperCase()).append("\n");
            sb.append("Sensor type: ").append(sensor.getType()).append("\n");
            sb.append("Sensor vendor: ").append(sensor.getVendor()).append("\n");
            sb.append("Sensor version: ").append(sensor.getVersion()).append("\n");
            sb.append("Sensor range: ").append(sensor.getMaximumRange()).append("\n");
            sb.append("Sensor resolution: ").append(sensor.getResolution()).append("\n");
            i++;
        }
        textViewSensorsList.setText(sb);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listenerLight, sensorLight);
        sensorManager.unregisterListener(listenerTemp, sensorTemp);
        sensorManager.unregisterListener(listenerHumidity, sensorHumidity);

    }

    private void showLightSensors(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Light sensor value = ").append(event.values[0]);
        textViewLight.setText(stringBuilder);
    }

    SensorEventListener listenerLight = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            showLightSensors(event);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void showTempSensors(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Temp sensor value = ").append(event.values[0]);
        textViewTemp.setText(stringBuilder);
    }

    SensorEventListener listenerTemp = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            showTempSensors(event);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void showHumiditySensors(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Humidity sensor value = ").append(event.values[0]);
        textViewHumidity.setText(stringBuilder);
    }

    SensorEventListener listenerHumidity = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            showHumiditySensors(event);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
