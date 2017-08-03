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


}