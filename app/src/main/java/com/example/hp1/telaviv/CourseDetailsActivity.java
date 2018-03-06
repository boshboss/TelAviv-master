package com.example.hp1.telaviv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CourseDetailsActivity extends AppCompatActivity {
    InputStream is = null;
    InputStreamReader in;
    BufferedReader br;

    String temp = "", all = "";
    int num;

    TextView text;
    EditText write;
    Button btRead, btRead2, btWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileread);

        String course = getIntent().getStringExtra("course");

        text = (TextView) findViewById(R.id.tvFileContent);
        text.setText(course);
        if (course.equals("computer")) {
            is = getResources().openRawResource(R.raw.computerinfo);
        }
        if (course.equals("doctor")) {
            is = getResources().openRawResource(R.raw.doctorinfo);
        }
        if (course.equals("lawyer")) {
            is = getResources().openRawResource(R.raw.lawyerinfo);
        }
        if (course.equals("education")) {
            is = getResources().openRawResource(R.raw.teacherinfo);
        }
        try {
            temp = "";
            all = "";

            in = new InputStreamReader(is);
            br = new BufferedReader(in);

            while ((temp = br.readLine()) != null)
                all += temp + "\n";
            text.setText(all);
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



