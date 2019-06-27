package com.example.cursojava2.UsuariosFirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cursojava2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {
    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordEmail = (EditText)findViewById(R.id.etPasswordEmail);
        resetPassword = (Button)findViewById(R.id.btnPasswordReset);
        firebaseAuth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validacion3() | !validateEmail3()) {
                    String useremail = passwordEmail.getText().toString().trim();

                    if (useremail.equals("")) {
                        Toast.makeText(PasswordActivity.this, "Por favor ingrese su correo electrónico registrado", Toast.LENGTH_SHORT).show();
                    } else {
                        firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PasswordActivity.this, "Correo electrónico de restablecimiento de contraseña enviado!", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(PasswordActivity.this, LoginFire.class));
                                } else {
                                    Toast.makeText(PasswordActivity.this, "Error al enviar el email de restablecimiento de contraseña", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });

    }

    private Boolean validacion3(){
        Boolean result = false;

        email = passwordEmail.getText().toString();


        if(email.isEmpty()){
            Toast.makeText(this, "El campo esta vacío ", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        if (email.equals("")){
            passwordEmail.setError("Required");
        }

        return result;
    }

    private boolean validateEmail3() {

        if (email.isEmpty()) {
            passwordEmail.setError("El campo no puede estar vacío");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            passwordEmail.setError("Por favor, introduce una dirección de correo electrónico válida");
            return false;
        } else {
            passwordEmail.setError(null);
            return true;
        }
    }


}
