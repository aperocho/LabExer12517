package com.example.acer.labexer_12517;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText edit1;
    Button btn_dis;
    Button btn_save;
    Button btn_clr;
    TextView txt_par;
    FileOutputStream fos;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);
        btn_dis = findViewById(R.id.btn_dis);
        btn_save = findViewById(R.id.btn_save);
        btn_clr = findViewById(R.id.btn_clr);
        txt_par = findViewById(R.id.txt_par);
    }

    public void saveStorage (View view) {
        String message = edit1.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Message saved!", Toast.LENGTH_SHORT).show();
    }

    public void displayStorage (View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis = openFileInput("output.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        txt_par.setText(buffer.toString());
    }

    public void clear (View view) {
        edit1.setText("");
    }
}
