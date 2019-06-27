package com.example.cursojava2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Int2 extends AppCompatActivity {
    private Button btnEjecutar;
    private ProgressBar pbarProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int2);


        btnEjecutar = (Button)findViewById(R.id.btnEjecutar);
        pbarProgreso = (ProgressBar)findViewById(R.id.pbarProgreso);

        btnEjecutar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent msgIntent = new Intent(Int2.this, MiIntentService.class);
                msgIntent.putExtra("iteraciones", 10);
                startService(msgIntent);
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(MiIntentService.ACTION_PROGRESO);
        filter.addAction(MiIntentService.ACTION_FIN);
        ProgressReceiver rcv = new ProgressReceiver();
        registerReceiver(rcv, filter);
    }

    public class ProgressReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(MiIntentService.ACTION_PROGRESO)) {
                int prog = intent.getIntExtra("progreso", 0);
                pbarProgreso.setProgress(prog);
            }
            else if(intent.getAction().equals(MiIntentService.ACTION_FIN)) {
                Toast.makeText(Int2.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
