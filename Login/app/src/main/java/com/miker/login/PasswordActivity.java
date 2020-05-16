package com.miker.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordActivity extends AppCompatActivity {
    private EditText old_password;
    private EditText new_password;
    private EditText confirm_password;
    private EditText user_mame;
    private ImageButton button;
    private Usuario usuario;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        // initiate a button
        model = (Model) getIntent().getSerializableExtra("model");
        button = (ImageButton) findViewById(R.id.btn);
        old_password = (EditText) findViewById(R.id.old_password);
        new_password = (EditText) findViewById(R.id.new_password);
        user_mame = (EditText) findViewById(R.id.user);
        confirm_password = (EditText) findViewById(R.id.confirm_password);

        // perform click event on the button

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(validate()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        model.getLoggedUser().setPassword(new_password.getText().toString());
                        intent.putExtra("model", model);
                        startActivityForResult(intent, 0);
                    }
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

   private void setSelected(Spinner spinner, String[] options, String option) {
        int aux = 0;
        for (String o : options) {
            if (o.equals(option)) {
                spinner.setSelection(aux);
                break;
            }
            aux++;
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(this, NavDrawerActivity.class);
        a.putExtra("model", model);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        super.onBackPressed();
    }

    private boolean validate() throws Exception {
        boolean result = true;

        Usuario us = model.login(user_mame.getText().toString(), old_password.getText().toString());
        if (user_mame.getText().toString().isEmpty()) {
            user_mame.setError("The Old Password can't be empty");
            result = false;
        }
        if (old_password.getText().toString().isEmpty()) {
            old_password.setError("The Old Password can't be empty");
            result = false;
        }
        if (new_password.getText().toString().isEmpty()) {
            new_password.setError("The New Password can't be empty");
            result = false;
        }
        if (confirm_password.getText().toString().isEmpty()) {
            confirm_password.setError("The Confirm Password can't be empty");
            result = false;
        }
        if (confirm_password.getText().toString() != new_password.getText().toString()) {
            confirm_password.setError("The Confirm Password is not the same than the New Password");
            result = false;
        }
        if (us == null) {
            old_password.setError("Usuario o Password Incorrecto");
            result = false;
        }

        return result;
    }
}