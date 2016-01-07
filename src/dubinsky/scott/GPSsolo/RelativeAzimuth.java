package dubinsky.scott.GPSsolo;

/**
 * Created by Scott Dubinsky on 1/6/16.
 * http://williams.best.vwh.net/avform.htm
 *
 */

//Assume x is
public class RelativeAzimuth {
    public static double calculateAzimuth(double lat1, double lon1, double lat2, double lon2){
        double azimuthRadians =  (Math.atan2(Math.sin(lon1 - lon2) * Math.cos(lat2),
                Math.cos(lat1)* Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2))) % (2 * Math.PI);
        double azimuthDegrees = azimuthRadians * (180 / Math.PI);
        return azimuthDegrees;
    }
}
