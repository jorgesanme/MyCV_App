package com.jorge.mycv.ui.cursos;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jorge.mycv.CursosDB;
import com.jorge.mycv.R;

import io.realm.Realm;

public class MapsCursosActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    long id;
    Realm realm;
    CursosDB cursoMostrado;
    LatLng objetoMostrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras= getIntent().getExtras();
        id = extras.getLong(CursosDB.CURSODB_ID);
        realm = Realm.getDefaultInstance();
        cursoMostrado = realm.where(CursosDB.class).equalTo(CursosDB.CURSODB_ID,id).findFirst();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (cursoMostrado.getQuienImparte().equalsIgnoreCase("UPV")){
            objetoMostrado = new LatLng(39.4788721,-0.3342507);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("Universidad Politecnica de Valencia")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(cursoMostrado.getQuienImparte().equalsIgnoreCase("OpenWebinar")){
            objetoMostrado = new LatLng(37.3782519,-6.0018966);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("OpenWebinar")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        }else if(cursoMostrado.getQuienImparte().equalsIgnoreCase("keepCoding")) {
            objetoMostrado = new LatLng(40.4041266,-3.6899994);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("KeepCoding")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(cursoMostrado.getQuienImparte().equalsIgnoreCase("AppBrewery")) {
            objetoMostrado = new LatLng(51.5209573,-0.0520705);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("AppBrewery")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(objetoMostrado));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
