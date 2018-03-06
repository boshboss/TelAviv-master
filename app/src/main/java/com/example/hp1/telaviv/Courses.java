package com.example.hp1.telaviv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Courses extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv1;
    ArrayList<String> courses= new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        lv1= (ListView) findViewById(R.id.lv1);

        courses.add(new String("Computers"));
        courses.add(new String("Doctor"));
        courses.add(new String("Lawyer"));
        courses.add(new String("Teacher"));


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,courses);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(this,CourseDetailsActivity.class);
        if(position == 0){
            i.putExtra("course","computer");

        }
        if (position == 1){
            i.putExtra("course","doctor");
        }
        if (position == 2){
            i.putExtra("course","lawyer");

        }
        if (position == 3){
            i.putExtra("course","education");

        }
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.firstitem:
                Toast.makeText(getBaseContext(), "back to the first screen", Toast.LENGTH_LONG).show();
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                break;

            case R.id.seconditem:
                Toast.makeText(getBaseContext(), "Hi", Toast.LENGTH_LONG).show();

                break;
        }
        return true;
    }
}
