package com.example.fran.pruebabluetooth;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;

import java.util.Set;
import java.util.UUID;

public class ImpresoraBluetooth  {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket socket;
    private BluetoothDevice bluetoothDevice;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Thread workerThread;
    private byte[] readBuffer;
    private int readBufferPosition;
    private volatile boolean stopWorker;
    private String data;
    private Activity activity;


    ImpresoraBluetooth(Activity activity,String data){

        this.activity=activity;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void connectar() throws Exception {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            if (!bluetoothAdapter.isEnabled()) {

                Intent intent = new Intent(activity, MainActivity.class);
                //Intent intent = new Intent(activity, activity.getClass());
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    if (device.getName().equals("BT-SPP") || device.getName().equals("BTSPP")) {
                        bluetoothDevice = device;
                        break;
                    }
                }

                UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
                //Method m = bluetoothDevice.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
                socket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
               // socket = (BluetoothSocket) m.invoke(bluetoothDevice, 1);
                //bluetoothAdapter.cancelDiscovery();
                socket.connect();
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
                //beginListenForData();
            } else {
                System.out.println("Dispositivo Bluetooth no encontrado");
                Toast.makeText(activity, "Dispositivo Bluetooth no encontrado", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception ex) {
            System.out.println("Dispositivo Bluetooth no encontrado");
        }

    }

}
