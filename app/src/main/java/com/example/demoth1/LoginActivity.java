package com.example.demoth1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// import com.example.demoth1.MainActivity;

public class LoginActivity extends AppCompatActivity {
    EditText loginUserName, loginPassword;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUp();
        setupListeners();

    }

    public void setUp() {
        loginUserName = findViewById(R.id.loginUserName);
        loginPassword = findViewById(R.id.loginPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    public void setupListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strUN = loginUserName.getText().toString();
                String strPW = loginUserName.getText().toString();

                checkUserName();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent m = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(m);

                Intent m = new Intent(LoginActivity.this, AuthGoogleActivity.class);
                startActivity(m);

            }
        });
    }

    public void checkUserName() {
        boolean isValid = true;

        if (isEmpty(loginUserName)) {
            loginUserName.setError("You must enter username to login !");
            isValid = false;
        } else {
            if (!isEmail(loginUserName)) {
                loginUserName.setError("Enter valid Email !");
                isValid = false;
            }
            if (isEmpty(loginPassword)) {
                loginPassword.setError("You must enter password to login");
                isValid = false;
            } else {
                if (loginPassword.toString().length() < 4) {
                    loginPassword.setError("Password must at to least 4 chars long");
                    isValid = false;
                }
            }
        }

        if (isValid) {
            String valueUserName = loginUserName.getText().toString();
            String valuePassWord = loginPassword.getText().toString();

            MyDataBaseHelper myDB = new MyDataBaseHelper(LoginActivity.this);
            Cursor cursor = myDB.getUserByEmail(valueUserName, valuePassWord);

            if (cursor.getCount() == 1) {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, DictionaryActivity.class);
                startActivity(i);
                this.finish();
            } else {
                Toast t = Toast.makeText( this, "Wrong email or password", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

}
