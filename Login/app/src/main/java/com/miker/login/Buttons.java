package com.miker.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class Buttons extends AppCompatActivity {

    boolean click = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        //Definicion
        RadioGroup radio_group = findViewById(R.id.radio_group);
        RadioButton radio_button = findViewById(R.id.radio_button);
        ToggleButton toggle_button = findViewById(R.id.toggle_button);
        Switch a_switch = findViewById(R.id.a_switch);
        FloatingActionButton floating_button = findViewById(R.id.floating_button);
        ImageButton image_button = findViewById(R.id.imageButton);
        Button button = findViewById(R.id.button);
        CheckBox checkBox = findViewById(R.id.checkBox);
        ChipGroup chip_group = findViewById(R.id.chip_group);
        Chip chip = findViewById(R.id.chip2);

        //Eventos

       image_button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                  @Override
                  public void onFocusChange (View v, boolean hasFocus){
                     Intent intent = null;
                    intent = new Intent(Buttons.this, FormActivity.class);
               }
       });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Diste click en el button", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        checkBox.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean  onLongClick(View v){
                click = true;
                return click;
            }
        });

        //-----------------------------------------------------------------
        radio_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        toggle_button.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });
        a_switch.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });
        floating_button.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            }
        });

    }
}
