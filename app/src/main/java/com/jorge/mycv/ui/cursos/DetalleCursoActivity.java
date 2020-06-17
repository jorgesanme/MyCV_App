package com.jorge.mycv.ui.cursos;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.jorge.mycv.CursosDB;
import com.jorge.mycv.R;

import io.realm.Realm;

public class DetalleCursoActivity extends AppCompatActivity {
    TextView textDetalles;
    long idCurso;
    Realm realm;
    CursosDB cursoMostrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_curso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textDetalles = (TextView) findViewById(R.id.texto_Detalles_descripcion);
        Bundle extras = getIntent().getExtras();
        idCurso = extras.getLong(CursosDB.CURSODB_ID);
        realm = Realm.getDefaultInstance();

        cursoMostrado = realm.where(CursosDB.class).equalTo(CursosDB.CURSODB_ID,idCurso).findFirst();


        setTitle(""+cursoMostrado.getTitulo().toUpperCase());
        textDetalles.setText(cursoMostrado.getQuienImparte().toUpperCase()+",\n"
                +cursoMostrado.getHorasFormacion()+" Horas,\n\n"
                +cursoMostrado.getDescripcion());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_location_red_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Se mostrara un mapa con la localizacion\n de la empresa", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
