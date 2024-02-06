package com.example.gatito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnjugar, btnivel, btnesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Boton de inicio de juego
        btnjugar=(Button) findViewById(R.id.btnempezar);
        btnjugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pagina2 = new Intent(MainActivity.this, jugadores.class);
                startActivity(pagina2);
            }
        });

        // Boton para sar a los niveles que tiene el juego
        btnivel=(Button) findViewById(R.id.btniveles);
        btnivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), nivel_juego.class);
                        startActivityForResult(intent, 0);
            }
         });

        // Boton para pasar a las estadisticas
        btnesta=(Button) findViewById(R.id.btnestadistica);
        btnesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), estaditicas.class);
                startActivityForResult(intent, 0);
            }
        });
        }
    }

