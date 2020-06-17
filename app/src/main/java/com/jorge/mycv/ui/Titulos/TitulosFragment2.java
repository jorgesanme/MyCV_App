package com.jorge.mycv.ui.Titulos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jorge.mycv.MainActivity;
import com.jorge.mycv.R;
import com.jorge.mycv.TitulosDB;
import com.jorge.mycv.ui.cursos.NuevoCursoDialog;


import io.realm.Realm;
import io.realm.RealmResults;


public class TitulosFragment2 extends Fragment {
    private onTitulosInteracionListener mListener;
    private RealmResults<TitulosDB> titulosDBList;
    Realm realmInstancia;

    private Context context =getActivity();
    DialogFragment nuevoTituloDialogo;

    public TitulosFragment2() {
    }

    public static TitulosFragment2 newInstance(int columnCount) {
        TitulosFragment2 fragment = new TitulosFragment2();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realmInstancia = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_titulos2_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            //lista de TitulosDB
            titulosDBList = realmInstancia.where(TitulosDB.class).findAll();

            recyclerView.setAdapter(new MyTitulosRecyclerViewAdapter(getActivity(),titulosDBList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onTitulosInteracionListener) {
            mListener = (onTitulosInteracionListener) context;
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
    /*
    para mostrar y ocultar el FAB
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        final MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {

            mainActivity.showFloatingActionButton(); //fuerza la visibilidad
            FloatingActionButton fab = mainActivity.findViewById(R.id.fab);
            fab.setImageResource(R.drawable.ic_businessman_add); //Cambiar icono
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nuevoTituloDialogo = new NuevoTituloDialogo();
                    nuevoTituloDialogo.show(getFragmentManager(),"¿nuevo Titulo?");
                    Toast.makeText(mainActivity, "Se va a lanzar un DialogFragment\n " +
                            "Añadir un Curso On-Line", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



}
