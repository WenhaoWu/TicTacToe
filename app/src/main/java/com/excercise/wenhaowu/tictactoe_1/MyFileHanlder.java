package com.excercise.wenhaowu.tictactoe_1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by wenhaowu on 16/07/15.
 */
public class MyFileHanlder {

    public static String readFromFile(Context ctx){
        String result = "";
        String fileName = "gameStatu.txt";

        try {
            FileInputStream fis = ctx.openFileInput(fileName);
            InputStreamReader inputRead = new InputStreamReader(fis);
            char[] inputBuffer = new char[100];
            int charRead;
            while ((charRead= inputRead.read(inputBuffer))>0){
                //char to string convertion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                result += readstring;
            }
            inputRead.close();
            Toast.makeText(ctx.getApplicationContext(), "Loaded: "+result, Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }


    public static void saveToFile(Context ctx){
        char[][] board = MainActivity.getGameStatu();
        Log.e("board", board[0][0]+"");

        String fileName = "gameStatu.txt";
        try {
            FileOutputStream fos = ctx.openFileOutput(fileName, ctx.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            for (int i=0; i<3 ; i++){
                for (int j=0; j<3; j++){
                    writer.write(board[i][j]);
                }
            }
            writer.close();
            Toast.makeText(ctx.getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
