package com.flight_gear_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import com.flight_gear_app.databinding.ActivityMainBinding;
import model.FGModel;
import viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity implements Joystick.JoystickListener{

    private ViewModel vmModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        this.vmModel = new ViewModel(new FGModel());

        //data binding -------------------------------
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewmodel(vmModel);

        // NOT NEEDED, BECUASE THERE IS DATA BINDING
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

    }

    // when the joyS moves the Joystick class cacths it, then calculaits the elevator and aileron, and sends it here
    // so it can be sand to viewmodel without knowing it, just like in the instractions "should not know vm"
    @Override
    public void onJoystickMoved(float elevator, float aileron, int id) {
        this.vmModel.changeJoystickChange(elevator, aileron);
    }

//    //activit on click - get text
//    public void handleText(View v) {
//        String IPinput = findViewById(R.id.ipInput).toString();
//        String Port = findViewById(R.id.portInput).toString();
//
//        //Log.d("info", "here");
//        this.vmModel.onClickConnect(IPinput, Port);
//    }
}