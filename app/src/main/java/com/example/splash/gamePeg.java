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


//        grid = findViewById(R.id.GridPeg);
//        button = (Button) grid.getChildAt(2);
        gameOver = false;
        piecesConstantState();
        arrayButtons();
//        button.setOnClickListener(myhandler1);
    }

    public void piecesConstantState(){
        basePiece = getDrawable(R.drawable.peg_base_piece).getConstantState();
        emptyPiece = getDrawable(R.drawable.peg_empty_piece).getConstantState();
        selectedPiece = getDrawable(R.drawable.peg_selected_piece).getConstantState();
    }

    public void checkGameOver(){
        for (int i = 0; i<49; i++){
            if (i == 0 || i == 1 || i == 5 || i == 6 || i == 7 || i == 8 || i == 12 || i == 13 || i == 35 || i == 36 || i == 40 || i == 41 || i == 42 || i == 43 || i == 47 || i == 48){

            }
            else{
                if(((grid.getChildAt(i).getBackground().getConstantState()).equals(basePiece) && (grid.getChildAt(i+1).getBackground().getConstantState()).equals(basePiece))
                        && ((grid.getChildAt(i+2).getBackground().getConstantState()).equals(emptyPiece) || (grid.getChildAt(i-1).getBackground().getConstantState()).equals(emptyPiece))
                || ((grid.getChildAt(i).getBackground().getConstantState()).equals(basePiece) && (grid.getChildAt(i-1).getBackground().getConstantState()).equals(basePiece))
                        && ((grid.getChildAt(i+1).getBackground().getConstantState()).equals(emptyPiece) || (grid.getChildAt(i-2).getBackground().getConstantState()).equals(emptyPiece))){
                    Toast.makeText(gamePeg.this, "11", Toast.LENGTH_SHORT).show();
                }
//                else if(((grid.getChildAt(i).getBackground().getConstantState()).equals(basePiece) && (grid.getChildAt(i+7).getBackground().getConstantState()).equals(basePiece))
//                        && ((grid.getChildAt(i+14).getBackground().getConstantState()).equals(emptyPiece) || (grid.getChildAt(i-7).getBackground().getConstantState()).equals(emptyPiece))
//                        || ((grid.getChildAt(i).getBackground().getConstantState()).equals(basePiece) && (grid.getChildAt(i-7).getBackground().getConstantState()).equals(basePiece))
//                        && ((grid.getChildAt(i+7).getBackground().getConstantState()).equals(emptyPiece) || (grid.getChildAt(i-14).getBackground().getConstantState()).equals(emptyPiece))){
//                    Toast.makeText(gamePeg.this, "22", Toast.LENGTH_SHORT).show();
//                }

                else{
                    gameOver = true;
                }
            }

        }
        if(gameOver == true){
            Toast.makeText(gamePeg.this, "ded", Toast.LENGTH_SHORT).show();
        }
    }
//
//    || (grid.getChildAt(i).getBackground().getConstantState()).equals(basePiece) && (grid.getChildAt(i-1).getBackground().getConstantState()).equals(basePiece)
//                        || (grid.getChildAt(i).getBackground().getConstantState()).equals(basePiece) && (grid.getChildAt(i+7).getBackground().getConstantState()).equals(basePiece)
//                        || (grid.getChildAt(i).getBackground().getConstantState()).equals(basePiece) && (grid.getChildAt(i-7).getBackground().getConstantState()).equals(basePiece)

    public void arrayButtons(){
        grid = findViewById(R.id.GridPeg);
        for (int fila = 0; fila<7; fila++){
            for(int columna=0; columna<7; columna++){
                int aux=(fila*7)+columna;
                int aux2 = fila;
                int aux3 = columna;
//                if (aux != 0 && aux != 1 && aux != 7 && aux != 8 && aux != 5 && aux != 6 && aux != 12 && aux != 13 && aux != 35 && aux != 36 && aux != 42 && aux != 43 && aux != 40 && aux != 41 && aux != 47 && aux != 48) {
                    pieces[fila][columna] = (Button) grid.getChildAt(aux);
                    pieces[fila][columna].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            justClicked = findViewById(v.getId());

//                            System.out.println("peme "+ lastAux);
//                            System.out.println("peme "+ filaAux);
//                            System.out.println("peme "+ ColumnaAux);

                            if (lastClicked == null && (justClicked.getBackground().getConstantState()).equals(basePiece)) {
                                Toast.makeText(gamePeg.this, "normal", Toast.LENGTH_SHORT).show();
                                lastClicked = justClicked;
                                lastAux = aux;
                                lastFila = aux2;
                                lastColumna = aux3;
                                lastClicked.setBackgroundResource(R.drawable.peg_selected_piece);
                            }

                            else if (lastClicked == null && (justClicked.getBackground().getConstantState()).equals(emptyPiece)) {
                                Toast.makeText(gamePeg.this, "SIKE", Toast.LENGTH_SHORT).show();
//                                checkGameOver();
                            }


                            else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                    && (lastColumna-aux3 == -2)
                                    && (pieces[aux2][aux3-1].getBackground().getConstantState()).equals(basePiece)){
                                Toast.makeText(gamePeg.this, "uwu1", Toast.LENGTH_SHORT).show();
                                lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                                pieces[aux2][aux3-1].setBackgroundResource(R.drawable.peg_empty_piece);
                                justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                lastClicked = null;

                            }

                            else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                    && (lastColumna-aux3 == 2)
                                    && (pieces[aux2][aux3+1].getBackground().getConstantState()).equals(basePiece)){
                                Toast.makeText(gamePeg.this, "uwu2", Toast.LENGTH_SHORT).show();
                                lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                                pieces[aux2][aux3+1].setBackgroundResource(R.drawable.peg_empty_piece);
                                justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                lastClicked = null;

                            }



                            else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                    && (lastFila-aux2 == 2)
                                    && (pieces[aux2+1][aux3].getBackground().getConstantState()).equals(basePiece)){
                                Toast.makeText(gamePeg.this, "uwu3", Toast.LENGTH_SHORT).show();
                                lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                                pieces[aux2+1][aux3].setBackgroundResource(R.drawable.peg_empty_piece);
                                justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                lastClicked = null;
//                                lastAux = aux;
//                                lastFila = aux2;
//                                lastColumna = aux3;

                            }
                            else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)
                                    && (lastFila-aux2 == -2)
                                    && (pieces[aux2-1][aux3].getBackground().getConstantState()).equals(basePiece)){
                                Toast.makeText(gamePeg.this, "uwu4", Toast.LENGTH_SHORT).show();
                                lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                                pieces[aux2-1][aux3].setBackgroundResource(R.drawable.peg_empty_piece);
                                justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                lastClicked = null;

                            }


                            else{
                                if(justClicked.getBackground().getConstantState().equals(emptyPiece)
                                        && lastClicked.getBackground().getConstantState().equals(selectedPiece)) {
                                    Toast.makeText(gamePeg.this, "SIKE ENT", Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    Toast.makeText(gamePeg.this, "entramos", Toast.LENGTH_SHORT).show();
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
//    public void onClick(View v) {
//
//    }
}