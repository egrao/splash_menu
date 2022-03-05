package com.example.splash;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Chronometer;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Random;

public class game2048 extends AppCompatActivity {

    private static final String game = "game2048";
    private GestureDetector mDetector;
    private GridLayout grid;
    private LinearLayout ScoreBestLinear;
    private TextView scoreHolder;
    private TextView bestScoreHolder;
    private Chronometer mChronometer;
    private Integer copiedScore;
    private Integer randomFila;
    private Integer randomColumn;
    private Integer random100;
    private Integer score;
    private Integer HScore;
    private String timer2048;

    private boolean emptyCells;
    private boolean gameOver;
    private boolean win;
    private Button[][] cells = new Button [4][4];
    private String[][] initialBoard = new String [4][4];
    private String[][] copiedBoard = new String [4][4];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2048);
/////////////////////COSAS/////////////////
        Bundle extras = getIntent().getExtras();
        HScore = extras.getInt("HScore");

        grid = findViewById(R.id.Grid);
        mDetector = new GestureDetector(this, new GestureListener());
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        scoreHolder = (TextView) findViewById(R.id.score2048);
        bestScoreHolder = (TextView) findViewById(R.id.best_score2048);
        score = 0;
        gameOver = false;
        win = false;

        arrayButtons(cells);
        initialBoard = copyBoard(cells);
        board();
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();

        copiedBoard = copyBoard(cells);
//        cells[0][1].setBackgroundColor(paintCell(String.valueOf(1024)));
//        cells[0][2].setBackgroundColor(paintCell(String.valueOf(1024)));
//        cells[0][2].setText(String.valueOf(1024));
//        cells[0][1].setText(String.valueOf(1024));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
//        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{

        private final int SWIPE_THRESHOLD = 100;
        private final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
