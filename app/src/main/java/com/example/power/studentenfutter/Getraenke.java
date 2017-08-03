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

import Server_Connection_Handler.Server_Connection_Handler_Interface;

public class Getraenke extends AppCompatActivity {

    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    List<List<String>> list  = null;
    int restaurantid;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<List<String>>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getraenke);
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");
        restaurantid  = Integer.parseInt(intent.getExtras().get("restaurantid").toString());
        user_id = Integer.parseInt(intent.getExtras().get("user_id").toString());

        final ListView listView=(ListView)findViewById(R.id.listviewgetraenke);

        try {
            list = server_connection_handler.GetDrinkinfofromRestaurant(restaurantid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListViewAdapter adapter = new ListViewAdapter(this, list,3);
        listView.setAdapter(adapter);
    }
    public void transportWarenkorb(View view)
    {
        ListView lv = (ListView) findViewById(R.id.listviewgetraenke);
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
        stringlist.add("DRINK");
        stringlist.add("" + user_id + "");


        warenkorb.AddtoWarenkorbList(stringlist);
    }

    public void OpenWarenkorb(View view)
    {
        Intent intent = new Intent(this, Warenkorb.class);
        intent.putExtra("interface", server_connection_handler);
        intent.putExtra("warenkorb", warenkorb);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }
}
