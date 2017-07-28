package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Geschaefte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geschaefte);
    }
    public void screenChangeSpeisen(View view)
    {
        Intent intent = new Intent(this, Speisen.class);
        startActivity(intent);
    }
}
