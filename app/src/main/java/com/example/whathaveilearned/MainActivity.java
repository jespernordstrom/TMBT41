package com.example.whathaveilearned;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public final static int REQUEST_ENBALE_BT = 1;
    private static int first = 1;
    //testa om det funkar.
    ConnectedThread btt;
    BluetoothAdapter bta;
    BluetoothDevice mmDevice;
    BluetoothSocket mmSocket;
    private static TextView response;


    Button sendButton;
    Button changeToSeq;
    Button change;
    Button changeToGP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Haptic Garments");
        Log.i("[BLUETOOTH]", "Creating listeners");

        changeToSeq = (Button)   findViewById(R.id.chagneToSequence);
        response    = (TextView) findViewById(R.id.Headline);
        change      = (Button)   findViewById(R.id.changeActivity);
        sendButton  = (Button)   findViewById(R.id.send);
        changeToGP  = (Button)   findViewById(R.id.changeActivityToGP);



        changeToGP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityGP();
            }
        });


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });

        changeToSeq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSequence();
            }
        });

/*
        pulseSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mmSocket.isConnected() && btt != null){
                    String pulseString = 'p' + " " + pulseValveText.getText().toString() + " " +
                            pulseLengthText.getText().toString() + " " +
                            numberOfPulses.getText().toString();
                    btt.write(pulseString.getBytes());
                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String openString = 'o' + " " + sendText.getText().toString();
                if(mmSocket.isConnected() && btt != null) {
                    btt.write(openString.getBytes());
                    if (responseLength <= 13) {
                        response.setText(response.getText() + "\n" + "Oooo Somthing happening");
                        responseLength++;
                    } else {
                        response.setText("OOOOooo Something gone and gone.");
                        responseLength = 1;
                    }
                }
            }
        });

*/

        if(first == 1) {
            bta = BluetoothAdapter.getDefaultAdapter();


            if (!bta.isEnabled()) {
                Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBTIntent, REQUEST_ENBALE_BT);
            } else {

                BluetoothConnection.initiateBluetoothProcess();
                bta = BluetoothConnection.getBta();
                btt = BluetoothConnection.getBtt();
                mmDevice = BluetoothConnection.getMmDevice();
                mmSocket = BluetoothConnection.getMmSocket();
            }
            first = 2;
        } else {
            bta = BluetoothConnection.getBta();
            btt = BluetoothConnection.getBtt();
            mmDevice = BluetoothConnection.getMmDevice();
            mmSocket = BluetoothConnection.getMmSocket();
        }
    }

    private void changeSequence() {
        Intent intent = new Intent(this, Sequenses.class);
        startActivity(intent);
    }

    private void changeActivity() {
        Intent intent = new Intent(this, Hold_valves.class);
        startActivity(intent);
    }

    private void changeActivityGP() {
        Intent intent = new Intent(this, GeneralPurpose.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_ENBALE_BT) {
            BluetoothConnection.initiateBluetoothProcess();

        }
    }
    public static TextView getResponse(){
        return response;
    }
}
