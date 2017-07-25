package com.example.power.studentenfutter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class loggedin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
    }
    public void screenChangeGeschaeft(View view)
    {
        Intent intent = new Intent(this, Geschaefte.class);
        startActivity(intent);
    }
    public void screenChangespeisen(View view)
    {
        Intent intent = new Intent(this, Speisen.class);
        startActivity(intent);
    }
    public void screenChangesPaypal(View view)
    {
        Intent intent = new Intent(this, Paypal.class);
        startActivity(intent);
    }
    public void screenChangesWarenkorb(View view)
    {
        Intent intent = new Intent(this, Warenkorb.class);
        startActivity(intent);
    }

}
