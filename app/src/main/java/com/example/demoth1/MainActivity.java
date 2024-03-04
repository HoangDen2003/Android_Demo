package com.example.demoth1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, postAddress, email;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        postAddress = findViewById(R.id.postAddress);
        email = findViewById(R.id.email);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                checkData();
//                Intent i = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(i);
            }
        });
    }
    private void startActivities(Intent i) {
    }

    // dinh dang email
    Boolean isEmail (EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    // empty
    Boolean isEmpty (EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void checkData () {
        boolean isValue = true;
        if (isEmpty(firstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to Register!", Toast.LENGTH_LONG);
            t.show();
            isValue = false;
        }
//        false: không bắt buộc
//        if (isEmpty(lastName) == false) {
//            lastName.setError("Last name is required");
//            isValue = false;
//        }
        if (isEmail(email) == false) {
            email.setError("Enter valid email !");
            isValue = false;
        }
        if (isValue) {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }

    }

}