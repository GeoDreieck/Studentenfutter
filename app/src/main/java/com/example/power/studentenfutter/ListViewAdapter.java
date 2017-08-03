package com.example.power.studentenfutter;

import java.util.List;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class ListViewAdapter extends BaseAdapter{

    public List<List<String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    Spinner spinner;
    Button btn;
    int option;

    public ListViewAdapter(Activity activity,List<List<String>> list, int option){
        super();
        this.activity=activity;
        this.list=list;
        this.option = option;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (option == 2 || option == 3)
        {
            LayoutInflater inflater=activity.getLayoutInflater();

            if(convertView == null){

                convertView=inflater.inflate(R.layout.collums_row, null);

                txtFirst=(TextView) convertView.findViewById(R.id.name);
                txtSecond=(TextView) convertView.findViewById(R.id.price);
                spinner=(Spinner) convertView.findViewById(R.id.amount);
                btn=(Button) convertView.findViewById(R.id.add);

            }

            List <String> stringList=list.get(position);
            txtFirst.setText(stringList.get(1));
            txtSecond.setText(stringList.get(3));
            spinner.setPrompt("1");


            //create a list of items for the spinner.
            String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
            //create an adapter to describe how the items are displayed, adapters are used in several places in android.
            //There are multiple variations of this, but this is the basic variant.
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, items);
            //set the spinners adapter to the previously created one.
            spinner.setAdapter(adapter);

            btn.setText("Zum Warenkorb");

            return convertView;
        }
        else
        {
         if (option == 1)
         {
             LayoutInflater inflater=activity.getLayoutInflater();

             if(convertView == null){

                 convertView=inflater.inflate(R.layout.collums_row2, null);

                 txtFirst=(TextView) convertView.findViewById(R.id.name);
                 txtSecond=(TextView) convertView.findViewById(R.id.description);
                 txtThird=(TextView) convertView.findViewById(R.id.opentime);

             }

             List <String> stringList=list.get(position);
             txtFirst.setText(stringList.get(1));
             txtSecond.setText(stringList.get(3));

             String temp;
             if(stringList.get(8).equals("0"))
                 temp = "Geschlossen";
             else
                 temp = stringList.get(4)+":" + stringList.get(5) +" - " + stringList.get(6) + ":" + stringList.get(7);

             txtThird.setText(temp);

             return convertView;
         }
         else{
             if(option == 4)
             {
                 LayoutInflater inflater=activity.getLayoutInflater();

                 if(convertView == null){

                     convertView=inflater.inflate(R.layout.collums_row3, null);

                     txtFirst=(TextView) convertView.findViewById(R.id.name);
                     txtSecond=(TextView) convertView.findViewById(R.id.price);
                     txtThird=(TextView) convertView.findViewById(R.id.amount);

                 }

                 List <String> stringList=list.get(position);
                 txtFirst.setText(stringList.get(1));
                 txtSecond.setText(stringList.get(2));
                 txtThird.setText(stringList.get(3));


                 return convertView;
             }
             else
             {
                 return convertView;
             }
         }
        }


    }

}