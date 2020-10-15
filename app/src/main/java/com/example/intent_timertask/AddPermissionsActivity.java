package com.example.intent_timertask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class AddPermissionsActivity extends AppCompatActivity {

    private TimerTask mTimerTask;
    private int currentTime=0;
    private int activatedPermissions=0;
    private int timeMain;
    private Timer mTimer=null;
    private TextView txt_timeMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_permissions);

        Button btn_goHome = (Button) findViewById(R.id.btn_goActivity1_2);
        Button btn_addPermissions = (Button) findViewById(R.id.btn_addPermissions);
        txt_timeMain = (TextView) findViewById(R.id.txt_numTimeMainP);
        chronometer();

        timeMain = getIntent().getIntExtra("timeMain", 0);
        txt_timeMain.setText(String.valueOf(timeMain));

        btn_goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent();
                goBack.putExtra("timePermission", currentTime);
                finish();
            }
        });

        btn_addPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activatedPermissions==0){
                    Intent sendPermissions = new Intent(AddPermissionsActivity.this, MainActivity.class);
                    sendPermissions.putExtra("booleanPermission", "True");
                    Toast.makeText(getApplicationContext(), "Permissions added correctly", Toast.LENGTH_SHORT).show();
                    activatedPermissions=1;
                }else {
                    Toast.makeText(getApplicationContext(), "Permissions are already added", Toast.LENGTH_SHORT).show();
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
