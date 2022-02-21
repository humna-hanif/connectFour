package edu.quinnipiac.ser210.connectfour;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class Board extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    public static FourInARow logic;
    public static String currPlayer = "user";
    boolean clicked = false;
    TextView winnerText;
    GridLayout gridLayout;
    public static TextView playerName;
    static List<Button> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9,
            R.id.btn10,
            R.id.btn11,
            R.id.btn12,
            R.id.btn13,
            R.id.btn14,
            R.id.btn15,
            R.id.btn16,
            R.id.btn17,
            R.id.btn18,
            R.id.btn19,
            R.id.btn20,
            R.id.btn21,
            R.id.btn22,
            R.id.btn23,
            R.id.btn24,
            R.id.btn25,
            R.id.btn26,
            R.id.btn27,
            R.id.btn28,
            R.id.btn29,
            R.id.btn30,
            R.id.btn31,
            R.id.btn32,
            R.id.btn33,
            R.id.btn34,
            R.id.btn35,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        String playerText = intent.getStringExtra(EXTRA_MESSAGE);
        playerName = (TextView) findViewById(R.id.playerName);
        playerName.setText(playerText);
        logic = new FourInARow();

        buttons = new ArrayList<Button>();
        for (int id : BUTTON_IDS) {
            Button button = (Button) findViewById(id);
            buttons.add(button);
        }

        playerName.setBackgroundColor(Color.YELLOW);
        winnerText = findViewById(R.id.winnerTxt);

    }

    public void restartGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("ResourceAsColor")
    public void onClick(View view) {
        int index = 0;
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getId() == view.getId()) {
                index = i;
                break;
            }
        }

        Button currBtn = buttons.get(index);
        System.out.println(index);
        currBtn.setBackgroundColor(Color.BLUE);
        currBtn.setText("X");
        logic.setMove(FourInARow.BLUE, index);
        currBtn.setEnabled(false);
        checkWinner();
        playerName.setBackgroundColor(Color.WHITE);
        findViewById(R.id.computer).setBackgroundColor(Color.YELLOW);
        currPlayer = "computer";

        int bestMove = logic.getComputerMove();
        while (logic.getPosition(bestMove) != IGame.EMPTY) {
            bestMove = logic.getComputerMove();
        }

        if (logic.getPosition(bestMove) == IGame.EMPTY) {
            logic.setMove(FourInARow.RED, bestMove);
            currBtn = buttons.get(bestMove);
            currBtn.setBackgroundColor(Color.RED);
            currBtn.setText("O");
            currBtn.setEnabled(false);
            checkWinner();
            findViewById(R.id.computer).setBackgroundColor(Color.WHITE);
            playerName.setBackgroundColor(Color.YELLOW);
            currPlayer = "user";
        }
    }

    public void checkWinner() {
        System.out.println(logic.checkForWinner());
        int winner = logic.checkForWinner();
        if (winner == IGame.RED_WON) {
            winnerText.setVisibility(View.VISIBLE);
            winnerText.setText("COMPUTER WINS!");
        } else if (winner == IGame.BLUE_WON) {
            winnerText.setVisibility(View.VISIBLE);
            winnerText.setText("YOU WIN!");
        } else if (winner == IGame.TIE) {
            winnerText.setVisibility(View.VISIBLE);
            winnerText.setText("TIE!");
        }
    }
}
