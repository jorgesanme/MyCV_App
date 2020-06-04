package com.jorge.mycv.ui.employment;

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

public class NuevoLaboralDialogo extends DialogFragment {
    onNuevoLaboralGuardarListener mlistener;
    Context contexto;
    View v;
    EditText editTextCargo;
    EditText editTextEmpresa;
    EditText editTextdireccion;
    EditText editTextperido;
    EditText editTextDescripcion;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        contexto  = getActivity();
        LayoutInflater inflater= getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialogo_laboral_insert, null);

        editTextCargo= v.findViewById(R.id.new_laboral_cargo);
        editTextEmpresa = v.findViewById(R.id.new_laboral_empresa);
        editTextdireccion = v.findViewById(R.id.new_laboral_direccion);
        editTextperido = v.findViewById(R.id.new_laboral_periodo);
        editTextDescripcion = v.findViewById(R.id.new_laboral_descripcion);
        builder.setView(v);

        builder.setTitle(R.string.nueva_trabajo)
                .setPositiveButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {
                    String cargo = editTextCargo.toString();
                    String empresa = editTextEmpresa.toString();
                    String direccion = editTextdireccion.toString();
                    String periodo = editTextperido.toString();
                    String descripcion = editTextDescripcion.toString();

                    public void onClick(DialogInterface dialog, int id) {
                        if(!cargo.isEmpty() && !empresa.isEmpty() && !direccion.isEmpty()
                            && !periodo.isEmpty() && !descripcion.isEmpty()) {
                            mlistener.onLaboralGuardarClickListener(cargo, empresa, direccion, periodo, descripcion);
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getActivity(), "Se deben cumplimentar todos \n \t los campos", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.boton_cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mlistener = (onNuevoLaboralGuardarListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
