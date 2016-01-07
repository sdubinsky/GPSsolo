package dubinsky.scott.GPSsolo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputActivity extends Activity {
    public final static String LAT = "dubinsky.scott.latitude";
    public final static String LON = "dubinsky.scott.longitude";
    private final double baseLat = 0.0;
    private final double baseLon = 0.0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View view){
        Context context = getApplicationContext();
        String text = "You clicked the button!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        EditText lat = (EditText) findViewById(R.id.latitude);
        EditText lon = (EditText) findViewById(R.id.longitude);
        double latitude = Double.parseDouble(lat.getText().toString());
        double longitude = Double.parseDouble(lon.getText().toString());

        double relativeAzimuth = RelativeAzimuth.calculateAzimuth(baseLat, baseLon, latitude, longitude);

        TextView azimuth = (TextView) findViewById(R.id.calculatedAzimuth);
        azimuth.setText(Double.toString(relativeAzimuth));
//        Intent displayBearing = new Intent(this, DirectionActivity.class);
//        displayBearing.putExtra(LAT, latitude);
//        displayBearing.putExtra(LON, longitude);
//
//        startActivity(displayBearing);
    }
}