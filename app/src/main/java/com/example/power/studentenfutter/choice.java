package com.example.power.studentenfutter;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import java.io.IOException;
import java.util.List;

import Server_Connection_Handler.Server_Connection_Handler_Interface;

public class choice extends AppCompatActivity {

    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    int user_id;

    RelativeLayout notificationCount1;
    boolean isClicked = true;
    PopupWindow popUpWindow;
    LinearLayout containerLayout;
    ConstraintLayout mainLayout;
    TextView tvMsg;
    LayoutParams layoutParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        notificationCount1 = (RelativeLayout) findViewById(R.id.badge_layout1);

        // Übergabe der Parameter für das PopUp
        popUpWindow = new PopupWindow(this);
        popUpWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.whitetrans));
        containerLayout = new LinearLayout(this);
        mainLayout = (ConstraintLayout) findViewById(R.id.cons);
        tvMsg = new TextView(this);
        tvMsg.setText("Bezahlvorgang Erfolgreich!");
        layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        containerLayout.addView(tvMsg, layoutParams);
        popUpWindow.setContentView(containerLayout);

        // Intent für Interface & Warenkorb
        Intent intent = getIntent();
        server_connection_handler = (Server_Connection_Handler_Interface) intent.getSerializableExtra("interface");
        warenkorb = (Warenkorbinhalt) intent.getSerializableExtra("warenkorb");
        user_id = Integer.parseInt(intent.getExtras().get("user_id").toString());
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
    public void screenChangepaypal(View view)
    {
        Intent intent = new Intent(this, Paypal.class);
        startActivity(intent);
    }

    public void screenChangecredit(View view) throws IOException, InterruptedException {

        String returnstring = server_connection_handler.OrderwithCredits(warenkorb.GetWarenkorbList());
        tvMsg.setText(returnstring);

        if (isClicked) {
            isClicked = false;
            popUpWindow.showAtLocation(mainLayout, Gravity.BOTTOM, 30, 30);
            popUpWindow.update(650, 880, 400, 200);


        } else {
            isClicked = true;
            popUpWindow.dismiss();
        }
    }
    public void screenChangeWarenkorb(View view)
    {
        Intent intent = new Intent(this, Warenkorb.class);
        intent.putExtra("interface", server_connection_handler);
        intent.putExtra("warenkorb", warenkorb);
        startActivity(intent);
    }


}