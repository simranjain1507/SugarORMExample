package com.example.android.sugarormexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvList;
    LinearLayout linearLayout;
    PersonAdapter adapter;
    FloatingActionButton fab;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Person.deleteAll(Person.class);
                this.recreate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);

        fab=(FloatingActionButton) findViewById(R.id.fabnext);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });

        rvList = (RecyclerView) findViewById(R.id.rv_list);
        linearLayout = (LinearLayout) findViewById(R.id.layouttotal);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvList.setLayoutManager(layoutManager);
        rvList.setHasFixedSize(true);

        List<Person> persons = Person.listAll(Person.class);
        if (persons.size() > 0) {
            rvList.setVisibility(View.VISIBLE);
            adapter = new PersonAdapter(this, persons);
            rvList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            rvList.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
        }

    }
}