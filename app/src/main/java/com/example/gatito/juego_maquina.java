package com.example.gatito;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class juego_maquina extends AppCompatActivity implements View.OnClickListener{

    Button btnRegresa;
    TextView tv1, tv2;
    Button[][] botones = new Button[3][3];
    Button btnResett;
    int pPoints=0,cPoints=0,empate=0;
    boolean pTurn=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_juego_maquina);
            getSupportActionBar().hide();

            tv1=findViewById(R.id.tv1);
            tv2=findViewById(R.id.tv2);
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    String  btnID="button_"+i+j;
                    int idFin=getResources().getIdentifier(btnID,"id",getPackageName());
                    botones[i][j]=findViewById(idFin);
                    botones[i][j].setOnClickListener(this);
                    botones[i][j].setText("");
                }
            }
            btnResett=findViewById(R.id.btnResett);
            btnResett.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pPoints=0;
                    cPoints=0;
                    tv1.setText("Jugador: "+pPoints);
                    tv2.setText("Máquina: "+cPoints);
                    limpiar();
                }
            });


            btnRegresa = (Button) findViewById(R.id.btnRegresa);
            btnRegresa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pagina1 = new Intent(juego_maquina.this, MainActivity.class);
                    startActivity(pagina1);
                }
            });



        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar(){
        empate=0;
        pTurn=true;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                botones[i][j].setText("");
            }
        }
    }


    @Override
    public void onClick(View view) {
        if(((Button)view).getText().toString().equals("")){
            ((Button)view).setText("O");
            empate++;
        }
        if(verificar()&&pTurn){
            pPoints++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tv1.setText("Jugador: "+pPoints);
                    limpiar();
                }
            },1000);
            tv1.setText("Jugador: Ganador");
        }else if(empate==9){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tv1.setText("Jugador: "+pPoints);
                    tv2.setText("Máquina: "+cPoints);
                }
            },1000);
            tv1.setText("Jugador: Empate!");
            tv2.setText("Máquina: Empate!");
            limpiar();
        }else{
            pTurn=!pTurn;
            cpu();
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

    public void cpu(){
        Random random = new Random();
        int i,j;
        do {
            i = random.nextInt(3);
            j = random.nextInt(3);
        } while (botones[i][j].getText() != "");
        final int fI=i,fJ=j;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                botones[fI][fJ].setText("X");
                empate++;
                if(verificar()&&!pTurn){
                    cPoints++;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tv2.setText("Máquina: "+cPoints);
                            limpiar();
                        }
                    },1000);
                    tv2.setText("Máquina: Ganador");
                }else if (empate == 9) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText("Jugador: "+pPoints);
                            tv2.setText("Máquina: "+cPoints);
                        }
                    },1000);
                    tv1.setText("Jugador: Empate!");
                    tv2.setText("Máquina: Empate!");
                    limpiar();
                }else pTurn=!pTurn;
            }
        }, 500);
    }


}