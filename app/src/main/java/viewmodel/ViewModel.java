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
    private FGModel model;
    public String currentPortText = "";
    public String currentIPText = "";
    public int currentThrottle = 0;
    public int currentRudder = 0;

    // data binding --------------------------------
    // text and connect
    // Port: set, get
    public String getCurrentPortText(){ return currentPortText; }
    public void setCurrentPortText(CharSequence s) {
        this.currentPortText = s.toString();
    }
    // IP: set, get
    public String getCurrentIPText(){ return currentIPText; }
    public void setCurrentIPText(CharSequence s) {
        this.currentIPText = s.toString();
    }

    // onclick "connect" button, sands the info to model
    public void handleClick(){
//        Log.d("handleClick", "1");
        this.model.connect(currentIPText, currentPortText);
    }

    // seekbars
    // rudder: get, set - in set we also call to model becuase the rudder changed
    // the resone for a/100, is for sanding more acurate numbers to model. like 0.5
    // other wise it will be only 1 0 -1
    public int getCurrentRudder(){ return currentRudder; }
    public void setCurrentRudder(int a) {
        this.currentRudder = a;
//        Log.d("Rudder", Integer.toString(a));
        this.model.changeRudder(Double.valueOf(a) / 100.0);
    }

    // Throttle: get, set - in set we also call to model becuase the Throttle changed
    // the resone for a/100, is for sanding more acurate numbers to model. like 0.5
    // other wise it will be only 1 0 -1
//    public int currentThrottle = 0;
    public int getCurrentThrottle() {
        return currentThrottle;
    }
    public void setCurrentThrottle(int a) {
        this.currentThrottle = a;
        this.model.changeThrottle(Double.valueOf(a) / 100.0);
    }

    // ---------------------------------------------------------------------------
    // Constructor
    public ViewModel(FGModel model){
        this.model = model;
    }
    public void changeJoystickChange(float a, float b) {
        this.model.changeJoystick(a, b);
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
}
