package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Warenkorb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);
    }
    public void screenChangePaypal(View view)
    {
        Intent intent = new Intent(this, Paypal.class);
        startActivity(intent);
    }
}