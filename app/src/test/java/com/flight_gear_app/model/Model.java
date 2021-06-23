package com.flight_gear_app.model;

public class Model {
    private TelnetClient client;
    private Model model;
    //public event PropertyChang

    private int ip;
    private int port;

    public Model Model(){
        //get{
            if(model == null){
                model = new Model(new TelnetClient());
            }
            return model;
        //}
    }
    // Constructor
    private Model(TelnetClient client){
        this.initialize(client);
        this.connect();
    }
    // initialize the com.flight_gear_app.model class : set defult values
    private void initialize(TelnetClient client){
        this.client = client;
    }

    // connect to localhost
    private void connect(){
        this.connect("127.0.0.1", 5400);
    }
    // connect to given info
    private void connect(String ip, int port){
        this.client.connect(ip, port);
    }

    public void NotifyPropertyChange(String propName){
  //      if(this.PropertyChange != null){
    //        this.NotifyPropertyChange(this, new NotifyPropertyChangeEvent(propName));
      //  }
    }
    // --------------------------------------------------------------------
    // -- for Sliders
    private int throttle;
    private int rubber;


    // --------------------------------------------------------------------
    // -- for Joy stick
    private int elevator;
    private int aileron;
}
