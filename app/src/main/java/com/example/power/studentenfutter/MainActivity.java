package com.example.power.studentenfutter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void screenChangeGeschaeft(View view)
    {
        Intent intent = new Intent(this, loggedin.class);
        startActivity(intent);
    }
}
