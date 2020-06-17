package com.jorge.mycv.ui.cursos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jorge.mycv.CursosDB;
import com.jorge.mycv.MainActivity;
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

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            //recyclerView.setLayoutManager(new LinearLayoutManager(context));

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

            fab.setImageResource(R.drawable.ic_online_course_add); //Cambiar icono

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogoNuevoCurso = new NuevoCursoDialog();
                    dialogoNuevoCurso.show(getFragmentManager(),"Añadir un curso");
                    Toast.makeText(mainActivity, "Se va a lanzar un DialogFragment\n " +
                            "Añadir un Curso On-Line", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
