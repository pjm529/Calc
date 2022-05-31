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

        // 현재 textview 값
        String current = text.getText().toString();

        // textview가 0이면 0지우고 숫자 입력
        if(current.equals("0")) {
            text.setText("");
            current = text.getText().toString();
        }

        // 최대 자릿 수 지정
        if(current.length() > 10 && view.getId() != R.id.btn_AC) {
            Toast.makeText(this, "최대 숫자를 넘어섰습니다.", Toast.LENGTH_SHORT).show();
            return;
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

        check();
    }

    // 숫자 변환기
    public void check() {
        String temp = text.getText().toString();
        String current;

        temp = temp.replaceAll(",", "");

        if(temp.length() > 6) {
            System.out.println(6);
            current = temp.substring(0, temp.length() - 6) + "," + temp.substring(temp.length() - 6, temp.length() - 3) + "," + temp.substring(temp.length() - 3, temp.length());
            text.setText(current);
        } else if(temp.length() > 3) {
            System.out.println(3);
            current = temp.substring(0, temp.length() - 3) + "," + temp.substring(temp.length() - 3, temp.length());
            text.setText(current);
        }
    }
}