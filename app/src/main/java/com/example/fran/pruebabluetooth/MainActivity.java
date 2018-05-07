package com.example.fran.pruebabluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button conectarBtn,imprimirBtn,desconectarBtn, todoBtn;
    private TextView estados;
    private ImpresoraBluetooth impresora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.conectarBtn = findViewById(R.id.btnConectar);
        this.imprimirBtn = findViewById(R.id.btnImprimir);
        this.desconectarBtn = findViewById(R.id.btnDesconectar);
        this.todoBtn = findViewById(R.id.btntodo);

        this.estados = findViewById(R.id.textEstados);

        this.impresora = new ImpresoraBluetooth(this,"");
        conectar();
        imprimir();
        desconectar();
        todo();
    }

    public void todo(){
        this.todoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cpclData = "! 0 200 200 300 1\r\n"
                        +"ENCODING UTF-8\r\n"
                        +"CENTER\r\n"
                        + "TEXT 7 1 1 20 TOURIST PRODUCTS MADRID SL.\r\n"
                        +"CENTER\r\n"
                        +"TEXT 7 1 1 70 *************************************\r\n"
                        + "PRINT\r\n";
                impresora.setData(cpclData);
                try {
                    impresora.connectar();
                    estados.setText("Conectar");
                    impresora.imprimir();
                    estados.setText("Imprimir");
                    impresora.desconectar();
                    estados.setText("Desconectar");

                } catch (Exception e) {
                    e.printStackTrace();
                    estados.setText("Error!!");
                }
            }
        });
    }

   public void conectar(){
        this.conectarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    impresora.connectar();
                    estados.setText("Conectar");
                } catch (Exception e) {
                    e.printStackTrace();
                    estados.setText("Error!!");
                }
            }
        });
   }

    public void imprimir(){
        this.imprimirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cpclData = "! 0 200 200 300 1\r\n"
                        +"ENCODING UTF-8\r\n"
                        +"CENTER\r\n"
                        + "TEXT 7 1 1 20 TOURIST PRODUCTS MADRID SL.\r\n"
                        +"CENTER\r\n"
                        +"TEXT 7 1 1 70 *************************************\r\n"
                        + "PRINT\r\n";
                impresora.setData(cpclData);
                try {
                    impresora.imprimir();
                    estados.setText("Imprimir");
                } catch (Exception e) {
                    e.printStackTrace();
                    estados.setText("Error!!");
                }
            }
        });
    }

    public void desconectar(){
        this.desconectarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    impresora.desconectar();
                    estados.setText("Desconectar");
                } catch (Exception e) {
                    e.printStackTrace();
                    estados.setText("Error!!");
                }

            }
        });
    }


}
