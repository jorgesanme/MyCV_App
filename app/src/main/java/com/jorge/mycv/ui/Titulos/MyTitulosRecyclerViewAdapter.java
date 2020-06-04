package com.jorge.mycv.ui.Titulos;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.mycv.R;
import com.jorge.mycv.TitulosDB;


import java.util.List;


public class MyTitulosRecyclerViewAdapter extends RecyclerView.Adapter<MyTitulosRecyclerViewAdapter.ViewHolder> {

    private final List<TitulosDB> mValues;
    private final onTitulosInteracionListener mListener;

    public MyTitulosRecyclerViewAdapter(List<TitulosDB> items, onTitulosInteracionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.titulos_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.centro.setText(holder.mItem.getCentro());
        holder.nombre.setText(holder.mItem.getTitulo());
        holder.rama.setText(holder.mItem.getRama());
        holder.nota.setText( String.valueOf(holder.mItem.getNota())+"\tPuntos");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onTituloClick(holder.mItem);
                }
            }
        });
        holder.editTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTituloEditClick(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView centro;
        public final TextView nombre;
        public final TextView rama;
        public final TextView nota;
        public final ImageView editTitulo;
        public TitulosDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            centro = (TextView) view.findViewById(R.id.titulos_centro);
            nombre = (TextView) view.findViewById(R.id.titulos_nombre_Titulacion);
            rama = (TextView) view.findViewById(R.id.titulos_rama);
            nota = (TextView) view.findViewById(R.id.titulos_nota);
            editTitulo = (ImageView) view.findViewById(R.id.editar_titulo);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nombre.getText() + "'";
        }
    }
}
