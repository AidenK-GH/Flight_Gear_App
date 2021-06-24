package viewmodel;

import android.util.Log;

import model.FGModel;

public class ViewModel {
    private ViewModel viewModel;
    private FGModel model;

    // Constructor
    public ViewModel(FGModel model){
        this.model = model;
    }

    public void onClickConnect(String ip, int port) throws InterruptedException {
        //Log.d("info", "here 2");
        this.model.connect(ip, port);
    }

    public void onRudderChange(double value) throws InterruptedException {
        //Log.d("info", "here rudder 2");
        this.model.changeRudder(value);
    }
    public void onThrottleChange(double value) throws InterruptedException {
        this.model.changeThrottle(value);
    }

    public void changeJoystickChange(float a, float b) throws InterruptedException {
        this.model.changeJoystick(a, b);
    }
}
