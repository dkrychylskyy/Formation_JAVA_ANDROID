package com.lapzap.cgi;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

    static final int ID_MENU_ALERT_DIALOG = 25;
    static final int ID_MENU_DATA_PICKER = 26;
    static final int ID_MENU_TIME_PICKER = 27;
    static final int ID_MENU_SERVICE = 28;


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
        menu.add(0, ID_MENU_ALERT_DIALOG, 0, "alertDialog");
        menu.add(0, ID_MENU_DATA_PICKER, 0, "DatePicker");
        menu.add(0, ID_MENU_TIME_PICKER, 0, "TimePicker");
        menu.add(0, ID_MENU_SERVICE, 0, "Service");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ID_MENU_ALERT_DIALOG:
                alertDialog();
                break;
            case ID_MENU_DATA_PICKER:
                datePicker();
                break;
            case ID_MENU_TIME_PICKER:
                timePicker();
                break;
            case ID_MENU_SERVICE:
                getPermission();
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
        }
        return true;
    }


    // Test permissons
    public void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(this, ServiceActivity.class);
            startActivity(intent);

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 25);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] gr) {
        super.onRequestPermissionsResult(requestCode, permissions, gr);
        //Est ce que c'est la permission qu'on a demandé ?
        if (requestCode == 25) {
            //On verifie la réponse
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Veuliez autoriser cette application á acceder á la position.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
