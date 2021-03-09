package com.example.dardos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment d = new MyDialogFragment();
                FragmentManager FM = getSupportFragmentManager();
                d.show(FM,"add");

            }
        });

        final Button jugadorActual = findViewById(R.id.start);
        if(ListaJugadores.getJugadores().isEmpty())
            jugadorActual.setText("start");

            jugadorActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ListaJugadores.getJugadores().size()>1) {
                    add.setVisibility(View.GONE);
                    Intent i = new Intent(getApplicationContext(), DatosJugador.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"There must be at least 2 players",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ListaJugadores.getJugadores()!=null){
            ListView listaJugadores = findViewById(R.id.listaJugadores);
            listaJugadores.setAdapter(new ArrayAdapter<>(getApplicationContext(),R.layout.listitem, R.id.textview,
                    ListaJugadores.arrayDatosJugadores()));
        }
    }
}