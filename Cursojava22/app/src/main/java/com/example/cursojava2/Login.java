package com.example.cursojava2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    //"(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    private EditText editText1, editText2;
    private Button buttonLA;
    String usurio ;
    String contra ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        buttonLA=(Button) findViewById(R.id.buttonLA);
        buttonLA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonLA) {
            if (validacionLA()) {

                if(usurio.equals("login")&&contra.equals("Aa12Bb12")){
                    Toast.makeText(this,"Dato correcto",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this, Main2Activity.class);
                    super.startActivity(i);
                }
                else {
                    Toast.makeText(this,"Verificar que el usuario y contraseña estén correctos",Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    private Boolean validacionLA(){
        Boolean result = false;

        usurio = editText1.getText().toString();
        contra = editText2.getText().toString();

        /*
        if(name.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Complete los datos por favor", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        */
        if (usurio.equals("")){
            editText1.setError("Requerido");
        }
        else if (contra.equals("")){
            editText2.setError("Requerido");
        }else if (!PASSWORD_PATTERN.matcher(contra).matches()) {
            editText2.setError("La contraseña debe teer al menos una Mayuscula una minuscula, 1 numero y contar con 8 caracteres");
            return false;
        } else {
            editText2.setError(null);
            return true;
        }

        return result;
    }
}

