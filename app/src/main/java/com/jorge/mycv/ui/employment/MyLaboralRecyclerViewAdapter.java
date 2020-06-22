package com.jorge.mycv.ui.employment;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.R;


import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;


public class MyLaboralRecyclerViewAdapter extends RecyclerView.Adapter<MyLaboralRecyclerViewAdapter.ViewHolder> {

    private final List<LaboralDB> mValues;
    private final onLaboralInteracionListener mListener;
    private RealmChangeListener listenerRefresco;
    private Context contexto;

    public MyLaboralRecyclerViewAdapter(Context context, List<LaboralDB> items, onLaboralInteracionListener listener) {
        mValues = items;
        mListener = listener;
        contexto=context;
        this.listenerRefresco = new RealmChangeListener<OrderedRealmCollection<LaboralDB>>() {
            @Override
            public void onChange(OrderedRealmCollection<LaboralDB> results) {
                notifyDataSetChanged();
            }
        };

        if (items != null) {
            addListener((OrderedRealmCollection<LaboralDB>) items);
        }
    }

    private void addListener(OrderedRealmCollection<LaboralDB> items) {
        if (items instanceof RealmResults) {
            RealmResults realmResults = (RealmResults) items;
            realmResults.addChangeListener(listenerRefresco);
        } else if (items instanceof RealmList) {
            RealmList<LaboralDB> list = (RealmList<LaboralDB>) items;
            //noinspection unchecke
            list.addChangeListener((RealmChangeListener) listenerRefresco);
        } else {
            throw new IllegalArgumentException("RealmCollection not supported: " + items.getClass());
        }

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

        holder.cargo.setText(mValues.get(position).getCargo());
        holder.empresa.setText(mValues.get(position).getEmpresa());
        holder.direccion.setText(mValues.get(position).getDirecion());
        holder.periodo.setText(mValues.get(position).getPeriodo());

        String urlFloral = "https://www.bookaris.com/images/HA/images/hoteles/129691_fotpe1_web1.jpg";
        String urlMilord = "https://r-cf.bstatic.com/images/hotel/max1024x768/201/201096238.jpg";
        String urlH10 = "https://cmspro.h10hotels.com/ImagenesHotel/hotelhca%20(4).jpg";
        String urlFinca = "https://www.hotel-fincasalamanca.com/cache/64/1f/641fdb8235c37e144af206f4f943cc3d.jpg";

        if (holder.mItem.getEmpresa().contains("floral")){
            Glide
                    .with(contexto)
                    .load(urlFloral)
                    .centerCrop()
                    .into(holder.photo);
        }else if (holder.mItem.getEmpresa().contains("milord")) {
            Glide
                    .with(contexto)
                    .load(urlMilord)
                    .centerCrop()
                    .into(holder.photo);
        }else if (holder.mItem.getEmpresa().contains("H10")) {
            Glide
                    .with(contexto)
                    .load(urlH10)
                    .centerCrop()
                    .into(holder.photo);
        }else if (holder.mItem.getEmpresa().contains("Finca")) {
            Glide
                    .with(contexto)
                    .load(urlFinca)
                    .centerCrop()
                    .into(holder.photo);
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
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

        holder.eliminarLaboral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLaboralBorrarClick(holder.mItem);
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
        public final ImageView eliminarLaboral;
        public LaboralDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            cargo = (TextView) view.findViewById(R.id.titulos_centro);
            empresa = (TextView) view.findViewById(R.id.titulos_nombre_Titulacion);
            direccion = (TextView) view.findViewById(R.id.titulos_rama);
            periodo = (TextView) view.findViewById(R.id.titulos_nota);
            photo =  (ImageView) view.findViewById(R.id.imageView_Laboral_photo);
            editLaboral = (ImageView) view.findViewById(R.id.editar_laboral);
            eliminarLaboral = (ImageView) view.findViewById(R.id.borrar_laboral);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + empresa.getText() + "'";
        }
    }
}
