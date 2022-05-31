package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Calculator");

        text = findViewById(R.id.text);
    }

    public void onClick(View view) {
        String current = text.getText().toString();

        if(current.equals("0")) {
            text.setText("");
            current = text.getText().toString();
        }

        switch (view.getId()) {
            case R.id.btn_0:
                text.setText(current + "0");
                break;
            case R.id.btn_1:
                text.setText(current + "1");
                break;
            case R.id.btn_2:
                text.setText(current + "2");
                break;
            case R.id.btn_3:
                text.setText(current + "3");
                break;
            case R.id.btn_4:
                text.setText(current + "4");
                break;
            case R.id.btn_5:
                text.setText(current + "5");
                break;
            case R.id.btn_6:
                text.setText(current + "6");
                break;
            case R.id.btn_7:
                text.setText(current + "7");
                break;
            case R.id.btn_8:
                text.setText(current + "8");
                break;
            case R.id.btn_9:
                text.setText(current + "9");
                break;
            case R.id.btn_dot:
                text.setText(current + ".");
                break;
            case R.id.btn_AC:
                text.setText("0");
                break;
        }
    }
}