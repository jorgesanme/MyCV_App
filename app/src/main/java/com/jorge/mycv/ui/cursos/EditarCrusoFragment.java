package com.jorge.mycv.ui.cursos;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jorge.mycv.CursosDB;
import com.jorge.mycv.R;

import static com.jorge.mycv.CursosDB.CURSODB_ID;


public class EditarCrusoFragment extends DialogFragment {


    private long idCurso;
    private String titulo, empresa, horas,descripcion;
    onNuevoCursoGuardarListener mlistener;
    Context contexto;
    View v;
    EditText editTextTitulo;
    EditText editTextEmpresa;
    EditText editTextHoras;
    EditText editTextDescripcio;

    public EditarCrusoFragment() {
    }

    public static EditarCrusoFragment newInstance(long id, String titulo, String empresa,
                                                  String horas, String descripcion) {
        EditarCrusoFragment fragment = new EditarCrusoFragment();
        Bundle args = new Bundle();
        args.putLong(CursosDB.CURSODB_ID, id);
        args.putString(CursosDB.CURSODB_TITULO,titulo);
        args.putString(CursosDB.CURSODB_QUIENIMPARTE,empresa);
        args.putString(CursosDB.CURSODB_HORAS, horas);
        args.putString(CursosDB.CURSODB_DESCRIPCION,descripcion);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idCurso = getArguments().getLong(CursosDB.CURSODB_ID);
            titulo = getArguments().getString(CursosDB.CURSODB_TITULO);
            empresa = getArguments().getString(CursosDB.CURSODB_QUIENIMPARTE);
            horas = getArguments().getString(CursosDB.CURSODB_HORAS);
            descripcion = getArguments().getString(CursosDB.CURSODB_DESCRIPCION);

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        contexto = getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialogo_curso_insert, null);
        editTextTitulo = v.findViewById(R.id.nuevo_curso_nombre);
        editTextEmpresa = v.findViewById(R.id.nuevo_curso_empresa);
        editTextHoras = v.findViewById(R.id.nuevo_curso_horas);
        editTextDescripcio = v.findViewById(R.id.nuevo_curso_descripcion);

        //se precarca el contenido
        editTextTitulo.setText(titulo);
        editTextEmpresa.setText(empresa);
        editTextHoras.setText(horas);
        editTextDescripcio.setText(descripcion);

        builder.setView(v);

        builder.setTitle(R.string.editar_Curso)
                .setPositiveButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = editTextTitulo.getText().toString();
                        String empresa = editTextEmpresa.getText().toString();
                        String horas = editTextHoras.getText().toString();
                        String descripcion = editTextDescripcio.getText().toString();
                        if (!titulo.isEmpty() && !empresa.isEmpty() && !horas.isEmpty() && !descripcion.isEmpty()) {
                            mlistener.onCursoUpdateClickListener(idCurso, titulo, empresa, horas,descripcion);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(contexto, "Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // cerrar el dialogo
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mlistener = (onNuevoCursoGuardarListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(this.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
