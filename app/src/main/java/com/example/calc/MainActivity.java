package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 숫자가 입력될 TextView
    private TextView text;
    // 값을 저장할 변수
    private double value;
    // 사칙연산
    private char operator;
    // 최종 결과를 담을 변수
    private double total = 0;
    // 초기화가 된 상태 인지
    private boolean init = true;
    // 결과를 출력해낸 상태인지
    private boolean result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
    }

    public void onClick(View view) {

        // 현재 textview 값
        String current = text.getText().toString();

        // 입력된 버튼이 .이 아닐 경우 및 textview가 0이면 0지우고 숫자 입력
        if(current.equals("0") && view.getId() != R.id.btn_dot ) {
            text.setText("");
            current = text.getText().toString();
        }

        // 결과가 출력된 상태이고 %버튼이 아닌경우
        if(result && view.getId() != R.id.btn_per){
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
                // 소숫점 체크
                if(checkDot()) {
                    text.setText(current + ".");
                }
                break;

            case R.id.btn_AC:
                // 초기화
                text.setText("0");
                total = 0;
                init = true;
                break;

            case R.id.btn_plus:
                // 입력된 숫자가 없을 경우
                if(current.equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }
                // 사칙연산
                operation(current);
                operator = '+';
                break;

            case R.id.btn_min:
                // 입력된 숫자가 없을 경우
                if(current.equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }
                // 사칙연산
                operation(current);
                operator = '-';
                break;

            case R.id.btn_mul:
                // 입력된 숫자가 없을 경우
                if(current.equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }
                // 사칙연산
                operation(current);
                operator = '*';
                break;

            case R.id.btn_div:
                // 입력된 숫자가 없을 경우
                if(current.equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }
                // 사칙연산
                operation(current);
                operator = '/';
                break;

            case R.id.btn_eq:
                // 입력된 숫자가 없거나 초기화 상태일 경우
                if(current.equals("") || init) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }

                value = Double.parseDouble(current.replaceAll(",", ""));
                calc(value);
                // 계산 된 값이 허용범위를 넘었을 경우
                if(total > 999999999) {
                    Toast.makeText(this, "최대 숫자를 넘어섰습니다.", Toast.LENGTH_SHORT).show();
                    break;
                }
                // 초기화 true
                init = true;
                // 결과 true
                result = true;
                //소숫점 마지막자리 0 제거
                rmZero(Double.toString(total));
                break;

            case R.id.btn_per:
                // 입력된 숫자가 없거나 초기화 상태일 경우
                if(current.equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }
                init = true;
                result = false;
                //소숫점 마지막자리 0 제거
                rmZero(Double.toString(percent(current)));
                break;

            case R.id.btn_pm:
                // 입력된 숫자가 없을 경우
                if(current.equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    break;
                }

                // 입력되어있는 숫자가 양수일 경우
                if(current.substring(0, 1).equals("-")) {
                    current = current.substring(1, current.length());
                } else {
                    current = "-" + current;
                }
                text.setText(current);
                break;
        }
        // 숫자변환
        conversion();

    }

    // 사칙연산 전 확인
    public void operation(String current){
        // 초기화가 된 상태인 경우 먼저 total에 값을 넣는다
        if(init){
            total = Double.parseDouble(current.replaceAll(",", ""));
            init = false;
        } else {
            // 초기화 상태가 아닌 경우 계산
            value = Double.parseDouble(current.replaceAll(",", ""));
            calc(value);
        }
        text.setText("");
    }

    // 계산
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
        // 현재 입력된 값
        String current = text.getText().toString();

        // .의 위치를 찾는다
        int n = current.indexOf(".");

        // 소숫점이 없을 경우
        if(n != -1)
            return false;

        // 소숫 점이 있을 경우
        return true;
    }

    // 숫자 변환기
    public void conversion() {
        // 현재 입력된 숫자
        String current = text.getText().toString();
        // 소숫점 자리를 담을 변수
        String dot = "";
        // true일 경우 양수
        Boolean flag = true;

        current = current.replaceAll(",", "");
        int n = current.indexOf(".");

        // 입력된 값의 길이가 0 이상일 경우
        if(current.length() > 0) {
            // 입력된 값이 음수인 경우
            if(current.substring(0,1).equals("-")){
                current = current.substring(1, current.length());
                flag = false;
            }
        }

        // 소숫점을 찾았을 경우
        if(n != -1){
            // 소숫점 아래 문자열
            dot = current.substring(n, current.length());
            // 정수
            current = current.substring(0, n);
        }

        // 현재 값이 6자리보다 클 경우
        if(current.length() > 6) {
            current = current.substring(0, current.length() - 6) + "," + current.substring(current.length() - 6, current.length() - 3) + "," + current.substring(current.length() - 3, current.length());
        // 현재 값이 3자리보다 클 경우
        } else if(current.length() > 3) {
            current = current.substring(0, current.length() - 3) + "," + current.substring(current.length() - 3, current.length());
        }

        // 입력된 값이 양수 일 경우
        if(flag){
            text.setText(current + dot);
        } else {
            text.setText("-" + current + dot);
        }

    }

    // 소수점 0 지우기
    public void rmZero(String current){
        String dot = "";
        int n = current.indexOf(".");

        // 소숫점을 찾았을 경우
        if(n != -1){
            // 소숫점 아래 문자열
            dot = current.substring(n, current.length());

            // 정수
            current = current.substring(0, n);

            // 소숫점 마지막 자리가 0 일경우
            if(dot.charAt(dot.length()-1) == '0') {
                // 마지막 자리를 지운다
                dot = dot.substring(0, dot.length() - 1);

                // 소숫점이 나온경우
                if (dot.equals(".")) {
                    // 정수만 출력
                    text.setText(current);
                    return;
                }
                // 다시 검사를 위한 재귀함수
                rmZero(current + dot);
            }
        }
        text.setText(current + dot);
    }

    // 퍼센트
    public double percent(String current) {
        current = current.replaceAll(",", "");
        double n = Double.parseDouble(current);
        return n / 100;
    }

}