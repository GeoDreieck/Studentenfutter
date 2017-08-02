package com.example.power.studentenfutter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

public class test extends AppCompatActivity {

    RelativeLayout notificationCount1;
    boolean isClicked = true;
    PopupWindow popUpWindow;
    LinearLayout containerLayout;
    LinearLayout mainLayout;
    TextView tvMsg;
    LayoutParams layoutParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        notificationCount1 = (RelativeLayout) findViewById(R.id.badge_layout1);
        popUpWindow = new PopupWindow(this);
        containerLayout = new LinearLayout(this);
        mainLayout = (LinearLayout) findViewById(R.id.linelay);
        tvMsg = new TextView(this);
        tvMsg.setText("Hi this is pop up window...");
        layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        containerLayout.addView(tvMsg, layoutParams);
        popUpWindow.setContentView(containerLayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item1 = menu.findItem(R.id.actionbar_item);
        MenuItemCompat.setActionView(item1, R.layout.notification_update_count_layout);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        return super.onCreateOptionsMenu(menu);


    }
    public void screenChangetest(View view)
    {

        if (isClicked) {
            isClicked = false;
            popUpWindow.showAtLocation(mainLayout, Gravity.BOTTOM, 40, 40);
            popUpWindow.update(900, 900, 900, 500);
        } else {
            isClicked = true;
            popUpWindow.dismiss();
        }
    }


}