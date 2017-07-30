package com.example.power.studentenfutter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import Server_Connection_Handler.*;

public class Choose extends AppCompatActivity {

    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
    }
    public void screenChangeGetraenke(View view)
    {
        Intent intent = new Intent(this, Getraenke.class);
        intent.putExtra("interface", server_connection_handler);
        startActivity(intent);
    }
    public void screenChangespeisen(View view)
    {
        Intent intent = new Intent(this, Speisen.class);
        intent.putExtra("interface", server_connection_handler);
        startActivity(intent);
    }

}
