package com.codebreakers.tictactoe;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mainlist;
    Button btn_Start,btn_Remove;
    RadioGroup inputRadioGroup;
    TextView txtCount;
    int Number_of_sticks;
    static List<String> main_String= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        for(int i = 0; i<21; i++) {
            main_String.add("| ");
        }

        mainlist = findViewById(R.id.MainList);
        mainlist.setText(main_String.toString()
                .replace(",","")
                .replace("[","")
                .replace("]","")
                .trim());

        btn_Start = findViewById(R.id.btnStart);
        btn_Remove = findViewById(R.id.btnRemove);
        inputRadioGroup = findViewById(R.id.Radio_SelectionGroup);
        txtCount = findViewById(R.id.CountDisplay);

        btn_Remove.setVisibility(View.INVISIBLE);
        inputRadioGroup.setVisibility(View.INVISIBLE);

        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_Start.setVisibility(View.INVISIBLE);
                btn_Remove.setVisibility(View.VISIBLE);
                inputRadioGroup.setVisibility(View.VISIBLE);
               furtherOperations();
            }
        });

    }

private void furtherOperations() {

    btn_Remove.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(inputRadioGroup.getCheckedRadioButtonId() == -1){

                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.none_selected_dialog, viewGroup, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView);
                AlertDialog alertDialog;
                alertDialog  = builder.create();
                alertDialog.show();
            }
            else {

                ColorCheck(mainlist);
                ColorCheck(txtCount);
                int radioButtonChoosen = inputRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonChoosen);
                Number_of_sticks = Integer.parseInt((String) radioButton.getText());

                if(main_String.size()!=1) { //Improve Logic Over here as the input is always going to be  between 1-4
                    if (Number_of_sticks == 1 || Number_of_sticks == 2 || Number_of_sticks == 3 || Number_of_sticks == 4) {

                        for (int i = 0; i < Number_of_sticks; i++) {
                            main_String.remove("| ");
                        }
                        mainlist.setText(main_String.toString()
                                .replace(",", "")
                                .replace("[", "")
                                .replace("]", "")
                                .trim());

                        Toast.makeText(MainActivity.this, "NOW MOBILE IS REMOVING STICKS", Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < (5 - Number_of_sticks); i++) {
                            main_String.remove("| ");
                        }

                        mainlist.setText(main_String.toString()
                                .replace(",", "")
                                .replace("[", "")
                                .replace("]", "")
                                .trim());

                        txtCount.setText("" + main_String.size());
                        ColorCheck(txtCount);
                        ColorCheck(mainlist);

                        Number_of_sticks = 0;

                        if(main_String.size()==1) {

                            ViewGroup viewGroup = findViewById(android.R.id.content);
                            View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.my_dialog, viewGroup, false);
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setView(dialogView);
                            AlertDialog alertDialog;
                            alertDialog  = builder.create();
                            alertDialog.show();
                            reset();

                        }

                        txtCount.setText("" + main_String.size());

                        inputRadioGroup.clearCheck();


                    } else {
                        //Enter the Custom Dialog box for Values between 1-4 accepted only
                        //NOT NEEDED AS WE USED RADIO BUTTONS
                    }
                }
                else{
                    ViewGroup viewGroup = findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.my_dialog, viewGroup, false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setView(dialogView);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }

        }


    });

}

    @SuppressLint("ResourceAsColor")
    private void reset() {

        for(int i = 0; i<20; i++) {
            main_String.add("| ");
        }

        mainlist.setText(main_String.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim());

        mainlist.setTextColor(getResources().getColor(R.color.colorGreaterTwenty));
        txtCount.setTextColor(getResources().getColor(R.color.colorGreaterTwenty));
    }

    private void ColorCheck(TextView x) {

        if(main_String.size() > 20) {
            x.setTextColor(getResources().getColor(R.color.colorGreaterTwenty));
        }

        if(main_String.size() == 16) {
            x.setTextColor(getResources().getColor(R.color.colorGreaterFourteen));
        }

        if(main_String.size() == 11) {
            x.setTextColor(getResources().getColor(R.color.colorGreaterTen));
        }

        if(main_String.size() == 6) {
            x.setTextColor(getResources().getColor(R.color.colorLessThanFive));
        }

    }


}
