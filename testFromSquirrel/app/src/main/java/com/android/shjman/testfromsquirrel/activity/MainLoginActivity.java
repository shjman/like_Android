package com.android.shjman.testfromsquirrel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.shjman.testfromsquirrel.R;

public class MainLoginActivity extends AppCompatActivity {
    private Button btnForLogin;
    private Button btnForRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnForLogin = findViewById(R.id.btn_for_login);
        btnForRegistration = findViewById(R.id.btn_for_registration);

        btnForRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainLoginActivity.this, RegistrationActivity.class));
            }
        });

        btnForLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                if (modeLogin) {
                    if (Constants.isOnline()) {
                        enteredEmail = etEnteredEmail.getText().toString().trim();
                        enteredPassword = etEnteredPassword.getText().toString().trim();
                        enteredPassword = replaceInput(enteredPassword);
                        if (isRightInputLogin(enteredEmail, enteredPassword)) {
                            if (showProgress()) {
                                LoginProvider.taskLoginProvider(enteredEmail, enteredPassword);
                            }
                        }
                    } else {
                        showSnackbar(getString(R.string.we_are_not_online));
                    }
                } else {
                    runModeLogin();
                }*/
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
