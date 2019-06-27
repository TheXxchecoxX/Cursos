package com.example.cursojava2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityRecicler extends AppCompatActivity {
    private RecyclerView recView;
    private Button btnInsertar;
    private Button btnEliminar;
    private Button btnMover;

    private ArrayList<Titular> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler);


        datos = new ArrayList<Titular>();
        for(int i=1; i<50; i++)
            datos.add(new Titular("Título " + i, "Subtítulo item " + i));

        recView = (RecyclerView) findViewById(R.id.RecView);
        recView.setHasFixedSize(true);

        final AdaptadorTitulares adaptador = new AdaptadorTitulares(datos);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + recView.getChildPosition(v));
            }
        });

        recView.setAdapter(adaptador);

        recView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //recView.setLayoutManager(new GridLayoutManager(this,3));

        recView.addItemDecoration(
                new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        recView.setItemAnimator(new DefaultItemAnimator());
    }
}
