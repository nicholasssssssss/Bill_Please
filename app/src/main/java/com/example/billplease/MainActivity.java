package com.example.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
public class MainActivity extends AppCompatActivity {
    TextView tvDisplay;
    TextView tvDisplay2;
    Button btnSplit;
    EditText etInput;
    EditText etInput2;
    EditText etInput3;
    ToggleButton tglButton;
    ToggleButton tglButton2;
    RadioGroup rgPayment;
    Button reset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplay = findViewById(R.id.textViewDisplay);
        tvDisplay2 = findViewById(R.id.textViewDisplay2);
        btnSplit = findViewById(R.id.buttonDisplay);
        etInput = findViewById(R.id.editTextInput);
        etInput2 = findViewById(R.id.editTextPhone2);
        etInput3 = findViewById(R.id.editTextPhone3);
        tglButton = findViewById(R.id.toggleSVSEnabled);
        tglButton2 = findViewById(R.id.toggleGSTEnabled);
        rgPayment = findViewById(R.id.radioGroupPayment);
        reset = findViewById(R.id.button2);


        if(etInput.getText().toString().trim().length()!=0 && etInput2.getText().toString().trim().length()!=0) {
            double total = 0.0;
            if (!tglButton.isChecked() && !tglButton2.isChecked()) {
                total = Double.parseDouble(etInput.getText().toString());
            } else if (tglButton.isChecked() && !tglButton2.isChecked()) {
                total = Double.parseDouble(etInput.getText().toString()) * 1.1;
            } else if (!tglButton.isChecked() && tglButton2.isChecked()) {
                total = Double.parseDouble(etInput.getText().toString()) * 1.07;
            } else {
                total = Double.parseDouble(etInput.getText().toString()) * 1.17;
            }

            if (etInput3.getText().toString().trim().length() != 0) {
                total *= 1 - Double.parseDouble(etInput3.getText().toString()) / 100;

                tvDisplay.setText("Total Bill: $" + String.format("%.2f", total));

                double finalTotal = total;
                btnSplit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int numPerson = Integer.parseInt(etInput2.getText().toString());
                        int checkedRadioId = rgPayment.getCheckedRadioButtonId();
                        if (numPerson != 1 && checkedRadioId == R.id.radioButton3) {
                            tvDisplay2.setText("Each Pays: $" + String.format("%.2f", finalTotal / numPerson));
                        } else if ((numPerson != 1 && checkedRadioId == R.id.radioButton4)) {
                            tvDisplay2.setText("Each Pays: $" + String.format("%.2f %s", finalTotal / numPerson, "via Paynow to 91234567"));
                        } else if ((numPerson == 1 && checkedRadioId == R.id.radioButton4)) {
                            tvDisplay2.setText("Each Pays: $" + String.format("%.2f %s", finalTotal, "via Paynow to 91234567"));
                        } else {
                            tvDisplay2.setText("Each Pays: $" + finalTotal);
                        }
                    }
                });
            }
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInput.setText("");
                etInput2.getText().clear();
                tglButton.setChecked(false);
                tglButton2.setChecked(false);
                etInput3.setText("");

            }
        });
    }
}
