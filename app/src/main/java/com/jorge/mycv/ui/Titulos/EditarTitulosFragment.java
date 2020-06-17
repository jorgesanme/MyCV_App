package com.jorge.mycv.ui.Titulos;

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

import com.jorge.mycv.R;
import com.jorge.mycv.TitulosDB;


public class EditarTitulosFragment extends DialogFragment {

    private long idTitulo;
    private String centro,titulo,rama,nota,descripcion;
    onNuevoTituloGuardarListener mlistener;
    Context contexto;
    View v;
    EditText editTextCentro;
    EditText editTextNombre;
    EditText editTextRama;
    EditText editTextNota;
    EditText editTextDescripcion;


    public EditarTitulosFragment() {
    }


    public static EditarTitulosFragment newInstance(long idTitulo, String centro, String titulo,
                                                    String rama, String nota, String descripcion) {
        EditarTitulosFragment fragment = new EditarTitulosFragment();
        Bundle args = new Bundle();
        args.putLong(TitulosDB.TITULOSDB_ID, idTitulo);
        args.putString(TitulosDB.TITULOSDB_CENTRO,centro);
        args.putString(TitulosDB.TITULOSDB_TITULO,titulo);
        args.putString(TitulosDB.TITULOSDB_RAMA, rama);
        args.putString(TitulosDB.TITULOSDB_NOTA,nota);
        args.putString(TitulosDB.TITULOSDB_DESCRIPCION,descripcion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idTitulo = getArguments().getLong(TitulosDB.TITULOSDB_ID);
            centro = getArguments().getString(TitulosDB.TITULOSDB_CENTRO);
            titulo = getArguments().getString(TitulosDB.TITULOSDB_TITULO);
            rama = getArguments().getString(TitulosDB.TITULOSDB_RAMA);
            nota = getArguments().getString(TitulosDB.TITULOSDB_NOTA);
            descripcion = getArguments().getString(TitulosDB.TITULOSDB_DESCRIPCION);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        contexto= getActivity();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialogo_nuevo_titulo, null);

        editTextCentro = v.findViewById(R.id.nuevo_titulo_centro);
        editTextNombre = v.findViewById(R.id.nuevo_titulo_nombre);
        editTextRama = v.findViewById(R.id.nuevo_titulo_rama);
        editTextNota = v.findViewById(R.id.nuevo_titulo_nota);
        editTextDescripcion = v.findViewById(R.id.nuevo_titulo_descripcion);

        //se precarga el contenido
        editTextCentro.setText(centro);
        editTextNombre.setText(titulo);
        editTextRama.setText(rama);
        editTextNota.setText(nota);
        editTextDescripcion.setText(descripcion);

        builder.setView(v);

        builder.setTitle(R.string.editar_titulo)
                .setPositiveButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        String centro = editTextCentro.getText().toString();
                        String nombre = editTextNombre.getText().toString();
                        String rama = editTextRama.getText().toString();
                        String nota = editTextNota.getText().toString();
                        String descripcion = editTextDescripcion.getText().toString();
                        //
                        if (!centro.isEmpty() && !nombre.isEmpty() && !rama.isEmpty()
                                && !nota.isEmpty() && !descripcion.isEmpty()){
                            mlistener.onTituloUpdateClickListener(idTitulo,centro,nombre,rama,nota,descripcion);
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
            mlistener = (onNuevoTituloGuardarListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
