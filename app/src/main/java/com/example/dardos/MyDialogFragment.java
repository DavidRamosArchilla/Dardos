package com.example.dardos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    public static MyDialogFragment newInstance(int title){
        MyDialogFragment frag=new MyDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title",title);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_fragment,null));

        builder.setMessage("Datos")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView nombre= (TextView) getDialog().findViewById(R.id.nombre);
                        nombre.requestFocus();
                        Jugador j =new Jugador(nombre.getText().toString());
                        ListaJugadores.add(j);
                        String[] lista = ListaJugadores.arrayDatosJugadores();
                        ListView listaJugadores = getActivity().findViewById(R.id.listaJugadores);
                        ArrayAdapter<String> a =new ArrayAdapter<>(getContext(),R.layout.listitem, R.id.textview,
                                lista);
                        listaJugadores.setAdapter(a);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }
}
