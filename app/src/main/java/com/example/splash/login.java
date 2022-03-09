package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        //////COSAS////////

        mDB = new MyOpenHelper(this);
        mEditUserView = (EditText) findViewById(R.id.login_username);
        mEditPassView = (EditText) findViewById(R.id.login_pass);

        Button registerButton = (Button) findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
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

                        mDB.insert(user, pass);
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "campos vacios",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }



    public void login(View view) {
        String userLogin = mEditUserView.getText().toString();
        String passLogin = mEditPassView.getText().toString();

        Cursor c = mDB.getUserInfo(userLogin, "users");
        if (c != null && c.getCount() > 0) {
            Boolean loginSuccessful = checkCredentials(c, userLogin, passLogin);
            if(loginSuccessful == true){
                Intent intent = new Intent(this, Menu.class);
                intent.putExtra(MyOpenHelper.KEY_USER, userLogin);
                //meter extra con el user que se logea
                startActivity(intent);
                login.this.finish();
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

        if(auxUser.equals(user) && auxPass.equals(password)){

            c.close();
            return true;
        }
        c.close();
        return false;
    }

}
