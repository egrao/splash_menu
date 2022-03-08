package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class scoreboard extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerViewPeg;
    private RecyclerView mRecyclerView2048;
    private ArrayList<score> mScoresData;
    private ArrayList<score> mScoresData2;
    private ScoresAdapter mAdapter;
    private ScoresAdapter mAdapter2;
    private MyOpenHelper mDB;
    public String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        mDB = new MyOpenHelper(this);
        Bundle extras = getIntent().getExtras();
        user = extras.getString("user");

        // Initialize the RecyclerView.
        mRecyclerViewPeg = findViewById(R.id.recyclerviewPegscore);
        mRecyclerView2048 = findViewById(R.id.recyclerview2048score);

        // Get the appropriate column count.
//        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        // Set the Layout Manager.
        mRecyclerViewPeg.setLayoutManager(new GridLayoutManager(
                this, 1));
        mRecyclerView2048.setLayoutManager(new GridLayoutManager(
                this, 1));

        // Initialize the ArrayList that will contain the data.
        mScoresData = new ArrayList<>();
        mScoresData2 = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new ScoresAdapter(this, mScoresData);
        mAdapter2 = new ScoresAdapter(this, mScoresData2);
        mRecyclerViewPeg.setAdapter(mAdapter);
        mRecyclerView2048.setAdapter(mAdapter2);

        // Get the data.
        initializeData("gamePeg");
        initializeData("game2048");



    }

    /**
     * Initialize the sports data from resources.
     */
    private void initializeData(String game) {
        // Get the resources from the XML file.
        String[] scoresList = getList(game, "_score");
        String[] timeList = getList(game, "time");
//        TypedArray scoresImageResources =
//                getResources().obtainTypedArray(R.array.sports_images);


        // Create the ArrayList of Sports objects with the titles and
        // information about each sport.
        if (game.equals("gamePeg")) {
            // Clear the existing data (to avoid duplication).
            mScoresData.clear();

            for (int i = 0; i < scoresList.length; i++) {
                mScoresData.add(new score(scoresList[i], timeList[i],
                        R.drawable.solitaire));
            }
        }
        else{
            // Clear the existing data (to avoid duplication).
            mScoresData2.clear();

            for (int i = 0; i < scoresList.length; i++) {
                mScoresData2.add(new score(scoresList[i], timeList[i],
                        R.drawable.score2048_bg));
            }
        }

        // Recycle the typed array.
//        scoresImageResources.recycle();

        // Notify the adapter of the change.
        if (game.equals("gamePeg")){
            mAdapter.notifyDataSetChanged();
        }
        else{
            mAdapter2.notifyDataSetChanged();
        }

    }

    public String [] getList(String game, String column){

        Cursor c = mDB.getScoreC(user, game);
        String[] list = new String[c.getCount()];

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            String value;
            Integer cont = 0;
            do {
                if (column.equals("time")){
                    value = c.getString(c.getColumnIndexOrThrow(column));
                }
                else{
                    value = String.valueOf(c.getInt(c.getColumnIndexOrThrow(column)));
                }
                list[cont] = value;
                cont++;
            } while (c.moveToNext());
        }
        c.close();

        return list;
    }
}