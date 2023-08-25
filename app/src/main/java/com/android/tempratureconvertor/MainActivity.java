package com.android.tempratureconvertor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText celsiusEditText;
    private EditText fahrenheitEditText;
    private EditText kalvinEditText;
    private TextView convertedTextView;

    private TextView DevTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celsiusEditText = findViewById(R.id.celsiusEditText);
        fahrenheitEditText = findViewById(R.id.fahrenheitEditText);
        kalvinEditText = findViewById(R.id.kalvinEditText);
        convertedTextView = findViewById(R.id.convertedTextView);
        TextView DevTxt = findViewById(R.id.txtDeveloper);

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
            }
        });

        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });

        DevTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DevProfile.class);
                startActivity(intent);
            }
        });
    }

    private void clearInputs() {
        celsiusEditText.setText("");
        fahrenheitEditText.setText("");
        kalvinEditText.setText("");
        convertedTextView.setText("");
    }

    private void convertTemperature() {
        String celsiusStr = celsiusEditText.getText().toString();
        String fahrenheitStr = fahrenheitEditText.getText().toString();
        String kalvinStr = kalvinEditText.getText().toString();

        if (!celsiusStr.isEmpty()) {
            double celsiusValue = Double.parseDouble(celsiusStr);
            double fahrenheitValue = (celsiusValue * 9 / 5) + 32;
            double kalvinValue = celsiusValue + 273.15;

            fahrenheitEditText.setText(String.format("%.2f", fahrenheitValue));
            kalvinEditText.setText(String.format("%.2f", kalvinValue));
            convertedTextView.setText(celsiusValue + "°C = " + fahrenheitValue + "°F = " + kalvinValue + "°K");
        } else if (!fahrenheitStr.isEmpty()) {
            double fahrenheitValue = Double.parseDouble(fahrenheitStr);
            double celsiusValue = (fahrenheitValue - 32) * 5 / 9;
            double kalvinValue = celsiusValue + 273.15;

            celsiusEditText.setText(String.format("%.2f", celsiusValue));
            kalvinEditText.setText(String.format("%.2f", kalvinValue));
            convertedTextView.setText(fahrenheitValue + "°F = " + celsiusValue + "°C = " + kalvinValue + "°K");
        } else if (!kalvinStr.isEmpty()) {
            double kalvinValue = Double.parseDouble(kalvinStr);
            double celsiusValue = kalvinValue - 273.15;
            double fahrenheitValue = (celsiusValue * 9 / 5) + 32;

            celsiusEditText.setText(String.format("%.2f", celsiusValue));
            fahrenheitEditText.setText(String.format("%.2f", fahrenheitValue));
            convertedTextView.setText(kalvinValue + "°K = " + celsiusValue + "°C = " + fahrenheitValue + "°F");
        } else {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
        }
    }
}
