package com.example.dardos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DatosJugador extends AppCompatActivity {

    private ImageView im15;
    private ImageView im16;
    private ImageView im17;
    private ImageView im18;
    private ImageView im19;
    private ImageView im20;
    private ImageView im25;
    private int puntuacionAct;
    ImageView[] arrayImagenes;
    TextView puntos;
    Jugador jugadorActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_jugador);

        getSupportActionBar().hide();

        im15 = findViewById(R.id.imagen15);
        im16 = findViewById(R.id.imagen16);
        im17 = findViewById(R.id.imagen17);
        im18 = findViewById(R.id.imagen18);
        im19 = findViewById(R.id.imagen19);
        im20 = findViewById(R.id.imagen20);
        im25 = findViewById(R.id.imagen25);
        arrayImagenes = new ImageView[]{im15,im16,im17,im18,im19,im20,im25};

        Button marcador = findViewById(R.id.marcador);
        marcador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        jugadorActual=ListaJugadores.getJugadorActual();
        puntuacionAct=jugadorActual.getPuntos();
        TextView nombre = findViewById(R.id.nomre);
        nombre.setText(jugadorActual.getNombre());
        puntos=findViewById(R.id.puntuacion);
        puntos.setText("Score: "+jugadorActual.getPuntos());


        this.setImages();

        Button bot15 = findViewById(R.id.button15);
        Button bot16 = findViewById(R.id.button16);
        Button bot17 = findViewById(R.id.button17);
        Button bot18 = findViewById(R.id.button18);
        Button bot19 = findViewById(R.id.button19);
        Button bot20 = findViewById(R.id.button20);
        Button bot25 = findViewById(R.id.button25);

        Button[] arrayBotones={bot15,bot16,bot17,bot18,bot19,bot20,bot25};
        //los indices del 0 al 6 corresponden con los numeros del 15 al 20 y 25(centro) de la diana.
        for ( int i=0;i<7;i++){
            final int finalI = i;
            arrayBotones[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click(finalI);
                }
            });
        }

        Button siguiente = findViewById(R.id.siguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaJugadores.siguienteJugador();
                finish();
                startActivity(getIntent());
            }
        });

    }

    public void setImages(){
        for(int i =0;i<7;i++){
            //i.setBackgroundColor(getResources().getColor(R.color.colorBlanco,null));
            this.setImage(arrayImagenes[i],jugadorActual.getStacks()[i]);
        }
    }

    public void setImage(ImageView i,int cantidad){
        switch (cantidad){
            case 0 :
                Drawable drawable0 = ResourcesCompat.getDrawable(getResources(), R.drawable.mi_marca_blanca_small, null);
                i.setImageDrawable(drawable0);
                break;
            case 1:
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.mi_marca_1_small, null);
                i.setImageDrawable(drawable);
                break;
            case 2:
                Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.mi_marca_2_small, null);
                i.setImageDrawable(drawable2);
                break;
            case 3:
                Drawable drawable3 = ResourcesCompat.getDrawable(getResources(), R.drawable.mi_marca_3_small, null);
                i.setImageDrawable(drawable3);
        }
    }

    private void click(int pos){
        ListaJugadores.addImpact(pos);
        setImage(arrayImagenes[pos],jugadorActual.getStacks()[pos]);
        puntos.setText("Score: "+jugadorActual.getPuntos());
        if(ListaJugadores.haGanado()){
            Toast.makeText(getApplicationContext(),"You have finished",Toast.LENGTH_LONG).show();
            ListaJugadores.addAcabado(jugadorActual);
            if(ListaJugadores.getJugadores().size()<=1){
                //lanzar activity pantalla final
                ListaJugadores.addAcabado(ListaJugadores.getElemento(0));
                Intent i =new Intent(getApplicationContext(),PantallaFinal.class);
                startActivity(i);
            }
        }
    }

    public boolean esGanador(){
        boolean salida = false;

        return salida;
    }
}