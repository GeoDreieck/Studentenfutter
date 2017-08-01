package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import Server_Connection_Handler.*;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Server_Connection_Handler.Server_Connection_Handler_Interface;

public class Getraenke extends AppCompatActivity {

    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    List<List<String>> list  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<List<String>>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getraenke);
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");

        final ListView listView=(ListView)findViewById(R.id.listviewgetraenke);


        /*try {
            list = server_connection_handler.GetDrinkinfofromRestaurant(0);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        List<String> testlist = new ArrayList<String>();
        List<List<String>> testlist2 = new ArrayList<List<String>>();

        testlist = new ArrayList<String>();
        testlist.add("0");
        testlist.add("1 Euro");
        testlist.add("platzhalter");
        testlist.add("billiger Wein");
        testlist.add("ende");
        testlist2.add(testlist);

        testlist.add("1");
        testlist.add("18 Euro");
        testlist.add("platzhalter");
        testlist.add("teuerer Wein");
        testlist.add("ende");
        testlist2.add(testlist);

        ListViewAdapter adapter = new ListViewAdapter(this, testlist2,3);
        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }
    public void screenChangeWarenkorb(View view)
    {
        Intent intent = new Intent(this, Warenkorb.class);
        intent.putExtra("interface", server_connection_handler);
        intent.putExtra("warenkorb", warenkorb);
        startActivity(intent);
    }
    public void transportWarenkorb(View view)
    {
        final int position = (int) view.getTag();

        List<String> stringlist = new ArrayList<String>();

        stringlist.add(list.get(position).get(1));
        stringlist.add(list.get(position).get(3));

        Spinner spinner=(Spinner)view.findViewById(R.id.amount);
        stringlist.add(spinner.getSelectedItem().toString());

        warenkorb.AddtoWarenkorbList(stringlist);
    }
}
