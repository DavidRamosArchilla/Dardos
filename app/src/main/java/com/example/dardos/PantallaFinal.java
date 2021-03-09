package com.example.dardos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PantallaFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_final);

        TextView t = findViewById(R.id.clasificacion);
        String mensaje="";
        List<String> clasificacion = ListaJugadores.getHanAcabado();
        for (int i = 0; i<clasificacion.size();i++){
            mensaje = mensaje + (i+1) + "º " + clasificacion.get(i)+".\n";
        }

        t.setText(mensaje);

        Button b = findViewById(R.id.acabar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }
}