package com.miker.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private static final String ARG_REGISTRO_ID = "arg_lawyer_id";
    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    private EditText first_name;
    private EditText last_name;
    private EditText email;
    private TextView date;
    private EditText user;
    private EditText password;
    private EditText confirmation;
    private ImageButton button;
    private Usuario usuario;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // initiate a button
        model = (Model) getIntent().getSerializableExtra("model");
        button = (ImageButton) findViewById(R.id.btn);
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        date = (TextView) findViewById(R.id.date);
        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        confirmation = (EditText) findViewById(R.id.confirmation);
        // perform click event on the button
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.date:
                        obtenerFecha();
                        break;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getInformation();
                    Intent intent = new Intent(getApplicationContext(), NavDrawerActivity.class);

                    if (usuario.getPersona().getId() == -1) {
                        model.insertUser(usuario, confirmation.getText().toString());
                    } else {
                        model.updateUser(usuario, confirmation.getText().toString());
                    }
                    intent.putExtra("model", model);
                    startActivityForResult(intent, 0);
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        if (usuario == null) {
            usuario = new Usuario();
        } else {
            setInformation();
        }
    }

    private void obtenerFecha() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                date.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    private void getInformation() {
        usuario.getPersona().setFirst_name(first_name.getText().toString());
        usuario.getPersona().setLast_name(last_name.getText().toString());
        usuario.getPersona().setEmail(email.getText().toString());
        usuario.getPersona().setDate(new Date(date.getText().toString()));
        usuario.setUser(user.getText().toString());
        usuario.setPassword(password.getText().toString());
    }

    private void setInformation() {
        first_name.setText(usuario.getPersona().getFirst_name());
        first_name.setEnabled(false);
        last_name.setText(usuario.getPersona().getLast_name());
        last_name.setEnabled(false);
        email.setText(usuario.getPersona().getEmail());
        email.setEnabled(false);
        date.setText(usuario.getPersona().getDate().toString());
        date.setEnabled(false);
        user.setText(usuario.getUser());
        user.setEnabled(false);
        password.setText(usuario.getPassword());
        password.setEnabled(false);
        confirmation.setEnabled(false);
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
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        super.onBackPressed();
    }
}