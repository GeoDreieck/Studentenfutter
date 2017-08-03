package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import Server_Connection_Handler.*;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Server_Connection_Handler.Server_Connection_Handler_Interface;

public class Getraenke extends AppCompatActivity {

    // Attributes
    RelativeLayout notificationCount1;
    LinearLayout containerLayout;
    ConstraintLayout mainLayout;
    ViewGroup.LayoutParams layoutParams;


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
        notificationCount1 = (RelativeLayout) findViewById(R.id.badge_layout1);

        // Intent für Warenkorb &  Interface & Restaurantid
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");
        restaurantid  = Integer.parseInt(intent.getExtras().get("restaurantid").toString());
        user_id = Integer.parseInt(intent.getExtras().get("user_id").toString());

        // Listview mit Connection zur Datenbank
        final ListView listView=(ListView)findViewById(R.id.listviewgetraenke);

        try {
            list = server_connection_handler.GetDrinkinfofromRestaurant(restaurantid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListViewAdapter adapter = new ListViewAdapter(this, list,3);
        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }

    // Parameter für den Warenkorbbtn
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item1 = menu.findItem(R.id.actionbar_item);
        MenuItemCompat.setActionView(item1, R.layout.notification_update_count_layout);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        return super.onCreateOptionsMenu(menu);

    }

    // Button Klickmethoden

    public void screenChangeWarenkorb(View view)
    {
        Intent intent = new Intent(this, Warenkorb.class);
        intent.putExtra("interface", server_connection_handler);
        intent.putExtra("warenkorb", warenkorb);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
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
}
