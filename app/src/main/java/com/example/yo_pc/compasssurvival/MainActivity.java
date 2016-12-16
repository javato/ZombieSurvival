package com.example.yo_pc.compasssurvival;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.button;
import static com.example.yo_pc.compasssurvival.R.id.imageView;

public class MainActivity extends AppCompatActivity {

    public static Activity fa;
    public String welcome;
    public boolean emailVerified;

    private Button mSelectImage;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 2;
    private static final int VOICE_INTENT = 1001;


    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;

    String nombreUsuario;
    TextView tvNombreUsuario;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    ImageView ivImageFromUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fa = this;


        super.onCreate(savedInstanceState);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ivImageFromUrl = (ImageView)findViewById(R.id.iv_image_from_url);


        //StorageReference urlPicture = mStorage.child("photos/" + auth.getCurrentUser().getUid().toString());

        //String cadena2 = "photos/" + auth.getCurrentUser().getUid().toString();

        //String cadena = auth.getCurrentUser().getUid().toString();
        //Log.d("URL ENVIADA: ", urlPicture.getDownloadUrl().toString());
        //Picasso.with(getApplication()).load(urlPicture.getDownloadUrl().toString()).resize(350,350).into(ivImageFromUrl);
        //Picasso.with(getApplication()).load("https://image.freepik.com/iconos-gratis/instagram-logo_318-84939.jpg").into(ivImageFromUrl);



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

                if(welcome != null){
                    tvNombreUsuario.setText(welcome.toString());
                }
                else{
                    welcome = " ";
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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



        mStorage = FirebaseStorage.getInstance().getReference();

        StorageReference urlPicture = mStorage.child("photos/" + auth.getCurrentUser().getUid().toString());
        Log.d("URL ENVIADA: ", urlPicture.getDownloadUrl().toString());

        urlPicture.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri downloadUrl)
            {
                Log.d("URL DE DENTRO: ", downloadUrl.toString());
                //Picasso.with(getApplication()).load(downloadUrl.toString()).resize(350,350).into(ivImageFromUrl);
                //BUENO Picasso.with(getApplication()).load(downloadUrl.toString()).resizeDimen(25, 25).into(ivImageFromUrl);
                Picasso.with(getApplication()).load(downloadUrl.toString()).memoryPolicy(MemoryPolicy.NO_STORE).resize(500, 500).into(ivImageFromUrl);
            }
        });


        mSelectImage = (Button) findViewById(R.id.selectImage);
        mSelectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });



        //Picasso.with(getApplication()).load("https://image.freepik.com/iconos-gratis/instagram-logo_318-84939.jpg").resizeDimen(25, 25).into(ivImageFromUrl);

    }


    public void startGame(View v){

        SharedPreferences sp = this.getSharedPreferences("com.example.yo_pc.compasssurvival", 0);
        //FirebaseAuth auth1;
        //auth1 = FirebaseAuth.getInstance();

        if(!welcome.toString().equals("Name doesn't establisheddd")){
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

    public void buttonPicture(View v){
        //Intent intent = new Intent(MainActivity.this, RankingActivity.class);
        //startActivity(intent);


    }

    public void buttonNombreUsuario(View v){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say your name");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);


        startActivityForResult(intent, VOICE_INTENT);
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

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();
            Log.d("NOMBRE ARCHIVO: ", uri.toString());
            Log.d("NOMBRE: ", uri.getLastPathSegment().toString());
            final StorageReference filepath = mStorage.child("photos").child(auth.getCurrentUser().getUid().toString());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this, "Upload done", Toast.LENGTH_LONG).show();
                    Log.d("URL: ", filepath.getDownloadUrl().toString());
                    //Picasso.with(getApplication()).load("https://firebasestorage.googleapis.com/v0/b/mi-fabuloso-proyecto-9221e.appspot.com/o/photos%2F1912155.jpg?alt=media&token=1f7c6084-a2b3-4493-99b7-b42f6b6d310a").resize(350,350).into(ivImageFromUrl);

                }
            });

            mStorage = FirebaseStorage.getInstance().getReference();


            StorageReference urlPicture = mStorage.child("photos/" + auth.getCurrentUser().getUid().toString());
            Log.d("URL ENVIADA: ", urlPicture.getDownloadUrl().toString());

            urlPicture.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
            {
                @Override
                public void onSuccess(Uri downloadUrl)
                {
                    Log.d("URL DE DENTRO: ", downloadUrl.toString());
                    //Picasso.with(getApplication()).load(downloadUrl.toString()).resize(500, 500).into(ivImageFromUrl);
                    Picasso.with(getApplication()).load(downloadUrl.toString()).memoryPolicy(MemoryPolicy.NO_STORE).resize(500, 500).into(ivImageFromUrl);
                }
            });
        }

        if (requestCode == VOICE_INTENT && resultCode == RESULT_OK) {
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
