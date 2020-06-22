package com.jorge.mycv.ui.employment;

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
import com.jorge.mycv.ui.Titulos.MapsTitulosActivity;
import com.jorge.mycv.R;

import io.realm.Realm;

public class DetallesEmploymentActivity extends AppCompatActivity {

    TextView textDetalles;
    long idLaboral;
    Realm realm;
    LaboralDB empleoMostrado;
    ImageView fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_employment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textDetalles = (TextView) findViewById(R.id.texto_detalles_employment);
        fondo = (ImageView) findViewById(R.id.fondo_empleo);
        Bundle extras = getIntent().getExtras();
        idLaboral = extras.getLong(LaboralDB.LABORALDB_ID);
        realm = Realm.getDefaultInstance();

        empleoMostrado = realm.where(LaboralDB.class).equalTo(LaboralDB.LABORALDB_ID,idLaboral).findFirst();
        String urlFloral = "https://www.bookaris.com/images/HA/images/hoteles/129691_fotpe1_web1.jpg";
        String urlMilord = "https://r-cf.bstatic.com/images/hotel/max1024x768/201/201096238.jpg";
        String urlH10 = "https://cmspro.h10hotels.com/ImagenesHotel/hotelhca%20(4).jpg";
        String urlFinca = "https://www.hotel-fincasalamanca.com/cache/64/1f/641fdb8235c37e144af206f4f943cc3d.jpg";

        if (empleoMostrado.getEmpresa().contains("floral")){
            Glide
                    .with(this)
                    .load(urlFloral)
                    .centerCrop()
                    .into(fondo);
        }else if (empleoMostrado.getEmpresa().contains("milord")) {
            Glide
                    .with(this)
                    .load(urlMilord)
                    .centerCrop()
                    .into(fondo);
        }else if (empleoMostrado.getEmpresa().contains("H10")) {
            Glide
                    .with(this)
                    .load(urlH10)
                    .centerCrop()
                    .into(fondo);
        }else if (empleoMostrado.getEmpresa().contains("Finca")) {
            Glide
                    .with(this)
                    .load(urlFinca)
                    .centerCrop()
                    .into(fondo);
        }

        setTitle(empleoMostrado.getCargo().toUpperCase());
        textDetalles.setText(empleoMostrado.getCargo()+"\n"
                +empleoMostrado.getEmpresa()+"\n\n"
                +empleoMostrado.getDescripcion());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_location);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsEmpleoActivity.class);
                i.putExtra(LaboralDB.LABORALDB_ID, empleoMostrado.getId());
                startActivity(i);
            }
        });
    }
}
