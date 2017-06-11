package com.example.android.sugarormexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.ArrayList;

public class UpdateDeleteActivity extends AppCompatActivity {
    Button updateButton;
    ImageView deleteButton;
    int position_db;
    ArrayList<Person> personDetailses;
    String name_db, email_db, phone_db, area_db;
    EditText name, email, phone,area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);


        SugarContext.init(this);
        Intent intent = getIntent();
        position_db = Integer.parseInt(intent.getStringExtra("position_db"));
        name_db=intent.getStringExtra("name_db");
        email_db=intent.getStringExtra("email_db");
        phone_db=intent.getStringExtra("phone_db");
        area_db=intent.getStringExtra("area_db");

        name = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        phone = (EditText) findViewById(R.id.et_pn);
        area=(EditText) findViewById(R.id.et_area);

        updateButton=(Button) findViewById(R.id.updateButton);
        deleteButton=(ImageView) findViewById(R.id.deleteButton);
        name.setText(name_db);
        email.setText(email_db);
        phone.setText(phone_db);
        area.setText(area_db);




        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dname = name.getText().toString();
                String demail = email.getText().toString();
                String dphn = phone.getText().toString();
                String darea=area.getText().toString();

                if ((TextUtils.isEmpty(dname) && TextUtils.isEmpty(demail) && TextUtils.isEmpty(dphn) && TextUtils.isEmpty(darea))
                        || (TextUtils.isEmpty(dname) || TextUtils.isEmpty(demail) || TextUtils.isEmpty(dphn) || TextUtils.isEmpty(darea) )) {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }

                else{
                    Person person=Person.findById(Person.class, position_db);
                    person.name=dname;
                    person.email=demail;
                    person.phone=dphn;
                    person.area=darea;
                    person.save();

                    Intent listIntent=new Intent(UpdateDeleteActivity.this, MainActivity.class);
                    startActivity(listIntent);
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Person person=Person.findById(Person.class, position_db);

                AlertDialog.Builder dialog=new AlertDialog.Builder(UpdateDeleteActivity.this);
                dialog.setTitle("Delete");
                dialog.setMessage("Are you sure you want to delete");
                dialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        person.delete();
                        Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                        Intent listIntent=new Intent(UpdateDeleteActivity.this, MainActivity.class);
                        startActivity(listIntent);
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());


                    }
                });
                dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }


        });


    }
}
