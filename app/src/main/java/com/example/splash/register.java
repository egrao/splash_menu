package com.example.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {

    private static final String TAG = register.class.getSimpleName();

//    private static final int NO_ID = -99;
//    private static final String NO_WORD = "";

    private EditText mEditWordView;
    private EditText mEditUserView;
    private EditText mEditPassView;

    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY1 = "com.example.splash.REPLY1";
    public static final String EXTRA_REPLY2 = "com.example.splash.REPLY2";

//    int mId = MainActivity.WORD_ADD;
//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEditUserView = (EditText) findViewById(R.id.edit_username);
        mEditPassView = (EditText) findViewById(R.id.edit_pass);
//        mEditWordView = (EditText) findViewById(R.id.edit_word);


        // Get data sent from calling activity.
//        Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
//        if (extras != null) {
//            int id = extras.getInt(WordListAdapter.EXTRA_ID, NO_ID);
//            String word = extras.getString(WordListAdapter.EXTRA_WORD, NO_WORD);
//            if (id != NO_ID && word != NO_WORD) {
//                mId = id;
//                mEditWordView.setText(word);
//            }
//        } // Otherwise, start with empty fields.
    }

    /* *
     * Click handler for the Save button.
     *  Creates a new intent for the reply, adds the reply message to it as an extra,
     *  sets the intent result, and closes the activity.
     */
    public void returnReply(View view) {
        String user = ((EditText) findViewById(R.id.edit_username)).getText().toString();
        String pass = ((EditText) findViewById(R.id.edit_pass)).getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY1, user);
        replyIntent.putExtra(EXTRA_REPLY2, pass);
//        replyIntent.putExtra(WordListAdapter.EXTRA_ID, mId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}