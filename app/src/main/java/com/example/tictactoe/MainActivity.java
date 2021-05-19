package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //x=0 and O=1
    int activePlayer=0;

    boolean gameIsActive=true;
    // 2 means unPlayed
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPosition= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void dropIn(View view){
        ImageView counter= (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter]==2&&gameIsActive){
            gameState[tappedCounter]=activePlayer;
        counter.setTranslationY(-1000f);
        if (activePlayer==0){
        counter.setImageResource(R.drawable.xphoto);
        activePlayer=1;
        }else{
            counter.setImageResource(R.drawable.o);
            activePlayer=0;
        }

        counter.animate().translationYBy(1000f).rotation(3600).setDuration(1000);
            for (int[] winningPosition:winningPosition){
                if (gameState[winningPosition[0]]==gameState[winningPosition[1]]&&gameState[winningPosition[1]]==gameState[winningPosition[2]]
                    &&gameState[winningPosition[0]]!=2){
                    String Winner="O";
                    if(gameState[winningPosition[0]]==0){
                        Winner="X";
                    }
                    gameIsActive=false;
                    LinearLayout layout=findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    TextView textView=findViewById(R.id.winner);
                    textView.setText(Winner +" has Won!");

                }else {
                    boolean gameIsOver=true;
                    for (int counterState:gameState){
                      if (counterState==2){
                        gameIsOver=false;}
                    }
                    if(gameIsOver){
                        LinearLayout layout=findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                        TextView textView=findViewById(R.id.winner);
                        textView.setText("It's a draw");
                        Button button=findViewById(R.id.playAgainButton);
                        button.setText("Play Again");
                    }
                }
            }
    }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playAgain(View view) {
        gameIsActive=true;
        LinearLayout layout=findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer=0;
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        try {


        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridlayout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}