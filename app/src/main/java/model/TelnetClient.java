package model;

import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class TelnetClient implements ITelnetClient {
    public Socket socket;
    public OutputStreamWriter output; // to send the
    public InputStreamReader input; // to get from

    public TelnetClient(){}

    @Override
    public void connect(String ip, int port) {
        socket = new Socket(Proxy.NO_PROXY);
        try {
            socket.connect(new InetSocketAddress(ip, port));
            this.output = new OutputStreamWriter(socket.getOutputStream());
            //this.output = new PrintWriter(socket.getOutputStream());
            this.input = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Log.d("info", "here 4");
    }

    @Override
    public void disconnect() {
        if(this.input != null){
            try {this.input.close();}
            catch (IOException e){}
        }
        if(this.output != null){
            try{this.output.close();}
            catch (IOException e){}
        }
        if(this.socket != null){
            try{this.socket.close();}
            catch (IOException e){}
        }
    }

    @Override
    public void write(String operation) {
        if(this.socket.isConnected()){
            try {
                this.output.write(operation);
            }
            catch (IOException e) {}
        }
    }

    @Override
    public String read() {
        return null;//this.input.read(); //readline
    }
}
