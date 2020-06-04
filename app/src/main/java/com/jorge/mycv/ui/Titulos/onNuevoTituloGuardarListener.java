package com.jorge.mycv.ui.Titulos;

public interface onNuevoTituloGuardarListener {
    public void onTituloGuardarClickListener(String c, String n, String r, String nota, String des);
    public void onTituloUpdateClickListener(long id,String c, String n, String r, String nota, String des);
}
