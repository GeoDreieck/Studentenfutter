package com.example.power.studentenfutter;

import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import Server_Connection_Handler.*;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    // Attributes
    Server_Connection_Handler_Interface server_connection_handler;
    Warenkorbinhalt warenkorb;
    int user_id;

    boolean isClicked = true;
    PopupWindow popUpWindow;
    LinearLayout containerLayout;
    ConstraintLayout mainLayout;
    TextView tvMsg;
    ViewGroup.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popUpWindow = new PopupWindow(this);
        popUpWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.whitetrans));
        containerLayout = new LinearLayout(this);
        mainLayout = (ConstraintLayout) findViewById(R.id.cons3);
        tvMsg = new TextView(this);
        tvMsg.setText("Fehlerhafte Eingabe");
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        containerLayout.addView(tvMsg, layoutParams);
        popUpWindow.setContentView(containerLayout);

        server_connection_handler = new Server_Connection_Handler();
        warenkorb = new Warenkorbinhalt();
    }
    public void screenChangeChooset(View view)
    {
        Intent intent = new Intent(this, choice.class);
        intent.putExtra("interface", server_connection_handler);
        intent.putExtra("warenkorb", warenkorb);
        startActivity(intent);
    }
    public void poppupForFailure(View view)
    {

        if (isClicked) {
            isClicked = false;
            popUpWindow.showAtLocation(mainLayout, Gravity.BOTTOM, 30, 30);
            popUpWindow.update(650, 880, 400, 200);


        } else {
            isClicked = true;
            popUpWindow.dismiss();
        }
    }


    public void loggintry(View view) throws IOException, InterruptedException
    {
        List<String> stringlist = new ArrayList<String>();

        user_id = server_connection_handler.CheckLoggin(stringlist);
        if (user_id > 0)
        {
            Intent intent = new Intent(this, choice.class);
            intent.putExtra("interface", server_connection_handler);
            intent.putExtra("warenkorb", warenkorb);
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        }
        else
        {
            poppupForFailure(view);
        }
    }
}
