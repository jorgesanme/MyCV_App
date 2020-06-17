package com.jorge.mycv;

import com.jorge.mycv.App.MyApp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LaboralDB extends RealmObject {
    public static final String LABORALDB_ID = "id";
    public static final String LABORALDB_CARGO = "cargo";
    public static final String LABORALDB_EMPRESA = "empresa";
    public static final String LABORALDB_DIRECCION = "direccion";
    public static final String LABORALDB_PERIODO = "periodo";
    public static final String LABORALDB_DESCRIPCION = "descripcion";

    @PrimaryKey
    private long id;
    private  String cargo;
    private  String empresa;
    private  String direcion;
    private  String periodo;
    private  String descripcion;

    public LaboralDB() {
        this.id = MyApp.LaboralID.incrementAndGet();
    }

    public LaboralDB(long id,String cargo, String empresa, String direcion, String periodo, String descripcion) {
        this.id = MyApp.CursosID.incrementAndGet();
        this.cargo = cargo;
        this.empresa = empresa;
        this.direcion = direcion;
        this.periodo = periodo;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "LaboralDB{" +
                "cargo='" + cargo + '\'' +
                ", empresa='" + empresa + '\'' +
                ", direcion='" + direcion + '\'' +
                ", periodo='" + periodo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
