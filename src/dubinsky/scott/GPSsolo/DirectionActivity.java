package dubinsky.scott.GPSsolo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Scott Dubinsky on 1/1/14.
 */
public class DirectionActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direction);
        TextView bearing_view = (TextView) findViewById(R.id.bearing);

        Intent loc = getIntent();
        //TODO: find a reasonable default value.
        double latitude = loc.getDoubleExtra(InputActivity.LAT, 0);
        double longitude = loc.getDoubleExtra(InputActivity.LON, 0);

        double bearing = direction(latitude, longitude);
        bearing_view.setText(Double.toString(bearing));
    }

    /**
     @param latitude latitude of the location to point towards.
     @param longitude longitude of the location to point towards.

     @return the bearing from here to the specified location.
     **/

    public double direction(double latitude, double longitude){
        Location other = new Location("latlong");
        other.setLatitude(latitude);
        other.setLongitude(longitude);
        /*
        Next two lines are for when I get on a real device.  No guarantee they'll work right now.
        Also need to check network for better location/use GPS to get real current location.
        */
//        LocationManager manager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
//        manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        double home_lat = 40.437507;
        double home_long = -79.912736;
        Location home = new Location("home");
        home.setLongitude(home_long);
        home.setLatitude(home_lat);

        if (home.equals(other)){
            return -1;
        }
        float bearing = home.bearingTo(other);

        return bearing;
    }

}