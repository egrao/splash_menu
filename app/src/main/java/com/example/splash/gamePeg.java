package com.example.splash;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLOutput;

public class gamePeg extends AppCompatActivity {

    private static final String game = "gamePeg";
    private GridLayout grid;
    private Button button, button0;
    private Button lastClicked = null;
    private Button justClicked = null;
    private int lastFila;
    private int lastColumna;
    private Drawable.ConstantState basePiece;
    private Drawable.ConstantState emptyPiece;
    private Drawable.ConstantState selectedPiece;
    private Drawable.ConstantState borderPiece;
    private Button[][] pieces = new Button [7][7];
    private Drawable[][] initialMatrix = new Drawable [7][7];
    private Drawable[][] copiedMatrix = new Drawable [7][7];
    private boolean gameOver;
    private boolean win;
    private Integer HScore;
    private Integer score;
    private String timerPeg;
    private Chronometer mChronometer;
    private TextView scoreHolder;
    private long elapsedMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game_peg);

        /////////////////////COSAS/////////////////////////
        Bundle extras = getIntent().getExtras();
        HScore = extras.getInt("HScore");
        mChronometer = (Chronometer) findViewById(R.id.chronometerPeg);
        scoreHolder = (TextView) findViewById(R.id.scorePeg);
        score = 0;
        gameOver = false;
        win = false;
        piecesConstantState();
        arrayButtons();

        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
        //copying initial position into 2 arrays
        copyBoard(pieces, initialMatrix);
        copyBoard(pieces, copiedMatrix);

    }

    public void piecesConstantState(){
        basePiece = getDrawable(R.drawable.peg_base_piece).getConstantState();
        emptyPiece = getDrawable(R.drawable.peg_empty_piece).getConstantState();
        selectedPiece = getDrawable(R.drawable.peg_selected_piece).getConstantState();
        borderPiece = getDrawable(R.drawable.peg_piece_bg).getConstantState();
    }


    public void arrayButtons(){
        grid = findViewById(R.id.GridPeg);
        for (int fila = 0; fila<pieces.length; fila++){
            for(int columna=0; columna<pieces.length; columna++){
                int aux=(fila*7)+columna;
                int aux2 = fila;
                int aux3 = columna;
                pieces[fila][columna] = (Button) grid.getChildAt(aux);
                pieces[fila][columna].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        justClicked = findViewById(v.getId());

                        if (lastClicked == null && (justClicked.getBackground().getConstantState()).equals(basePiece)) {

                            lastClicked = justClicked;
                            lastFila = aux2;
                            lastColumna = aux3;
                            lastClicked.setBackgroundResource(R.drawable.peg_selected_piece);

                        }

                        else if (lastClicked == null && (justClicked.getBackground().getConstantState()).equals(emptyPiece)) {

                        }

                        else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                && (lastColumna-aux3 == -2 && lastFila == aux2)
                                && (pieces[aux2][aux3-1].getBackground().getConstantState()).equals(basePiece)){

                            copyBoard(pieces, copiedMatrix);
                            lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                            pieces[aux2][aux3-1].setBackgroundResource(R.drawable.peg_empty_piece);
                            justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                            score = Integer.parseInt(scoreHolder.getText().toString());
                            Integer points = calcPoints();
                            elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
                            setScore(points);
                            lastClicked = null;
                            checkGameOver();
                        }

                        else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                && (lastColumna-aux3 == 2  && lastFila == aux2)
                                && (pieces[aux2][aux3+1].getBackground().getConstantState()).equals(basePiece)){

                            copyBoard(pieces, copiedMatrix);
                            lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                            pieces[aux2][aux3+1].setBackgroundResource(R.drawable.peg_empty_piece);
                            justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                            score = Integer.parseInt(scoreHolder.getText().toString());
                            Integer points = calcPoints();
                            elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
                            setScore(points);
                            lastClicked = null;
                            checkGameOver();

                        }

                        else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                && (lastFila-aux2 == 2  && lastColumna == aux3)
                                && (pieces[aux2+1][aux3].getBackground().getConstantState()).equals(basePiece)){

                            copyBoard(pieces, copiedMatrix);
                            lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                            pieces[aux2+1][aux3].setBackgroundResource(R.drawable.peg_empty_piece);
                            justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                            score = Integer.parseInt(scoreHolder.getText().toString());
                            Integer points = calcPoints();
                            elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
                            setScore(points);
                            lastClicked = null;
                            checkGameOver();

                        }
                        else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                && (lastFila-aux2 == -2  && lastColumna == aux3)
                                && (pieces[aux2-1][aux3].getBackground().getConstantState()).equals(basePiece)){

                            copyBoard(pieces, copiedMatrix);
                            lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                            pieces[aux2-1][aux3].setBackgroundResource(R.drawable.peg_empty_piece);
                            justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                            score = Integer.parseInt(scoreHolder.getText().toString());
                            Integer points = calcPoints();
                            elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
                            setScore(points);
                            lastClicked = null;
                            checkGameOver();
                        }

                        else{
                            if(justClicked.getBackground().getConstantState().equals(emptyPiece)
                                    && lastClicked.getBackground().getConstantState().equals(selectedPiece)) {

                            }

                            else {
                                lastClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                lastFila = aux2;
                                lastColumna = aux3;
                                lastClicked = justClicked;
                                lastClicked.setBackgroundResource(R.drawable.peg_selected_piece);
                            }
                        }
                    }
                });
            }
        }
    }

    public void copyBoard( Button[][] sourceMatrix,  Drawable[][] copiedMatrix){
        for (int fila = 0; fila<pieces.length; fila++){
            for(int columna=0; columna<pieces.length; columna++){
                if(sourceMatrix[fila][columna].getBackground().getCurrent().getConstantState().equals(selectedPiece)){
                    copiedMatrix[fila][columna] = getDrawable(R.drawable.peg_base_piece);
                }
                else {
                    copiedMatrix[fila][columna] = sourceMatrix[fila][columna].getBackground().getCurrent();
                }
            }
        }
    }

