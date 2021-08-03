package com.example.pelotajava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void ayuda (View vista){
        Intent intencion = new Intent(this, AyudaActividad.class);
        startActivity(intencion);
    }


    public void dificultad(View vista){
        String dific = (String) ((Button) vista).getText();
        int dificultad =1;

        if(dific.equals(getString(R.string.medio))){
            dificultad = 2;
        }
        if(dific.equals(getString(R.string.dificil)))
            dificultad=3;

        Intent in = new Intent(this, Gestion.class);
        in.putExtra("DIFICULTAD",dificultad);

        Intent musica = new Intent(this,MyServiceMusica.class);
        stopService(musica);
        startActivityForResult(in,1);
    }


    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int peticion, int codigo, Intent puntuacion){
        if(peticion!=1 || codigo!=RESULT_OK) return;
        int resultado=puntuacion.getIntExtra("PUNTUACION",0);
        if(resultado>record){
            record=resultado;
            TextView caja = (TextView) findViewById(R.id.record);
            caja.setText("Record: "+record);
            guardarecord();
        }else{

            Toast miToast = Toast.makeText(this,getText(R.string.Mensaje_emergente)+" "+resultado, Toast.LENGTH_LONG);
            miToast.show();
        }
        TextView caja = (TextView) findViewById(R.id.record);
        caja.setText("Record: "+resultado);
    }

@Override
public void onStart(){
        super.onStart();
    Intent musica = new Intent(this,MyServiceMusica.class);
    startService(musica);
}


    @Override
    public void onResume(){
        super.onResume();


        leerecord();
    }

    private void guardarecord(){
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mi_editor = datos.edit();
        mi_editor.putInt("RECORD",record);
        mi_editor.apply();
    }


    private void leerecord(){
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        record = datos.getInt("RECORD",0);
        TextView caja = (TextView) findViewById(R.id.record);
        caja.setText("Record: "+record);
    }



    @Override
    public void onDestroy(){
        Intent musica = new Intent(this,MyServiceMusica.class);
        stopService(musica);
        super.onDestroy();

    }


}