package com.jorge.mycv.ui.employment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.R;


public class EditarLaboralFragmen extends DialogFragment {

    private long idLaboral;

    onNuevoLaboralGuardarListener mlistener;
    Context contexto;
    View v;
    EditText editTextCargo;
    EditText editTextEmpresa;
    EditText editTextdireccion;
    EditText editTextperido;
    EditText editTextDescripcion;

    public EditarLaboralFragmen() {
    }


    public static EditarLaboralFragmen newInstance(long idLaboral ) {
        EditarLaboralFragmen fragment = new EditarLaboralFragmen();
        Bundle args = new Bundle();
        args.putLong(LaboralDB.LABORALDB_ID, idLaboral);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idLaboral = getArguments().getLong(LaboralDB.LABORALDB_ID);

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

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
                            mlistener.onLaboralUpdateClickListener(idLaboral,cargo, empresa, direccion, periodo, descripcion);
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getActivity(), "Se deben cumplimentar todos \n \t los campos", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.boton_cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

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
