package model;

import android.util.Log;
import com.flight_gear_app.R;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class FGModel{
    private static FGModel model;
    private BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<Runnable>();
    public Socket socket;
    public OutputStreamWriter output;
    public static boolean isConnected = false;

    public static FGModel Model(){
        if(model == null){
            model = new FGModel(/*new TelnetClient()*/);
            isConnected = false;
        }
        return model;
    }
    // Constructor
    public FGModel(/*TelnetClient client*/){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        dispatchQueue.take().run();
                    } catch (InterruptedException e) {}
                }
            }
        }).start();
    }

    // connect to given info
    public void connect(String ip, String port){
        try {
            dispatchQueue.put(new Runnable() {
                @Override
                public void run() {
                    //Log.d("info", "here 3");
                    socket = new Socket(Proxy.NO_PROXY);
                    try {
                        int iport = Integer.parseInt(port);
                        socket.connect(new InetSocketAddress(ip, iport));
                        output = new OutputStreamWriter(socket.getOutputStream());
                        isConnected = true;
                    } catch (IOException e) {
                        Log.d("error", e.getMessage());
                        System.exit(0);
                    }
                }
            });
        } catch (InterruptedException e) { Log.d("error", e.getMessage()); }
    }

    // --------------------------------------------------------------------
    private final String setFlight = "set /controls/flight/";
    // -- for Sliders
    public void changeRudder(double value) {
        try {
            if (isConnected) { // in case user press seekbar before connecting to server
                dispatchQueue.put(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            output.write(setFlight + "/rudder " + value + "\r\n");
                            output.flush();
                        } catch (IOException e) {Log.d("error", e.getMessage());}
                    }
                });
            }
        } catch (InterruptedException e) { Log.d("error", e.getMessage()); }
    }

    public void changeThrottle(double value) {
        if(isConnected) {
            try {
                dispatchQueue.put(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            output.write("set /controls/engines/current-engine/throttle " + value + "\r\n");
                            output.flush();
                        } catch (IOException e) {Log.d("error", e.getMessage());}
                    }
                });
            } catch (InterruptedException e) { Log.d("error", e.getMessage()); }
        }
    }
    // --------------------------------------------------------------------
    // -- for Joy stick
    public void changeJoystick(float elevator, float aileron) {
        if(isConnected) {
            try {
                dispatchQueue.put(new Runnable() {
                    @Override
                    public void run() {
                        //Log.d("info", "here throttle 3");
                        try {
                            output.write(setFlight + "/elevator " + elevator + "\r\n");
                            output.flush();
                            output.write(setFlight + "/aileron " + aileron + "\r\n");
                            output.flush();
                        } catch (IOException e) { Log.d("error", e.getMessage()); }
                    }
                });
            } catch (InterruptedException e) { Log.d("error", e.getMessage()); }
        }
    }
}
