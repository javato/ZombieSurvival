package com.example.yo_pc.compasssurvival;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = this.getSharedPreferences("com.example.yo_pc.compasssurvival", 0);

        if(sp.getInt("popupInicial", 0) == 0){
            sp.edit().putInt("popupInicial", 1).commit();
            AlertDialog.Builder ajustesAlert = new AlertDialog.Builder(this);
            ajustesAlert.setMessage("¿Desea configurar los ajustes de accesibilidad antes de comenzar?")
                    .setPositiveButton("Ajustes...", new DialogInterface.OnClickListener(){
                        // Codigo a ejecutar cuando pulsemos "Ajustes..."
                        public void onClick(DialogInterface dialog, int which){
                            Intent intent = new Intent(MainActivity.this, AccesibilidadActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No, ¡dejame en paz!", new DialogInterface.OnClickListener() {
                        // Codigo a ejecutar cuando pulsemos "Continuar"
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            ajustesAlert.show();

        }
    }

    public void startGame(View v){
        Intent intent = new Intent(MainActivity.this, Juego.class);
        startActivity(intent);
    }

    public void startSettings(View v){
        Intent intent = new Intent(MainActivity.this, AccesibilidadActivity.class);
        startActivity(intent);
    }

    public void buttonRanking(View v){
        Intent intent = new Intent(MainActivity.this, RankingActivity.class);
        startActivity(intent);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

}
