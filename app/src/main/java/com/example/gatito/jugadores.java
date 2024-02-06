package com.example.gatito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class jugadores extends AppCompatActivity {

    Button jugador, jugadores, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_jugadores);
            getSupportActionBar().hide();


            // Boton para ya no jala :c
            jugador = (Button) findViewById(R.id.jugador);
            jugador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //try {
                        Intent intent = new Intent(v.getContext(), nivel_juego.class);
                        startActivityForResult(intent, 0);
                    //}catch (Exception e){


                    //}
                }
            });

            // Boton para sar a los niveles que tiene el juego
            jugadores = (Button) findViewById(R.id.jugadores);
            jugadores.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(v.getContext(), juego.class);
                    startActivityForResult(intent1, 0);
                }
            });

            salir = (Button) findViewById(R.id.salir);

            salir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pagina1 = new Intent(jugadores.this, MainActivity.class);
                    startActivity(pagina1);
                }
            });
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();

        }
    }
}

