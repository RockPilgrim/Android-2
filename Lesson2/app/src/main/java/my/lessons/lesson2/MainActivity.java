package my.lessons.lesson2;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleObject simpleObject;
    private SensorManager sensorManager;


    private TextView lightTextView;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;

    private TextView temperatureTextView;
    private Sensor temperatureSensor;
    private SensorEventListener temperatureEventListener;

    private TextView humidityTextView;
    private Sensor humiditySensor;
    private SensorEventListener humidityEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setSensorsListener();
        setSensors();
    }

    private void setSensors() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        //// HUMIDITY
        if (humiditySensor != null) {
            sensorManager.registerListener(humidityEventListener, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else
            humidityTextView.setText("Can\'t measure humidity");
        ///// LIGHT
        if (lightSensor != null) {
            sensorManager.registerListener(lightEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else
            lightTextView.setText("Can\'t measure light");
        ////// TEMPERATURE
        if (temperatureSensor != null) {
            sensorManager.registerListener(temperatureEventListener, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else
            temperatureTextView.setText("Can\'t measure temperature");
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightEventListener, lightSensor);
        sensorManager.unregisterListener(temperatureEventListener, temperatureSensor);
        sensorManager.unregisterListener(humidityEventListener, humiditySensor);
    }

    private void setSensorsListener() {
        humidityEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                System.out.println(event.values[0]);
                humidityTextView.setText("H: "+String.valueOf(event.values[0]));
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        temperatureEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                System.out.println(event.values[0]);
                temperatureTextView.setText("T: "+String.valueOf(event.values[0]));
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        lightEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                System.out.println(event.values[0]);
                lightTextView.setText("L: "+String.valueOf(event.values[0]));
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }

    private void init() {
        simpleObject = findViewById(R.id.simple_view);
        humidityTextView = findViewById(R.id.humidity_text);
        temperatureTextView = findViewById(R.id.temperature_text);
        lightTextView = findViewById(R.id.light_text);
    }
}
