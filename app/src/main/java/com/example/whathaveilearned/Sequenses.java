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

public class Sequenses extends AppCompatActivity {

    ConnectedThread btt;
    BluetoothAdapter bta;
    BluetoothDevice mmDevice;
    BluetoothSocket mmSocket;
    Button seqence1;
    Button seqence2;
    Button seqence3;
    Button seqence4;
    Button back;
    String TAG = "Sequences";
    TextView textBox1;
    TextView textBox2;
    TextView textBox3;
    int textSize =  0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequenses);
        this.setTitle("Sequence");


        bta      = BluetoothConnection.getBta();
        btt      = BluetoothConnection.getBtt();
        mmDevice = BluetoothConnection.getMmDevice();
        mmSocket = BluetoothConnection.getMmSocket();


        back     = (Button)findViewById(R.id.moveBack);
        seqence1 = (Button)findViewById(R.id.seq1);
        seqence2 = (Button)findViewById(R.id.seq2);
        seqence3 = (Button)findViewById(R.id.seq3);
        seqence4 = (Button)findViewById(R.id.seq4);
        textBox1  = (TextView)findViewById(R.id.respone1);
        textBox2  = (TextView)findViewById(R.id.reponse2);
        textBox3  = (TextView)findViewById(R.id.respone3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveBack();
            }
        });

        seqence1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mmSocket.isConnected() && btt != null) {
                    String str = "s1";
                    if(textSize < 7) {
                        textBox1.setText(textBox1.getText() + "\n" + "Crazy Sequence");
                        textSize++;
                    }else if(textSize < 14){
                        textBox2.setText(textBox2.getText() + "\n" + "Crazy Sequence");
                        textSize++;
                    }else if(textSize < 21){
                        textBox3.setText(textBox3.getText() + "\n" + "Crazy Sequence");
                        textSize++;
                    }else{
                        textBox1.setText("Crazy Sequence");
                        textBox2.setText("");
                        textBox3.setText("");
                        textSize = 1;
                    }
                    btt.write(str.getBytes());
                }
                else
                {
                    Log.d(TAG, "onClick: Fail ");
                }
            }
        });

        seqence2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mmSocket.isConnected() && btt != null) {
                    String str = 's' + " " + "2";
                        if(textSize < 7) {
                            textBox1.setText(textBox1.getText() + "\n" + "Staircase Sequence");
                            textSize++;
                        }else if(textSize < 14){
                            textBox2.setText(textBox2.getText() + "\n" + "Staircase Sequence");
                            textSize++;
                        }else if(textSize < 21){
                            textBox3.setText(textBox3.getText() + "\n" + "Staircase Sequence");
                            textSize++;
                        }else{
                            textBox1.setText("Staircase Sequence");
                            textBox2.setText("");
                            textBox3.setText("");
                            textSize = 1;
                        }
                    btt.write(str.getBytes());
                }
            }
        });

        seqence3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        seqence4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

        private void MoveBack() {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
}


