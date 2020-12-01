package com.example.conexionhttp;

import android.os.Parcel;
import android.os.Parcelable;

public class Sitios implements Parcelable {
    private String nombreS;
    private String tipo;
    private String descripcion;
    private String nombreM;

    public Sitios(String nombreS, String tipo, String descripcion, String nombreM) {
        this.nombreS = nombreS;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.nombreM = nombreM;
    }

    protected Sitios(Parcel in) {
        nombreS = in.readString();
        tipo = in.readString();
        descripcion = in.readString();
        nombreM = in.readString();
    }

    public static final Creator<Sitios> CREATOR = new Creator<Sitios>() {
        @Override
        public Sitios createFromParcel(Parcel in) {
            return new Sitios(in);
        }

        @Override
        public Sitios[] newArray(int size) {
            return new Sitios[size];
        }
    };

    public String getNombreS() {
        return nombreS;
    }

    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreM() {
        return nombreM;
    }

    public void setNombreM(String nombreM) {
        this.nombreM = nombreM;
    }

    @Override
    public String toString() {
        return "Sitios{" +
                "nombreS='" + nombreS + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", nombreM='" + nombreM + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreS);
        dest.writeString(tipo);
        dest.writeString(descripcion);
        dest.writeString(nombreM);
    }
}

