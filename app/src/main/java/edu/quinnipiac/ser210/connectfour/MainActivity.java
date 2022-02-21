package edu.quinnipiac.ser210.connectfour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        EditText playerName = (EditText) findViewById(R.id.editTextTextPersonName);
        String playerText = playerName.getText().toString();
        Intent intent = new Intent(this, Board.class);
        intent.putExtra(Board.EXTRA_MESSAGE, playerText);
        startActivity(intent);
    }
}