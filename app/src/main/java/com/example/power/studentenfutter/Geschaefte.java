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
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Server_Connection_Handler.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Geschaefte extends AppCompatActivity {

    // Attributes
    RelativeLayout notificationCount1;
    LinearLayout containerLayout;
    ConstraintLayout mainLayout;
    ViewGroup.LayoutParams layoutParams;
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    List<List<String>> list  = null;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<List<String>>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geschaefte);
        notificationCount1 = (RelativeLayout) findViewById(R.id.badge_layout1);

        // Intent für Interface & Warenkorb
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");
        user_id = Integer.parseInt(intent.getExtras().get("user_id").toString());

       // Listview füllen mit Datenbank Connection
        final ListView listView=(ListView)findViewById(R.id.listviewGeschaefte);

        try {
            list = server_connection_handler.GetRestaurantinfo();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListViewAdapter adapter=new ListViewAdapter(this, list,1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                TextView v = (TextView) findViewById(R.id.opentime);
                if(v.getText().toString() == "Geschlossen")
                {

                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), Choose.class);
                    intent.putExtra("interface", server_connection_handler);
                    intent.putExtra("warenkorb", warenkorb);
                    intent.putExtra("restaurantid", list.get(position).get(0));
                    intent.putExtra("user_id", user_id);
                    startActivity(intent);
                }

            }

        });


    }
    // Parameter für Warenkorb
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item1 = menu.findItem(R.id.actionbar_item);
        MenuItemCompat.setActionView(item1, R.layout.notification_update_count_layout);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        return super.onCreateOptionsMenu(menu);
    }


    // Button Klickmethode
    public void screenChangeWarenkorb(View view)
        {
            Intent intent = new Intent(this, Warenkorb.class);
            intent.putExtra("interface", server_connection_handler);
            intent.putExtra("warenkorb", warenkorb);
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        }

}
