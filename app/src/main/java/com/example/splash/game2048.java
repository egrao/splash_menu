package com.example.splash;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

public class game2048 extends AppCompatActivity {

    private GestureDetector mDetector;
    private GridLayout grid;
    private LinearLayout ScoreBestLinear;
    TextView scoreHolder;
    private Integer randomFila;
    private Integer randomColumn;
    private Integer random100;
    private Integer score;
    private Integer scoreUndo;

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
        grid = findViewById(R.id.Grid);
        ScoreBestLinear = findViewById(R.id.ScoreBestId);
        mDetector = new GestureDetector(this, new GestureListener());
        scoreHolder = (TextView) ScoreBestLinear.getChildAt(0);
        score = 0;
        gameOver = false;
        win = false;

        arrayButtons();
        board();
        initialBoard = copyBoard(cells);
        copiedBoard = copyBoard(cells);
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

    public void arrayButtons(){
        for (int i = 0; i< cells.length; i++){
            for(int j=0; j<cells.length; j++){
                int aux=(i*4)+j;
                cells[i][j] = (Button) grid.getChildAt(aux);
            }
        }
    }

    public void board(){
        for (int i = 0; i < 2; i++) {
            newCell();
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

    public void repaintBoard( String[][] board ){
        for (int i = 0; i<cells.length; i++){
            for(int j=0; j<cells.length; j++){
                cells[i][j].setText(board[i][j]);
            }
        }
        paintColor();
    }


    public void setScore(Integer result){
//        score = Integer.parseInt(scoreHolder.getText().toString());
        int newScore = score + result;
        scoreHolder.setText(String.valueOf(newScore));
    }

//    public void checkGameOver(){
//        String [][] auxBoard = copyBoard(cells);
//        gameOver = Arrays.deepEquals(copiedBoard, auxBoard);
//    }

    public void checkWin(){
        if(win == true){
            Toast.makeText(game2048.this, "SUCCESFULLY INVADED UKRANIA", Toast.LENGTH_SHORT).show();
        }
    }

    public void newCell(){
        randomFila = new Random().nextInt(4);
        randomColumn = new Random().nextInt(4);
        random100 = new Random().nextInt(101);

        do {
            randomFila = new Random().nextInt(4);
            randomColumn = new Random().nextInt(4);
        }while(!cells[randomFila][randomColumn].getText().equals(""));

        if (random100 <= 90) {

            cells[randomFila][randomColumn].setText(String.valueOf(2));
            cells[randomFila][randomColumn].setBackgroundColor(paintCell(String.valueOf(2))); //pintamos

        }else if (random100>90){

            cells[randomFila][randomColumn].setText(String.valueOf(4));
            cells[randomFila][randomColumn].setBackgroundColor(paintCell(String.valueOf(4))); //pintamos

        }

    }

    public void flingRight(){
        copiedBoard = copyBoard(cells);
        for (int i=0; i<4;i++){
            checkRight(i);
            addRight(i);
            checkRight(i);
            paintColor();
        }
        newCell();
        checkWin();
//        checkGameOver();
//        if (gameOver) {
//            Toast.makeText(game2048.this, "IS OVER", Toast.LENGTH_SHORT).show();
//        }
    }

    public void flingLeft(){
        copiedBoard = copyBoard(cells);
        for (int i=0; i<4;i++){
            checkLeft(i);
            addLeft(i);
            checkLeft(i);
            paintColor();
        }
        newCell();
        checkWin();
//        checkGameOver();
//        if (gameOver) {
//            Toast.makeText(game2048.this, "IS OVER", Toast.LENGTH_SHORT).show();
//        }
    }

    public void flingUp(){
        copiedBoard = copyBoard(cells);
        for (int i=0; i<4;i++){
            checkUp(i);
            addUp(i);
            checkUp(i);
            paintColor();
        }
        newCell();
        checkWin();
//        checkGameOver();
//        if (gameOver) {
//            Toast.makeText(game2048.this, "IS OVER", Toast.LENGTH_SHORT).show();
//        }
    }

    public void flingDown(){
        copiedBoard = copyBoard(cells);
        for (int i=0; i<4;i++){
            checkDown(i);
            addDown(i);
            checkDown(i);
            paintColor();
        }
        newCell();
        checkWin();
//        checkGameOver();
//        if (gameOver) {
//            Toast.makeText(game2048.this, "IS OVER", Toast.LENGTH_SHORT).show();
//        }
    }

    public void addRight(int row){
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

    public void checkRight(int row){
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

    public void addLeft(int row){
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

    public void checkLeft(int row){
        for (int i = 0; i < 4; i++) {
            for (int j=0; j < 3; j++) {
                if (cells[row][j].getText().equals("") && !(cells[row][j+1].getText().equals(""))) {
                    cells[row][j].setText(cells[row][j+1].getText()); //3
                    cells[row][j+1].setText(""); //2
            }
            }
        }
    }

    public void addUp(int column){
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

    public void checkUp(int column){
        for (int i = 0; i < 4; i++) {
            for (int j=0; j < 3; j++) {
                if (cells[j][column].getText().equals("") && !(cells[j+1][column].getText().equals(""))) {
                    cells[j][column].setText(cells[j+1][column].getText()); //3
                    cells[j+1][column].setText(""); //2
                }
            }
        }
    }

    public void addDown(int column){
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

    public void checkDown(int column){
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
//                return R.drawable.n_2;
                return 0xFFEEE6DB;

            case "4":
//                return R.drawable.n_4;
                return 0xFFECDEC3;

            case "8":
//                return R.drawable.n_8;
                return 0xFFEEB27E;

            case "16":
//                return R.drawable.n_16;
                return 0xFFF59666;

            case "32":
//                return R.drawable.n_32;
                return 0xFFF37C64;

            case "64":
//                return R.drawable.n_64;
                return 0xFFF46042;

            case "128":
//                return R.drawable.n_128;
                return 0xFFEACF76;

            case "256":
//                return R.drawable.n_256;
                return 0xFFEBCA69;

            case "512":
//                return R.drawable.n_512;
                return 0xFFEDC95B;

            case "1024":
//                return R.drawable.n_1024;
                return 0xFFEAC155;

            case "2048":
//                return R.drawable.n_2048;
                win = true;
                return 0xFFE8BE4E;
        }
        return 0xFFC7BFB8;
    }

    public void undoButton2048(View view) {
        repaintBoard(copiedBoard);
        setScore(0);
    }

    public void restartButton2048(View view) {
//        copyBoard(initialMatrix, copiedMatrix);
        score = 0;
        repaintBoard(initialBoard);
        setScore(score);
    }
}