package com.jorge.mycv.ui.cursos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.mycv.CursosDB;
import com.jorge.mycv.ui.Titulos.MapsTitulosActivity;
import com.jorge.mycv.R;

import io.realm.Realm;

public class DetalleCursoActivity extends AppCompatActivity {
    TextView textDetalles;
    long idCurso;
    Realm realm;
    CursosDB cursoMostrado;
    ImageView fondo;
    @SuppressLint("ResourceAsColor")
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
        fondo = (ImageView) findViewById(R.id.fondo_curso);
        cursoMostrado = realm.where(CursosDB.class).equalTo(CursosDB.CURSODB_ID,idCurso).findFirst();

        String urlKeepCoding = "https://www.eu-startups.com/wp-content/uploads/2017/06/Logo-KC.png";
        String urlUPV = "http://www.howdeniberia.com/wp-content/uploads/2015/07/UPV.gif";
        String urlOpenWebinar = "https://www.linuxadictos.com/wp-content/uploads/openwebinars-logo.jpg";
        String urlAppBrewery = "https://pbs.twimg.com/profile_images/608304087021572096/Dnig5VDZ_400x400.png";

        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.fitCenter();

        if (cursoMostrado.getQuienImparte().equalsIgnoreCase("upv")){
            Glide
                    .with(this)
                    .load(urlUPV)
                    .apply(options)
                    .into(fondo);
        }else if (cursoMostrado.getQuienImparte().equalsIgnoreCase("OpenWebinar")){
            Glide
                    .with(this)
                    .load(urlOpenWebinar)
                    .apply(options)
                    .into(fondo);
        }else if (cursoMostrado.getQuienImparte().equalsIgnoreCase("keepCoding")){
            Glide
                    .with(this)
                    .load(urlKeepCoding)
                    .apply(options)
                    .into(fondo);
        }else if (cursoMostrado.getQuienImparte().equalsIgnoreCase("AppBrewery")){
            Glide
                    .with(this)
                    .load(urlAppBrewery)
                    .apply(options)
                    .into(fondo);
        }


        setTitle(""+cursoMostrado.getTitulo().toUpperCase());
        textDetalles.setText(cursoMostrado.getTitulo().toUpperCase()+"\n"
                +cursoMostrado.getQuienImparte().toUpperCase()+",\n"
                +cursoMostrado.getHorasFormacion()+" Horas,\n\n"
                +cursoMostrado.getDescripcion());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_location);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsCursosActivity.class);
                i.putExtra(CursosDB.CURSODB_ID, cursoMostrado.getId());
                startActivity(i);

            }
        });
    }
}
