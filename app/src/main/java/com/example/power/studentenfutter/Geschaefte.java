package com.example.power.studentenfutter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Geschaefte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geschaefte);

        ListView listView=(ListView)findViewById(R.id.listviewGeschaefte);

        List<List<String>> list = new ArrayList<List<String>>();
        ArrayList <String> list2 = new ArrayList <String>();
        ArrayList <String> list3 = new ArrayList <String>();
        list2.add("Pommeriens");
        list2.add("Italienisch angehaucht");
        list2.add("17:00 - 22:00");

        list3.add("Dönerburg");
        list3.add("Schmeckt nicht gut");
        list3.add("14:00 - 18:00");
        list.add(list2);
        list.add(list3);

        ListViewAdapter adapter=new ListViewAdapter(this, list,1);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), Choose.class);
                startActivity(intent);
            }

        });


    }
    public void screenChangeSpeisen(View view)
    {
        Intent intent = new Intent(this, Getraenke.class);
        startActivity(intent);
    }


}
