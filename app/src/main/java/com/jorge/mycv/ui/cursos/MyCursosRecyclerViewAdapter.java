package com.jorge.mycv.ui.cursos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.jorge.mycv.CursosDB;
import com.jorge.mycv.LaboralDB;
import com.jorge.mycv.R;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;


public class MyCursosRecyclerViewAdapter extends RecyclerView.Adapter<MyCursosRecyclerViewAdapter.ViewHolder> {

    private final RealmResults<CursosDB> mValues;
    private final onCursosInteractionListener mListener;
    private RealmChangeListener listenerRefresco;
    private Context ctx;

    public MyCursosRecyclerViewAdapter(Context context, RealmResults<CursosDB> items, onCursosInteractionListener listener) {
        ctx = context;
        mValues = items;
        mListener = listener;
        this.listenerRefresco = new RealmChangeListener<OrderedRealmCollection<CursosDB>>() {
            @Override
            public void onChange(OrderedRealmCollection<CursosDB> results) {
                notifyDataSetChanged();
            }
        };

        if (items != null) {
            addListener((OrderedRealmCollection<CursosDB>) items);
        }
    }

    private void addListener(OrderedRealmCollection<CursosDB> items) {
        if (items instanceof RealmResults) {
            RealmResults realmResults = (RealmResults) items;
            realmResults.addChangeListener(listenerRefresco);
        } else if (items instanceof RealmList) {
            RealmList<CursosDB> list = (RealmList<CursosDB>) items;
            //noinspection unchecke
            list.addChangeListener((RealmChangeListener) listenerRefresco);
        } else {
            throw new IllegalArgumentException("RealmCollection not supported: " + items.getClass());
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cursos_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tituloCurso.setText(mValues.get(position).getTitulo());
        holder.empresaImparte.setText(mValues.get(position).getQuienImparte());
        holder.horasCurso.setText(mValues.get(position).getHorasFormacion()+" Hr");


        String urlKeepCoding = "https://www.eu-startups.com/wp-content/uploads/2017/06/Logo-KC.png";
        String urlUPV = "http://www.howdeniberia.com/wp-content/uploads/2015/07/UPV.gif";
        String urlOpenWebinar = "https://www.linuxadictos.com/wp-content/uploads/openwebinars-logo.jpg";
        String urlAppBrewery = "https://pbs.twimg.com/profile_images/608304087021572096/Dnig5VDZ_400x400.png";

        if (mValues.get(position).getQuienImparte().equalsIgnoreCase("UPV" )){
            Glide
                    .with(ctx)
                    .load(urlUPV)
                    .centerCrop()
                    .into(holder.logoEmpresa);
        }else if (mValues.get(position).getQuienImparte().equalsIgnoreCase("OpenWebinar")){
            Glide
                    .with(this.ctx)
                    .load(urlOpenWebinar)
                    .centerCrop()
                    .into(holder.logoEmpresa);
        }else if (mValues.get(position).getQuienImparte().equalsIgnoreCase("keepCoding")){
            Glide
                    .with(ctx)
                    .load(urlKeepCoding)
                    .into(holder.logoEmpresa);
        }else if (mValues.get(position).getQuienImparte().equalsIgnoreCase("AppBrewery")){
            Glide
                    .with(ctx)
                    .load(urlAppBrewery)
                    .into(holder.logoEmpresa);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onCursosClick(holder.mItem);
                }
            }
        });
        holder.editarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onCursosEditClick(holder.mItem);
                }
            }
        });
        holder.eliminaCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mListener){
                    mListener.onCursosBorrarClick(holder.mItem);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tituloCurso;
        public final TextView empresaImparte;
        public final TextView horasCurso;
        public final ImageView logoEmpresa;
        public final ImageView editarCurso;
        public final ImageView eliminaCurso;
        public CursosDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tituloCurso =  view.findViewById(R.id.titulos_centro);
            empresaImparte = (TextView) view.findViewById(R.id.titulos_nombre_Titulacion);
            horasCurso = (TextView) view.findViewById(R.id.titulos_nota);
            logoEmpresa = (ImageView) view.findViewById(R.id.imageView_Laboral_photo);
            editarCurso = (ImageView) view.findViewById(R.id.Editar_curso);
            eliminaCurso = (ImageView) view.findViewById(R.id.borrar_curso);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + empresaImparte.getText() + "'";
        }
    }
}
