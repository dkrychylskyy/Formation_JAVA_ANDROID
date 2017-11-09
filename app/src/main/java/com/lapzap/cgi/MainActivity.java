package com.lapzap.cgi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    Button btn_valider;
    Button btn_anuler;
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

        radio_group = (RadioGroup) findViewById(R.id.radio_group);

        btn_radio_like = (RadioButton) findViewById(R.id.btn_radio_like);
        btn_radio_unlike = (RadioButton) findViewById(R.id.btn_radio_unlike);

        image_view = (ImageView) findViewById(R.id.imageView);
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
}
