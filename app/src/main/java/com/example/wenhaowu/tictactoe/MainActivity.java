package com.example.wenhaowu.tictactoe;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity {

    ImageButton b11, b12, b13, b21, b22, b23, b31, b32, b33;
    int flag = 1;
    private boolean Player_turn = false; // who s turn is it? false = cross true = tic
    private int board[][] = new int[3][3]; // represent the board as array of character

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b11 = (ImageButton)findViewById(R.id.ID_B11);
        b12 = (ImageButton)findViewById(R.id.ID_B12);
        b13 = (ImageButton)findViewById((R.id.ID_B13));

        b11.setOnClickListener(ClickHandler(b11, flag));

        b12.setOnClickListener(ClickHandler(b12, flag));

        b13.setOnClickListener(ClickHandler(b13, flag));
    }

    private View.OnClickListener ClickHandler(final ImageButton imgB, final int test){
        View.OnClickListener imgButtonHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (test % 2 != 0 )
                {imgB.setImageResource(R.drawable.tic);}
                else
                {imgB.setImageResource(R.drawable.cross);}
                Log.e("MyTag","flag is "+test);
            }
        };

        flag++;
        return imgButtonHandler;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
