package com.excercise.wenhaowu.tictactoe_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private boolean ticTurn = false;
    private char board[][] = new char[3][3];
    private int numflag =0 ;
    private int musicFlag = 0;
    public static char[][] gameStatu = new char[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load onclickListeners method for every button
        setupOnClickListeners();
        newGame();

        Button b =(Button)findViewById(R.id.newGameBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

        //Deal with the music button
        final Intent svc = new Intent(this, Bgm_Service.class);

        Button m = (Button)findViewById(R.id.musicBtn);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicFlag == 0) {
                    startService(svc);
                    musicFlag = 1;
                } else {
                    stopService(svc);
                    musicFlag = 0;
                }
            }
        });

        //Deal with the save game button
        Button saveGame = (Button)findViewById(R.id.saveBtn);
        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFileHanlder.saveToFile(getApplicationContext());
            }
        });

        //Deal with the load game button
        Button loadGame = (Button)findViewById(R.id.loadBtn);
        loadGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = MyFileHanlder.readFromFile(getApplicationContext());
                newGame();

                //converting one dimension array to two dimension array and make the button like it s been clicked
                //stupid way find a elegent way later
                for (int i=0; i<test.length();i++){
                    char temp = test.charAt(i);
                    Button B;
                    //make the button display 'o'
                    if (temp == '1'){
                        switch (i){
                            case 0:
                                board[0][0] = 'O';
                                B = (Button)findViewById(R.id.Btn11);
                                break;
                            case 1:
                                board[0][1] = 'O';
                                B = (Button)findViewById(R.id.Btn21);
                                break;
                            case 2:
                                board[0][2] = 'O';
                                B = (Button)findViewById(R.id.Btn31);
                                break;
                            case 3:
                                board[1][0] = 'O';
                                B = (Button)findViewById(R.id.Btn12);
                                break;
                            case 4:
                                board[1][1] = 'O';
                                B = (Button)findViewById(R.id.Btn22);
                                break;
                            case 5:
                                board[1][2] = 'O';
                                B = (Button)findViewById(R.id.Btn32);
                                break;
                            case 6:
                                board[2][0] = 'O';
                                B = (Button)findViewById(R.id.Btn13);
                                break;
                            case 7:
                                board[2][1] = 'O';
                                B = (Button)findViewById(R.id.Btn23);
                                break;
                            case 8:
                                board[2][2] = 'O';
                                B = (Button)findViewById(R.id.Btn33);
                                break;
                            //no sepecial meaning, just for giving the default value for B
                            default:
                                B= (Button)findViewById(R.id.Btn33);
                                break;
                        }
                        btnSetText_fixed(B, true);
                        ticTurn = false;
                    }
                    else if (temp == '2'){
                        switch (i){
                            case 0:
                                board[0][0] = 'X';
                                B = (Button)findViewById(R.id.Btn11);
                                break;
                            case 1:
                                board[0][1] = 'X';
                                B = (Button)findViewById(R.id.Btn21);
                                break;
                            case 2:
                                board[0][2] = 'X';
                                B = (Button)findViewById(R.id.Btn31);
                                break;
                            case 3:
                                board[1][0] = 'X';
                                B = (Button)findViewById(R.id.Btn12);
                                break;
                            case 4:
                                board[1][1] = 'X';
                                B = (Button)findViewById(R.id.Btn22);
                                break;
                            case 5:
                                board[1][2] = 'X';
                                B = (Button)findViewById(R.id.Btn32);
                                break;
                            case 6:
                                board[2][0] = 'X';
                                B = (Button)findViewById(R.id.Btn13);
                                break;
                            case 7:
                                board[2][1] = 'X';
                                B = (Button)findViewById(R.id.Btn23);
                                break;
                            case 8:
                                board[2][2] = 'X';
                                B = (Button)findViewById(R.id.Btn33);
                                break;
                            //no sepecial meaning, just for giving the default value for B
                            default:
                                B= (Button)findViewById(R.id.Btn33);
                                break;
                        }
                        btnSetText_fixed(B, false);
                        ticTurn = true;
                    }
                }
            }
        });
    }

    private void btnSetText_fixed(Button B, Boolean circle){
        if (circle == true){
            if (numflag == 0){
                B.setText("O");
                B.setTextColor(Color.RED);
            }
            else if (numflag ==1){
                B.setText("1");
            }
        }
        else{
            if (numflag == 0){
                B.setText("X");
                B.setTextColor(Color.RED);
            }
            else if (numflag ==1){
                B.setText("2");
            }
        }
        B.setEnabled(false);
    }

    public static char[][] getGameStatu(){
        return gameStatu;
    }

    public void newGame(){
        ticTurn = false;
        board = new char[3][3];
        Log.e("MyTag","Here");
        resetButtons();
        //for saving game purpose, '0'=unclicked; '1'=o; '2'=x
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                gameStatu[i][j]='0';
            }
        }
    }

    private void resetButtons(){
        TableLayout T = (TableLayout)findViewById(R.id.tableLayout);
        for (int y=0;y<T.getChildCount();y++){
            if (T.getChildAt(y) instanceof TableRow){
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x=0; x<R.getChildCount(); x++){
                   if (R.getChildAt(x) instanceof Button){
                        Button B= (Button) R.getChildAt(x);
                        B.setText("");
                        B.setEnabled(true);
                   }
                }
            }
        }


    }

    private void setupOnClickListeners(){

        TableLayout T = (TableLayout)findViewById(R.id.tableLayout);

        for (int y=0; y<T.getChildCount(); y++){
            if(T.getChildAt(y) instanceof TableRow){
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x=0; x<R.getChildCount();x++){
                        View V = R.getChildAt(x);
                        V.setOnClickListener(new PlayOnClick(x,y));
                }
            }
        }

    }

    private class PlayOnClick implements View.OnClickListener{

        private int x= 0;
        private int y= 0;

        private PlayOnClick(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View v) {
            if (v instanceof Button){
                Button B = (Button) v;
                board[x][y] = ticTurn ? 'O' : 'X';
                gameStatu[x][y]= ticTurn ? '1' :'2' ;

                //set the text appear on the button depends on the numFlag
                buttonSetText(B);

                //change to other player's turn
                ticTurn = !ticTurn;

                if (checkWin()){
                    disableButtons();
                }
            }
        }

    }

    private void buttonSetText(Button B){
        if (numflag == 0){
            B.setText(ticTurn ? "O" : "X");
            B.setTextColor(Color.RED);
        }
        else if (numflag ==1){
            B.setText(ticTurn? "2":"1");
        }
        B.setEnabled(false);
    }

    private void disableButtons(){
        TableLayout T = (TableLayout)findViewById(R.id.tableLayout);
        for (int y=0;y<T.getChildCount();y++){
            if (T.getChildAt(y) instanceof TableRow){
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x=0; x<R.getChildCount(); x++){
                    if (R.getChildAt(x) instanceof Button){
                        Button B= (Button) R.getChildAt(x);
                        B.setEnabled(false);
                    }
                }
            }
        }


    }

    private boolean checkWin(){

        char winner = '\0';
        if (checkWinner(board, 3 , 'X')){
            winner = 'X' ;
        }
        else if(checkWinner(board, 3, 'O')){
            winner = 'O';
        }

        if (winner == '\0'){
            //displayDialog(winner,0);
            return false;

        }
        else {
            displayDialog(winner, 1);
            return true;
        }
    }

    private boolean checkWinner(char[][] board, int size, char player){
        //column

        for (int x=0; x<size; x++){
            int move = 0;
            for (int y=0; y<size; y++){
                if (board[x][y]==player){
                    move++;
                }
            }

            if (move >=size){
                return true;
            }
        }


        //row
        for (int y=0; y<size; y++){
            int move = 0;
            for (int x=0; x<size; x++){
                if (board[x][y]==player){
                    move++;
                }
            }
            if (move >=size){
                return true;
            }
        }


        //forward slope (0,0)(1,1)(2,2)
        int move1=0;
        for (int x=0; x<size; x++){
            for(int y=0; y<size; y++){
                if(x==y && board[x][y] == player){
                    move1++;
                }
            }
        }
        if (move1 >=size){
            return true;
        }

        //backward slope (0,2)(1,1)(2,0)
        int move2=0;
        for (int x=0; x<size; x++){
            for (int y=0; y<size; y++){
                if (x+y == size -1 && board[x][y]==player){
                    move2++;
                }
            }
        }
        if (move2 >=size){
            return true;
        }

        return false;
    }

    private void displayDialog(char player, int flag){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        if (flag == 1) {
            alert.setTitle(R.string.DialogTitle);
            if (player == 'X') {
                alert.setMessage("Player1 Wins!!!");
            }
            if (player == 'O') {
                alert.setMessage("Player2 Wins!!!");
            }
        }
        else {
            alert.setMessage("It's a tie!!!");
        }

        //pressing new game
        alert.setPositiveButton(R.string.ok, new
                DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

        //pressing exit
        alert.setNegativeButton(R.string.exit, new
                DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });

        alert.show();

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
        if (id == R.id.displayNum){
            numflag = 1;
            Toast.makeText(getBaseContext(), "Display number!", Toast.LENGTH_SHORT).show();
            newGame();
        }
        if (id == R.id.displayX){
            numflag = 0;
            Toast.makeText(getBaseContext(), "Display X & O !", Toast.LENGTH_SHORT).show();
            newGame();
        }

        return super.onOptionsItemSelected(item);
    }

    //public service
}
