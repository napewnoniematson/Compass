package com.napewnoniematson.compass.logic;

import com.napewnoniematson.compass.model.geo.GeoPoint;

public class DirectionFinder {

    private static final String TAG = DirectionFinder.class.getSimpleName();

    private static float latitude, longitude, tga, angle, extraAngle;

    public static float findDirectionAngle(GeoPoint current, GeoPoint destination) {
        latitude = destination.getLatitude() - current.getLatitude();
        longitude = destination.getLongitude() - current.getLongitude();
        extraAngle = getExtraAngle(longitude);
        angle = getAngle(latitude, longitude);
        angle = extraAngle - angle;
        return angle;
    }

    private static float getExtraAngle(float longitudeDiff) {
        return (longitudeDiff >= 0) ? 90f : 270f;
    }

    private static float getAngle(float latitudeDiff, float longitudeDiff) {
        if (longitudeDiff == 0)
            return (latitudeDiff >= 0) ? 90f : -90f;
        tga = latitudeDiff / longitudeDiff;
        angle = (float) Math.atan(tga);
        angle = (float) Math.toDegrees(angle);
        return angle;
    }
}
