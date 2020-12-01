package com.example.conexionhttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SiteAdapter extends ArrayAdapter {
    public SiteAdapter(@NonNull Context context,@NonNull ArrayList<Sitios>sitios) {
        super(context, 0,sitios);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Sitios site = (Sitios) getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fila_sitios,parent,false);
        }
        TextView nombre = (TextView) convertView.findViewById(R.id.nombretxt);
        TextView tipo = (TextView) convertView.findViewById(R.id.tipotxt);
        TextView descrip = (TextView) convertView.findViewById(R.id.descriptxt);
        TextView nombrem = (TextView) convertView.findViewById(R.id.nombremtxt);

        nombre.setText(site.getNombreS());
        tipo.setText(site.getTipo());
        descrip.setText(site.getDescripcion());
        nombrem.setText(site.getNombreM());
        return convertView;
    }
}
