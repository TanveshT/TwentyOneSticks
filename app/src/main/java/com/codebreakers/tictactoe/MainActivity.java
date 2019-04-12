package com.codebreakers.tictactoe;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MainActivity extends AppCompatActivity {
    List<CardItems> displayList;
    TextView mainHeading,card_title_txt;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recycler_view_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayList = new ArrayList<>();

        displayList.add(new CardItems(1,"SINGLE PLAYER",R.drawable.single_player));
        displayList.add(new CardItems(2,"DUAL PLAYER",R.drawable.dual_player));
        displayList.add(new CardItems(3,"ABOUT US",R.drawable.about_3));


        ItemAdapter adapter = new ItemAdapter(this,displayList);

        recyclerView.setAdapter(adapter);

    }
}
