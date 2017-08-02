package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import Server_Connection_Handler.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Geschaefte extends AppCompatActivity {

    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    List<List<String>> list  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = new ArrayList<List<String>>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geschaefte);
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");

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
                    startActivity(intent);
                }

            }

        });


    }

}
