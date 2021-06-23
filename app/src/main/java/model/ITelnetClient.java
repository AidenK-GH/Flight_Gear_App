package model;

public interface ITelnetClient {
    // connect to the server on the given ip and port
    void connect(String ip, int port);

    // close any open socket
    void disconnect();

    // send commands to the server
    void write(String operation);

    // return answer from the server
    String read();
}
