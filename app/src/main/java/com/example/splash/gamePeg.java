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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_peg);
        /////////////////////COSAS/////////////////////////


//        grid = findViewById(R.id.GridPeg);
//        button = (Button) grid.getChildAt(2);
        basePiece = getDrawable(R.drawable.peg_base_piece).getConstantState();
        emptyPiece = getDrawable(R.drawable.peg_empty_piece).getConstantState();
        selectedPiece = getDrawable(R.drawable.peg_selected_piece).getConstantState();
        arrayButtons();
//        button.setOnClickListener(myhandler1);
    }


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
                            }

//                            else if((justClicked.getBackground().getConstantState()).equals(emptyPiece)){
//                                Toast.makeText(gamePeg.this, "vacio", Toast.LENGTH_SHORT).show();
//                            }
                            else if((justClicked.getBackground().getConstantState()).equals(emptyPiece) && (grid.getChildAt(aux-1).getBackground().getConstantState()).equals(basePiece) && (lastAux-aux == -2)){
                                Toast.makeText(gamePeg.this, "uwu0", Toast.LENGTH_SHORT).show();
                                lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                                grid.getChildAt(aux-1).setBackgroundResource(R.drawable.peg_empty_piece);
                                justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                lastClicked = null;
                                lastAux = aux;
                                lastFila = aux2;
                                lastColumna = aux3;
                            }
                            else if((justClicked.getBackground().getConstantState()).equals(emptyPiece) && (grid.getChildAt(aux-7).getBackground().getConstantState()).equals(basePiece) && (lastAux-aux == -14)){
                                Toast.makeText(gamePeg.this, "uwu1", Toast.LENGTH_SHORT).show();
                                lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                                grid.getChildAt(aux-7).setBackgroundResource(R.drawable.peg_empty_piece);
                                justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                lastClicked = null;
                                lastAux = aux;
                                lastFila = aux2;
                                lastColumna = aux3;
                            }

                            else if((justClicked.getBackground().getConstantState()).equals(emptyPiece) && (grid.getChildAt(aux+1).getBackground().getConstantState()).equals(basePiece) && (lastAux-aux == 2)){
                                Toast.makeText(gamePeg.this, "uwu2", Toast.LENGTH_SHORT).show();
                                lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                                grid.getChildAt(aux+1).setBackgroundResource(R.drawable.peg_empty_piece);
                                justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                lastClicked = null;
                                lastAux = aux;
                                lastFila = aux2;
                                lastColumna = aux3;
                            }

                            else if((justClicked.getBackground().getConstantState()).equals(emptyPiece) && (grid.getChildAt(aux+7).getBackground().getConstantState()).equals(basePiece) && (lastAux-aux == 14)){
                                Toast.makeText(gamePeg.this, "uwu3", Toast.LENGTH_SHORT).show();
                                lastClicked.setBackgroundResource(R.drawable.peg_empty_piece);
                                grid.getChildAt(aux+7).setBackgroundResource(R.drawable.peg_empty_piece);
                                justClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                lastClicked = null;
                                lastAux = aux;
                                lastFila = aux2;
                                lastColumna = aux3;
                            }

                            else{
                                if(justClicked.getBackground().getConstantState().equals(emptyPiece) && lastClicked.getBackground().getConstantState().equals(selectedPiece)) {
                                    Toast.makeText(gamePeg.this, "SIKE ENT", Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    Toast.makeText(gamePeg.this, "entramos", Toast.LENGTH_SHORT).show();
                                    lastClicked.setBackgroundResource(R.drawable.peg_base_piece);
                                    lastAux = aux;
                                    lastFila = aux2;
                                    lastColumna = aux3;
                                    lastClicked = justClicked;
                                    lastClicked.setBackgroundResource(R.drawable.peg_selected_piece);
                                }
                            }
//            else{
//
//            }
                        }
                    });
//                    meterle un onclick a cada boton, que almacene el aux y mire si es +-1 o +-7.
//                }
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
//            Toast.makeText(gamePeg.this, String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
//        }
//    };
//    public void onClick(View v) {
//
//    }
}