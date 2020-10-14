package com.example.intent_timertask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SaveDataActivity extends AppCompatActivity {

    private TimerTask mTimerTask;
    private int currentTime=0;
    private int activatedData=0;
    private int timeMain;
    private Timer mTimer=null;
    private TextView txt_timeMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        Button btn_goHome = (Button) findViewById(R.id.btn_goActivity1);
        Button btn_saveData = (Button) findViewById(R.id.btn_saveData);
        txt_timeMain = (TextView) findViewById(R.id.txt_numTimeMainD);
        chronometer();

        timeMain = getIntent().getIntExtra("timeMain", 0);
        txt_timeMain.setText(String.valueOf(timeMain));
        //txt_timeMain.setText(getIntent().getStringExtra("timeMain"));

        btn_goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(/*SaveDataActivity.this, MainActivity.class*/);
                goBack.putExtra("timeData", currentTime);
                //startActivity(goBack);
                finish();
            }
        });

        btn_saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activatedData==0){
                    Intent sendData = new Intent(SaveDataActivity.this, MainActivity.class);
                    sendData.putExtra("booleanData", "True");
                    Toast.makeText(getApplicationContext(), "Data saved correctly", Toast.LENGTH_SHORT).show();
                    activatedData=1;
                }else{
                    Toast.makeText(getApplicationContext(), "Data is already saved", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void chronometer(){

        mTimerTask = new TimerTask(){

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentTime++;
                    }
                });
            }
        };

        mTimer = new Timer();
        mTimer.schedule(mTimerTask, 1, 1000);
    }
}
