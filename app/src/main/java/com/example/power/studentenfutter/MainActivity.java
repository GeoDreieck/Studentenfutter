package com.example.power.studentenfutter;

import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import Server_Connection_Handler.*;

import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        server_connection_handler = new Server_Connection_Handler();
        warenkorb = new Warenkorbinhalt();
    }
    public void screenChangeChooset(View view) throws IOException, InterruptedException {

        Intent intent = new Intent(this, Choose.class);
        intent.putExtra("interface", server_connection_handler);
        intent.putExtra("warenkorb", warenkorb);
        startActivity(intent);
    }

    public void loggintry(View view) throws IOException, InterruptedException
    {
        List<String> stringlist = new ArrayList<String>();

        user_id = server_connection_handler.CheckLoggin(stringlist);
        if (user_id > 0)
        {
            Intent intent = new Intent(this, Choose.class);
            intent.putExtra("interface", server_connection_handler);
            intent.putExtra("warenkorb", warenkorb);
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        }
        else
        {
            //do popUp!
        }
    }
}
