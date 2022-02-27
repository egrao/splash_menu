package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    private EditText mEditUserView;
    private EditText mEditPassView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //////COSAS////////

        mDB = new MyOpenHelper(this);
        mEditUserView = (EditText) findViewById(R.id.login_username);
        mEditPassView = (EditText) findViewById(R.id.login_pass);
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
                            "campos vacios",
                            Toast.LENGTH_LONG).show();
                }
            }
//            hacer otro if para result cancelled cuando el search dice que ya se ha encontrado el user si
//                    quiero controlarlo desde esta actividad, si no pues hacerlo en el propio act de register
//            String [] aux = mDB.getUser();
//            TextView aux1 = (TextView) findViewById(R.id.uno);
//            TextView aux2 = (TextView) findViewById(R.id.dos);
//            aux1.setText(aux[0]);
//            aux2.setText(aux[1]);
//            Boolean aux9 = mDB.search("pepe");
//            if (aux9 == true){
//                Toast.makeText(login.this, "WORKING AS INTEnDED", Toast.LENGTH_SHORT).show();
//            }
        }
    }



    public void login(View view) {
        String userLogin = mEditUserView.getText().toString();
        String passLogin = mEditPassView.getText().toString();

        Cursor c = mDB.getUser(userLogin);
        if (c != null && c.getCount() > 0) {
            Boolean loginSuccessful = checkCredentials(c, userLogin, passLogin);
            if(loginSuccessful == true){
                Intent intent = new Intent(this, Menu.class);
                intent.putExtra(MyOpenHelper.KEY_USER, userLogin);
                //meter extra con el user que se logea
                startActivity(intent);
            }
            else{
                mEditUserView.setText("");
                mEditPassView.setText("");
                Toast.makeText(login.this, "Contraseña incorrecta. Te queda 1 intento", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            mEditUserView.setText("");
            mEditPassView.setText("");
            Toast.makeText(login.this, "Usuario incorrecto o no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean checkCredentials(Cursor c, String user, String password){
        c.moveToFirst();

        String auxUser = c.getString(c.getColumnIndexOrThrow(MyOpenHelper.KEY_USER));
        String auxPass = c.getString(c.getColumnIndexOrThrow(MyOpenHelper.KEY_PASS));
        System.out.println("liturgia "+auxUser);
        System.out.println("liturgia "+auxPass);
        System.out.println("liturgia editv user "+ user);
        System.out.println("liturgia editv pass "+ password);

        if(auxUser.equals(user) && auxPass.equals(password)){
            System.out.println("liturgia pene");
            c.close();
            mDB.getWritableDatabase().execSQL("DELETE FROM users");
            return true;
        }
        c.close();
        return false;
    }

}
