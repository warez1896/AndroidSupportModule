package com.ediwow.supportmodule.backend;

import android.location.Location;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Haversine {
    //in kilometers, but rounded to nearest thousandths to translate to meters
    public static BigDecimal calculateCoordinateDistance(Location a, Location b) {
        final int earthRadius = 6371;
        BigDecimal distance = BigDecimal.ZERO;
        distance.setScale(3, RoundingMode.HALF_UP);
        double latDistance = Math.toRadians(b.getLatitude() - a.getLatitude());
        double lngDistance = Math.toRadians(b.getLongitude() - a.getLongitude());
        double calcA = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) + (Math.cos(Math.toRadians(a.getLatitude())) * Math.cos(Math.toRadians(b.getLatitude()))) + (Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2));
        double calcC = 2 * Math.atan2(Math.sqrt(calcA), Math.sqrt(1 - calcA));
        distance = new BigDecimal(earthRadius * calcC);
        return distance.abs();
    }
}
