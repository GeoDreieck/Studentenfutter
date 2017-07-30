package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import Server_Connection_Handler.*;

public class Speisen extends AppCompatActivity {

    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speisen);
    }
    public void screenChangeWarenkorb(View view)
    {
        Intent intent = new Intent(this, Warenkorb.class);
        intent.putExtra("interface", server_connection_handler);
        startActivity(intent);
    }
    public void transportWarenkorb()
    {

    }
}
