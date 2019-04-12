package com.codebreakers.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
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
    TextView mainlist,textPlayerTwo,textViewChooseNumberOfSticks,textViewUserPicks_title,textViewUserPicks_sticks,textViewUserPicks_count,textViewMobilePicks_title,textViewMobilePicks_sticks,textViewMobilePicks_count;
    Button btn_Start,btn_Remove;
    TextView txtCount,textPlayerOne;
    RadioGroup inputRadioGroup;
    int Number_of_sticks;
    static List<String> main_String;
    List<String> playerone_picks_string,playertwo_picks_string;
    RadioButton radioButton4;
    RadioButton radioButton3;
    RadioButton radioButton2;
    RadioButton radioButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_player);
        getSupportActionBar().hide();

        main_String = new ArrayList<String>() {{
            add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");
            add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");
            add("| ");
        }};

        playerone_picks_string = new ArrayList<>();
        playertwo_picks_string = new ArrayList<>();



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
        radioButton4 = findViewById(R.id.radio_4);
        radioButton3 = findViewById(R.id.radio_3);
        radioButton2 = findViewById(R.id.radio_2);
        radioButton1 = findViewById(R.id.radio_1);

        textViewMobilePicks_sticks = findViewById(R.id.textview_mobilePicks_sticks);
        textViewMobilePicks_title = findViewById(R.id.textview_mobilePicks_title);
        textViewMobilePicks_count = findViewById(R.id.textview_mobilePicks_count);
        textViewUserPicks_count = findViewById(R.id.textview_userPicks_count);
        textViewUserPicks_sticks = findViewById(R.id.textview_userPicks_sticks);
        textViewUserPicks_title = findViewById(R.id.textview_userPicks_title);

        textViewUserPicks_title.setVisibility(View.INVISIBLE);
        textViewUserPicks_count.setVisibility(View.INVISIBLE);
        textViewUserPicks_sticks.setVisibility(View.INVISIBLE);
        textViewMobilePicks_title.setVisibility(View.INVISIBLE);
        textViewMobilePicks_count.setVisibility(View.INVISIBLE);
        textViewMobilePicks_sticks.setVisibility(View.INVISIBLE);


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

        Button buttonok = dialogView.findViewById(R.id.buttonOkOnMainDialog);

        buttonok.setOnClickListener(new View.OnClickListener() {
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
                btn_Start.setVisibility(View.GONE);
                btn_Remove.setVisibility(View.VISIBLE);
                inputRadioGroup.setVisibility(View.VISIBLE);
                textViewUserPicks_title.setVisibility(View.VISIBLE);
                textViewUserPicks_count.setVisibility(View.VISIBLE);
                textViewUserPicks_sticks.setVisibility(View.VISIBLE);
                textViewMobilePicks_title.setVisibility(View.VISIBLE);
                textViewMobilePicks_count.setVisibility(View.VISIBLE);
                textViewMobilePicks_sticks.setVisibility(View.VISIBLE);

                textViewUserPicks_sticks.setText(playerone_picks_string.toString()
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", "")
                        .trim());

                textViewUserPicks_count.setText("" + playerone_picks_string.size());

                textViewMobilePicks_sticks.setText(playerone_picks_string.toString()
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", "")
                        .trim());

                textViewMobilePicks_count.setText("" + playertwo_picks_string.size());

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
                        if(textPlayerOne.getVisibility() == View.VISIBLE) {
                            playerone_picks_string.add("|");
                        }
                        if(textPlayerTwo.getVisibility() == View.VISIBLE) {
                            playertwo_picks_string.add("|");
                        }

                    }
                    mainlist.setText(main_String.toString()
                            .replace(",", "")
                            .replace("[", "")
                            .replace("]", "")
                            .trim());

                    textViewUserPicks_sticks.setText(playerone_picks_string.toString()
                            .replace(",", "")
                            .replace("[", "")
                            .replace("]", "")
                            .trim());

                    textViewUserPicks_count.setText("" + playerone_picks_string.size());


                    textViewMobilePicks_sticks.setText(playertwo_picks_string.toString()
                            .replace(",", "")
                            .replace("[", "")
                            .replace("]", "")
                            .trim());

                    txtCount.setText("" + main_String.size());
                    textViewMobilePicks_count.setText("" + playertwo_picks_string.size());


                    txtCount.setText("" + main_String.size());

                    inputRadioGroup.clearCheck();

                    switchUser();
                    checkForLastStick();

                    if(main_String.size() < 5) {
                        radioButton4.setVisibility(View.INVISIBLE);
                        if(main_String.size() < 4) {
                            radioButton3.setVisibility(View.INVISIBLE);
                            if(main_String.size() < 3) {
                                radioButton2.setVisibility(View.INVISIBLE);
                            }
                        }

                    }

                }

            }
        });
    }

    private void ColorCheck(TextView x) {

        if(main_String.size() > 20) {
            x.setTextColor(getResources().getColor(R.color.colorGreaterTwenty));
        }

        if(main_String.size() > 16 && main_String.size() < 20) {
            x.setTextColor(getResources().getColor(R.color.colorGreaterFourteen));
        }

        if(main_String.size() > 11 && main_String.size() <16) {
            x.setTextColor(getResources().getColor(R.color.colorGreaterTen));
        }

        if(main_String.size() < 8) {
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
                Button buttonMainMenu = dialogView.findViewById(R.id.buttonMainMenu);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset();
                        alertDialog.dismiss();
                    }
                });

                buttonMainMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
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

        main_String = new ArrayList<String>() {{
            add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");
            add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");
            add("| ");
        }};

        playerone_picks_string = new ArrayList<>();
        playertwo_picks_string = new ArrayList<>();

        textViewUserPicks_sticks.setText(playerone_picks_string.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim());

        textViewUserPicks_count.setText("" + playerone_picks_string.size());

        textViewMobilePicks_sticks.setText(playerone_picks_string.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim());

        textViewMobilePicks_count.setText("" + playertwo_picks_string.size());

        mainlist.setText(main_String.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim());

        txtCount.setText("" + main_String.size());

        mainlist.setTextColor(getResources().getColor(R.color.colorGreaterTwenty));
        txtCount.setTextColor(getResources().getColor(R.color.colorGreaterTwenty));

        radioButton1.setVisibility(View.VISIBLE);
        radioButton2.setVisibility(View.VISIBLE);
        radioButton3.setVisibility(View.VISIBLE);
        radioButton4.setVisibility(View.VISIBLE);


    }

}
