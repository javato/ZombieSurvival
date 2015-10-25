package com.example.yo_pc.compasssurvival;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(MainActivity.this, TestActivity.class);
        //startActivity(intent);
        AlertDialog.Builder ajustesAlert = new AlertDialog.Builder(this);
        ajustesAlert.setMessage("¿Desea configurar los ajustes de accesibilidad antes de comenzar?")
                .setPositiveButton("Ajustes...", new DialogInterface.OnClickListener(){
                    // Codigo a ejecutar cuando pulsemos "Ajustes..."
                    public void onClick(DialogInterface dialog, int which){
                        //dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, AccesibilidadActivity.class);
                        startActivity(intent);
                        //finish();
                    }
                })
                .setNegativeButton("No, ¡dejame en paz!", new DialogInterface.OnClickListener() {
                    // Codigo a ejecutar cuando pulsemos "Continuar"
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //Intent intent = new Intent(TestActivity.this, MainActivity.class);
                        //startActivity(intent);
                        //finish();
                    }
                })
                .create();
        ajustesAlert.show();
    }

    public void startGame(View v){
        Intent intent = new Intent(MainActivity.this, JuegoActivity.class);
        startActivity(intent);
    }

    public void startSettings(View v){
        Intent intent = new Intent(MainActivity.this, AccesibilidadActivity.class);
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