//                            Toast.makeText(game2048.this, "RIGHT", Toast.LENGTH_SHORT).show();
                            flingRight();
                        } else {
                            flingLeft();
//                            Toast.makeText(game2048.this, "LEFT", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            flingDown();
//                            Toast.makeText(game2048.this, "DOWN", Toast.LENGTH_SHORT).show();
                        } else {
                            flingUp();
//                            Toast.makeText(game2048.this, "UP", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }

    public void arrayButtons(Button[][] cells){
        for (int i = 0; i< cells.length; i++){
            for(int j=0; j<cells.length; j++){
                int aux=(i*4)+j;
                cells[i][j] = (Button) grid.getChildAt(aux);
            }
        }
    }

    public void board(){
        for (int i = 0; i < 2; i++) {
            newCell(cells);
        }
    }

    public String [][] copyBoard( Button[][] originalBoard){
        String [][] newBoard = new String [cells.length][cells.length];
        for (int i = 0; i<cells.length; i++){
            for(int j=0; j<cells.length; j++){
                  newBoard[i][j] = String.valueOf(originalBoard[i][j].getText());
            }
        }
        return newBoard;
    }

//    public Button [][] copyBoard2( Button[][] originalBoard){
//        Button [][] newBoard = new Button [cells.length][cells.length];
//        for (int i = 0; i<cells.length; i++){
//            for(int j=0; j<cells.length; j++){
//                newBoard[i][j] = originalBoard[i][j];
//            }
//        }
//        return newBoard;
//    }

    public void repaintBoard( String[][] board ){
        for (int i = 0; i<cells.length; i++){
            for(int j=0; j<cells.length; j++){
                cells[i][j].setText(board[i][j]);
            }
        }
        paintColor();
    }


    public void setScore(Integer result){
        int newScore = score + result;
        scoreHolder.setText(String.valueOf(newScore));
    }


    public boolean checkEmptyCells(){
        emptyCells = true;
        for (int i = 0; i<cells.length; i++){
            for(int j=0; j<cells.length; j++){
                if(cells[i][j].getText().equals("")){
                    return emptyCells;
                }
            }
        }
        emptyCells = false;
        return emptyCells;
    }

    public void checkGameOver(){
        emptyCells = checkEmptyCells();
        System.out.println("gameover empty"+emptyCells);
        gameOver = true;

        if(emptyCells == false){
            //right
            for (int row=0; row<4;row++) {
                for (int i = 3; i > 0; i--) {
                    if (cells[row][i].getText().equals(cells[row][i - 1].getText())) {
                        gameOver = false;
                        break;
                    }
                }
            }
            //left
            if(gameOver == true) {
                for (int row = 0; row < 4; row++) {
                    for (int i = 0; i < 3; i++) {
                        if (cells[row][i].getText().equals(cells[row][i + 1].getText())) {
                            gameOver = false;
                            break;
                        }
                    }

                }
            }
            //up
            if(gameOver == true) {
                for (int column = 0; column < 4; column++) {
                    for (int i = 0; i < 3; i++) {
                        if (cells[i][column].getText().equals(cells[i + 1][column].getText())) {
                            gameOver = false;
                            break;
                        }
                    }

                }
            }
            //down
            if(gameOver == true) {
                for (int column = 0; column < 4; column++) {
                    for (int i = 3; i > 0; i--) {
                        if (cells[i][column].getText().equals(cells[i - 1][column].getText())) {
                            gameOver = false;
                            break;
                        }
                    }
                }
            }
        }
        else{
            gameOver = false;
        }

        if (gameOver == true) {
            score = Integer.parseInt(scoreHolder.getText().toString());
            timer2048 = mChronometer.getText().toString();
            Toast.makeText(game2048.this, "IS OVER", Toast.LENGTH_SHORT).show();
            returnReply();
        }

    }

    public void checkWin(){
        if(win == true){
            score = Integer.parseInt(scoreHolder.getText().toString());
            timer2048 = mChronometer.getText().toString();
            Toast.makeText(game2048.this, "WIN WIN WIN WIN", Toast.LENGTH_SHORT).show();
            returnReply();

        }
    }

    public void newCell(Button[][] cells){
        int cont = 0;
        Boolean avaibleCell = checkEmptyCells();
        randomFila = new Random().nextInt(4);
        randomColumn = new Random().nextInt(4);
        random100 = new Random().nextInt(101);


        do {
            randomFila = new Random().nextInt(4);
            randomColumn = new Random().nextInt(4);
            cont++;
        }while(!cells[randomFila][randomColumn].getText().equals("") && avaibleCell == true);

        if (random100 <= 90 && avaibleCell == true) {

            cells[randomFila][randomColumn].setText(String.valueOf(2));
            cells[randomFila][randomColumn].setBackgroundColor(paintCell(String.valueOf(2))); //pintamos

        }else if (random100>90 && avaibleCell == true){

            cells[randomFila][randomColumn].setText(String.valueOf(4));
            cells[randomFila][randomColumn].setBackgroundColor(paintCell(String.valueOf(4))); //pintamos

        }

    }

    public void flingRight(){
        copiedBoard = copyBoard(cells);
        copiedScore = Integer.parseInt(scoreHolder.getText().toString());
        for (int i=0; i<4;i++){
            checkRight(i, cells);
            addRight(i, cells);
            checkRight(i, cells);
            paintColor();
        }
        newCell(cells);
        checkGameOver();
        checkWin();
//        checkGameOver();
//        if (gameOver) {
//            Toast.makeText(game2048.this, "IS OVER", Toast.LENGTH_SHORT).show();
//        }
    }

    public void flingLeft(){
        copiedBoard = copyBoard(cells);
        copiedScore = Integer.parseInt(scoreHolder.getText().toString());
        for (int i=0; i<4;i++){
            checkLeft(i, cells);
            addLeft(i, cells);
            checkLeft(i, cells);
            paintColor();
        }
        newCell(cells);
        checkGameOver();
        checkWin();
//        if (gameOver) {
//            Toast.makeText(game2048.this, "IS OVER", Toast.LENGTH_SHORT).show();
//        }
    }

    public void flingUp(){
        copiedBoard = copyBoard(cells);
        copiedScore = Integer.parseInt(scoreHolder.getText().toString());
        for (int i=0; i<4;i++){
            checkUp(i, cells);
            addUp(i, cells);
            checkUp(i, cells);
            paintColor();
        }
        newCell(cells);
        checkGameOver();
        checkWin();
//        if (gameOver) {
//            Toast.makeText(game2048.this, "IS OVER", Toast.LENGTH_SHORT).show();
//        }
    }

    public void flingDown(){
        copiedBoard = copyBoard(cells);
        copiedScore = Integer.parseInt(scoreHolder.getText().toString());
        for (int i=0; i<4;i++){
            checkDown(i, cells);
            addDown(i, cells);
            checkDown(i, cells);
            paintColor();
        }
        newCell(cells);
        checkGameOver();
        checkWin();
//        if (gameOver) {
//            Toast.makeText(game2048.this, "IS OVER", Toast.LENGTH_SHORT).show();
//        }
    }

    public void addRight(int row, Button[][] cells){
//        int index = row * 4;
//        Button button1 = (Button) grid.getChildAt(index);
//        Button button2 = (Button) grid.getChildAt(index+1);
//        Button button3 = (Button) grid.getChildAt(index+2);
//        Button button4 = (Button) grid.getChildAt(index+3);
//
//
//        if (button1.getText() == button2.getText() && button1.getText() == button3.getText() && button1.getText() == button4.getText()) {
//            int original = Integer.parseInt(String.valueOf(button1.getText()));
//            int result = original * 4;
//            button1.setText("");
//            button2.setText("");
//            button3.setText("");
//            button4.setText(String.valueOf(result));
//        }
        for(int i=3; i>0; i--) {
            //botón de la dcha
//            Button buttonDcha = (Button) grid.getChildAt(i); //3
//            //botón de la izq
//            Button buttonIzq = (Button) grid.getChildAt(i-1); //2
            if (cells[row][i].getText().equals(cells[row][i-1].getText())) {

                if ((cells[row][i].getText()).equals("") || (cells[row][i-1].getText().equals(""))) {
                    System.out.println("Empty cells bro");
                } else {
                    int original = Integer.parseInt(String.valueOf(cells[row][i-1].getText()));
                    int result = original * 2;
                    cells[row][i-1].setText(""); //2
                    cells[row][i].setText(String.valueOf(result)); //3
                    score = Integer.parseInt(scoreHolder.getText().toString());
                    setScore(result);
                }
            }
        }
//        Button buttonDcha = (Button) grid.getChildAt(2);
//        Button buttonIzq = (Button) grid.getChildAt(1); //2
//        if (buttonDcha.getText().equals("") && !(buttonIzq.getText().equals(""))){
//            Toast.makeText(game2048.this, "FUNKA", Toast.LENGTH_SHORT).show();
////                buttonDcha.setText("pene");
//            buttonDcha.setText(buttonIzq.getText()); //3
//            buttonIzq.setText(""); //2
//        }
///////////////////////////////////////7
    }

    public void checkRight(int row, Button[][] cells){
//        int index = row * 4;
        for (int i = 0; i < 4; i++) {
            for (int j=3; j > 0; j--) {
                //botón de la dcha
//                Button buttonDcha = (Button) grid.getChildAt(j); //3
//                //botón de la izq
//                Button buttonIzq = (Button) grid.getChildAt(j-1); //2
                if (cells[row][j].getText().equals("") && !(cells[row][j-1].getText().equals(""))) {
                    cells[row][j].setText(cells[row][j-1].getText()); //3
                    cells[row][j-1].setText(""); //2
                }
            }
        }
    }

    public void addLeft(int row, Button[][] cells){
        for(int i=0; i<3; i++) {
            if (cells[row][i].getText().equals(cells[row][i+1].getText())) {

                if ((cells[row][i].getText()).equals("") || (cells[row][i+1].getText().equals(""))) {
                    System.out.println("Empty cells bro");
                } else {
                    int original = Integer.parseInt(String.valueOf(cells[row][i+1].getText()));
                    int result = original * 2;
                    cells[row][i+1].setText(""); //2
                    cells[row][i].setText(String.valueOf(result)); //3
                    score = Integer.parseInt(scoreHolder.getText().toString());
                    setScore(result);
                }
            }
        }
    }

    public void checkLeft(int row, Button[][] cells){
        for (int i = 0; i < 4; i++) {
            for (int j=0; j < 3; j++) {
                if (cells[row][j].getText().equals("") && !(cells[row][j+1].getText().equals(""))) {
                    cells[row][j].setText(cells[row][j+1].getText()); //3
                    cells[row][j+1].setText(""); //2
            }
            }
        }
    }

    public void addUp(int column, Button[][] cells){
        for(int i=0; i<3; i++) {
            if (cells[i][column].getText().equals(cells[i+1][column].getText())) {

                if ((cells[i][column].getText()).equals("") || (cells[i+1][column].getText().equals(""))) {
                    System.out.println("Empty cells bro");
                } else {
                    int original = Integer.parseInt(String.valueOf(cells[i+1][column].getText()));
                    int result = original * 2;
                    cells[i+1][column].setText(""); //2
                    cells[i][column].setText(String.valueOf(result)); //3
                    score = Integer.parseInt(scoreHolder.getText().toString());
                    setScore(result);
                }
            }
        }
    }

    public void checkUp(int column, Button[][] cells){
        for (int i = 0; i < 4; i++) {
            for (int j=0; j < 3; j++) {
                if (cells[j][column].getText().equals("") && !(cells[j+1][column].getText().equals(""))) {
                    cells[j][column].setText(cells[j+1][column].getText()); //3
                    cells[j+1][column].setText(""); //2
                }
            }
        }
    }

    public void addDown(int column, Button[][] cells){
        for(int i=3; i>0; i--) {
            if (cells[i][column].getText().equals(cells[i-1][column].getText())) {

                if ((cells[i][column].getText()).equals("") || (cells[i-1][column].getText().equals(""))) {
                    System.out.println("Empty cells bro");
                } else {
                    int original = Integer.parseInt(String.valueOf(cells[i-1][column].getText()));
                    int result = original * 2;
                    cells[i-1][column].setText(""); //2
                    cells[i][column].setText(String.valueOf(result)); //3
                    cells[i][column].setText(String.valueOf(result)); //3
                    score = Integer.parseInt(scoreHolder.getText().toString());
                    setScore(result);
                }
            }
        }
    }

    public void checkDown(int column, Button[][] cells){
        for (int i = 0; i < 4; i++) {
            for (int j=3; j > 0; j--) {
                if (cells[j][column].getText().equals("") && !(cells[j-1][column].getText().equals(""))) {
                    cells[j][column].setText(cells[j-1][column].getText()); //3
                    cells[j-1][column].setText(""); //2
                }
            }
        }
    }

    public void paintColor(){
        for (int i = 0; i<4; i++){
            for(int j=0; j<4; j++){
                int color = paintCell(cells[i][j].getText().toString());
                cells[i][j].setBackgroundColor(color);
            }
        }
    }

    public int paintCell(String num) {
        switch (num) {
            case "2":
                return 0xFFEEE6DB;

            case "4":
                return 0xFFECDEC3;

            case "8":
                return 0xFFEEB27E;

            case "16":
                return 0xFFF59666;

            case "32":
                return 0xFFF37C64;

            case "64":
                return 0xFFF46042;

            case "128":
                return 0xFFEACF76;

            case "256":
                return 0xFFEBCA69;

            case "512":
                return 0xFFEDC95B;

            case "1024":
                return 0xFFEAC155;

            case "2048":
                win = true;
                return 0xFFE8BE4E;
        }
        return 0xFFC7BFB8;
    }

    public void undoButton2048(View view) {
        repaintBoard(copiedBoard);
        score = copiedScore;
        setScore(0);
        win = false;
    }

    public void restartButton2048(View view) {
//        copyBoard(initialMatrix, copiedMatrix);
        score = 0;
        win = false;
        gameOver = false;
        repaintBoard(initialBoard);
        setScore(score);
        board();
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    public void returnReply() {
        if(score > HScore){
            Intent replyIntent = new Intent();
            replyIntent.putExtra("score", String.valueOf(score));
            replyIntent.putExtra("time", timer2048);
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