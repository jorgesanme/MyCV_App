package com.jorge.mycv.ui.employment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class LaboralFragment2 extends Fragment {

    private onLaboralInteracionListener mListener;
    private RealmResults<LaboralDB> laboralDBList;
    Realm realInstancia;

    private Context context;
    DialogFragment nuevoLaboralDialogo;

    public LaboralFragment2() {
    }


    public static LaboralFragment2 newInstance(int columnCount) {
        LaboralFragment2 fragment = new LaboralFragment2();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realInstancia = Realm.getDefaultInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laboral2_list, container, false);
        FloatingActionButton fab = (FloatingActionButton)  view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoLaboralDialogo = new NuevoLaboralDialogo();
                nuevoLaboralDialogo.show(getFragmentManager(),"¿nuevo laboral?");
                Snackbar.make(view, "añadir del employments", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;


            //lista de empleos
            laboralDBList =realInstancia.where(LaboralDB.class).findAll();

/*
            laboralDBList.add( new LaboralDB("Ayudante recepcion","Paraiso floral","playa paraiso",
                    "2 años",
                    "ayuda en la entradas"));
            laboralDBList.add( new LaboralDB("Ayudante Recepcion","hotel milor","ibiza",
                    "6 meses",
                    "ayuda en los catetos"));
            laboralDBList.add( new LaboralDB("Recepcionista","Hotel Floral","Tenerfie",
                    "2 años",
                    "trata directo con clientes"));
            laboralDBList.add( new LaboralDB("Recepcionista ","Fair Hotel","Frankfurt",
                    "2 meses",
                    "trata directo con clientes"));
            laboralDBList.add( new LaboralDB("Recepcionista ","Bed hotel Bruselas","Bruselas",
                    "2 meses",
                    "trata directo con clientes"));
            laboralDBList.add( new LaboralDB("Recepcionista ","Bed hotel Padova","Padova",
                    "2 meses",
                    "trata directo con clientes"));
            laboralDBList.add( new LaboralDB("Jefe 2º Recepcion ","Fiesta hotels","Tenerife",
                    "10 años",
                    "supervisar trabajo de Recepcionista y ayudantes"));
            laboralDBList.add( new LaboralDB("Jefe Recepcion ","Hotel Rural Güimar","Tenerife",
                    "6 mese",
                    "Dirigir el equipo de  Recepcionista y ayudantes"));

 */

            recyclerView.setAdapter(new MyLaboralRecyclerViewAdapter(getActivity(), laboralDBList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onLaboralInteracionListener) {
            mListener = (onLaboralInteracionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
