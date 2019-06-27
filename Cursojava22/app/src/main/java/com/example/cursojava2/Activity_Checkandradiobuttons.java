package com.example.cursojava2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Activity_Checkandradiobuttons extends AppCompatActivity {
    private TextView lblMensaje;
    private RadioButton rbOpcion1;
    private RadioButton rbOpcion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__checkandradiobuttons);


        lblMensaje = (TextView)findViewById(R.id.LblSeleccion);
        rbOpcion1 = (RadioButton)findViewById(R.id.RbOpcion1);
        rbOpcion2 = (RadioButton)findViewById(R.id.RbOpcion2);

        View.OnClickListener list = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opcion = "";
                switch(view.getId()) {
                    case R.id.RbOpcion1:
                        opcion = "opción 1";
                        break;
                    case R.id.RbOpcion2:
                        opcion = "opción 2";
                        break;
                }

                lblMensaje.setText("ID opción seleccionada: " + opcion);
            }
        };

        //para iniciar el texto del tesxView
        rbOpcion1.setOnClickListener(list);
        rbOpcion2.setOnClickListener(list);

        RadioGroup rgOpciones = (RadioGroup) findViewById(R.id.GrbGrupo1);
        rgOpciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                String opcion = "";
                switch(checkedId) {
                    case R.id.RbOpcion1:
                        opcion = "opción 1";
                        break;
                    case R.id.RbOpcion2:
                        opcion = "opción 2";
                        break;
                }

                lblMensaje.setText("ID opción seleccionada: " + opcion);
            }
        });

        final CheckBox cbMarcame = (CheckBox) findViewById(R.id.ChkMarcame);

        cbMarcame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();

                if (isChecked) {
                    cbMarcame.setText("Checkbox marcado!");
                }
                else {
                    cbMarcame.setText("Checkbox desmarcado!");
                }
            }
        });

        RadioGroup rg = (RadioGroup)findViewById(R.id.GrbGrupo1);
        rg.clearCheck();
        rg.check(R.id.RbOpcion1);
        int idSeleccionado = rg.getCheckedRadioButtonId();

    }
}
