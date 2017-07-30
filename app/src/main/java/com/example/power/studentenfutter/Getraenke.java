package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Getraenke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speisen);

    }
    public void screenChangeWarenkorb(View view)
    {
        Intent intent = new Intent(this, Warenkorb.class);
        startActivity(intent);
    }
    public void transportWarenkorb()
    {

    }
}
