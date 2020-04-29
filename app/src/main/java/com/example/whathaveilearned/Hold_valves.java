package com.example.whathaveilearned;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Hold_valves extends AppCompatActivity {

    ConnectedThread btt;
    BluetoothAdapter bta;
    BluetoothDevice mmDevice;
    BluetoothSocket mmSocket;
    Button back;
    Button send;
    EditText valve;
    EditText inflation;
    EditText deflation;
    EditText cycles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_valves);


        bta = BluetoothConnection.getBta();
        btt = BluetoothConnection.getBtt();
        mmDevice = BluetoothConnection.getMmDevice();
        mmSocket = BluetoothConnection.getMmSocket();

        back = (Button) findViewById(R.id.moveBack);
        valve = (EditText) findViewById(R.id.Pulse);
        deflation = (EditText)findViewById(R.id.deflationText);
        inflation = (EditText)findViewById(R.id.inflationText);
        cycles = (EditText)findViewById(R.id.Cycles);
        send = (Button) findViewById(R.id.send);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveBack();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mmSocket.isConnected() && btt != null) {
                    String str = 'd' + " " + valve.getText().toString() + " " + inflation.getText().toString()
                            + " " + deflation.getText().toString() + " " + cycles.getText().toString();
                    btt.write(str.getBytes());
                }
            }
        });

        bta = BluetoothAdapter.getDefaultAdapter();
    }

    private void MoveBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }








}
