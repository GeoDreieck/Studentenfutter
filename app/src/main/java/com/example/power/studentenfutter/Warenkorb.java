package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Server_Connection_Handler.Server_Connection_Handler_Interface;

public class Warenkorb extends AppCompatActivity {

    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    List<List<String>> list  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<List<String>>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");

        final ListView listView=(ListView)findViewById(R.id.lv);

        list = warenkorb.GetWarenkorbList();

        double price = 0;
        String temp = "";
        for (int i = 0; i<list.size();i++)
        {
            temp = list.get(i).get(2);
            temp = temp.substring(0, temp.length()-1);
            temp = temp.replace(',', '.');
            price = price + Double.parseDouble(temp);
        }
        TextView tv  = (TextView) findViewById(R.id.tv_price);
        tv.setText(Double.toString(price) + "€");

        ListViewAdapter adapter=new ListViewAdapter(this, list, 4);
        listView.setAdapter(adapter);
    }



    public void screenChangeBezahlungsoption(View view)
    {

    }
}
