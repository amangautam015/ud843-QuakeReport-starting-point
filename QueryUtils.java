
package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
     /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<Earthquake> extractEarthquakes(String y) {

        // Create an empty ArrayList that we can start adding earthquakes to
       // ArrayList<Earthquake> earthquakes = new ArrayList<>();
        List<Earthquake> earthquakes = new ArrayList<>();
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject jsonObj = new JSONObject(y);
            JSONArray features = jsonObj.getJSONArray("features");
            for(int i = 0;i<features.length();i++){
                JSONObject obj0 = features.getJSONObject(i);
                JSONObject obj1 = obj0.getJSONObject("properties");
                String mag = obj1.getString("mag");
                String place = obj1.getString("place");
                long time =(obj1.getLong("time"));
                String urrl = obj1.getString("url");



                earthquakes.add(new Earthquake(mag,place,time,urrl));


            }
            //  Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}