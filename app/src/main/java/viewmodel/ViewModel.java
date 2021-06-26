package viewmodel;

import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import java.io.IOException;

import model.FGModel;

public class ViewModel {
    //private ViewModel viewModel;
    private FGModel model;
    public String currentPortText = "";
    public String currentIPText = "";

    // data binding --------------------------------
    // text and connect
    public String getCurrentPortText(){ return currentPortText; }
    public void setCurrentPortText(CharSequence s) {
        this.currentPortText = s.toString();
    }
    public String getCurrentIPText(){ return currentIPText; }
    public void setCurrentIPText(CharSequence s) {
        this.currentIPText = s.toString();
    }
    public void handleClick(){
//        Log.d("handleClick", "1");
        this.model.connect(currentIPText, currentPortText);
    }
    // seekbars
    public int currentRudder = 0;
    public int getCurrentRudder(){ return currentRudder; }
    public void setCurrentRudder(int a) {
        this.currentRudder = a;
//        Log.d("Rudder", Integer.toString(a));
        this.model.changeRudder(Double.valueOf(a / 100));
    }

    public int currentThrottle = 0;
    public int getCurrentThrottle(){ return currentThrottle; }
    public void setCurrentThrottle(int a) {
        this.currentThrottle = a;
//        Log.d("Throttle", Integer.toString(a));
        this.model.changeThrottle(Double.valueOf(a / 100));
    }

    // ---------------------------------------------------------------------------
    // Constructor
    public ViewModel(FGModel model){
        this.model = model;
    }

//    public void onClickConnect(String ip, String port){
//        this.model.connect(ip, port);
//    }
//
//    public void onRudderChange(double value) {
//        this.model.changeRudder(value);
//    }
//    public void onThrottleChange(double value) {
//        this.model.changeThrottle(value);
//    }

    public void changeJoystickChange(float a, float b) {
        this.model.changeJoystick(a, b);
    }
}
