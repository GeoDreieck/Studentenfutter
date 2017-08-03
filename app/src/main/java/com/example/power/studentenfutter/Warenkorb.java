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
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<List<String>>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");
        user_id = Integer.parseInt(intent.getExtras().get("user_id").toString());

        final ListView listView=(ListView)findViewById(R.id.lv);

        list = warenkorb.GetWarenkorbList();

        double price = 0;
        String temp = "";
        double tempprice = 0;
        for (int i = 0; i<list.size();i++)
        {
            temp = list.get(i).get(2);
            temp = temp.trim();
            temp = temp.substring(0, temp.length()-1);
            temp = temp.replace(',', '.');
            tempprice = Double.parseDouble(temp) * Double.parseDouble(list.get(i).get(3));
            price = price + tempprice;
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
