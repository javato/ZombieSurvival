package com.example.yo_pc.compasssurvival;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AccesibilidadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesibilidad);
    }

    public void ejecutarTest(View v){
        Intent intent = new Intent(AccesibilidadActivity.this, TestActivity.class);
        startActivity(intent);
    }

    public void aplicarAjustes(View v){
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
