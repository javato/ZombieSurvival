package com.example.yo_pc.compasssurvival;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Activity fa;
    public String welcome;
    public boolean emailVerified;

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;

    String nombreUsuario;
    TextView tvNombreUsuario;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fa = this;

        super.onCreate(savedInstanceState);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //get firebase auth instance
        //auth = FirebaseAuth.getInstance();

        //get current user
        //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        tvNombreUsuario = (TextView) findViewById(R.id.tvNombreUsuario);

        SharedPreferences sp = this.getSharedPreferences("com.example.yo_pc.compasssurvival", 0);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                /*Log.d("ANTES DEL IF: ", Boolean.toString(user.isEmailVerified()));
                if(user.isEmailVerified() == false){
                    emailVerified = false;
                    Log.d("PRIMER IF: ", Boolean.toString(emailVerified));
                }
                else{
                    emailVerified = true;
                    Log.d("SEGUNDO IF: ", Boolean.toString(emailVerified));
                }*/

                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };





        //modify data of the logged user
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mRootRef.child("users").child(user.getUid());

        ref.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                welcome = dataSnapshot.getValue(String.class);
                //spp.edit().putInt("worldRecord", value).commit();
                //Log.d("NAMEEEEEEEEE: ", welcome);
                tvNombreUsuario.setText(welcome.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Log.d("LLEGAAAAAAAAAAAAAA ", welcome);


        //tvNombreUsuario.setText("pepito");
        /*if(!sp.getString("nombreusuario", "---").equals("---")){
            tvNombreUsuario.setText("Bienvenido " + sp.getString("nombreusuario", "---"));
        }
        else{
            tvNombreUsuario.setText("Nombre de usuario no establecido");
        }*/

        /*if(sp.getInt("popupInicial", 0) == 0){
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

        }*/

    }

    public void startGame(View v){

        SharedPreferences sp = this.getSharedPreferences("com.example.yo_pc.compasssurvival", 0);
        //FirebaseAuth auth1;
        //auth1 = FirebaseAuth.getInstance();
        if(auth.getCurrentUser().isEmailVerified() == false){
            showToastMessage("Email not confirmed, please, go to profile and make it!");
        }
        else if(!welcome.toString().equals("Name doesn't establisheddd")){
            Intent intent = new Intent(MainActivity.this, PopUpMapas.class);
            startActivity(intent);
        }
        else{
            showToastMessage("Wait, I need your name!");
        }

    }

    public void startSettings(View v){
        Intent intent = new Intent(MainActivity.this, AccesibilidadActivity.class);
        startActivity(intent);
    }

    public void buttonRanking(View v){
        Intent intent = new Intent(MainActivity.this, RankingActivity.class);
        startActivity(intent);
    }

    public void buttonNombreUsuario(View v){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say your name");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);


        startActivityForResult(intent, 1001);
    }

    public void buttonProfile(View v){
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);


        /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }

        };

        if (user != null) {
            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }*/

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SharedPreferences sp = this.getSharedPreferences("com.example.yo_pc.compasssurvival", 0);
        if (resultCode == RESULT_OK) {
            tvNombreUsuario = (TextView) findViewById(R.id.tvNombreUsuario);
            ArrayList<String> textMatchlist = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String nameUser = textMatchlist.get(0).toString();

            welcome = textMatchlist.get(0).toString();


            //get firebase auth instance
            auth = FirebaseAuth.getInstance();

            //get current user
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            //modify name of the logged user
            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference ref = mRootRef.child("users").child(user.getUid());
            ref.child("name").setValue(nameUser);

            //sp.edit().putString("nombreusuario", nameUser).commit();
            //tvNombreUsuario.setText("welcome " + nameUser);


            //Log.d("matchlist: ", textMatchlist.get(0).toString());

            //super.onActivityResult(requestCode, resultCode, data);
        }

    }

    void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
