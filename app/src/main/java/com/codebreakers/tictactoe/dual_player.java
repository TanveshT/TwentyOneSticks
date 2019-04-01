package com.codebreakers.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class dual_player extends AppCompatActivity {
    TextView mainlist,textPlayerTwo,textViewChooseNumberOfSticks;
    Button btn_Start,btn_Remove;
    TextView txtCount,textPlayerOne;
    RadioGroup inputRadioGroup;
    int Number_of_sticks;
    static List<String> main_String= new ArrayList<String>() {{
        add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");
        add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");
        add("| ");
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_player);
        getSupportActionBar().hide();

        mainlist = findViewById(R.id.MainList);
        mainlist.setText(main_String.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim());

        btn_Start = findViewById(R.id.btnStart);
        btn_Remove = findViewById(R.id.btnRemove);
        inputRadioGroup = findViewById(R.id.Radio_SelectionGroup);
        txtCount = findViewById(R.id.CountDisplay);
        textPlayerTwo = findViewById(R.id.txtPlayerTwo);
        textPlayerOne = findViewById(R.id.txtPlayerOne);
        textViewChooseNumberOfSticks = findViewById(R.id.txtChooseNumberOfSticks);

        textViewChooseNumberOfSticks.setVisibility(View.INVISIBLE);
        textPlayerOne.setVisibility(View.INVISIBLE);
        textPlayerTwo.setVisibility(View.INVISIBLE);
        btn_Remove.setVisibility(View.INVISIBLE);
        inputRadioGroup.setVisibility(View.INVISIBLE);


        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(dual_player.this).inflate(R.layout.rules_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(dual_player.this);
        builder.setView(dialogView);
        final AlertDialog alertDialog;
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();


        Button button = dialogView.findViewById(R.id.buttonOkOnMainDialog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textPlayerOne.setVisibility(View.VISIBLE);
                textViewChooseNumberOfSticks.setVisibility(View.VISIBLE);
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
            public void onClick(View v) {

                //no radio button is selected
                if(inputRadioGroup.getCheckedRadioButtonId() == -1){

                    ViewGroup viewGroup = findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(dual_player.this).inflate(R.layout.none_selected_dialog, viewGroup, false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(dual_player.this);
                    builder.setView(dialogView);
                    final AlertDialog alertDialogNone;
                    alertDialogNone  = builder.create();
                    alertDialogNone.show();

                    final Timer t = new Timer();
                    t.schedule(new TimerTask() {
                        public void run() {
                            alertDialogNone.dismiss(); // when the task active then close the dialog
                            t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                        }
                    }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.

                }
                //if a radio button is selected
                else{

                    ColorCheck(mainlist);
                    ColorCheck(txtCount);
                    int radioButtonChoosen = inputRadioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioButtonChoosen);
                    Number_of_sticks = Integer.parseInt((String) radioButton.getText());

                    for (int i = 0; i < Number_of_sticks; i++) {
                        main_String.remove("| ");
                    }
                    mainlist.setText(main_String.toString()
                            .replace(",", "")
                            .replace("[", "")
                            .replace("]", "")
                            .trim());

                    txtCount.setText("" + main_String.size());

                    inputRadioGroup.clearCheck();

                    switchUser();
                    checkForLastStick();

                }

            }
        });
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

    private void switchUser(){

        if(textPlayerOne.getVisibility() == View.VISIBLE){
            textPlayerTwo.setVisibility(View.VISIBLE);
            textPlayerOne.setVisibility(View.INVISIBLE);
        }
        else{
            textPlayerTwo.setVisibility(View.INVISIBLE);
            textPlayerOne.setVisibility(View.VISIBLE);
        }
    }

    private void checkForLastStick() {
        if (main_String.size() <= 1) {
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(dual_player.this).inflate(R.layout.my_dialog, viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(dual_player.this);
            TextView txtFailureDialog = dialogView.findViewById(R.id.txtOverFailureDialog_id);
            if (textPlayerOne.getVisibility() == View.VISIBLE) {
                txtFailureDialog.setText("PLAYER ONE FAILS");
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        // Prevent dialog close on back press button
                        return keyCode == KeyEvent.KEYCODE_BACK;
                    }
                });

                Button button = dialogView.findViewById(R.id.buttonOk);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset();
                        alertDialog.dismiss();
                    }
                });
            } else {
                txtFailureDialog.setText("PLAYER TWO FAILS");
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        // Prevent dialog close on back press button
                        return keyCode == KeyEvent.KEYCODE_BACK;
                    }
                });

                Button button = dialogView.findViewById(R.id.buttonOk);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset();
                        alertDialog.dismiss();
                    }
                });
            }

        }

    }

    private void reset() {

        for(int i = 0; i<20; i++) {
            main_String.add("| ");
        }

        mainlist.setText(main_String.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim());

        txtCount.setText("" + main_String.size());

        mainlist.setTextColor(getResources().getColor(R.color.colorGreaterTwenty));
        txtCount.setTextColor(getResources().getColor(R.color.colorGreaterTwenty));
    }

}
