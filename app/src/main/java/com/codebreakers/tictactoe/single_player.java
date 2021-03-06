package com.codebreakers.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;;
import android.util.Log;
import android.view.KeyEvent;
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
import java.util.Timer;
import java.util.TimerTask;

public class single_player extends AppCompatActivity {
    TextView mainlist,textViewCounter,displayCounterMessage,textViewChooseNumberOfSticks,textViewUserPicks_title,textViewUserPicks_sticks,textViewUserPicks_count,textViewMobilePicks_title,textViewMobilePicks_sticks,textViewMobilePicks_count;
    Button btn_Start,btn_Remove;
    RadioGroup inputRadioGroup;
    TextView txtCount,textViewYourTurn;
    int Number_of_sticks;
    static List<String> main_String;
    List<String> user_picks_string,mobile_picks_string;

    CountDownTimer mCountDownTimer = new CountDownTimer(3 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            //this will be called every second.
            textViewCounter.setText("" + (millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {


            for (int i = 0; i < (5 - Number_of_sticks); i++) {
                main_String.remove("| ");
                mobile_picks_string.add("|");
            }
            System.out.println(mobile_picks_string);
            mainlist.setText(main_String.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", "")
                    .trim());

            textViewMobilePicks_sticks.setText(mobile_picks_string.toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", "")
                    .trim());

            txtCount.setText("" + main_String.size());
            textViewMobilePicks_count.setText("" + mobile_picks_string.size());

            ColorCheck(mainlist);
            ColorCheck(txtCount);
            Number_of_sticks = 0;

            if(main_String.size()==1) {

                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(single_player.this).inflate(R.layout.my_dialog, viewGroup, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(single_player.this);
                builder.setView(dialogView);
                final AlertDialog alertDialog;
                alertDialog  = builder.create();
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
                        Intent intent = new Intent(single_player.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

            }

            textViewYourTurn.setVisibility(View.VISIBLE);
            textViewCounter.setVisibility(View.INVISIBLE);
            displayCounterMessage.setVisibility(View.INVISIBLE);
            btn_Remove.setEnabled(true);


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        getSupportActionBar().hide();

        main_String = new ArrayList<String>() {{
            add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");
            add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");add("| ");
            add("| ");
        }};

        mobile_picks_string = new ArrayList<>();
        user_picks_string = new ArrayList<>();



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
        textViewCounter = findViewById(R.id.txtCounter);
        displayCounterMessage = findViewById(R.id.DisplayMobileChoosing);
        textViewYourTurn = findViewById(R.id.txtYourTurn);
        textViewChooseNumberOfSticks = findViewById(R.id.txtChooseNumberOfSticks);
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
        textViewYourTurn.setVisibility(View.INVISIBLE);
        textViewCounter.setVisibility(View.INVISIBLE);
        displayCounterMessage.setVisibility(View.INVISIBLE);
        btn_Remove.setVisibility(View.INVISIBLE);
        inputRadioGroup.setVisibility(View.INVISIBLE);

        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(single_player.this).inflate(R.layout.rules_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(single_player.this);
        builder.setView(dialogView);
        final AlertDialog alertDialog;
        alertDialog  = builder.create();
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
                textViewYourTurn.setVisibility(View.VISIBLE);
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


                textViewUserPicks_sticks.setText(user_picks_string.toString()
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", "")
                        .trim());

                textViewUserPicks_count.setText("" + user_picks_string.size());

                textViewMobilePicks_sticks.setText(mobile_picks_string.toString()
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", "")
                        .trim());

                textViewMobilePicks_count.setText("" + mobile_picks_string.size());


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
                    View dialogView = LayoutInflater.from(single_player.this).inflate(R.layout.none_selected_dialog, viewGroup, false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(single_player.this);
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
                else {

                    ColorCheck(mainlist);
                    ColorCheck(txtCount);
                    int radioButtonChoosen = inputRadioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioButtonChoosen);
                    Number_of_sticks = Integer.parseInt((String) radioButton.getText());

                    if(main_String.size()!=1) {
                        //Improve Logic Over here as the input is always going to be  between 1-4
                        if (Number_of_sticks == 1 || Number_of_sticks == 2 || Number_of_sticks == 3 || Number_of_sticks == 4) {

                            for (int i = 0; i < Number_of_sticks; i++) {
                                main_String.remove("| ");
                              user_picks_string.add("|");

                            }
                            mainlist.setText(main_String.toString()
                                    .replace(",", "")
                                    .replace("[", "")
                                    .replace("]", "")
                                    .trim());

                            txtCount.setText("" + main_String.size());

                            textViewUserPicks_sticks.setText(user_picks_string.toString()
                                    .replace(",", "")
                                    .replace("[", "")
                                    .replace("]", "")
                                    .trim());

                            textViewUserPicks_count.setText("" + user_picks_string.size());

                            inputRadioGroup.clearCheck();

                            textViewYourTurn.setVisibility(View.INVISIBLE);
                            textViewCounter.setVisibility(View.VISIBLE);
                            displayCounterMessage.setVisibility(View.VISIBLE);
                            btn_Remove.setEnabled(false);
                            mCountDownTimer.start();

                        } else {
                            //Enter the Custom Dialog box for Values between 1-4 accepted only
                            //NOT NEEDED AS WE USED RADIO BUTTONS
                        }
                    }
                    else{
                        ViewGroup viewGroup = findViewById(android.R.id.content);
                        View dialogView = LayoutInflater.from(single_player.this).inflate(R.layout.my_dialog, viewGroup, false);
                        AlertDialog.Builder builder = new AlertDialog.Builder(single_player.this);
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


        user_picks_string = new ArrayList<>();
        mobile_picks_string = new ArrayList<>();

        textViewUserPicks_sticks.setText(user_picks_string.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim());

        textViewUserPicks_count.setText("" + user_picks_string.size());

        textViewMobilePicks_sticks.setText(mobile_picks_string.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim());

        textViewMobilePicks_count.setText("" + mobile_picks_string.size());

        mainlist.setText(main_String.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim());

        txtCount.setText("" + main_String.size());

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
