package com.example.per2.uselessmachine;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonSelfDestruct;
    private Switch switchUseless;
    private ConstraintLayout view;

    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();

    }

    private void setListeners() {
        //TODO self destruct button
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelfDestrutSequence();
            }


        });
        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    startSwitchOffTimer();
                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();


                }
                else{
                    Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void startSelfDestrutSequence() {
        buttonSelfDestruct.setEnabled(false);


        new CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                buttonSelfDestruct.setText("Destruct in " + (millisUntilFinished/1000));
                if((millisUntilFinished/1000)%2 == 0) {
                    view.setBackgroundColor(Color.WHITE);
                }
                else{
                    view.setBackgroundColor(Color.BLACK);
                }
            }

            @Override
            public void onFinish() {
                finish();

            }
        }.start();
    }
    private void startSwitchOffTimer(){
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                if(!switchUseless.isChecked()){
                    Log.d(TAG, "onTick: canceling");
                    cancel();
                }

            }

            @Override
            public void onFinish() {
                switchUseless.setChecked(false);
                Log.d(TAG, "onFinish: switch set to false");

            }
        }.start();
    }

    private void wireWidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
        view = findViewById(R.id.constraintlayout_main);
}
}
