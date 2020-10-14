package com.example.intent_timertask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TimerTask mTimerTask;
    private int currentTime=0;
    private int timeData;
    private int timePermission;
    private TextView txt_numTimeMain;
    private TextView txt_numTimeData;
    private TextView txt_numTimePermission;
    private Timer mTimer=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_goActivity2 = (Button) findViewById(R.id.btn_goSaveData);
        Button btn_goActivity3 = (Button) findViewById(R.id.btn_goPermissions);
        txt_numTimeMain = (TextView) findViewById(R.id.txt_numTimeMain);
        txt_numTimeData = (TextView) findViewById(R.id.txt_numTimeData);
        txt_numTimePermission = (TextView) findViewById(R.id.txt_numTimePermission);
        chronometer();

        timeData = getIntent().getIntExtra("timeData", 0);
        txt_numTimeData.setText(String.valueOf(timeData));
        timePermission = getIntent().getIntExtra("timePermission", 0);
        txt_numTimePermission.setText(String.valueOf(timePermission));

        //txt_numTimeData.setText(getIntent().getStringExtra("timeData"));
        //txt_numTimePermission.setText(getIntent().getStringExtra("timePermission"));

        btn_goActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goActivity2 = new Intent(MainActivity.this, SaveDataActivity.class);
                goActivity2.putExtra("timeMain", currentTime);
                startActivityForResult(goActivity2, 2);
            }
        });

        btn_goActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goActivity3 = new Intent(MainActivity.this, AddPermissionsActivity.class);
                goActivity3.putExtra("timeMain", currentTime);
                startActivityForResult(goActivity3, 3);
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
                        txt_numTimeMain.setText(String.valueOf(currentTime));

                    }
                });
            }
        };

        mTimer = new Timer();
        mTimer.schedule(mTimerTask, 1, 1000);
    }
}