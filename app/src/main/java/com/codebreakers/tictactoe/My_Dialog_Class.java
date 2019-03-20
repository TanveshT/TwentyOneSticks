package com.codebreakers.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class My_Dialog_Class extends AppCompatActivity {

    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnOK = findViewById(R.id.buttonOk);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(My_Dialog_Class.this,"Hello",Toast.LENGTH_LONG).show();
            }
        });


    }
}
