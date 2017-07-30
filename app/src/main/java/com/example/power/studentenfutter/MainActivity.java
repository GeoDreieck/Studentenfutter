package com.example.power.studentenfutter;

import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import Server_Connection_Handler.*;

import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        server_connection_handler = new Server_Connection_Handler();
    }
    public void screenChangeGeschaeft(View view)
    {
        Intent intent = new Intent(this, Geschaefte.class);
        intent.putExtra("interface", server_connection_handler);
        startActivity(intent);
    }
}
