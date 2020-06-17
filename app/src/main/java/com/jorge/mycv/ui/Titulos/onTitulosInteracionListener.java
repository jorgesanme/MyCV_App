package com.jorge.mycv.ui.Titulos;

import com.jorge.mycv.TitulosDB;

public interface onTitulosInteracionListener {
    void onTituloClick(TitulosDB titulo);
    void onTituloEditClick(TitulosDB titulo);
    void onTituloBorrarClick(TitulosDB titulo);
}
