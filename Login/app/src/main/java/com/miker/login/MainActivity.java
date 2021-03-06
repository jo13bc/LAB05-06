package com.miker.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;
    private Usuario usuario;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initiate a button
        ImageButton loginButton =  (ImageButton ) findViewById(R.id.btn);
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        usuario = new Usuario();
        // perform click event on the button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Usuario usuario = model.login(user.getText().toString(), password.getText().toString());
                    if (usuario != null) {
                        openActivity(usuario);
                    } else {
                        Toast.makeText(getApplicationContext(), "¡Datos incorrectos!", Toast.LENGTH_LONG).show();  // display a toast message
                    }
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
       TextView registerButton = findViewById(R.id.texto_registrarse);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                intent.putExtra("model", model);
                startActivityForResult(intent, 0);
            }
        });
        TextView passwordButton = findViewById(R.id.texto_olvidaste_contrasena);
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PasswordActivity.class);
                intent.putExtra("model", model);
                startActivityForResult(intent, 0);
            }
        });
        model = (Model) getIntent().getSerializableExtra("model");
        if(model == null){
            model = new Model();
        }
    }

    private void openActivity(Usuario usuario){
        Intent intent;
        String message;
        if(usuario.isAdmin()){
            intent = new Intent(getApplicationContext(), NavDrawerActivity.class);
            message = "Administrador";
        }else {
            intent = new Intent(getApplicationContext(), NavDrawerActivity.class);
            message = "Usuario";
        }
        intent.putExtra("model", model);
        startActivityForResult(intent, 0);
        Toast.makeText(getApplicationContext(), "¡Bienvenido " + message + "!", Toast.LENGTH_LONG).show();  // display a toast message
    }
}
