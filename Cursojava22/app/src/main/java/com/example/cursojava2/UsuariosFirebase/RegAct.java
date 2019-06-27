package com.example.cursojava2.UsuariosFirebase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cursojava2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegAct extends AppCompatActivity {
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

    private EditText userName, userPassword, userEmail, userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    String email, name, age, password;
    private FirebaseStorage firebaseStorage;
    //private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate2()){
                    //Upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                //sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(RegAct.this, "Registrado con éxito, carga completa!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegAct.this, LoginFire.class));
                            }else{
                                Toast.makeText(RegAct.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegAct.this, LoginFire.class));
            }
        });

    }

    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
        userAge = (EditText)findViewById(R.id.etAge);
    }

    private Boolean validate2(){
        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        age = userAge.getText().toString();

        if(name.isEmpty() || password.isEmpty()|| email.isEmpty()|| age.isEmpty()){
            Toast.makeText(this, "Complete los datos por favor", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        if (name.equals("")){
            userName.setError("Required");
        }
        else if (email.equals("")){
            userEmail.setError("Required");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.setError("Por favor, introduce una dirección de correo electrónico válida");
            return false;
        }
        else if (password.equals("")){
            userPassword.setError("Required");
        }
        else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            userPassword.setError("La contraseña debe teer al menos una Mayuscula una minuscula, 1 numero y contar con 8 caracteres");
            return false;
        }
        else if (age.equals("")){
            userAge.setError("Required");
        }
        else {
            userPassword.setError(null);
            userEmail.setError(null);
            return true;
        }
        return result;
    }


    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        //sendUserData();
                        Toast.makeText(RegAct.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegAct.this, LoginFire.class));
                    }else{
                        Toast.makeText(RegAct.this, "El correo de verificación no ha sido enviado!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}