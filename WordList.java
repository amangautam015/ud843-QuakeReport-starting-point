package com.example.android.quakereport;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.ArrayList;

import static android.R.attr.format;
import static android.R.attr.format24Hour;
import static com.example.android.quakereport.R.id.intensity;
import static java.lang.Float.parseFloat;
import android.graphics.drawable.GradientDrawable;
/**
 * Created by Dell on 07-04-2017.
 */

public class WordList extends ArrayAdapter<Earthquake> {

    public WordList(Activity context, ArrayList<Earthquake>earthquakes ) {
        super(context ,  0 , earthquakes);
    }

   @Override
     public View getView(int position , View  currentView , ViewGroup parent) {
       View listItemView = currentView ;
       if(listItemView == null ) {

           listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_rss,parent,false);

       }
       Earthquake currentwordfile = getItem(position);
       String s = "magnitude" +  (int)Math.floor(Float.parseFloat(currentwordfile.getIntensity()));
int magnitude1Color;
      switch (s){

          case "magnitude9":  magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude9); break;
          case "magnitude8":  magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude8); break;
          case "magnitude7":  magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude7); break;
          case "magnitude6":  magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude6); break;
          case "magnitude5":  magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude5); break;
          case "magnitude4":  magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude4);break;
          case "magnitude3":  magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude3); break;
          case "magnitude2":  magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude2); break;
          case "magnitude1":  magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude1); break;
                     default: magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude10plus);

      }
       if (currentwordfile.getLocation().contains("of ")) {
           String[] parts = currentwordfile.getLocation().split("of ");
           TextView location = (TextView)listItemView.findViewById(R.id.location); location.setText(parts[0] + " of ");
           TextView location1 = (TextView)listItemView.findViewById(R.id.location1);location1.setText(parts[1]);
       } else {
           TextView location = (TextView)listItemView.findViewById(R.id.location); location.setText("Near the");
           TextView location1 = (TextView)listItemView.findViewById(R.id.location1);location1.setText(currentwordfile.getLocation());
       }






       TextView intensity = (TextView) listItemView.findViewById(R.id.intensity);
       intensity.setText((currentwordfile.getIntensity()));
       //TextView time = (TextView) listItemView.findViewById(R.id.time);time.setText(currentwordfile.getTime());

       // Set the proper background color on the magnitude circle.
       // Fetch the background from the TextView, which is a GradientDrawable.
       GradientDrawable magnitudeCircle = (GradientDrawable) intensity.getBackground();

       // Get the appropriate background color based on the current earthquake magnitude


       // Set the color on the magnitude circle
       magnitudeCircle.setColor(magnitude1Color);

       Date dateobj = new Date(currentwordfile.getTime());
       TextView date = (TextView) listItemView.findViewById(R.id.date);
       date.setText(formatDate(dateobj));
       TextView time = (TextView) listItemView.findViewById(R.id.time);

       time.setText(formatTime(dateobj));

       return listItemView;
   }

    private String formatTime(Date dateobj) {
        SimpleDateFormat d = new SimpleDateFormat("LLL dd, yyyy");
        return(d.format(dateobj));
    }

    private String formatDate(Date dateobj) {
        SimpleDateFormat t = new SimpleDateFormat("h:mm a");
        return(t.format(dateobj));
    }


}
