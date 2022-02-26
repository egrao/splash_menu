package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class login extends AppCompatActivity {

    public static final int REGISTER = 1;
    private MyOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //////COSAS////////

        mDB = new MyOpenHelper(this);

//        boton para añadir, seria el register que te manda a la ventana para nuevo usuario
        Button fab = (Button) findViewById(R.id.button_register);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts empty edit activity.
                Intent intent = new Intent(getBaseContext(), register.class);
                startActivityForResult(intent, REGISTER);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REGISTER) {
            if (resultCode == RESULT_OK) {
                String user = data.getStringExtra(register.EXTRA_REPLY1);
                String pass = data.getStringExtra(register.EXTRA_REPLY2);

                // Update the database.
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
//                    int id = data.getIntExtra(WordListAdapter.EXTRA_ID, -99);

//                    if (id == WORD_ADD) {
                        mDB.insert(user, pass, null, null, null, null);
//                    } else if (id >= 0) {
//                        mDB.update(id, word);
//                    }
                    // Update the UI.
//                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "no se ha añadido nada uwu",
                            Toast.LENGTH_LONG).show();
                }
            }
            String [] aux = mDB.getUser();
            TextView aux1 = (TextView) findViewById(R.id.uno);
            TextView aux2 = (TextView) findViewById(R.id.dos);
            aux1.setText(aux[0]);
            aux2.setText(aux[1]);
        }
    }

}
