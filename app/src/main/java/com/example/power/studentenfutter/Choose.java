package com.example.power.studentenfutter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class Choose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
    }
    public void screenChangeGetraenke(View view)
    {
        Intent intent = new Intent(this, Getraenke.class);
        startActivity(intent);
    }
    public void screenChangespeisen(View view)
    {
        Intent intent = new Intent(this, Speisen.class);
        startActivity(intent);
    }

}