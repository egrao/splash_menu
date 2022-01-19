package com.example.splash;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
//        menuItems.add("Entrenament especial");
//        menuItems.add("Força i longitud");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems);
        listview.setAdapter(adapter);

        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if(position == 0){
//                    img1.setVisibility(arg1.VISIBLE);
                    showItem1(arg1);

                }
//                    if(position == 1){
////                    img2.setVisibility(arg1.VISIBLE);
//                        showEntrenament2(arg1);
//                    }
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
    public void showItem1(View view) {
        showGame();
    }

    public void showGame() {
        Intent intent = new Intent(this, game2048.class);
        startActivity(intent);
    }
}