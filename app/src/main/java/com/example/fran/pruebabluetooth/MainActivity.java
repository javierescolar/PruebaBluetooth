package com.example.fran.pruebabluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button conectarBtn,imprimirBtn,desconectarBtn;
    private TextView estados;
    private ImpresoraBluetooth impresora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.conectarBtn = findViewById(R.id.btnConectar);
        this.imprimirBtn = findViewById(R.id.btnImprimir);
        this.desconectarBtn = findViewById(R.id.btnDesconectar);
        this.estados = findViewById(R.id.textEstados);
        this.impresora = new ImpresoraBluetooth(this,"");
        conectar();
        imprimir();
        desconectar();
    }

   public void conectar(){
        this.conectarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estados.setText("Conectar");
                try {
                    impresora.connectar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
   }

    public void imprimir(){
        this.imprimirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estados.setText("Imprimir");
            }
        });
    }

    public void desconectar(){
        this.desconectarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estados.setText("Desconectar");
            }
        });
    }
}
