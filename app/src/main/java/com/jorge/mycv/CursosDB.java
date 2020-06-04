package com.jorge.mycv;

import com.jorge.mycv.App.MyApp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CursosDB extends RealmObject {
    public static final String CURSODB_ID = "id";
    public static final String CURSODB_TITULO = "titulo";
    public static final String CURSODB_QUIENIMPARTE = "empresa";
    public static final String CURSODB_HORAS= "horas";

    @PrimaryKey
    private long id;
    private String titulo;
    private String quienImparte;
    private String horasFormacion;
    private String descripcion;

    public CursosDB() {
        this.id = MyApp.CursosID.incrementAndGet();
    }

    public CursosDB(long id,String titulo, String quienImparte, String horasFormacion,String descripcion) {
        this.id = MyApp.CursosID.incrementAndGet();
        this.titulo = titulo;
        this.quienImparte = quienImparte;
        this.horasFormacion = horasFormacion;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getQuienImparte() {
        return quienImparte;
    }

    public void setQuienImparte(String quienImparte) {
        this.quienImparte = quienImparte;
    }

    public String getHorasFormacion() {
        return horasFormacion;
    }

    public void setHorasFormacion(String horasFormacion) {
        this.horasFormacion = horasFormacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
