package com.flight_gear_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import model.FGModel;
import model.TelnetClient;
import viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity {

    private ViewModel vmModel;
    private SeekBar rudderBar;
    private SeekBar throttleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.vmModel = new ViewModel(new FGModel(/*new TelnetClient()*/));

        // seek bars ------------------------------------------
        this.rudderBar = (SeekBar)findViewById(R.id.rudderBar);
        rudderBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("info", "here rudder 1");
                try {
                    vmModel.onRudderChange(progress / 100.0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        this.throttleBar = (SeekBar)findViewById(R.id.throttleBar);
        throttleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    vmModel.onThrottleChange(progress / 100.0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    //activit on click - get text
    public void handleText(View v) throws InterruptedException {
        TextView ip = findViewById(R.id.ipInput);
        String IPinput = ip.getText().toString();

        TextView port = findViewById(R.id.portInput);
        String Port = port.getText().toString();
        int iPort = Integer.parseInt(Port);
        //Log.d("info", "here 1");

        this.vmModel.onClickConnect(IPinput, iPort);
    }

    public void onClickRudderChange(){

    }
}