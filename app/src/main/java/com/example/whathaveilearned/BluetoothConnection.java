package com.example.whathaveilearned;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.UUID;

public class BluetoothConnection extends MainActivity {


    public final static String MODULE_MAC = "98:D3:33:80:C7:38";
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private static BluetoothAdapter bta =BluetoothAdapter.getDefaultAdapter();
    private static BluetoothDevice mmDevice;
    private static BluetoothSocket mmSocket;
    private static ConnectedThread btt;
    private static Handler mHandler;

    public static TextView response = MainActivity.getResponse();


    public static void initiateBluetoothProcess() {

        if (bta.isEnabled()) {


            BluetoothSocket tmp = null;
            mmDevice = bta.getRemoteDevice(MODULE_MAC);


            try {
                tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);

                mmSocket = tmp;
                mmSocket.connect();
                Log.i("[BLUETOOTH]", "Connected to: " + mmDevice.getName());
            } catch (IOException e) {
                try {
                    mmSocket.close();
                } catch (IOException c) {
                    return;
                }
            }

            Log.i("[BLUETOOTH]", "Creating handler");
            mHandler = new Handler(Looper.getMainLooper()) {

                public void handleMessage(Message msg) {
                    // super.handleMessage(msg);

                    if (msg.what == ConnectedThread.RESPONSE_MESSAGE) {
                        String txt = (String) msg.obj;
                        if (response.getText().toString().length() >= 30) {
                            response.setText("");
                            response.append(txt);
                        } else {
                            response.append("\n" + txt);
                        }
                    }
                }
            };

            Log.i("[BLUETOOTH]", "Creating and running Thread");
            btt = new ConnectedThread(mmSocket, mHandler);
            btt.start();
        }

    }

    public static BluetoothAdapter getBta() {
        return bta;
    }
    public static BluetoothDevice getMmDevice() {
        return mmDevice;
    }

    public static BluetoothSocket getMmSocket(){
        return mmSocket;
    }

    public static ConnectedThread getBtt(){
        return btt;
    }
}
