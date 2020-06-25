package com.jorge.mycv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.jorge.mycv.ui.Titulos.DetallesTitulosActivity;
import com.jorge.mycv.ui.Titulos.EditarTitulosFragment;
import com.jorge.mycv.ui.Titulos.onNuevoTituloGuardarListener;
import com.jorge.mycv.ui.cursos.DetalleCursoActivity;
import com.jorge.mycv.ui.cursos.EditarCrusoFragment;
import com.jorge.mycv.ui.cursos.onCursosInteractionListener;
import com.jorge.mycv.ui.cursos.onNuevoCursoGuardarListener;
import com.jorge.mycv.ui.employment.DetallesEmploymentActivity;
import com.jorge.mycv.ui.employment.EditarLaboralFragmen;
import com.jorge.mycv.ui.employment.onLaboralInteracionListener;
import com.jorge.mycv.ui.Titulos.onTitulosInteracionListener;
import com.jorge.mycv.ui.employment.onNuevoLaboralGuardarListener;

import androidx.appcompat.app.AlertDialog;
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
    private FloatingActionButton fab;
    ImageView imageView;
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
        //se instancia la FloatingActionButton y se oculta
        fab = findViewById(R.id.fab);
        hideFloatingActionButton();
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

        hideFloatingActionButton();

    }
    /* Métodos publicos que ocultan o muestral el
     * FAB Floating Action button
     */
    public void showFloatingActionButton() {
        fab.show();
    }

    public void hideFloatingActionButton() {
        fab.hide();
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
        Intent i = new Intent(this, DetalleCursoActivity.class);
        i.putExtra(CursosDB.CURSODB_ID, cursosDB.getId());
        startActivity(i);
    }

    @Override
    public void onCursosEditClick(CursosDB mItem) {
        dialogoEditCurso = EditarCrusoFragment
                .newInstance(mItem.getId(),
                        mItem.getTitulo(),
                        mItem.getQuienImparte(),
                        mItem.getHorasFormacion(),
                        mItem.getDescripcion());
        dialogoEditCurso.show(getSupportFragmentManager(),"Editando_el_Curso");
    }

    @Override
    public void onCursosBorrarClick(CursosDB cursosDB) {
        cursoShowDialogConfirmation(cursosDB);
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
        Intent i = new Intent(this, DetallesEmploymentActivity.class);
        i.putExtra(LaboralDB.LABORALDB_ID, laboralDB.getId());
        startActivity(i);
    }

    @Override
    public void onLaboralEditClick(LaboralDB mItem) {
        dialogoEditLaboral = EditarLaboralFragmen.newInstance(mItem.getId(),
                mItem.getCargo(),
                mItem.getEmpresa(),
                mItem.getDirecion(),
                mItem.getPeriodo(),
                mItem.getDescripcion());
        dialogoEditLaboral.show(getSupportFragmentManager(),"EditandoLaboral");

    }

    @Override
    public void onLaboralBorrarClick(LaboralDB laboralDB) {
        laboralShowDialogConfirmation(laboralDB);

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
    public void onTituloUpdateClickListener(final long id, final String c, final String n,
                                            final String r, final String nota, final String des) {
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
    public void onTituloClick(TitulosDB tituloDB) {
        Intent i = new Intent(this, DetallesTitulosActivity.class);
        i.putExtra(TitulosDB.TITULOSDB_ID, tituloDB.getId());
        startActivity(i);

    }

    @Override
    public void onTituloEditClick(TitulosDB mItem) {
        dialogoEditTitulo =  EditarTitulosFragment.newInstance(mItem.getId(),
                mItem.getCentro(),
                mItem.getTitulo(),
                mItem.getRama(),
                mItem.getNota(),
                mItem.getDescripcion());
        dialogoEditTitulo.show(getSupportFragmentManager(),"editandoTitulo");

    }

    @Override
    public void onTituloBorrarClick(TitulosDB titulo) {
        tituloShowDialogConfirmation(titulo);
    }

    private void cursoShowDialogConfirmation(final CursosDB cursosDB){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String smjConfirma = cursosDB.getTitulo();
        builder.setTitle(R.string.eliminar_Curso).
        setMessage(R.string.eliminar_mensaje);
        final Context ctx = getApplication();

        builder.setPositiveButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int id) {
                realmInstancia.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        long idEliminar =  cursosDB.getId();
                        CursosDB cursoEliminar = realm.where(CursosDB.class).equalTo(CursosDB.CURSODB_ID,idEliminar).findFirst();
                        // se confirma la eliminacion en DB
                        cursoEliminar.deleteFromRealm();
                    }
                });
                dialog.dismiss();
                Toast.makeText(ctx, "se ha eliminado el curso\n"+smjConfirma.toUpperCase(), Toast.LENGTH_SHORT).show();
            }

        });
        builder.setNegativeButton(R.string.boton_cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void laboralShowDialogConfirmation(final LaboralDB laboralDB) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String smjConfirma = laboralDB.getCargo();
        builder.setTitle(R.string.eliminar_trabajo).
                setMessage(R.string.eliminar_mensaje);
        final Context ctx = getApplication();

        builder.setPositiveButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int id) {
                realmInstancia.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        long idEliminar =  laboralDB.getId();
                        LaboralDB laboralEliminar = realm.where(LaboralDB.class).equalTo(LaboralDB.LABORALDB_ID,idEliminar).findFirst();
                        // se confirma la eliminacion en DB
                        laboralEliminar.deleteFromRealm();
                    }
                });
                dialog.dismiss();
                Toast.makeText(ctx, "se ha eliminado el curso\n"+smjConfirma.toUpperCase(), Toast.LENGTH_SHORT).show();
            }

        });
        builder.setNegativeButton(R.string.boton_cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void tituloShowDialogConfirmation(final TitulosDB tituloDB) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String smjConfirma = tituloDB.getTitulo();
        builder.setTitle(R.string.eliminar_titulo).
                setMessage(R.string.eliminar_mensaje);
        final Context ctx = getApplication();

        builder.setPositiveButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int id) {
                realmInstancia.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        long idEliminar =  tituloDB.getId();
                        TitulosDB tituloEliminar = realm.where(TitulosDB.class).equalTo(TitulosDB.TITULOSDB_ID,idEliminar).findFirst();
                        // se confirma la eliminacion en DB
                        tituloEliminar.deleteFromRealm();
                    }
                });
                dialog.dismiss();
                Toast.makeText(ctx, "se ha eliminado el curso\n"+smjConfirma.toUpperCase(), Toast.LENGTH_SHORT).show();
            }

        });
        builder.setNegativeButton(R.string.boton_cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
