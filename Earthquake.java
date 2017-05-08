package com.example.android.quakereport;

/**
 * Created by Dell on 07-04-2017.
 */

public class Earthquake {



        /** Default translation for the word */
        private String intensity ;


        private String location;
        private long time;
        private String u;


        /** Image resource ID for the word */


        public Earthquake(String cintensity , String clocation , long ctime , String cu) {

            intensity = cintensity;
            location = clocation;
            time = ctime;
            u = cu;
        }

    public String getIntensity() { return intensity; }
    public String getUrl() {return u;}
    public long getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }
}

