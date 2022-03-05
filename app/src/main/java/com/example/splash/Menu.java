package com.example.splash;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> menuItems;
    public static final String EXTRA_MESSAGE = "com.example.splash.MESSAGE";
    public static final int REQ_CODE = 1;
    private Integer HScore;
    private MyOpenHelper mDB;
    public String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extras = getIntent().getExtras();
        user = extras.getString("user");
        Toast.makeText(Menu.this, "BIENVENIDO "+user, Toast.LENGTH_SHORT).show();

        listview = (ListView) findViewById(R.id.listview);
        mDB = new MyOpenHelper(this);

        menuItems = new ArrayList<String>();
        menuItems.add("PEG");
        menuItems.add("2048");
        menuItems.add("Scores");
        menuItems.add("Settings");

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
                    showItem1(arg1, gamePeg.class);

                }

                if(position == 1){
                    showItem2(arg1, game2048.class);
                }

                if(position == 2){
                    showItem2(arg1, scoreboard.class);
                }

//                if(position == 3){
//                    showItem2(arg1, game2048.class);
//                }

            }
        });
    }
    public void showItem1(View view, Class game) {
        HScore = mDB.getHScore(user, "game2048");
        System.out.println("insert desde fuera "+HScore);
        showGame(game2048.class);
    }
    public void showItem2(View view, Class game) {
        HScore = mDB.getHScore(user, "gamePeg");
        System.out.println("insert desde fuera "+HScore);
        showGame(gamePeg.class);
    }

    public void showItem3(View view, Class game) {
        HScore = mDB.getHScore(user, "game2048");
        System.out.println("insert desde fuera "+HScore);
        showGame(game2048.class);
    }

    public void showGame(Class game) {
        Intent intent = new Intent(this, game);
        intent.putExtra("HScore", HScore);
        startActivityForResult(intent, REQ_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                Integer score = Integer.parseInt(data.getStringExtra("score"));
                String time = data.getStringExtra("time");
                String game = data.getStringExtra("game");

//                if (!score.equals(null) && !TextUtils.isEmpty(time)) {
                    mDB.insertHScore(score, time, game, user);

                    Toast.makeText(
                            getApplicationContext(),
                            "NUEVO RECORD! "+game+ " "+score+" "+time,
                            Toast.LENGTH_LONG).show();
//                }
            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(
                        getApplicationContext(),
                        "no hay hscore nuevo",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}