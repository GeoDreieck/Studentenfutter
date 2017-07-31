package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import Server_Connection_Handler.*;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Server_Connection_Handler.Server_Connection_Handler_Interface;

public class Getraenke extends AppCompatActivity {

    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;


    Warenkorb warenkorb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speisen);
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorb) intent.getSerializableExtra("warenkorb");

        ListView listView=(ListView)findViewById(R.id.listview);

        List<List<String>> list  = null;
        try {
            list = server_connection_handler.GetDrinkinfofromRestaurant(0);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ListViewAdapter adapter = new ListViewAdapter(this, list,3);
        listView.setAdapter(adapter);
    }
    public void screenChangeWarenkorb(View view)
    {
        Intent intent = new Intent(this, Warenkorb.class);
        intent.putExtra("interface", server_connection_handler);
        intent.putExtra("warenkorb", warenkorb);
        startActivity(intent);
    }
    public void transportWarenkorb()
    {

    }
}
