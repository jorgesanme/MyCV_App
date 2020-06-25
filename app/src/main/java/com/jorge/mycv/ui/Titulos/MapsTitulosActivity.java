package com.jorge.mycv.ui.Titulos;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jorge.mycv.R;
import com.jorge.mycv.TitulosDB;

import io.realm.Realm;

public class MapsTitulosActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    long id;
    Realm realm;
    TitulosDB tituloMostrado;
    LatLng objetoMostrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong(TitulosDB.TITULOSDB_ID);
        realm = Realm.getDefaultInstance();
        tituloMostrado = realm.where(TitulosDB.class).equalTo(TitulosDB.TITULOSDB_ID,id).findFirst();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (tituloMostrado.getCentro().contains("Adeje")){
            objetoMostrado = new LatLng(28.1211831, -16.7343663);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("IES Adeje")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(tituloMostrado.getCentro().contains("Telesforo")){
            objetoMostrado = new LatLng(28.4120809,-16.5503228);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("IES Puerto de la Cruz")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        }else if(tituloMostrado.getCentro().contains("Manrique")) {
            objetoMostrado = new LatLng(28.456282, -16.2832199);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("CIFP Cesar Manrique")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(tituloMostrado.getCentro().contains("Cedeco")) {
            objetoMostrado = new LatLng(40.4379915,-3.67176);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("Cedeco Formación")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else {
            objetoMostrado = new LatLng(28.5104263,-16.1826083);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("Referencia no encontrada.")
                    .snippet("Espere aquí mientras buscamos la referencia")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(objetoMostrado));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(objetoMostrado));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
