package com.example.cursojava2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cursojava2.utils.ArquivoDB;

import java.util.HashMap;

public class SharedPreferencesActivity extends AppCompatActivity {

    private EditText edtEmail;
    private RadioGroup rgPegmento;
    private CheckBox cbNovidades;
    private Button btnguardar;

    private ArquivoDB arquivoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);


        initViews();
        initArquivoDB();
    }

    //metodo


    @Override
    protected void onResume() {
        super.onResume();
        carregar();
    }

    private void initArquivoDB(){
        //pasar context aplication
        //esse context possul todas as information de aplicacion
        try {
            arquivoDB = new ArquivoDB(getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, R.string.arquivoDB_fail ,Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        rgPegmento = (RadioGroup) findViewById(R.id.rgPegmento);
        cbNovidades = (CheckBox) findViewById(R.id.cbNovidades);
        btnguardar = (Button) findViewById(R.id.btnGuardar);

    }

    //metodo para validar campos de activity
    public boolean validarCampos(){
        if(Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()//verifica si es un email
                //verifica el radio es seleccionado, -1 significa que fue seleccionado
                && rgPegmento.getCheckedRadioButtonId() != -1){
            return true;
        }
        return false;
    }

    //metodo para boton guardar
    public void guardar(View view){

        if (!validarCampos()){
            Toast.makeText(this, R.string.llenarcampos,Toast.LENGTH_SHORT).show();
            return;
        }

        //se validar os campos, salvar no SharedPreferences
        try {
            String email = edtEmail.getText().toString();
            // pagar texto del radio
            RadioButton rb = (RadioButton) findViewById(rgPegmento.getCheckedRadioButtonId());
            String formaPagamento =  rb.getText().toString();
            String novidades = (cbNovidades.isChecked()) ? "si" : "no";

            //salvar
            HashMap<String, String> map = new HashMap<>();
            map.put("EMAIL",email);
            map.put("PAGAMENTO", formaPagamento);
            map.put("NOVIDADES", novidades);

            arquivoDB.escreverTodasAsChaves(map);
            Toast.makeText(this, R.string.guardado_correcto, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    //metodo para guardar arquivo
    public void carregar(){
        try {
            HashMap<String, String> map = arquivoDB.lerTodasAsChaves(new String[]{"EMAIL","PAGAMENTO","NOVIDADES"});

            edtEmail.setText(map.get("EMAIL"));

            int gtdFilhosNoRadioGroup = rgPegmento.getChildCount();

            for (int i = 0; i < gtdFilhosNoRadioGroup; i++ ){
                if(rgPegmento.getChildAt(i) instanceof RadioButton){
                    RadioButton rb = (RadioButton) rgPegmento.getChildAt(i);
                    if (rb.getText().toString().equals(map.get("PAGAMENTO"))){
                        rb.setChecked(true);// marcar como cheked
                    }
                }
            }

            //Marcar checkbox
            if (map.get("NOVIDADES").equals("si")){
                cbNovidades.setChecked(true);
            }

            Toast.makeText(this, R.string.cargado, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
