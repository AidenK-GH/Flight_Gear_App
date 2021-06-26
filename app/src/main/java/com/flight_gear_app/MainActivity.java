package com.flight_gear_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
//import android.view.View;
//import android.widget.SeekBar;
//import android.widget.TextView;

import com.flight_gear_app.databinding.ActivityMainBinding;

import model.FGModel;
import viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity implements Joystick.JoystickListener{

    private ViewModel vmModel;
   // private SeekBar rudderBar;
   // private SeekBar throttleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        this.vmModel = new ViewModel(new FGModel());

        //data binding -------------------------------
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewmodel(vmModel);

        // seek bars -----------------------------------------------------------------------
//        this.rudderBar = (SeekBar)findViewById(R.id.rudderBar);
//        rudderBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
////                    vmModel.onRudderChange(progress / 100.0);
//            }
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {}
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {}
//        });
//
//        this.throttleBar = (SeekBar)findViewById(R.id.throttleBar);
//        throttleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//               // vmModel.onThrottleChange(progress / 100.0);
//            }
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {}
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {}
//        });
    }

//    //activit on click - get text
//    public void handleText(View v) {
//        //TextView ip = findViewById(R.id.ipInput);
//        String IPinput = findViewById(R.id.ipInput).toString();
//
//        //TextView port = findViewById(R.id.portInput);
//        String Port = findViewById(R.id.portInput).toString();
//
//        //Log.d("info", "here");
//        this.vmModel.onClickConnect(IPinput, Port);
//    }

    @Override
    public void onJoystickMoved(float elevator, float aileron, int id) {
        this.vmModel.changeJoystickChange(elevator, aileron);
    }
}