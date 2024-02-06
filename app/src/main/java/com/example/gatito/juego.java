package com.example.gatito;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class juego extends AppCompatActivity  implements View.OnClickListener{

    Button btnregresar22;
    TextView tx1, tx2;
    Button[][] botones = new Button[3][3];
    Button btnReset;
    int j1Puntos=0, j2Puntos=0, empate=0;
    boolean jTurno=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        tx1=findViewById(R.id.tx1);
        tx2=findViewById(R.id.tx2);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String  btnID="btn"+i+j;
                int idFin=getResources().getIdentifier(btnID,"id",getPackageName());
                botones[i][j]=findViewById(idFin);
                botones[i][j].setOnClickListener(this);
                botones[i][j].setText("");
            }
        }

        btnregresar22=(Button) findViewById(R.id.btnregresar22);

        btnregresar22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pagina1 = new Intent(juego.this, MainActivity.class);
                startActivity(pagina1);
            }
        });

        btnReset=findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j1Puntos=0;
                j2Puntos=0;
                tx1.setText("Jugador 1: "+j1Puntos);
                tx2.setText("Jugador 2: "+j2Puntos);
                limpiar();
            }
        });
    }

    public void limpiar(){
        empate=0;
        jTurno=true;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                botones[i][j].setText("");
            }
        }
    }


    public void onClick(View view) {
        if(((Button)view).getText().toString().equals("") && jTurno == true){
            ((Button)view).setText("O");
            empate++;
        }
        if(((Button)view).getText().toString().equals("") && jTurno == false){
            ((Button)view).setText("X");
            empate++;
        }
        if(verificar()&&jTurno){
            j1Puntos++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tx1.setText("Jugador 1: "+j1Puntos);
                    limpiar();
                }
            },1000);
            tx1.setText("Jugador 1: Ganador");
        }else if(empate==9){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tx1.setText("Jugador 1: "+j1Puntos);
                    tx2.setText("Jugador 2: "+j2Puntos);
                }
            },1000);
            tx1.setText("Jugador 1: Empate!");
            tx2.setText("Jugador 2: Empate!");
            limpiar();
        }else if (jTurno=!jTurno){
            if(verificar()&&jTurno){
                j2Puntos++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tx2.setText("Jugador 2: "+j2Puntos);
                        limpiar();
                    }
                },1000);
                tx2.setText("Jugador 2: Ganador");
            }else if(empate==9){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tx1.setText("Jugador 1: "+j1Puntos);
                        tx2.setText("Jugador 2: "+j2Puntos);
                    }
                },1000);
                tx1.setText("Jugador 1: Empate!");
                tx2.setText("Jugador 2: Empate!");
                limpiar();
            }

        }
    }

    public boolean verificar(){
        return botones[0][0].getText() == botones[0][1].getText() && botones[0][0].getText() == botones[0][2].getText() && botones[0][0].getText() != ""
                || botones[1][0].getText() == botones[1][1].getText() && botones[1][0].getText() == botones[1][2].getText() && botones[1][0].getText() != ""
                || botones[2][0].getText() == botones[2][1].getText() && botones[2][0].getText() == botones[2][2].getText() && botones[2][0].getText() != ""
                || botones[0][0].getText() == botones[1][0].getText() && botones[0][0].getText() == botones[2][0].getText() && botones[0][0].getText() != ""
                || botones[0][1].getText() == botones[1][1].getText() && botones[0][1].getText() == botones[2][1].getText() && botones[0][1].getText() != ""
                || botones[0][2].getText() == botones[1][2].getText() && botones[0][2].getText() == botones[2][2].getText() && botones[0][2].getText() != ""
                || botones[0][0].getText() == botones[1][1].getText() && botones[0][0].getText() == botones[2][2].getText() && botones[0][0].getText() != ""
                || botones[0][2].getText() == botones[1][1].getText() && botones[0][2].getText() == botones[2][0].getText() && botones[0][2].getText() != "";
    }
}
