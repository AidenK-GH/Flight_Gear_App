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
    //private TelnetClient client;
    private static FGModel model;
    //public Thread clientThread;
    private BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<Runnable>();
    public Socket socket;
    public OutputStreamWriter output; // to send the

    public static FGModel Model(){
        if(model == null){
            model = new FGModel(/*new TelnetClient()*/);
        }
        return model;
    }
    // Constructor
    public FGModel(/*TelnetClient client*/){
        //this.client = client;
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

    // connect to localhost
    //public void connect() {this.connect("127.0.0.1", 6400);}
    // connect to given info
    public void connect(String ip, int port) throws InterruptedException {
        dispatchQueue.put(new Runnable() {
            @Override
            public void run() {
                //Log.d("info", "here 3");
                socket = new Socket(Proxy.NO_PROXY);
                try {
                    socket.connect(new InetSocketAddress(ip, port));
                    output = new OutputStreamWriter(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
                //Log.d("info", "here 4");
                //this.client.connect(ip, port);
            }
        });
        //Log.d("info", "here 3");
        //this.client.connect(ip, port);

        //this.clientThread = new Thread("telnet"){ this.client.connect(ip, port); };
        //this.clientThread.start();
    }

    // --------------------------------------------------------------------
    private final String setFlight = "set /controls/flight/";
    // -- for Sliders
    public void changeRudder(double value) throws InterruptedException {
        dispatchQueue.put(new Runnable() {
            @Override
            public void run() {
                //Log.d("info", "here rudder 3");
                try {
                    output.write( setFlight + "/rudder "+ value + "\r\n");
                    output.flush();
                } catch (IOException e) {}
            }
        });
    }

    public void changeThrottle(double value) throws InterruptedException {
        dispatchQueue.put(new Runnable() {
            @Override
            public void run() {
                //Log.d("info", "here throttle 3");
                try {
                    output.write( "set /controls/engines/current-engine/throttle "+ value + "\r\n");
                    output.flush();
                } catch (IOException e) {}
            }
        });
    }
    // --------------------------------------------------------------------
    // -- for Joy stick
    public void changeJoystick(float elevator, float aileron) throws InterruptedException {
        dispatchQueue.put(new Runnable() {
            @Override
            public void run() {
                //Log.d("info", "here throttle 3");
                try {
                    output.write( setFlight + "/elevator "+ elevator + "\r\n");
                    output.flush();
                    output.write( setFlight + "/aileron "+ aileron + "\r\n");
                    output.flush();
                } catch (IOException e) {}
            }
        });
    }
}
