package com.example.hp1.telaviv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity implements View.OnClickListener{
Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==signup){
            Intent i=new Intent(this,Thankyou.class);
            startActivity(i);

        }

    }
}
