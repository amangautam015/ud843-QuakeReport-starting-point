/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String SAMPLE_JSON_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        getinfo task = new getinfo();
        task.execute(SAMPLE_JSON_RESPONSE); }
        // Create a fake list of earthquake locations.

       /* earthquakes.add(new Earthquake("0","San Francisco","0"));
        earthquakes.add(new Earthquake("0","London","0"));
        earthquakes.add(new Earthquake("0","Tokyo","0"));
        earthquakes.add(new Earthquake("0","Mexico City","0"));
        earthquakes.add(new Earthquake("0","Moscow","0"));
        earthquakes.add(new Earthquake("0","Rio de Janeiro","0"));
        earthquakes.add(new Earthquake("0","Paris","0")); */

        private void updateUi(final ArrayList<Earthquake> earthquakes) {
            final   WordList adapter = new WordList(this , earthquakes );
            // Find a reference to the {@link ListView} in the layout
            final   ListView earthquakeListView = (ListView) findViewById(R.id.list);
            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);


            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Earthquake e = earthquakes.get(position);
                    String u = e.getU();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(u));
                    startActivity(i);
                }
            });
        }


        // Create a new {@link ArrayAdapter} of earthquakes


    public class getinfo extends AsyncTask<String,Void,ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            } else {
                ArrayList<Earthquake> earthquake = Utils.fetchEarthquakeData(urls[0]);
                return earthquake;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            if(earthquakes==null){
                return;
            }
            updateUi(earthquakes);
           // super.onPostExecute(earthquakes);
        }
    }


}