//
    public void repaintBoard( Drawable[][] matrix ){
        for (int fila = 0; fila<pieces.length; fila++){
            for(int columna=0; columna<pieces.length; columna++){
                pieces[fila][columna].setBackground(matrix[fila][columna]);
            }
        }
        lastClicked = null;
    }

    public void setScore(Integer points){
        int newScore = score + points;
        scoreHolder.setText(String.valueOf(newScore));
    }

    public void setScoreRestart(){
        int newScore = 0;
        score = Integer.parseInt(scoreHolder.getText().toString());
        scoreHolder.setText(String.valueOf(newScore));
    }

    public Integer calcPoints(){
        long aux =(SystemClock.elapsedRealtime() - mChronometer.getBase()) - elapsedMillis;
        int seconds = (int) ((aux / 1000) % 60);
        int points = 10-seconds;
        if(points<0){
            points = 0;
        }
        return points;
    }

    public boolean checkWin(){
        win = false;
        int cont = 0;
        int fila = 0;
        int columna = 0;
            while (fila < pieces.length && cont < 2) {
                fila++;
                while (columna < pieces.length && cont < 2) {
                    if (pieces[fila][columna].getBackground().getConstantState().equals(basePiece)) {
                        cont++;
                    }
                    columna++;
                }
            }

        if(cont == 1){
            win = true;
        }
        return win;
    }

    //                    checking possible moves sideways
    public void checkGameOver(){
        gameOver = true;
        for (int i = 0; i<pieces.length; i++){
            for (int j = 0; j<pieces.length; j++){
                int aux1 = j-2;
                int aux2 = j+2;

                if (aux1 == -2){
                    //                    checking possible moves sideways
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j+1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+2].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }

                else if (aux1 == -1){
                    //                    checking possible moves sideways
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j+1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+2].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i][j-1].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                    //                    checking possible moves sideways
                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+1].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }
                ////////////
                else if (aux2 == 7){
                    //                    checking possible moves sideways
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+1].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i][j-2].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
//                                      checking possible moves sideways
                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j+1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j-1].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }

                else if (aux2 == 8){
                    //                    checking possible moves sideways
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j-2].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }


                /////////
                else if (aux1 >= 0 && aux2 <= 6){
                    //                    checking possible moves sideways
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j+1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+2].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i][j-1].getBackground().getConstantState()).equals(emptyPiece))){

                        gameOver = false;
                    }


                    //                    checking possible moves sideways
                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j-2].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i][j+1].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }


            }
        }

        if (gameOver == true){
            checkGameOverToptoBottom();
        }
    }

    //                    checking possible moves top to bottom
    public void checkGameOverToptoBottom(){
        for (int i = 0; i<pieces.length; i++){
            for (int j = 0; j<pieces.length; j++){
                int aux1 = i-2;
                int aux2 = i+2;

                if (aux1 == -2){
//                    checking possible moves top to bottom
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i+2][j].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }

                else if (aux1 == -1){
//                    checking possible moves top to bottom
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i+2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
//                    checking possible moves top to bottom
                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+1].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }

                else if (aux2 == 7){
//                    checking possible moves top to bottom
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i+1][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }

                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }

                else if (aux2 == 8){

                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }

                else if (aux1 >= 0 && aux2 <= 6){

                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i+2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }


                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i+1][j].getBackground().getConstantState()).equals(emptyPiece))){
                        gameOver = false;
                    }
                }

            }
        }

        win = checkWin();
        if(gameOver == true){
            if(win == true){
                score = Integer.parseInt(scoreHolder.getText().toString());
                timerPeg = mChronometer.getText().toString();
                Toast.makeText(gamePeg.this, "WINNER DINNER", Toast.LENGTH_SHORT).show();
                returnReply();
            }
            else{
                score = Integer.parseInt(scoreHolder.getText().toString());
                timerPeg = mChronometer.getText().toString();
                MediaPlayer audio = MediaPlayer.create(gamePeg.this, R.raw.chimp_screech2);
                audio.start();
                Toast.makeText(gamePeg.this, "Game Over bois", Toast.LENGTH_SHORT).show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        audio.stop();
                        returnReply();
                    }
                }, 1000);

            }
        }
    }

    public void undoButton(View view) {
        elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
        setScore(0);
        repaintBoard(copiedMatrix);
    }

    public void restartButton(View view) {

        repaintBoard(initialMatrix);
        setScoreRestart();
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
        elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
    }

    public void returnReply() {
        if(score > HScore){
            Intent replyIntent = new Intent();
            replyIntent.putExtra("score", String.valueOf(score));
            replyIntent.putExtra("time", timerPeg);
            replyIntent.putExtra("game", game);
            setResult(RESULT_OK, replyIntent);
            finish();
        }
        else{
            setResult(RESULT_CANCELED);
            finish();
        }
    }

}