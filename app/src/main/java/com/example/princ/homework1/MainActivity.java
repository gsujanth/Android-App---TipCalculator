package com.example.princ.homework1;

/*
  Author : Sujanth Babu Guntupalli
*/

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText billValue;
    TextView tip, total_bill, cusPer;
    RadioGroup radioGroup;
    SeekBar seekBar;
    Button bExit;
    SeekBar.OnSeekBarChangeListener seekBarChangeListener,seekBarChangeListener2;

    int tipRB = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("  Tip Calculator");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.tip);
        //actionBar.setTitle("  Tip Calculator");

        billValue = (EditText) findViewById(R.id.editBillVal);
        tip = (TextView) findViewById(R.id.eTip);
        total_bill = (TextView) findViewById(R.id.eTotal);
        cusPer = (TextView) findViewById(R.id.cusPer);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        bExit = (Button) findViewById(R.id.exitButton);

        bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        billValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
                if (billValue.getText().toString().isEmpty()) {
                    billValue.setError("Enter Bill Total");
                    tip.setText(String.valueOf(0.00));
                    total_bill.setText(String.valueOf(0.00));
                } else {
                    double bill_value = Double.parseDouble(billValue.getText().toString());
                    double tip_value = Double.parseDouble(String.format("%.2f",(bill_value * tipRB) / 100));
                    double total_bill_value = Double.parseDouble(String.format("%.2f",bill_value + tip_value));
                    tip.setText(String.valueOf(tip_value));
                    total_bill.setText(String.valueOf(total_bill_value));
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (billValue.getText().toString().isEmpty()) {
                    billValue.setError("Enter Bill Total");
                    tip.setText(String.valueOf(0.00));
                    total_bill.setText(String.valueOf(0.00));
                } else {
                    if (rb.getText().toString().equals("10 %")) {
                        tipRB = 10;
                        //seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
                    } else if (rb.getText().toString().equals("15 %")) {
                        tipRB = 15;
                        //seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
                    } else if (rb.getText().toString().equals("18 %")) {
                        tipRB = 18;
                        //seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
                    } else if (rb.getText().toString().equals("Custom")) {
                        tipRB = seekBar.getProgress();
                        cusPer.setText(String.valueOf(tipRB));
                        double bill_value = Double.parseDouble(billValue.getText().toString());
                        double tip_value = Double.parseDouble(String.format("%.2f",(bill_value * tipRB) / 100));
                        double total_bill_value = Double.parseDouble(String.format("%.2f",bill_value + tip_value));
                        tip.setText(String.valueOf(tip_value));
                        total_bill.setText(String.valueOf(total_bill_value));
                        seekBar.setOnSeekBarChangeListener(seekBarChangeListener2);

                    }
                    double bill_value = Double.parseDouble(billValue.getText().toString());
                    double tip_value = Double.parseDouble(String.format("%.2f",(bill_value * tipRB) / 100));
                    double total_bill_value = Double.parseDouble(String.format("%.2f",bill_value + tip_value));
                    tip.setText(String.valueOf(tip_value));
                    total_bill.setText(String.valueOf(total_bill_value));
                }
            }
        });

        seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (billValue.getText().toString().isEmpty()) {
                    billValue.setError("Enter Bill Total");
                    tip.setText(String.valueOf(0.00));
                    total_bill.setText(String.valueOf(0.00));
                } else {
                    cusPer.setText(String.valueOf(i));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarChangeListener2 = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                cusPer.setText(String.valueOf(i));
                if (billValue.getText().toString().isEmpty()) {
                    billValue.setError("Enter Bill Total");
                    tip.setText(String.valueOf(0.00));
                    total_bill.setText(String.valueOf(0.00));
                } else {
                    tipRB = Integer.parseInt(cusPer.getText().toString());
                    double bill_value = Double.parseDouble(billValue.getText().toString());
                    double tip_value = Double.parseDouble(String.format("%.2f",(bill_value * tipRB) / 100));
                    double total_bill_value = Double.parseDouble(String.format("%.2f",bill_value + tip_value));
                    tip.setText(String.valueOf(tip_value));
                    total_bill.setText(String.valueOf(total_bill_value));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

    }


}
