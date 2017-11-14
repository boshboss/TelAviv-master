package com.example.hp1.telaviv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Fileread extends AppCompatActivity implements View.OnClickListener{
    InputStream is=null;
    InputStreamReader in;
    BufferedReader br;

    String temp="", all="";
    int num;

    TextView text;
    EditText write;
    Button btRead, btRead2, btWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileread);


        text=(TextView)findViewById(R.id.tvFileContent);
      /*  write = (EditText)findViewById(R.id.etText);

        btRead=(Button)findViewById(R.id.btRead);
        btRead.setOnClickListener(this);

        btWrite=(Button)findViewById(R.id.btWrite);
        btWrite.setOnClickListener(this);

        btRead2=(Button)findViewById(R.id.btRead2);
        btRead2.setOnClickListener(this);*/

    }

    @Override
    public void onClick(View v) {

        if(v==btRead)
        {
            try{
                temp="";
                all="";

                is=getResources().openRawResource(R.raw.myfile);

                in= new InputStreamReader(is);
                br= new BufferedReader(in);


                while((temp=br.readLine())!=null)
                    all+=temp+"\n";
                text.setText(all);
                is.close();
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    }

