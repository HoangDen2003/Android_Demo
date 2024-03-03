package com.example.demoth1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// import com.example.demoth1.MainActivity;

public class LoginActivity extends AppCompatActivity {
    EditText editUserName, editPassword;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUp();
        setupListeners();
    }

    public void setUp() {
        editUserName = findViewById(R.id.editUserName);
        editPassword = findViewById(R.id.editPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    public void setupListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkUserName();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(m);
            }
        });
    }

    public void checkUserName() {
        boolean isValid = true;

        if (isEmpty(editUserName)) {
            editUserName.setError("You must enter username to login !");
            isValid = false;
        } else {
            if (!isEmail(editUserName)) {
                editUserName.setError("Enter valid Email !");
                isValid = false;
            }
            if (isEmpty(editPassword)) {
                editPassword.setError("You must enter password to login");
                isValid = false;
            } else {
                if (editPassword.toString().length() < 4) {
                    editPassword.setError("Password must at to least 4 chars long");
                    isValid = false;
                }
            }
        }

        if (isValid) {
            String valueUserName = editUserName.getText().toString();
            String valuePassWord = editPassword.getText().toString();
            if (valueUserName.equals("test@test.com") && valuePassWord.equals("password1234")) {
                Intent i = new Intent(LoginActivity.this, Redirect.class);
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
