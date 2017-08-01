package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Server_Connection_Handler.*;

public class Speisen extends AppCompatActivity {

    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    List<List<String>> list  = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<List<String>>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speisen);
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");

        final ListView listView=(ListView)findViewById(R.id.listviewspeisen);


        try {
            list = server_connection_handler.GetFoodinfofromRestaurant(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*List<String> testlist = new ArrayList<String>();
        List<List<String>> testlist2 = new ArrayList<List<String>>();

        testlist = new ArrayList<String>();
        testlist.add("0");
        testlist.add("1 Euro");
        testlist.add("platzhalter");
        testlist.add("billiger Wein");
        testlist.add("ende");
        testlist2.add(testlist);

        testlist = new ArrayList<String>();
        testlist.add("1");
        testlist.add("18 Euro");
        testlist.add("platzhalter");
        testlist.add("teuerer Wein");
        testlist.add("ende");
        testlist2.add(testlist);*/

        ListViewAdapter adapter=new ListViewAdapter(this, list,2);
        listView.setAdapter(adapter);
    }
    public void transportWarenkorb(View view)
    {
        ListView lv = (ListView) findViewById(R.id.listviewspeisen);
        LinearLayout ll = (LinearLayout)view.getParent();
        final int position = lv.getPositionForView(ll);

        List<String> stringlist = new ArrayList<String>();

        View v = lv.getChildAt(position);

        TextView textview1=(TextView)v.findViewById(R.id.name);
        TextView textview2=(TextView)v.findViewById(R.id.price);
        Spinner spinner=(Spinner)v.findViewById(R.id.amount);

        stringlist.add(list.get(position).get(0));
        stringlist.add(textview1.getText().toString());
        stringlist.add(textview2.getText().toString());
        stringlist.add(spinner.getSelectedItem().toString());
        stringlist.add("FOOD");


        warenkorb.AddtoWarenkorbList(stringlist);
    }
}
