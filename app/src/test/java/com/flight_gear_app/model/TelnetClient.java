package com.flight_gear_app.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class TelnetClient implements ITelnetClient{
    private Socket socket;
    private OutputStreamWriter output; // to send the
    private InputStreamReader input; // to get from

    public TelnetClient(){}

    @Override
    public void connect(String ip, int port) {
        //IPAddress ipAddressX = IPAddress.Parse(ip);
        //InetAddress ipAddress = InetAddress.getByName(ip);
        socket = new Socket(Proxy.NO_PROXY);
                //ipAddress..AddressFamily, SocketType.Stream, Protocol);
        try {
            socket.connect(new InetSocketAddress(ip, port));
            this.output = new OutputStreamWriter(socket.getOutputStream());
            this.input = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
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
            try{this.output.write(operation);}
            catch (IOException e){}
        }
    }

    @Override
    public String read() {
        return null;//this.input.read(); //readline
    }
}
