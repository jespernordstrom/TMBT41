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
import android.widget.TextView;
import android.widget.Toast;

public class GeneralPurpose extends AppCompatActivity {


    ConnectedThread btt;
    BluetoothAdapter bta;
    BluetoothDevice mmDevice;
    BluetoothSocket mmSocket;
    int responseLength;
    Button bivalve1;
    Button bivalve2;
    Button bivalve3;
    Button bivalve4;
    Button valveOpenAll;
    Button valveCloseAll;
    Button home;
    Boolean valve1;
    Boolean valve2;
    Boolean valve3;
    Boolean valve4;
    TextView descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_purpose);

        setTitle("General Purpose");
        bta = BluetoothConnection.getBta();
        btt = BluetoothConnection.getBtt();
        mmDevice = BluetoothConnection.getMmDevice();
        mmSocket = BluetoothConnection.getMmSocket();

        valve1 = false;
        valve2 = false;
        valve3 = false;
        valve4 = false;
        home   =   (Button)findViewById(R.id.back);
        bivalve1 = (Button)findViewById(R.id.open1);
        bivalve2 = (Button)findViewById(R.id.open2);
        bivalve3 = (Button)findViewById(R.id.open3);
        bivalve4 = (Button)findViewById(R.id.open4);
        descriptionText = (TextView)findViewById(R.id.textBox);
        valveCloseAll   = (Button)findViewById(R.id.closeAll);
        valveOpenAll    = (Button)findViewById(R.id.openAll);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveBack();
            }
        });

        bivalve1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("[BLUETOOTH]", "Attempting to send data ");
                if (mmSocket.isConnected() && btt != null) {
                    if (!valve1) {
                        String sendtext = 'o' + " " +  "1";
                        btt.write(sendtext.getBytes());
                        if (responseLength <= 13) {
                            descriptionText.setText(descriptionText.getText() + "\n" + "VALVE 1 OPEN");
                            responseLength++;
                        } else {
                            descriptionText.setText("VALVE 1 OPEN");
                            responseLength = 1;
                        }
                        valve1 = true;
                    }
                    else{
                        String sendtext = 'o' + " " + "5";
                        btt.write(sendtext.getBytes());
                        if (responseLength <= 13) {
                            descriptionText.setText(descriptionText.getText() + "\n" + "VALVE 1 CLOSED");
                            responseLength++;
                        } else {
                            descriptionText.setText("VALVE 1 CLOSED");
                            responseLength = 1;
                        }
                        valve1 = false;
                    }

                }
                else {
                    Toast.makeText(GeneralPurpose.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            }
        });


        bivalve2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mmSocket.isConnected() && btt != null) {
                    if (!valve2) {
                        String sendtext = 'o' + " " + "2";
                        btt.write(sendtext.getBytes());
                        if (responseLength <= 13) {
                            descriptionText.setText(descriptionText.getText() + "\n" + "VALVE 2 OPEN");
                            responseLength++;
                        } else {
                            descriptionText.setText("VALVE 2 OPEN");
                            responseLength = 1;
                        }
                        valve2 = true;
                    }
                    else{
                        String sendtext = 'o' + " " + "6";
                        btt.write(sendtext.getBytes());
                        if (responseLength <= 13) {
                            descriptionText.setText(descriptionText.getText() + "\n" + "VALVE 2 CLOSED");
                            responseLength++;
                        } else {
                            descriptionText.setText("VALVE 2 CLOSED");
                            responseLength = 1;
                        }
                        valve2 = false;
                    }
                }
                else {
                    Toast.makeText(GeneralPurpose.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

        });

        bivalve3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mmSocket.isConnected() && btt != null) {
                    if (!valve3) {
                        String sendtext = 'o' + " " + "3";
                        btt.write(sendtext.getBytes());
                        if (responseLength <= 13) {
                            descriptionText.setText(descriptionText.getText() + "\n" + "VALVE 3 OPEN");
                            responseLength++;
                        } else {
                            descriptionText.setText("VALVE 3 OPEN");
                            responseLength = 1;
                        }
                        valve3 = true;
                    }
                    else {
                        String sendtext = 'o' + " " + "7";
                        btt.write(sendtext.getBytes());
                        if (responseLength <= 13) {
                            descriptionText.setText(descriptionText.getText() + "\n" + "VALVE 3 CLOSED");
                            responseLength++;
                        } else {
                            descriptionText.setText("VALVE 3 CLOSED");
                            responseLength = 1;
                        }
                        valve3 = true;
                    }
                }
                else {
                    Toast.makeText(GeneralPurpose.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });

        bivalve4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mmSocket.isConnected() && btt != null) {
                    if (!valve4) {
                        String sendtext = 'o' + " " + "4";
                        btt.write(sendtext.getBytes());
                        if (responseLength <= 13) {
                            descriptionText.setText(descriptionText.getText() + "\n" + "VALVE 4 OPEN");
                            responseLength++;
                        } else {
                            descriptionText.setText("VALVE 4 OPEN");
                            responseLength = 1;
                        }
                        valve4 = true;
                    }
                    else {
                        String sendtext = 'o' + " " + "8";
                        btt.write(sendtext.getBytes());
                        if (responseLength <= 13) {
                            descriptionText.setText(descriptionText.getText() + "\n" + "VALVE 4 CLOSED");
                            responseLength++;
                        } else {
                            descriptionText.setText("VALVE 4 CLOSED");
                            responseLength = 1;
                        }
                        valve4 = false;
                    }


                }
                else {
                    Toast.makeText(GeneralPurpose.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });

        valveOpenAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mmSocket.isConnected() && btt != null) {
                    String sendtext = 'o' + " " + "1234";
                    btt.write(sendtext.getBytes());
                    if (responseLength <= 13) {
                        descriptionText.setText(descriptionText.getText() + "\n" + "ALL VALVES OPEN");
                        responseLength++;
                    } else {
                        descriptionText.setText("ALL VALVES OPEN");
                        responseLength = 1;
                    }

                }
            }
        });

        valveCloseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mmSocket.isConnected() && btt != null) {
                    String sendtext = 'o' + " " + "5678";
                    btt.write(sendtext.getBytes());
                    if (responseLength <= 13) {
                        descriptionText.setText(descriptionText.getText() + "\n" + "ALL VALVES CLOSED");
                        responseLength++;
                    } else {
                        descriptionText.setText("ALL VALVES CLOSED");
                        responseLength = 1;
                    }
                }
            }
        });

    }

    private void MoveBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
