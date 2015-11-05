package com.example.yo_pc.compasssurvival;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class AccesibilidadActivity extends AppCompatActivity {

    CheckBox vibracion, efectos, musica;
    Context mContext;

    public void AccesibilidadActivity(Context context){
        this.mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences spp = this.getSharedPreferences("com.example.yo_pc.compasssurvival", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesibilidad);

        vibracion = (CheckBox) findViewById(R.id.checkBox);
        efectos = (CheckBox) findViewById(R.id.checkBox2);
        musica = (CheckBox) findViewById(R.id.checkBox3);

        //sp.edit().putInt("vibracionEnabled", 1);

        if(spp.getInt("vibracionEnabled", 1) == 1) vibracion.setChecked(true);
        else vibracion.setChecked(false);

        if(spp.getInt("efectosEnabled", 1) == 1) efectos.setChecked(true);
        else efectos.setChecked(false);

        if(spp.getInt("musicaEnabled", 1) == 1) musica.setChecked(true);
        else musica.setChecked(false);




    }

    public void ejecutarTest(View v){
        Intent intent = new Intent(AccesibilidadActivity.this, TestActivity.class);
        startActivity(intent);
    }

    public void aplicarAjustes(View v){

        SharedPreferences spp = this.getSharedPreferences("com.example.yo_pc.compasssurvival", 0);



        vibracion = (CheckBox) findViewById(R.id.checkBox);
        efectos = (CheckBox) findViewById(R.id.checkBox2);
        musica = (CheckBox) findViewById(R.id.checkBox3);

        if(vibracion.isChecked()){
            spp.edit().putInt("vibracionEnabled", 1).commit();
        }
        else spp.edit().putInt("vibracionEnabled", 0).commit();

        if(efectos.isChecked()){
            spp.edit().putInt("efectosEnabled", 1).commit();
        }
        else spp.edit().putInt("efectosEnabled", 0).commit();

        if(musica.isChecked()){
            spp.edit().putInt("musicaEnabled", 1).commit();
        }
        else spp.edit().putInt("musicaEnabled", 0).commit();

        //Log.d("checkbox: ", Integer.toString(spp.getInt("vibracionEnabled", 1)));

        AlertDialog.Builder ajustesAlert = new AlertDialog.Builder(this);
        ajustesAlert.setMessage("Ajustes aplicados")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                    // Codigo a ejecutar cuando pulsemos "Ajustes..."
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                        //finish();
                    }
                })
                .create();
        ajustesAlert.show();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accesibilidad, menu);
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
