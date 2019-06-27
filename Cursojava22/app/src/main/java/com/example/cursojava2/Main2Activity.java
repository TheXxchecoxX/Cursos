package com.example.cursojava2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button btnbotones,btnLayauts,bthCheckBox,bthLista,bthLitView,btnRecycle,btnLogin,btnTabs,btnFregment;
    Button btnCardView,btnFich,btnsharep, btnAlerdialog, btnNotificacion, btnHilos,btnIntetnS,btnLocalizacion;
    Button btnGS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setupActionBar();

        btnbotones = (Button) (findViewById(R.id.btnbotones));
        btnbotones.setOnClickListener(this);

        btnLayauts = (Button) (findViewById(R.id.btnLayauts));
        btnLayauts.setOnClickListener(this);

        bthCheckBox = (Button) (findViewById(R.id.bthCheckBox));
        bthCheckBox.setOnClickListener(this);

        bthLista = (Button) (findViewById(R.id.bthLista));
        bthLista.setOnClickListener(this);
        bthLitView = (Button) (findViewById(R.id.bthLitView));
        bthLitView.setOnClickListener(this);
        btnRecycle = (Button) (findViewById(R.id.btnRecycle));
        btnRecycle.setOnClickListener(this);
        btnLogin = (Button) (findViewById(R.id.btnLogin));
        btnLogin.setOnClickListener(this);

        btnTabs = (Button) (findViewById(R.id.btnTabs));
        btnTabs.setOnClickListener(this);

        btnFregment = (Button) (findViewById(R.id.btnFregment));
        btnFregment.setOnClickListener(this);
        btnCardView = (Button) (findViewById(R.id.btnCardView));
        btnCardView.setOnClickListener(this);
        btnFich = (Button) (findViewById(R.id.btnFich));
        btnFich.setOnClickListener(this);

        btnsharep = (Button) (findViewById(R.id.btnsharep));
        btnsharep.setOnClickListener(this);

        btnAlerdialog = (Button) (findViewById(R.id.btnAlerdialog));
        btnAlerdialog.setOnClickListener(this);

        btnNotificacion = (Button) (findViewById(R.id.btnNotificacion));
        btnNotificacion.setOnClickListener(this);

        btnHilos = (Button) (findViewById(R.id.btnHilos));
        btnHilos.setOnClickListener(this);

        btnIntetnS = (Button) (findViewById(R.id.btnIntetnS));
        btnIntetnS.setOnClickListener(this);

        btnLocalizacion = (Button) (findViewById(R.id.btnLocalizacion));
        btnLocalizacion.setOnClickListener(this);

        btnGS = (Button) (findViewById(R.id.btnGS));
        btnGS.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnbotones) {
            Intent i = new Intent(this, EstilosB.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnLayauts) {
            Intent i = new Intent(this, Activity_ConstraitLayaut.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.bthCheckBox) {
            Intent i = new Intent(this, Activity_Checkandradiobuttons.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnRecycle) {
            Intent i = new Intent(this, ActivityRecicler.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnLogin) {
            Intent i = new Intent(this, Login.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.bthLitView) {
            Intent i = new Intent(this, ActivityListView.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.bthLista) {
            Intent i = new Intent(this, Activity_DropdownList.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnFregment) {
            Intent i = new Intent(this, FragmentActivity.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnCardView) {
            Intent i = new Intent(this, CardViewActivity.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnFich) {
            Intent i = new Intent(this, MemoriainternaActivity.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnsharep) {
            Intent i = new Intent(this, SharedPreferencesActivity.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnAlerdialog) {
            Intent i = new Intent(this, AlerdialogActivity.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnNotificacion) {
            Intent i = new Intent(this, NotificacioesActivity.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnHilos) {
            Intent i = new Intent(this, Hilos.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnIntetnS) {
            Intent i = new Intent(this, Int2.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnLocalizacion) {
            Intent i = new Intent(this, Loc.class);
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnGS) {
            Intent i = new Intent(this, GoogleSeg.class); // Googsegin
            super.startActivity(i);
        }
        if (v.getId() == R.id.btnTabs) {
            Intent i = new Intent(this, MenuActivity.class);
            super.startActivity(i);
        }
    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Curso java");
        }
    }

}
