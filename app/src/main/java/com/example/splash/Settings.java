package com.example.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    private MyOpenHelper mDB;
    private ListView listview;
    private ArrayList<String> menuItems;
    public String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_settings);

        Bundle extras = getIntent().getExtras();
        user = extras.getString("user");
        mDB = new MyOpenHelper(this);

        listview = (ListView) findViewById(R.id.listviewSettings);
        menuItems = new ArrayList<String>();
        String deleteUser = "Delete user: "+user;
        menuItems.add(deleteUser);
        menuItems.add("Change theme");
        menuItems.add("Log out");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.colorAccent));
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
                    AlertDialog.Builder builder = new AlertDialog.Builder (Settings.this);
                    builder.setMessage("¿Seguro que desea borrar este usuario y todos sus datos?");
                    builder.setTitle(deleteUser);
                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mDB.delete(user);
                            Toast.makeText(Settings.this, "Usuario "+user+" eliminado", Toast.LENGTH_SHORT).show();
                            logOut();
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.create().show();
                }

                if(position == 1){
                    int nightMode = AppCompatDelegate.getDefaultNightMode();
                    if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode
                                (AppCompatDelegate.MODE_NIGHT_NO);
//                        Toast.makeText(Settings.this, "no dark", Toast.LENGTH_SHORT).show();
                    } else {
                        AppCompatDelegate.setDefaultNightMode
                                (AppCompatDelegate.MODE_NIGHT_YES);
                    }
                    recreate();
                }

                if(position == 2){
                    logOut();
                }

            }
        });

    }

    public void logOut(){
        MediaPlayer audio = MediaPlayer.create(Settings.this, R.raw.ok_audio2);
        audio.start();
        Intent intent = new Intent(Settings.this, login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Toast.makeText(Settings.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                audio.stop();
            }
        }, 1000);

    }
}