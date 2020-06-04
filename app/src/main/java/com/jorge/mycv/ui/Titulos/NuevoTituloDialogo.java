package com.jorge.mycv.ui.Titulos;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.jorge.mycv.R;

public class NuevoTituloDialogo extends DialogFragment {
    onNuevoTituloGuardarListener mlistener;
    Context contexto;
    View v;
    EditText editTextCentro;
    EditText editTextNombre;
    EditText editTextRama;
    EditText editTextNota;
    EditText editTextDescripcion;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        contexto= getActivity();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialogo_nuevo_titulo, null);

        editTextCentro = v.findViewById(R.id.nuevo_titulo_centro);
        editTextNombre = v.findViewById(R.id.nuevo_titulo_nombre);
        editTextRama = v.findViewById(R.id.nuevo_titulo_rama);
        editTextNota = v.findViewById(R.id.nuevo_titulo_nota);
        editTextDescripcion = v.findViewById(R.id.nuevo_titulo_descripcion);
        builder.setView(v);

        builder.setMessage(R.string.nueva_titulo)
                .setPositiveButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {
                    String centro = editTextCentro.toString();
                    String nombre = editTextNombre.toString();
                    String rama = editTextRama.toString();
                    String nota = editTextNota.toString();
                    String descripcion = editTextDescripcion.toString();

                    public void onClick(DialogInterface dialog, int id) {
                        //
                        if (!centro.isEmpty() && !nombre.isEmpty() && !rama.isEmpty()
                                && !nota.isEmpty() && !descripcion.isEmpty()){
                            mlistener.onTituloGuardarClickListener(centro,nombre,rama,nota,descripcion);
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getActivity(), "Se deben cumplimentar todos \n \t los campos", Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton(R.string.boton_cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mlistener = (onNuevoTituloGuardarListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(this.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
