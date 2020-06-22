package com.jorge.mycv.ui.Titulos;

import androidx.fragment.app.FragmentActivity;
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
import com.jorge.mycv.TitulosDB;


import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;


public class MyTitulosRecyclerViewAdapter extends RecyclerView.Adapter<MyTitulosRecyclerViewAdapter.ViewHolder> {

    private final List<TitulosDB> mValues;
    private final onTitulosInteracionListener mListener;
    private RealmChangeListener listenerRefresco;
    private Context ctx;

    public MyTitulosRecyclerViewAdapter(Context context, List<TitulosDB> items, onTitulosInteracionListener listener) {
        mValues = items;
        mListener = listener;
        ctx = context;
        this.listenerRefresco = new RealmChangeListener<OrderedRealmCollection<TitulosDB>>() {
            @Override
            public void onChange(OrderedRealmCollection<TitulosDB> results) {
                notifyDataSetChanged();
            }
        };

        if (items != null) {
            addListener((OrderedRealmCollection<TitulosDB>) items);
        }
    }

    private void addListener(OrderedRealmCollection<TitulosDB> items) {
        if (items instanceof RealmResults) {
            RealmResults realmResults = (RealmResults) items;
            realmResults.addChangeListener(listenerRefresco);
        } else if (items instanceof RealmList) {
            RealmList<TitulosDB> list = (RealmList<TitulosDB>) items;
            //noinspection unchecke
            list.addChangeListener((RealmChangeListener) listenerRefresco);
        } else {
            throw new IllegalArgumentException("RealmCollection not supported: " + items.getClass());
        }

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

        String urlAdeje = "http://www3.gobiernodecanarias.org/medusa/edublog/iesadeje/wp-content/uploads/sites/189/2019/03/logo_blog.png";
        String urlCedeco = "https://www.cedeco.es/wp-content/uploads/2018/08/Logo-CEDECO-COLOR.jpg";
        String urlPuerto = "https://blog.iespuertodelacruz.es/wp-content/header/Logotipo_IES.png";
        String urlCesar = "https://cifpcesarmanrique.es/wp-content/uploads/2019/01/LogoCMTransparente-BrilloExt-300x93.png";

        if (holder.mItem.getCentro().contains("Adeje")){
            Glide
                    .with(ctx)
                    .load(urlAdeje)
                    //.centerCrop()
                    .into(holder.photo);
        }else if (holder.mItem.getCentro().contains("Cedeco")) {
            Glide
                    .with(ctx)
                    .load(urlCedeco)
                    //.centerCrop()
                    .into(holder.photo);
        }else if (holder.mItem.getCentro().contains("Telesforo")) {
            Glide
                    .with(ctx)
                    .load(urlPuerto)
                    //.centerCrop()
                    .into(holder.photo);
        }else if (holder.mItem.getCentro().contains("Cesar")) {
            Glide
                    .with(ctx)
                    .load(urlCesar)
                    //.centerCrop()
                    .into(holder.photo);
        }

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

        holder.eliminarTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTituloBorrarClick(holder.mItem);
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
        public final ImageView photo;
        public final ImageView editTitulo;
        public final ImageView eliminarTitulo;
        public TitulosDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            centro = (TextView) view.findViewById(R.id.titulos_centro);
            nombre = (TextView) view.findViewById(R.id.titulos_nombre_Titulacion);
            rama = (TextView) view.findViewById(R.id.titulos_rama);
            nota = (TextView) view.findViewById(R.id.titulos_nota);
            editTitulo = (ImageView) view.findViewById(R.id.editar_titulo);
            eliminarTitulo = (ImageView) view.findViewById(R.id.borrar_titulo);
            photo = (ImageView) view.findViewById(R.id.imageView_Laboral_photo);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nombre.getText() + "'";
        }
    }
}
