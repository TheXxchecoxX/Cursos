package com.example.cursojava2.UsuariosFirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cursojava2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginFire extends AppCompatActivity implements View.OnClickListener {
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

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
    String name,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fire);

        setupActionBar();

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        Login.setOnClickListener(this);
        userRegistration = (TextView)findViewById(R.id.tvRegister);
        forgotPassword = (TextView)findViewById(R.id.tvForgotPassword);

        Info.setText("Numero de intentos restantes: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            //finish();

            startActivity(new Intent(LoginFire.this, SecondActivity.class));

        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validacion();
                if(validacion()) {
                    validate(Name.getText().toString(), Password.getText().toString());
                }
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginFire.this, RegAct.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginFire.this, PasswordActivity.class));
            }
        });
    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("Espere un momento por favor");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }else{
                    Toast.makeText(LoginFire.this, "Verifique que los datos estén correctos ", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("Numero de intentos restantes: " + counter);
                    progressDialog.dismiss();
                    if(counter == 0){
                        Login.setEnabled(false);
                    }
                }
            }
        });


    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(LoginFire.this, SecondActivity.class));

    }

    private Boolean validacion(){

        Boolean result = false;

        password = Password.getText().toString();
        name = Name.getText().toString();


        if (name.equals("")){
            Name.setError("Requerido");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            Name.setError("Por favor, introduce una dirección de correo electrónico válida");
            return false;
        }
        else if (password.equals("")){
            Password.setError("Requerido");
        }else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            Password.setError("La contraseña no sigue los parametros");
            return false;
        } else {
            Password.setError(null);
            return true;
        }

        return result;
    }

    private boolean validatePassword() {

        if (password.isEmpty()) {
            Password.setError("El campo no puede estar vacío");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            Password.setError("Password too weak");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }

    @Override
    public void onClick(View v) {

    }

    private boolean validateEmail() {

        if (name.isEmpty()) {
            Name.setError("El campo no puede estar vacío");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            Name.setError("Por favor, introduce una dirección de correo electrónico válida");
            return false;
        } else {
            Name.setError(null);
            return true;
        }
    }



    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Login a Firebase");
        }
    }

}
