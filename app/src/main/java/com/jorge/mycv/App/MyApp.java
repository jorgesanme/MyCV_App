package com.jorge.mycv.App;

import android.app.Application;
import com.jorge.mycv.CursosDB;
import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.TitulosDB;
import java.util.concurrent.atomic.AtomicLong;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApp extends Application {
    public static  AtomicLong LaboralID = new AtomicLong();
    public static  AtomicLong TitulosID = new AtomicLong();
    public static  AtomicLong CursosID = new AtomicLong();

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();

        Realm realmDB = Realm.getDefaultInstance();
        LaboralID = getIdByTable(realmDB, LaboralDB.class);
        TitulosID = getIdByTable(realmDB, TitulosDB.class);
        CursosID = getIdByTable(realmDB, CursosDB.class);
        realmDB.close();

    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        //Se define la configuración de la DDBB Realm
        RealmConfiguration realmConfiguration =  new RealmConfiguration.Builder()
                .schemaVersion(1) //.deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    //metodo id unico
    private <T extends RealmObject> AtomicLong getIdByTable(Realm realm, Class<T>anyClass){
        RealmResults<T> results =  realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicLong(results.max("id").intValue()) :
                new AtomicLong();

    }
}
