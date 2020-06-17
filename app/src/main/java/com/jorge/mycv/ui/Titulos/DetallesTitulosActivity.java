package com.jorge.mycv.ui.Titulos;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.jorge.mycv.CursosDB;
import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.R;
import com.jorge.mycv.TitulosDB;

import io.realm.Realm;

public class DetallesTitulosActivity extends AppCompatActivity {

    TextView textDetalles;
    long idCurso;
    Realm realm;
    TitulosDB tituloMostrado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_titulos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textDetalles = (TextView) findViewById(R.id.texto_detalles_titulos);

        Bundle extras = getIntent().getExtras();
        idCurso = extras.getLong(LaboralDB.LABORALDB_ID);
        realm = Realm.getDefaultInstance();

        tituloMostrado = realm.where(TitulosDB.class).equalTo(TitulosDB.TITULOSDB_ID,idCurso).findFirst();
        setTitle(tituloMostrado.getTitulo().toUpperCase());
        textDetalles.setText(tituloMostrado.getDescripcion());

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
