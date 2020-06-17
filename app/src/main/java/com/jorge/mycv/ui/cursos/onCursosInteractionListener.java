package com.jorge.mycv.ui.cursos;

import com.jorge.mycv.CursosDB;

public interface onCursosInteractionListener {

    void onCursosClick(CursosDB cursosDB);
    void onCursosEditClick(CursosDB cursosDB);
    void onCursosBorrarClick(CursosDB cursosDB);
}
