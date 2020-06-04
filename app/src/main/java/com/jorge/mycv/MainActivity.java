package com.jorge.mycv;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.jorge.mycv.ui.Titulos.EditarTirulosFragment;
import com.jorge.mycv.ui.Titulos.onNuevoTituloGuardarListener;
import com.jorge.mycv.ui.cursos.EditarCrusoFragment;
import com.jorge.mycv.ui.cursos.onCursosInteractionListener;
import com.jorge.mycv.ui.cursos.onNuevoCursoGuardarListener;
import com.jorge.mycv.ui.employment.EditarLaboralFragmen;
import com.jorge.mycv.ui.employment.onLaboralInteracionListener;
import com.jorge.mycv.ui.Titulos.onTitulosInteracionListener;
import com.jorge.mycv.ui.employment.onNuevoLaboralGuardarListener;

import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
    implements onCursosInteractionListener,
            onLaboralInteracionListener,
            onTitulosInteracionListener,
            onNuevoCursoGuardarListener,
            onNuevoLaboralGuardarListener,
        onNuevoTituloGuardarListener {

    private AppBarConfiguration mAppBarConfiguration;
    ImageView imageView;
    DialogFragment dialogoNuevoCurso;
    DialogFragment dialogoEditCurso;
    DialogFragment dialogoEditLaboral;
    DialogFragment dialogoEditTitulo;
    Realm realmInstancia;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // se crea la instancia de la base de datos
        realmInstancia = Realm.getDefaultInstance();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_titulos, R.id.nav_cursos,R.id.nav_laboral,
                R.id.nav_linkedin, R.id.nav_github, R.id.nav_blog)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /// METODOS DE LOS CURSOS \\\
    @Override
    public void onCursoGuardarClickListener(final String t, final String e, final String h,final String d) {
        realmInstancia.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CursosDB nuevoCurso= new CursosDB();
                nuevoCurso.setTitulo(t);
                nuevoCurso.setQuienImparte(e);
                nuevoCurso.setHorasFormacion(h);
                nuevoCurso.setDescripcion(d);
                //persistencia en BBDD
                realmInstancia.copyToRealm(nuevoCurso);
            }
        });
        Toast.makeText(this, "Curso guardado satisfactoriamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCursoUpdateClickListener(final long id, final String t, final String e,
                                           final String h, final String des) {
        realmInstancia.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CursosDB nuevoCurso= new CursosDB();
                nuevoCurso.setId(id);
                nuevoCurso.setTitulo(t);
                nuevoCurso.setQuienImparte(e);
                nuevoCurso.setHorasFormacion(h);
                nuevoCurso.setDescripcion(des);
                //persistencia en BBDD
                realmInstancia.copyToRealmOrUpdate(nuevoCurso);
            }
        });
        Toast.makeText(this, "Curso actualizado con exito", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCursosClick(CursosDB cursosDB) {
        Toast.makeText(this, "Curso pulsado:\t"+ cursosDB.getTitulo(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCursosEditClick(CursosDB cursosDB) {
        dialogoEditCurso = new EditarCrusoFragment();
        dialogoEditCurso.show(getSupportFragmentManager(),"Editando el Curso");
    }


    /// METODOS DE LO LABORAL \\\
    @Override
    public void onLaboralGuardarClickListener(final String c, final String e, final String d, final String p, final String des) {
        realmInstancia.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                LaboralDB nuevoLaboral= new LaboralDB();
                nuevoLaboral.setCargo(c);
                nuevoLaboral.setEmpresa(e);
                nuevoLaboral.setDirecion(d);
                nuevoLaboral.setPeriodo(p);
                nuevoLaboral.setDescripcion(des);
                //persistencia en BBDD
                realmInstancia.copyToRealm(nuevoLaboral);
            }
        });

        Toast.makeText(this, "Employment guardado satisfactoriamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLaboralUpdateClickListener(final long id, final String c, final String e, final String d, final String p, final String des) {
        realmInstancia.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                LaboralDB nuevoLaboral= new LaboralDB();
                nuevoLaboral.setId(id);
                nuevoLaboral.setCargo(c);
                nuevoLaboral.setEmpresa(e);
                nuevoLaboral.setDirecion(d);
                nuevoLaboral.setPeriodo(p);
                nuevoLaboral.setDescripcion(des);
                //persistencia en BBDD
                realmInstancia.copyToRealmOrUpdate(nuevoLaboral);
            }
        });

    }
    @Override
    public void onLaboralClick(LaboralDB laboralDB) {
        Toast.makeText(this, "trabajo pulsado:\t"+ laboralDB.getCargo(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLaboralEditClick(LaboralDB cursosDB) {
        dialogoEditLaboral = new EditarLaboralFragmen();
        dialogoEditLaboral.show(getSupportFragmentManager(),"EditandoLaboral");

    }



    /// METODOS DE LOS TITULOS \\\
    @Override
    public void onTituloGuardarClickListener(final String c, final String n, final String r, final String nota, final String des) {
        realmInstancia.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TitulosDB nuevoTitulo= new TitulosDB();
                nuevoTitulo.setCentro(c);
                nuevoTitulo.setTitulo(n);
                nuevoTitulo.setRama(r);
                nuevoTitulo.setNota(nota);
                nuevoTitulo.setDescripcion(des);
                //persistencia en BBDD
                realmInstancia.copyToRealm(nuevoTitulo);
            }
        });

        Toast.makeText(this, "Degrees guardado satisfactoriamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTituloUpdateClickListener(final long id, final String c, final String n, final String r, final String nota, final String des) {
        realmInstancia.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TitulosDB nuevoTitulo= new TitulosDB();
                nuevoTitulo.setId(id);
                nuevoTitulo.setCentro(c);
                nuevoTitulo.setTitulo(n);
                nuevoTitulo.setRama(r);
                nuevoTitulo.setNota(nota);
                nuevoTitulo.setDescripcion(des);
                //persistencia en BBDD
                realmInstancia.copyToRealmOrUpdate(nuevoTitulo);
            }
        });

    }
    @Override
    public void onTituloClick(TitulosDB titulo) {
        Toast.makeText(this, "Titulo pulsado:\t"+titulo.getTitulo(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTituloEditClick(TitulosDB titulo) {
        dialogoEditTitulo = new EditarTirulosFragment();
        dialogoEditTitulo.show(getSupportFragmentManager(),"editandoTitulo");

    }
}
