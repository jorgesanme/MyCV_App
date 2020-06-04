package com.jorge.mycv.ui.cursos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jorge.mycv.CursosDB;
import com.jorge.mycv.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class CursosFragment extends Fragment {

    private onCursosInteractionListener mListener;
    RealmResults<CursosDB> cursosDBLista;
    Realm realmInstancia;

    private Context context = getActivity();
    DialogFragment dialogoNuevoCurso;

    public CursosFragment() {
    }


    public static CursosFragment newInstance(int columnCount) {
        CursosFragment fragment = new CursosFragment();
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

        View view = inflater.inflate(R.layout.fragment_cursos_list, container, false);
        FloatingActionButton fab = (FloatingActionButton)  view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoNuevoCurso = new NuevoCursoDialog();
                dialogoNuevoCurso.show(getFragmentManager(),"¿nuevo curso?");
                Snackbar.make(view, "añadir del Cursos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            //lista de cursos

            cursosDBLista = realmInstancia.where(CursosDB.class).findAll();

            recyclerView.setAdapter(new MyCursosRecyclerViewAdapter(getActivity(), cursosDBLista, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (this.context instanceof onCursosInteractionListener) {
            mListener = (onCursosInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onCursosIntereactionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
