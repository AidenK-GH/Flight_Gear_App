package com.flight_gear_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //activit on click - get text
    public void handleText(View v){
        TextView ip = findViewById(R.id.ipInput);
        String IPinput = ip.getText().toString();
        TextView port = findViewById(R.id.portInput);
        String Port = port.getText().toString();
        Log.d("info", Port);
    }
}