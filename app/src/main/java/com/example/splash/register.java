package com.example.splash;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {

    private static final String TAG = register.class.getSimpleName();

//    private EditText mEditWordView;
    private MyOpenHelper mDB;
    private EditText mEditUserView;
    private EditText mEditPassView;
    Boolean exists;

    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY1 = "com.example.splash.REPLY1";
    public static final String EXTRA_REPLY2 = "com.example.splash.REPLY2";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_register);

        mDB = new MyOpenHelper(this);
        mEditUserView = (EditText) findViewById(R.id.edit_username);
        mEditPassView = (EditText) findViewById(R.id.edit_pass);
    }


    /* *
     * Click handler for the Save button.
     *  Creates a new intent for the reply, adds the reply message to it as an extra,
     *  sets the intent result, and closes the activity.
     */
    public void returnReply(View view) {

        String user = mEditUserView.getText().toString();
        String pass = mEditPassView.getText().toString();

        //cambiar por getuser y c==null
        exists = mDB.search(user, MyOpenHelper.KEY_USER);

        if(exists == false){
            Intent replyIntent = new Intent();
            replyIntent.putExtra(EXTRA_REPLY1, user);
            replyIntent.putExtra(EXTRA_REPLY2, pass);
            setResult(RESULT_OK, replyIntent);
            finish();
        }
        else{
            mEditUserView.setText("");
            mEditPassView.setText("");
            Toast.makeText(register.this, "Usuario en uso, pruebe de nuevo", Toast.LENGTH_SHORT).show();
        }


    }
}