package com.example.power.studentenfutter;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;


import Server_Connection_Handler.Server_Connection_Handler_Interface;

public class Choose extends AppCompatActivity {
    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    int user_id;

    RelativeLayout notificationCount1;
    LinearLayout containerLayout;
    ConstraintLayout mainLayout;
    LayoutParams layoutParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
        notificationCount1 = (RelativeLayout) findViewById(R.id.badge_layout1);

        // Intends für Interface & Warenkorb
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");
        user_id = Integer.parseInt(intent.getExtras().get("user_id").toString());
    }
    //  Parameter für Warenkorbbtn
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
        startActivity(intent);
    }

    public void screenChangespeisen(View view)
    {

        Intent intent = new Intent(this, Speisen.class);
        intent.putExtra("interface", server_connection_handler);
        intent.putExtra("warenkorb", warenkorb);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }
    public void screenChangeGetraenke(View view)
    {
        Intent intent = new Intent(this, Getraenke.class);
        intent.putExtra("interface", server_connection_handler);
        intent.putExtra("warenkorb", warenkorb);
        intent.putExtra("user_id", user_id);
        startActivity(intent);
    }


}