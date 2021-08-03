package com.example.pelotajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AyudaActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_actividad);
    }


    public void volver(View vista) {
        onBackPressed();
    }

    @Override
    public void onPause(){
        super.onPause();
        Intent musica = new Intent(this,MyServiceMusica.class);
        stopService(musica);
    }

   


}