package com.example.splash;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> menuItems;
    public static final String EXTRA_MESSAGE = "com.example.splash.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listview = (ListView) findViewById(R.id.listview);

        menuItems = new ArrayList<String>();
        menuItems.add("2048");
        menuItems.add("PEG");
        menuItems.add("Settings");
//        menuItems.add("Força i longitud");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.RED);
                text.setTextSize(25);
                text.setTypeface(null, Typeface.BOLD);
                return view;
            }
        };
        listview.setAdapter(adapter);

        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if(position == 0){
//                    img1.setVisibility(arg1.VISIBLE);
                    showItem1(arg1, game2048.class);

                }
                    if(position == 1){
//                    img2.setVisibility(arg1.VISIBLE);
                        showItem2(arg1, gamePeg.class);
                    }
//                    if(position == 2){
////                    img3.setVisibility(arg1.VISIBLE);
//                        showEntrenament3(arg1);
//                    }
//                    if(position == 3){
////                    img4.setVisibility(arg1.VISIBLE);
//                        showEntrenament4(arg1);
//                    }

            }
        });
    }
    public void showItem1(View view, Class game) {
        showGame(game2048.class);
    }
    public void showItem2(View view, Class game) {
        showGame(gamePeg.class);
    }

    public void showGame(Class game) {
        Intent intent = new Intent(this, game);
        startActivity(intent);
    }
}