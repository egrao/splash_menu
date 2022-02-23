package com.example.splash;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class gamePeg extends AppCompatActivity {

    private GridLayout grid;
    private Button button, button0;
    private Button lastClicked = null;
    private Button justClicked = null;
    private int lastAux;
    private int lastFila;
    private int lastColumna;
    private Drawable.ConstantState basePiece;
    private Drawable.ConstantState emptyPiece;
    private Drawable.ConstantState selectedPiece;
    private Button[][] pieces = new Button [7][7];
    private boolean gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_peg);

        /////////////////////COSAS/////////////////////////
        gameOver = false;
        piecesConstantState();
        arrayButtons();
    }

    public void piecesConstantState(){
        basePiece = getDrawable(R.drawable.peg_base_piece).getConstantState();
        emptyPiece = getDrawable(R.drawable.peg_empty_piece).getConstantState();
        selectedPiece = getDrawable(R.drawable.peg_selected_piece).getConstantState();
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
//                        Toast.makeText(gamePeg.this, "1", Toast.LENGTH_SHORT).show();
//                        System.out.println("if1 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
                }

                else if (aux1 == -1){
                    //                    checking possible moves sideways
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j+1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+2].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i][j-1].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "2", Toast.LENGTH_SHORT).show();
//                        System.out.println("if2 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
                    //                    checking possible moves sideways
                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+1].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "3", Toast.LENGTH_SHORT).show();
//                        System.out.println("if3 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
                }
                ////////////
                else if (aux2 == 7){
                    //                    checking possible moves sideways
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+1].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i][j-2].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "4", Toast.LENGTH_SHORT).show();
//                        System.out.println("if4 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
//                                      checking possible moves sideways
                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j+1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j-1].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "5", Toast.LENGTH_SHORT).show();
//                        System.out.println("if5 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
                }

                else if (aux2 == 8){
                //                    checking possible moves sideways
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j-2].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "6", Toast.LENGTH_SHORT).show();
//                        System.out.println("if6 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
//                    //                    checking possible moves top to bottom
//                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
//                            && ((pieces[i+2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "61", Toast.LENGTH_SHORT).show();
//                        System.out.println("if7 "+"i:"+i+" j:"+j);
//                        gameOver = false;
//                    }
//
//                    //                    checking possible moves top to bottom
//                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
//                            && ((pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i+1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "62", Toast.LENGTH_SHORT).show();
//                        System.out.println("if8 "+"i:"+i+" j:"+j);
//                        gameOver = false;
//                    }
                }


                /////////
                else if (aux1 >= 0 && aux2 <= 6){
                    //                    checking possible moves sideways
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j+1].getBackground().getConstantState()).equals(basePiece)
                    && ((pieces[i][j+2].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i][j-1].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "7", Toast.LENGTH_SHORT).show();
//                        System.out.println("if7 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
//                    //                    checking possible moves top to bottom
//                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
//                            && ((pieces[i+2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "71", Toast.LENGTH_SHORT).show();
//                        System.out.println("if7 "+"i:"+i+" j:"+j);
//                        gameOver = false;
//                    }

                    //                    checking possible moves sideways
                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j-2].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i][j+1].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "8", Toast.LENGTH_SHORT).show();
//                        System.out.println("if8 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
//                    //                    checking possible moves top to bottom
//                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
//                            && ((pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i+1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "81", Toast.LENGTH_SHORT).show();
//                        System.out.println("if8 "+"i:"+i+" j:"+j);
//                        gameOver = false;
//                    }
//                    else{
//                        gameOver = false;
//                    }
                }


            }
        }
//        Toast.makeText(gamePeg.this, String.valueOf(gameOver), Toast.LENGTH_SHORT).show();
//        if(gameOver == true){
//            Toast.makeText(gamePeg.this, "dedSides", Toast.LENGTH_SHORT).show();
//        }
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
//                        Toast.makeText(gamePeg.this, "1", Toast.LENGTH_SHORT).show();
//                        System.out.println("if1 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
                }

                else if (aux1 == -1){
//                    checking possible moves top to bottom
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i+2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "2", Toast.LENGTH_SHORT).show();
//                        System.out.println("if2 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
//                    checking possible moves top to bottom
                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i][j-1].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i][j+1].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "3", Toast.LENGTH_SHORT).show();
//                        System.out.println("if3 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
                }

                else if (aux2 == 7){
//                    checking possible moves top to bottom
                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i+1][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "4", Toast.LENGTH_SHORT).show();
//                        System.out.println("if4 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }

                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "5", Toast.LENGTH_SHORT).show();
//                        System.out.println("if5 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
                }

                else if (aux2 == 8){

                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "6", Toast.LENGTH_SHORT).show();
//                        System.out.println("if6 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
//                    //                    checking possible moves top to bottom
//                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
//                            && ((pieces[i+2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "61", Toast.LENGTH_SHORT).show();
//                        System.out.println("if7 "+"i:"+i+" j:"+j);
//                        gameOver = false;
//                    }
//
//                    //                    checking possible moves top to bottom
//                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
//                            && ((pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i+1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "62", Toast.LENGTH_SHORT).show();
//                        System.out.println("if8 "+"i:"+i+" j:"+j);
//                        gameOver = false;
//                    }
                }

                else if (aux1 >= 0 && aux2 <= 6){

                    if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i+2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "7", Toast.LENGTH_SHORT).show();
