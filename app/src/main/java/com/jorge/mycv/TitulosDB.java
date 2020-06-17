package com.jorge.mycv;

import com.jorge.mycv.App.MyApp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TitulosDB extends RealmObject {
    public static final String TITULOSDB_ID = "id";
    public static final String TITULOSDB_CENTRO= "centro";
    public static final String TITULOSDB_TITULO = "titulo";
    public static final String TITULOSDB_RAMA = "rama";
    public static final String TITULOSDB_NOTA = "nota";
    public static final String TITULOSDB_DESCRIPCION = "descripcion";

    @PrimaryKey
    private long id;
    private String centro;
    private String titulo;
    private String rama;
    private String nota;
    private String descripcion;

    public TitulosDB() {
            this.id = MyApp.TitulosID.incrementAndGet();
    }

    public TitulosDB(long id, String centro, String titulo, String rama, String nota, String descripcion) {
        this.centro = centro;
        this.titulo = titulo;
        this.rama = rama;
        this.nota = nota;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public String getNota() {
        return  nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
