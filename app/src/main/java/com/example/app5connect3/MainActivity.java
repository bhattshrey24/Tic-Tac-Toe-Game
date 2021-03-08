package com.example.app5connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int [] gameState={2,2,2,2,2,2,2,2,2}; // 2 represents empty , 0 means yellow and 1 means red
   int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer=0;// 0:yellow,1:red
    boolean gameActive=true;


    public void dropIn(View view){
    ImageView counter = (ImageView) view;
//    Log.i("info",counter.getTag().toString());

    int tappedCounter= Integer.parseInt(counter.getTag().toString());
    if(gameState[tappedCounter]==2&&gameActive) {
        gameState[tappedCounter] = activePlayer;
        counter.setTranslationY(-1500);
        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(800);
        for (int[] winningPosition : winningPositions) {
//            Log.i("info","inside for loop");
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                String winner = "";
                if (activePlayer == 1) {
                    winner = "Yellow";
                } else {
                    winner = "Red";
                }

                gameActive=false;
                Button playAgain=(Button) findViewById(R.id.playAgainButton);
                TextView winnerText = (TextView) findViewById(R.id.winnerText);
                winnerText.setText(winner + " has won !");
                playAgain.setVisibility(view.VISIBLE);
                winnerText.setVisibility(view.VISIBLE);
            }
        }
    }
}
public void playAgainFunc(View view){
//    Log.i("Info","PLayAgainFunc BUtton");
    Button playAgain=(Button) findViewById(R.id.playAgainButton);
    TextView winnerText = (TextView) findViewById(R.id.winnerText);
    playAgain.setVisibility(view.INVISIBLE);
    winnerText.setVisibility(view.INVISIBLE);
    androidx.gridlayout.widget.GridLayout gridLayout= (androidx.gridlayout.widget.GridLayout) findViewById (R.id.gridLayout);    for(int i=0;i<gridLayout.getChildCount();i++){
        ImageView counter2=(ImageView) gridLayout.getChildAt(i);
        counter2.setImageDrawable(null);
    }
    for(int j=0;j<gameState.length;j++){
        gameState[j]=2;
    }
    activePlayer=0;// 0:yellow,1:red
    gameActive=true;

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}