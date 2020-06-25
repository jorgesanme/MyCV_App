package com.jorge.mycv.ui.employment;

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
import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.MainActivity;
import com.jorge.mycv.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class LaboralFragment2 extends Fragment {

    private onLaboralInteracionListener mListener;
    private RealmResults<LaboralDB> laboralDBList;
    Realm realInstancia;

    private Context context= getActivity();
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


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            //lista de empleos
            laboralDBList =realInstancia.where(LaboralDB.class).findAll();

            recyclerView.setAdapter(new MyLaboralRecyclerViewAdapter(getActivity(), laboralDBList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
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
                    nuevoLaboralDialogo = new NuevoLaboralDialogo();
                    nuevoLaboralDialogo.show(getFragmentManager(),"Nueva trabajo");
                    Toast.makeText(mainActivity, "Se va a lanzar un DialogFragment\n " +
                            "Añadir una experiencia laboral ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
