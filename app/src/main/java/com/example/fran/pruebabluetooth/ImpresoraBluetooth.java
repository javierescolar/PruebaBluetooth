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
        final String PrinterBsid = "00:19:0E:A2:EF:0A";
        try {
            if (!bluetoothAdapter.isEnabled()) {
                //Intent intent = new Intent(activity, MainActivity.class);
                Intent intent = new Intent(activity, activity.getClass());
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

                UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standard SerialPortService ID
                //Method m = bluetoothDevice.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
                socket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
               // socket = (BluetoothSocket) m.invoke(bluetoothDevice, 1);
                //bluetoothAdapter.cancelDiscovery();
                socket.connect();
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
                //beginListenForData();
                System.out.println("Bluetooth Conectado");
            } else {
                System.out.println("Dispositivo Bluetooth no encontrado sin catch");
            }
        } catch (Exception ex) {
            System.out.println("Dispositivo Bluetooth no encontrado");
        }

    }

    public void desconectar() throws Exception {
        try {
            stopWorker = true;
            outputStream.close();
            inputStream.close();
            socket.close();
            System.out.println("Bluetooth Closed");
        } catch (Exception e) {
            System.out.println("Error Bluetooth Closed");
            e.printStackTrace();
        }
    }

    public void imprimir() throws Exception {
        try {



            outputStream.write(this.data.getBytes());
            System.out.println("Imprimiendo");
        } catch (Exception ex){
            System.out.println("Error al imprimir");
        }
    }

}
