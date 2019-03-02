package com.epam.android.shjman.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String LAST_OPERATION_KEY = "LAST_OPERATION_KEY";
    private static final String OPERAND_KEY = "OPERAND_KEY";
    private static final String EMPTY_LINE = "";
    private static final String SIGN_EQUAL = "=";
    private static final String SIGN_ADDITION = "+";
    private static final String SIGN_SUBTRACTION = "-";
    private static final String SIGN_MULTIPLICATION = "*";
    private static final String SING_DIVISION = "/";

    private TextView resultFieldTextView;
    private TextView numberFieldTextView;
    private TextView operationFieldTextView;

    private Double operand = null;
    private String lastOperation = SIGN_EQUAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultFieldTextView = findViewById(R.id.tv_result);
        numberFieldTextView = findViewById(R.id.tv_number);
        operationFieldTextView = findViewById(R.id.tv_operation);
        Button clearButton = findViewById(R.id.btn_clear);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void clearFields() {
        numberFieldTextView.setText(EMPTY_LINE);
        resultFieldTextView.setText(EMPTY_LINE);
        operationFieldTextView.setText(EMPTY_LINE);
        lastOperation = SIGN_EQUAL;
        operand = null;
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        numberFieldTextView.append(button.getText());
        if (lastOperation.equals(SIGN_EQUAL) && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        String operationSTR = button.getText().toString();
        String number = numberFieldTextView.getText().toString();
        if (number.length() > 0) {
            try {
                performOperation(Double.valueOf(number), operationSTR);
            } catch (NumberFormatException ex) {
                numberFieldTextView.setText(EMPTY_LINE);
            }
        }
        lastOperation = operationSTR;
        operationFieldTextView.setText(lastOperation);
    }

    private void performOperation(Double number, String operation) {
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals(SIGN_EQUAL)) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case SIGN_EQUAL:
                    operand = number;
                    break;
                case SING_DIVISION:
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case SIGN_MULTIPLICATION:
                    operand *= number;
                    break;
                case SIGN_ADDITION:
                    operand += number;
                    break;
                case SIGN_SUBTRACTION:
                    operand -= number;
                    break;
                default:
                    Log.e("MainActivity", "something was did wrong with: " + lastOperation);
                    //in the release.this is redundantly. and lastOperation  must be Enum
                    break;
            }
        }
        resultFieldTextView.setText(String.valueOf(operand));
        numberFieldTextView.setText(EMPTY_LINE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(LAST_OPERATION_KEY, lastOperation);
        if (operand != null)
            outState.putDouble(OPERAND_KEY, operand);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString(LAST_OPERATION_KEY);
        operand = savedInstanceState.getDouble(OPERAND_KEY);
        resultFieldTextView.setText(String.valueOf(operand));
        operationFieldTextView.setText(lastOperation);
    }
}