package com.epam.android.shjman.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String LAST_OPERATION = "LAST_OPERATION";
    private static final String OPERAND = "OPERAND";

    private TextView resultField;
    private TextView numberField;
    private TextView operationField;
    private Button btnClear;

    private Double operand = null;
    private String lastOperation = "=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultField = findViewById(R.id.tv_result);
        numberField = findViewById(R.id.tv_number);
        operationField = findViewById(R.id.tv_operation);
        btnClear = findViewById(R.id.btn_clear);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberField.setText("");
                resultField.setText("");
                operationField.setText("");
                lastOperation = "=";
                operand = null;
            }
        });
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        numberField.append(button.getText());
        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        String operationSTR = button.getText().toString();
        String number = numberField.getText().toString();
        if (number.length() > 0) {
            try {
                performOperation(Double.valueOf(number), operationSTR);
            } catch (NumberFormatException ex) {
                numberField.setText("");
            }
        }
        lastOperation = operationSTR;
        operationField.setText(lastOperation);
    }

    private void performOperation(Double number, String operation) {
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "*":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
            }
        }
        resultField.setText(String.valueOf(operand));
        numberField.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(LAST_OPERATION, lastOperation);
        if (operand != null)
            outState.putDouble(OPERAND, operand);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString(LAST_OPERATION);
        operand = savedInstanceState.getDouble(OPERAND);
        resultField.setText(String.valueOf(operand));
        operationField.setText(lastOperation);
    }


}
