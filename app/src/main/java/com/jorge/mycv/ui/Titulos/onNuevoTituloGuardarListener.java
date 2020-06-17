package com.jorge.mycv.ui.Titulos;

public interface onNuevoTituloGuardarListener {
    void onTituloGuardarClickListener(String c, String n, String r, String nota, String des);
    void onTituloUpdateClickListener(long id,String c, String n, String r, String nota, String des);
}
