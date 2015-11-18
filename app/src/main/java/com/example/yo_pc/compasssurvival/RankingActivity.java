package com.example.yo_pc.compasssurvival;

import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RankingActivity extends AppCompatActivity {
    /*String nombre;
    int puntuacion;
    String localizacion;
    ArrayList<RankingActivity> p = new ArrayList<RankingActivity>();
    ArrayAdapter<String> mRankingAdapter;
    ArrayList<String> ranking = new ArrayList<String>();
    Context mContext;


    public void RankingActivity(){
        this.mContext = super.getBaseContext();
    }

    RankingActivity(int puntuacion, String nombre){
        this.puntuacion = puntuacion;
        this.nombre = nombre;
        localizacion = "Albacete";
    }
    */

    String nombre;
    int puntuacion;
    String localizacion;

    List<String> ranking;
    static ArrayAdapter<String> mRankingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);


        String data[] = {
                "Today - Sunny - 88/63",
                "Tomorrow - Foggy - 70/46",
                "Weds - Cloudy - 72/63",
                "Thurs - Rainy - 64/51",
                "Fri - Foggy - 70/46",
                "Sat - Sunny - 76/68",
                "Sunday - Sunny - 70/54",
                "Today - Sunny - 88/63",
                "Tomorrow - Foggy - 70/46",
                "Weds - Cloudy - 72/63",
                "Thurs - Rainy - 64/51",
                "Fri - Foggy - 70/46",
                "Sat - Sunny - 76/68",
                "Sunday - Sunny - 70/54",
                "Today - Sunny - 88/63",
                "Tomorrow - Foggy - 70/46",
                "Weds - Cloudy - 72/63",
                "Thurs - Rainy - 64/51",
                "Fri - Foggy - 70/46",
                "Sat - Sunny - 76/68",
                "Sunday - Sunny - 70/54",
                "Today - Sunny - 88/63",
                "Tomorrow - Foggy - 70/46",
                "Weds - Cloudy - 72/63",
                "Thurs - Rainy - 64/51",
                "Fri - Foggy - 70/46",
                "Sat - Sunny - 76/68",
                "Sunday - Sunny - 70/54"
        };


        ranking = new ArrayList<String>(Arrays.asList(data));


        //ArrayList<String> ranking = new ArrayList<String>();

        mRankingAdapter =
                new ArrayAdapter<String>(
                        this , // The current context (this activity)
                        R.layout.list_item_ranking, // The name of the layout ID.
                        R.id.list_item_ranking_textview, // The ID of the textview to populate.
                        ranking);
        ListView listView = (ListView) findViewById(R.id.listview_ranking);
        listView.setAdapter(mRankingAdapter);


    }

    public void introducirRanking(int puntuacion, String nombre){
        for (int i = 0; i < ranking.size(); i++) {

        }
    }

    /*
    public void putScore(){
        p.add(this);
    }
    public void llenarRanking(){

        for (int i = 0; i <ranking.size() ; i++) {
            if(p.get(i).puntuacion<this.puntuacion)
            ranking.add(i, "Puesto: " + i+1 + " | " + this.nombre + " | " + this.puntuacion + " | " + this.localizacion);
        }

    }*/
}
