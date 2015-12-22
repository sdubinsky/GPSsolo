package dubinsky.scott.GPSsolo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Scott Dubinsky on 1/1/14.
 * http://www.codingforandroid.com/2011/01/using-orientation-sensors-simple.html
 * http://www.helloandroid.com/tutorials/using-android-phones-sensors
 * http://www.journal.deviantdev.com/android-compass-azimuth-calculating/
 * http://stackoverflow.com/questions/4819626/android-phone-orientation-overview-including-compass
 * http://developer.android.com/reference/android/hardware/SensorManager.html#getOrientation(float[], float[])
 *
 * Given the sensor info from the accelerometer and magnetic field sensors, the phone can figure out what its
 * azimuth relative to north is.  I need to draw a line from phone to arbitrary point and find that azimuth instead.
 *
 * Also need to account for screen rotation(unless I lock the screen? That seems easiest).
 */
public class DirectionActivity extends Activity implements SensorEventListener{
    private SensorManager sensorManager;
    private final String TAG = "DirectionActivity";
    float[] gData = new float[3];
    float[] mData = new float[3];
    float[] rMat = new float[9];
	float[] iMat = new float[9];
    float[] orientation = new float[3];
    private int mAzimuth = 0;

    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direction);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    public void onSensorChanged(SensorEvent event){
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.i(TAG, "accelerometer changed");
            gData = event.values.clone();
        }
        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            Log.i(TAG, "magnetic field sensor changed");
            mData = event.values.clone();
        }else{
            return;
        }
        //This will calculate the azimuth between front and north.  How to set it to calculate the azimuth
        //between front and arbitrary direction?
        if ( SensorManager.getRotationMatrix( rMat, iMat, gData, mData ) ) {
            Log.i(TAG, "calculating new azimuth");
            mAzimuth = (int) ( Math.toDegrees( SensorManager.getOrientation( rMat, orientation )[0] ) + 360 ) % 360;
            TextView azimuthView = (TextView) findViewById(R.id.azimuthView);
            //Works.  Is unreliable.
            azimuthView.setText(Float.toString(mAzimuth));
        }
    }
}