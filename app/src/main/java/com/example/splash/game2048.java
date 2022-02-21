package com.example.splash;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class game2048 extends AppCompatActivity {

    private GestureDetector mDetector;
    private GridLayout grid;
    private Integer randomFila;
    private Integer randomColumn;
    private Integer random100;
    private int gameOver;
    private Button[][] cells = new Button [4][4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2048);
/////////////////////COSAS/////////////////
        grid = findViewById(R.id.Grid);
        mDetector = new GestureDetector(this, new GestureListener());
        arrayButtons();
        board();
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
                            Toast.makeText(game2048.this, "RIGHT", Toast.LENGTH_SHORT).show();
                            flingRight();
                        } else {
                            flingLeft();
                            Toast.makeText(game2048.this, "LEFT", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            flingDown();
                            Toast.makeText(game2048.this, "DOWN", Toast.LENGTH_SHORT).show();
                        } else {
                            flingUp();
                            Toast.makeText(game2048.this, "UP", Toast.LENGTH_SHORT).show();
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
        grid = findViewById(R.id.Grid);
        for (int i = 0; i<4; i++){
            for(int j=0; j<4; j++){
                int aux=(i*4)+j;
                cells[i][j] = (Button) grid.getChildAt(aux);
            }
        }
    }

    public void board(){
//        grid = findViewById(R.id.Grid);

//        cells[0][0].setText("8");
//        cells[0][1].setText("8");
//        cells[0][2].setText("8");
//        cells[0][3].setText("8");


//        Button button5 = (Button) grid.getChildAt(4);
//        button5.setText(String.valueOf(5));


        for (int i = 0; i < 2; i++) {
//            randomCelda = new Random().nextInt(16);
//            random100 = new Random().nextInt(101);
//            button = (Button) grid.getChildAt(randomCelda);
//            do {
//                randomCelda = new Random().nextInt(16);
//                button = (Button) grid.getChildAt(randomCelda);
//            }while(!button.getText().equals(""));
//
//            if (random100 <= 90) {
//                button.setText(String.valueOf(2));
//            }else if (random100>90 && random100<=100){
//                button.setText(String.valueOf(4));
//
//            }

            newCell();
        }
    }

    public void newCell(){
        gameOver = 0;
        randomFila = new Random().nextInt(4);
        randomColumn = new Random().nextInt(4);
        random100 = new Random().nextInt(101);


//        button = (Button) grid.getChildAt(randomCelda);
        do {
            gameOver++;
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

        for (int i=0; i<4;i++){
            checkRight(i);
            addRight(i);
            checkRight(i);
            paintboard();
        }
        newCell();

//        Button button1 = (Button) grid.getChildAt(0);
//        Button button2 = (Button) grid.getChildAt(1);
//        Button button3 = (Button) grid.getChildAt(2);
//        Button button4 = (Button) grid.getChildAt(3);
//
//
//        if (button1.getText() == button2.getText() && button1.getText() == button3.getText() && button1.getText() == ((Button) grid.getChildAt(3)).getText()) {
//            int original = Integer.parseInt(String.valueOf(button1.getText()));
//            int result = original * 4;
//            button1.setText("");
//            button2.setText("");
//            button3.setText("");
//            button4.setText(String.valueOf(result));
//        }

    }

    public void flingLeft(){
        for (int i=0; i<4;i++){
            checkLeft(i);
            addLeft(i);
            checkLeft(i);
            paintboard();
        }
        newCell();
    }

    public void flingUp(){
        for (int i=0; i<4;i++){
            checkUp(i);
            addUp(i);
            checkUp(i);
            paintboard();
        }
        newCell();
    }

    public void flingDown(){
        for (int i=0; i<4;i++){
            checkDown(i);
            addDown(i);
            checkDown(i);
            paintboard();
        }
        newCell();

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
    public void paintboard(){
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
                return 0xFFE8BE4E;
        }
        return 0xFFC7BFB8;
    }
}