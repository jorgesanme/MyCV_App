package com.jorge.mycv.ui.Titulos;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.R;
import com.jorge.mycv.TitulosDB;

import io.realm.Realm;

public class DetallesTitulosActivity extends AppCompatActivity {

    TextView textDetalles;
    long idCurso;
    Realm realm;
    TitulosDB tituloMostrado;
    ImageView fondo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_titulos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textDetalles = (TextView) findViewById(R.id.texto_detalles_titulos);
        fondo = (ImageView) findViewById(R.id.fondo_titulo);

        Bundle extras = getIntent().getExtras();
        idCurso = extras.getLong(LaboralDB.LABORALDB_ID);
        realm = Realm.getDefaultInstance();

        tituloMostrado = realm.where(TitulosDB.class).equalTo(TitulosDB.TITULOSDB_ID,idCurso).findFirst();

        String urlOlivos = "http://www3.gobiernodecanarias.org/medusa/edublog/iesadeje/wp-content/uploads/sites/189/2019/03/logo_blog.png";
        String urlCedeco = "https://www.cedeco.es/wp-content/uploads/2018/08/Logo-CEDECO-COLOR.jpg";
        String urlPuerto = "https://blog.iespuertodelacruz.es/wp-content/header/Logotipo_IES.png";
        String urlCesar = "https://cifpcesarmanrique.es/wp-content/uploads/2019/01/LogoCMTransparente-BrilloExt-300x93.png";

        if (tituloMostrado.getCentro().contains("Adeje")){
            Glide
                    .with(this)
                    .load(urlOlivos)
                    .centerInside()
                    .into(fondo);
        }else if (tituloMostrado.getCentro().contains("Cedeco")) {
            Glide
                    .with(this)
                    .load(urlCedeco)
                    .centerInside()
                    .into(fondo);
        }else if (tituloMostrado.getCentro().contains("Telesforo")) {
            Glide
                    .with(this)
                    .load(urlPuerto)
                    .centerInside()
                    .into(fondo);
        }else if (tituloMostrado.getCentro().contains("Cesar")) {
            Glide
                    .with(this)
                    .load(urlCesar)
                    .centerInside()
                    .into(fondo);
        }

        setTitle("");
        textDetalles.setText(tituloMostrado.getCentro()+"\n"
                +tituloMostrado.getTitulo()+"\n\n"
                +tituloMostrado.getDescripcion());



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_location);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsTitulosActivity.class);
                i.putExtra(TitulosDB.TITULOSDB_ID, tituloMostrado.getId());
                startActivity(i);
                Snackbar.make(view, "Se mostrara un mapa con la localizacion\n de la empresa", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
