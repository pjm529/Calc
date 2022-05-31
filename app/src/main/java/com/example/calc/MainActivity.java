package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private double value;
    private char operator;
    private double total = 0;
    private boolean init = true;
    private boolean result = false;

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
        if(current.equals("0") && view.getId() != R.id.btn_dot ) {
            text.setText("");
            current = text.getText().toString();
        }

        if(result){
            text.setText("");
            current = text.getText().toString();
            result = false;
        }

        // 최대 자릿 수 지정
        if(current.length() > 10
                && view.getId() != R.id.btn_AC
                && view.getId() != R.id.btn_dot
                && view.getId() != R.id.btn_eq
                && view.getId() != R.id.btn_plus
                && view.getId() != R.id.btn_min
                && view.getId() != R.id.btn_mul
                && view.getId() != R.id.btn_div) {
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
                if(checkDot()) {
                    text.setText(current + ".");
                }
                break;

            case R.id.btn_AC:
                text.setText("0");
                total = 0;
                init = true;
                break;

            case R.id.btn_plus:
                if(init){
                    total = Double.parseDouble(current.replaceAll(",", ""));
                    init = false;
                } else {
                    value = Double.parseDouble(current.replaceAll(",", ""));
                    calc(value);
                }
                text.setText("");
                operator = '+';
                break;

            case R.id.btn_min:
                if(init){
                    total = Double.parseDouble(current.replaceAll(",", ""));
                    init = false;
                } else {
                    value = Double.parseDouble(current.replaceAll(",", ""));
                    calc(value);
                }
                text.setText("");
                operator = '-';
                break;

            case R.id.btn_mul:
                if(init){
                    total = Double.parseDouble(current.replaceAll(",", ""));
                    init = false;
                } else {
                    value = Double.parseDouble(current.replaceAll(",", ""));
                    calc(value);
                }
                text.setText("");
                operator = '*';
                break;

            case R.id.btn_div:
                if(init){
                    total = Double.parseDouble(current.replaceAll(",", ""));
                    init = false;
                } else {
                    value = Double.parseDouble(current.replaceAll(",", ""));
                    calc(value);
                }
                text.setText("");
                operator = '/';
                break;

            case R.id.btn_eq:
                value = Double.parseDouble(current.replaceAll(",", ""));
                calc(value);

                System.out.println(total);
                if(total > 999999999) {
                    Toast.makeText(this, "최대 숫자를 넘어섰습니다.", Toast.LENGTH_SHORT).show();
                    break;
                }

                init = true;
                result = true;
                rmZero(Double.toString(total));
                break;
        }

        check();

    }

    public void calc(Double value) {
        switch(operator) {
            case '+':
                total += value;
                break;
            case '-':
                total -= value;
                break;
            case '*':
                total *= value;
                break;
            case '/':
                total /= value;
                break;
        }
    }

    // 소숫점 확인
    public boolean checkDot(){
        String current = text.getText().toString();

        int n = current.indexOf(".");

        if(n != -1)
            return false;

        return true;
    }

    // 숫자 변환기
    public void check() {
        String temp = text.getText().toString();
        String current;
        String dot = "";

        temp = temp.replaceAll(",", "");
        int n = temp.indexOf(".");

        // 소숫점을 찾았을 경우
        if(n != -1){
            // 소숫점 아래 문자열
            dot = temp.substring(n, temp.length());

            // 정수
            temp = temp.substring(0, n);
        }

        if(temp.length() > 6) {
            current = temp.substring(0, temp.length() - 6) + "," + temp.substring(temp.length() - 6, temp.length() - 3) + "," + temp.substring(temp.length() - 3, temp.length());
            text.setText(current + dot);


        } else if(temp.length() > 3) {
            current = temp.substring(0, temp.length() - 3) + "," + temp.substring(temp.length() - 3, temp.length());
            text.setText(current + dot);
        }
    }

    // 소수점 0 지우기
    public void rmZero(String temp){
        String dot = "";
        int n = temp.indexOf(".");

        // 소숫점을 찾았을 경우
        if(n != -1){
            // 소숫점 아래 문자열
            dot = temp.substring(n, temp.length());

            // 정수
            temp = temp.substring(0, n);

            if(dot.charAt(dot.length()-1) == '0') {
                dot = dot.substring(0, dot.length()-1);

                if (dot.equals(".")) {
                    text.setText(temp);
                    return;
                }
                rmZero(temp + dot);
            }
        }
        text.setText(temp + dot);
    }
}