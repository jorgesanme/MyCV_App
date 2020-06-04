package com.jorge.mycv.ui.cursos;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.jorge.mycv.R;

public class NuevoCursoDialog extends DialogFragment {
    onNuevoCursoGuardarListener mlistener;
    Context contexto;
    View v;
    EditText editTextTitulo;
    EditText editTextEmpresa;
    EditText editTextHoras;
    EditText editTextDescripcion;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        contexto  = getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        v= inflater.inflate(R.layout.dialogo_curso_insert, null);
        editTextTitulo = v.findViewById(R.id.nuevo_curso_nombre);
        editTextEmpresa = v.findViewById(R.id.nuevo_curso_empresa);
        editTextHoras= v.findViewById(R.id.nuevo_curso_horas);
        editTextDescripcion = v.findViewById(R.id.nuevo_curso_descripcion);
        builder.setView(v);

        builder.setTitle("Nuevo Curso")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo= editTextTitulo.getText().toString();
                        String empresa= editTextEmpresa.getText().toString();
                        String horas= editTextHoras.getText().toString();
                        String descripcion = editTextDescripcion.toString();
                        if (!titulo.isEmpty() && !empresa.isEmpty()
                                && !horas.isEmpty() && !descripcion.isEmpty()){
                            mlistener.onCursoGuardarClickListener(titulo,empresa,horas,descripcion);
                            dialog.dismiss();
                        }else{
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
