package com.example.conexionhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listado;
    Button filtrar;
    EditText edit;
    String url = "https://www.datos.gov.co/resource/jj37-fvz6.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listado = findViewById(R.id.list);
        filtrar = findViewById(R.id.filtrar);
        filtrar.setOnClickListener(this);
        edit = findViewById(R.id.edtFil);
        requestDatos(url);

    }

    public void requestDatos(String Url){
        RequestQueue cola = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // dato.setText(response.toString());
                        ParserJson(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        cola.add(jsonArrayRequest);
    }

    public void ParserJson (JSONArray response){
        try{
            String cadena = "";
            ArrayList<Sitios> Site = new ArrayList<Sitios>();
            for (int i =0;i< response.length();i++){
                JSONObject site = response.getJSONObject(i);
                String nombreS = site.getString("nombresitio");
                String tip = site.getString("tipo");
                String descrip = site.getString("descripcion");
                String nombrem = site.getString("nombremunicipio");
                cadena = cadena + nombreS + ", " + tip + ", " + "\n" + descrip + ", " + nombrem + "\n";
                Sitios sitios = new Sitios(nombreS,tip,descrip,nombrem);
                Site.add(sitios);
                SiteAdapter adapter = new SiteAdapter(this,Site);
                listado.setAdapter(adapter);
            }
            //dato.setText(cadena);
        }catch (JSONException e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void alertE(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        alerta.setView(inflater.inflate(R.layout.filtalert, null))
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
                }).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filtrar:
                if(TextUtils.isEmpty(edit.getText().toString())){
                    requestDatos(url);
                }else {
                    String urls = "https://www.datos.gov.co/resource/jj37-fvz6.json?nombremunicipio=" + edit.getText().toString();
                    requestDatos(urls);
                }
                break;
        }
    }
}