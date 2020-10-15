package com.example.intent_timertask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TimerTask mTimerTask;
    private int currentTime=0;
    private int timeData;
    private int timePermission;
    private String booleanData;
    private String booleanPermission;
    private TextView txt_numTimeMain;
    private TextView txt_numTimeData;
    private TextView txt_numTimePermission;
    private TextView txt_booleanData;
    private TextView txt_booleanPermission;
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
        txt_booleanData = (TextView) findViewById(R.id.txt_booleanData);
        txt_booleanPermission = (TextView) findViewById(R.id.txt_booleanPermission);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentData){
        try{
            //txt_numTimeData.setText(String.valueOf(requestCode));
           super.onActivityResult(requestCode, resultCode, intentData);
            if(requestCode==2){

                //txt_numTimePermission.setText(String.valueOf(requestCode));
                timeData = intentData.getIntExtra("timeData", 5);
                txt_numTimeData.setText(String.valueOf(timeData));
                //String timeData = String.valueOf(intentData.getIntExtra("timeData", 5));
                //txt_numTimeData.setText(timeData);
                booleanData = intentData.getStringExtra("booleanData");
                txt_booleanData.setText(String.valueOf(booleanData));

            }else if(requestCode==3){
                timePermission = intentData.getIntExtra("timePermission", 5);
                txt_numTimePermission.setText(String.valueOf(timePermission));
                booleanPermission = intentData.getStringExtra("booleanPermission");
                txt_booleanPermission.setText(String.valueOf(booleanPermission));
            }
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    protected void  onPause(){
        super.onPause();
        mTimer.cancel();
    }

    protected void onResume(){
        super.onResume();
        chronometer();
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