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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class FormActivity extends AppCompatActivity {
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
    private EditText address_1;
    private EditText address_2;
    private EditText city;
    private EditText state;
    private EditText code;
    private Spinner country;
    private EditText email;
    private EditText phone_area;
    private EditText phone_number;
    private Spinner posicion;
    private TextView date;
    private ImageButton button;
    private Aplication aplication;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        // initiate a button
        model = (Model) getIntent().getSerializableExtra("model");
        button = (ImageButton) findViewById(R.id.btn);
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        address_1 = (EditText) findViewById(R.id.address_1);
        address_2 = (EditText) findViewById(R.id.address_2);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        code = (EditText) findViewById(R.id.code);
        country = (Spinner) findViewById(R.id.country);
        email = (EditText) findViewById(R.id.email);
        phone_area = (EditText) findViewById(R.id.phone_area);
        phone_number = (EditText) findViewById(R.id.phone_number);
        posicion = (Spinner) findViewById(R.id.position);
        date = (TextView) findViewById(R.id.date);
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
        posicion.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, model.position));
        country.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, model.country));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    getInformation();
                    Intent intent = new Intent(getApplicationContext(), NavDrawerActivity.class);

                    if (aplication.getId() == -1) {
                        model.insertAplication(aplication);
                    } else {
                        model.updateAplication(aplication);
                    }
                    intent.putExtra("model", model);
                    startActivityForResult(intent, 0);
                }
            }
        });
        aplication = (Aplication) getIntent().getSerializableExtra("aplication");
        if (aplication == null) {
            aplication = new Aplication();
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
        aplication.setFirst_name(first_name.getText().toString());
        aplication.setLast_name(last_name.getText().toString());
        aplication.setAddress_1(address_1.getText().toString());
        aplication.setAddress_2(address_2.getText().toString());
        aplication.setCity(city.getText().toString());
        aplication.setState(state.getText().toString());
        aplication.setCode(code.getText().toString());
        aplication.setCountry(country.getSelectedItem().toString());
        aplication.setEmail(email.getText().toString());
        aplication.setPhone_area(phone_area.getText().toString());
        aplication.setPhone_number(phone_number.getText().toString());
        aplication.setPosicion(posicion.getSelectedItem().toString());
        aplication.setDate(new Date(date.getText().toString()));
    }

    private void setInformation() {
        first_name.setText(aplication.getFirst_name());
        first_name.setEnabled(false);
        last_name.setText(aplication.getLast_name());
        last_name.setEnabled(false);
        address_1.setText(aplication.getAddress_1());
        address_1.setEnabled(false);
        address_2.setText(aplication.getAddress_2());
        address_2.setEnabled(false);
        city.setText(aplication.getCity());
        city.setEnabled(false);
        state.setText(aplication.getState());
        state.setEnabled(false);
        code.setText(aplication.getCode());
        code.setEnabled(false);
        setSelected(country, model.country, aplication.getCountry());
        country.setEnabled(false);
        email.setText(aplication.getEmail());
        email.setEnabled(false);
        phone_area.setText(aplication.getPhone_area());
        phone_area.setEnabled(false);
        phone_number.setText(aplication.getPhone_number());
        phone_number.setEnabled(false);
        setSelected(posicion, model.position, aplication.getPosicion());
        posicion.setEnabled(false);
        date.setText(aplication.getDate().toString());
        date.setEnabled(false);
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

    private boolean validate() {
        boolean result = true;
        if (first_name.getText().toString().isEmpty()) {
            first_name.setError("First name can't be empty");
            result = false;
        }
        if (last_name.getText().toString().isEmpty()) {
            last_name.setError("Last name can't be empty");
            result = false;
        }
        if (address_1.getText().toString().isEmpty()) {
            address_1.setError("First adress line can't be empty");
            result = false;
        }
        if (address_2.getText().toString().isEmpty()) {
            address_2.setError("Second address line can't be empty");
            result = false;
        }
        if (city.getText().toString().isEmpty()) {
            city.setError("City can't be empty");
            result = false;
        }
        if (state.getText().toString().isEmpty()) {
            state.setError("State can't be empty");
            result = false;
        }
        if (code.getText().toString().isEmpty()) {
            code.setError("Portal/Zip code can't be empty");
            result = false;
        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Email can't be empty");
            result = false;
        }
        if (phone_area.getText().toString().isEmpty()) {
            phone_area.setError("Phone area can't be empty");
            result = false;
        }
        if (phone_number.getText().toString().isEmpty()) {
            phone_number.setError("Phone number can't be empty");
            result = false;
        }
        if (date.getText().toString().isEmpty()) {
            date.setError("Date can't be empty");
            result = false;
        }
        return result;
    }
}