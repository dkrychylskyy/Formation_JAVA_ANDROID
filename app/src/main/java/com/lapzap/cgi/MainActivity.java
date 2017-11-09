package com.lapzap.cgi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Button btn_valider, btn_anuler, bt_next;
    TextView text_view;
    EditText input_text;
    RadioGroup radio_group;
    RadioButton btn_radio_like;
    RadioButton btn_radio_unlike;
    ImageView image_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_valider = (Button) findViewById(R.id.btn_valider);
        btn_anuler = (Button) findViewById(R.id.btn_anuler);
        bt_next = (Button) findViewById(R.id.bt_next);

        text_view = (TextView) findViewById(R.id.textView2);

        input_text = (EditText) findViewById(R.id.input_text);
        input_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String Value = input_text.getText().toString();
                text_view.setText(Value);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_valider.setOnClickListener(this);
        btn_valider.setOnLongClickListener(this);
        btn_anuler.setOnClickListener(this);
        btn_anuler.setOnLongClickListener(this);
        bt_next.setOnClickListener(this);

        radio_group = (RadioGroup) findViewById(R.id.radio_group);

        btn_radio_like = (RadioButton) findViewById(R.id.btn_radio_like);
        btn_radio_unlike = (RadioButton) findViewById(R.id.btn_radio_unlike);

        image_view = (ImageView) findViewById(R.id.imageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 25, 0, "alertDialog");
        menu.add(0, 26, 0, "DatePicker");
        menu.add(0, 27, 0, "TimePicker");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 25:
                alertDialog();
                break;
            case 26:
                datePicker();
                break;
            case 27:
                timePicker();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void alertDialog() {

        //Préparation de la fenêtre
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //Message
        alertDialogBuilder.setMessage("Hello CGI");

        //titre
        alertDialogBuilder.setTitle("Hello");

        //bouton ok
        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Affiche un toast apres le click sur le bouton ok
                Toast.makeText(MainActivity.this, "Click sur ok", Toast.LENGTH_SHORT).show();
            }
        });

        // Icone
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);

        //Afficher la fenêtre
        alertDialogBuilder.show();
    }

    public void datePicker() {

        //Gestion de la date
        Calendar calendar = Calendar.getInstance();

        //Création de la fenêtre
        //Pour le callback -> Alt+entree -> implémente méthode -> Génère la méthode onTimeSet
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        // Show fenetre
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(this, "date est: " + year + "/" + month + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
    }

    public void timePicker() {
        //(Context, callback, heure, minute, 24h format)
        //Pour le callback -> Alt+entree -> implémente méthode -> Génère la méthode onTimeSet
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 14, 33, true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(this, "l\'heure est : " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == btn_valider) {
            if (btn_radio_like.isChecked()) {
                String value = (btn_radio_like.getText()) + "";
                input_text.setText(value);
                image_view.setColorFilter(Color.GREEN);
            } else if (btn_radio_unlike.isChecked()) {
                String value = (btn_radio_unlike.getText()) + "";
                input_text.setText(value);
                image_view.setColorFilter(Color.RED);
            }

        } else if (v == btn_anuler) {
            radio_group.clearCheck();
            input_text.setText("");
        } else if (v == bt_next) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == btn_valider) {
            image_view.setImageResource(R.drawable.ic_done_black_48dp);
        } else if (v == btn_anuler) {
            image_view.setImageResource(R.drawable.ic_delete_forever_black_48dp);
//            finish();
        }
        return true;
    }


}
