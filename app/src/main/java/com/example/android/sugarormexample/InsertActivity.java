package com.example.android.sugarormexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarContext;

public class InsertActivity extends AppCompatActivity {

    EditText name, email, phone, area;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        SugarContext.init(this);

        name = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        phone = (EditText) findViewById(R.id.et_pn);
        area = (EditText) findViewById(R.id.et_area);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dname = name.getText().toString();
                String demail = email.getText().toString();
                String dphn = phone.getText().toString();
                String darea = area.getText().toString();
                if ((TextUtils.isEmpty(dname) && TextUtils.isEmpty(demail) && TextUtils.isEmpty(dphn) && TextUtils.isEmpty(darea))
                        || (TextUtils.isEmpty(dname) || TextUtils.isEmpty(demail) || TextUtils.isEmpty(dphn) || TextUtils.isEmpty(darea))) {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    Person person = new Person(dname, demail, dphn, darea);
                    person.save();
                    name.setText(" ");
                    email.setText(" ");
                    phone.setText(" ");
                    area.setText(" ");
                    Intent listIntent = new Intent(InsertActivity.this, MainActivity.class);
                    startActivity(listIntent);
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        });


    }
}