package com.jorge.mycv.ui.employment;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.R;

import io.realm.Realm;

public class MapsEmpleoActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    long id;
    Realm realm;
    LaboralDB laboralMostrado;
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
        id = extras.getLong(LaboralDB.LABORALDB_ID);
        realm = Realm.getDefaultInstance();
        laboralMostrado = realm.where(LaboralDB.class).equalTo(LaboralDB.LABORALDB_ID,id).findFirst();

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (laboralMostrado.getEmpresa().contains("floral")){
            objetoMostrado = new LatLng(28.1209652,-16.7755885);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("Fiesta Hotel Paraiso Floral")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(laboralMostrado.getEmpresa().contains("milord")){
            objetoMostrado = new LatLng(38.972482,1.2848732);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("Fiesta Hotel Milord")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        }else if(laboralMostrado.getEmpresa().contains("H10")) {
            objetoMostrado = new LatLng(28.0972215,-16.7492801);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("H10 Costa Adeje Palace")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(laboralMostrado.getEmpresa().contains("Finca")) {
            objetoMostrado = new LatLng(28.3118498,-16.4022537);
            mMap.addMarker(new MarkerOptions()
                    .position(objetoMostrado)
                    .title("Hotel Rural Finca Salamanca")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(objetoMostrado));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
