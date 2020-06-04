package com.jorge.mycv.ui.employment;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.R;


import java.util.List;


public class MyLaboralRecyclerViewAdapter extends RecyclerView.Adapter<MyLaboralRecyclerViewAdapter.ViewHolder> {

    private final List<LaboralDB> mValues;
    private final onLaboralInteracionListener mListener;
    private Context contexto;

    public MyLaboralRecyclerViewAdapter(Context context, List<LaboralDB> items, onLaboralInteracionListener listener) {
        mValues = items;
        mListener = listener;
        contexto=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.laboral_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.cargo.setText(holder.mItem.getCargo());
        holder.empresa.setText(holder.mItem.getEmpresa());
        holder.direccion.setText(holder.mItem.getDirecion());
        holder.periodo.setText(holder.mItem.getPeriodo());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onLaboralClick(holder.mItem);
                }
            }
        });
        holder.editLaboral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLaboralEditClick(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView cargo;
        public final TextView empresa;
        public final TextView direccion;
        public final TextView periodo;
        public final ImageView photo;
        public final ImageView editLaboral;
        public LaboralDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            cargo = view.findViewById(R.id.titulos_centro);
            empresa =  view.findViewById(R.id.titulos_nombre_Titulacion);
            direccion =  view.findViewById(R.id.titulos_rama);
            periodo =  view.findViewById(R.id.titulos_nota);
            photo =  view.findViewById(R.id.imageView_Laboral_photo);
            editLaboral = view.findViewById(R.id.editar_laboral);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + empresa.getText() + "'";
        }
    }
}
