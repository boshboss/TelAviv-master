package com.example.hp1.telaviv;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    ImageView ivlogo;
    TextView tvregister;
    Button btlogin,btregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ivlogo=(ImageView)findViewById(R.id.ivlogo);
        tvregister=(TextView)findViewById(R.id.tvregister);
        btlogin=(Button)findViewById(R.id.btlogin);
        btregister=(Button)findViewById(R.id.btregister);
        btlogin.setOnClickListener(this);
        btregister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==btlogin){
            Intent login=new Intent(this,Login.class);
            startActivity(login);}
        if(v==btregister){


            Intent register=new Intent(this,Registration.class);
            startActivity(register);
        }}
}