//                        System.out.println("if7 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
//                    //                    checking possible moves top to bottom
//                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i+1][j].getBackground().getConstantState()).equals(basePiece)
//                            && ((pieces[i+2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i-1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "71", Toast.LENGTH_SHORT).show();
//                        System.out.println("if7 "+"i:"+i+" j:"+j);
//                        gameOver = false;
//                    }

                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
                            && ((pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i+1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "8", Toast.LENGTH_SHORT).show();
//                        System.out.println("if8 "+"i:"+i+" j:"+j);
                        gameOver = false;
                    }
//                    //                    checking possible moves top to bottom
//                    else if((pieces[i][j].getBackground().getConstantState()).equals(basePiece) && (pieces[i-1][j].getBackground().getConstantState()).equals(basePiece)
//                            && ((pieces[i-2][j].getBackground().getConstantState()).equals(emptyPiece) || (pieces[i+1][j].getBackground().getConstantState()).equals(emptyPiece))){
//                        Toast.makeText(gamePeg.this, "81", Toast.LENGTH_SHORT).show();
//                        System.out.println("if8 "+"i:"+i+" j:"+j);
//                        gameOver = false;
//                    }
//                    else{
//                        gameOver = false;
//                    }
                }


            }
        }
//        Toast.makeText(gamePeg.this, String.valueOf(gameOver), Toast.LENGTH_SHORT).show();
        if(gameOver == true){
            Toast.makeText(gamePeg.this, "Game Over bois", Toast.LENGTH_SHORT).show();
        }
    }


    public void arrayButtons(){
        grid = findViewById(R.id.GridPeg);
        for (int fila = 0; fila<7; fila++){
            for(int columna=0; columna<7; columna++){
                int aux=(fila*7)+columna;
                int aux2 = fila;
                int aux3 = columna;
                pieces[fila][columna] = (Button) grid.getChildAt(aux);
                pieces[fila][columna].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        justClicked = findViewById(v.getId());

                        if (lastClicked == null && (justClicked.getBackground().getConstantState()).equals(basePiece)) {
//                                Toast.makeText(gamePeg.this, "normal", Toast.LENGTH_SHORT).show();
                            lastClicked = justClicked;
                            lastAux = aux;
                            lastFila = aux2;
                            lastColumna = aux3;
                            lastClicked.setBackgroundResource(R.drawable.peg_selected_piece);
                        }

                        else if (lastClicked == null && (justClicked.getBackground().getConstantState()).equals(emptyPiece)) {
                            Toast.makeText(gamePeg.this, "SIKE", Toast.LENGTH_SHORT).show();
                        }

                        else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                && (lastColumna-aux3 == -2)
                                && (pieces[aux2][aux3-1].getBackground().getConstantState()).equals(basePiece)){
//                                Toast.makeText(gamePeg.this, "uwu1", Toast.LENGTH_SHORT).show();
                            lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                            pieces[aux2][aux3-1].setBackgroundResource(R.drawable.peg_empty_piece);
                            justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                            lastClicked = null;
                            checkGameOver();
                        }

                        else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                && (lastColumna-aux3 == 2)
                                && (pieces[aux2][aux3+1].getBackground().getConstantState()).equals(basePiece)){
//                                Toast.makeText(gamePeg.this, "uwu2", Toast.LENGTH_SHORT).show();
                            lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                            pieces[aux2][aux3+1].setBackgroundResource(R.drawable.peg_empty_piece);
                            justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                            lastClicked = null;
                            checkGameOver();

                        }

                        else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                && (lastFila-aux2 == 2)
                                && (pieces[aux2+1][aux3].getBackground().getConstantState()).equals(basePiece)){
//                                Toast.makeText(gamePeg.this, "uwu3", Toast.LENGTH_SHORT).show();
                            lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                            pieces[aux2+1][aux3].setBackgroundResource(R.drawable.peg_empty_piece);
                            justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                            lastClicked = null;
                            checkGameOver();

                        }
                        else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                && (lastFila-aux2 == -2)
                                && (pieces[aux2-1][aux3].getBackground().getConstantState()).equals(basePiece)){
//                                Toast.makeText(gamePeg.this, "uwu4", Toast.LENGTH_SHORT).show();
                            lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                            pieces[aux2-1][aux3].setBackgroundResource(R.drawable.peg_empty_piece);
                            justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                            lastClicked = null;
                            checkGameOver();
                        }

                        else{
                            if(justClicked.getBackground().getConstantState().equals(emptyPiece)
                                    && lastClicked.getBackground().getConstantState().equals(selectedPiece)) {
//                                    Toast.makeText(gamePeg.this, "SIKE ENT", Toast.LENGTH_SHORT).show();
                            }

                            else {
//                                    Toast.makeText(gamePeg.this, "entramos", Toast.LENGTH_SHORT).show();
                                lastClicked.setBackgroundResource(R.drawable.peg_base_piece);
//                                    lastAux = aux;
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
//    View.OnClickListener myhandler1 = new View.OnClickListener() {
//        public void onClick(View v) {
//            if (lastClicked == null) {
//                lastAux = aux;
//                lastClicked = findViewById(v.getId());
//                lastClicked.setBackgroundResource(R.drawable.peg_selected_piece);
//                System.out.println("peme "+ lastAux);
//            }
////            else{
////
////            }
//        }
//    };

}