package com.example.fran.pruebabluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button conectarBtn;
    private TextView estados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.conectarBtn = findViewById(R.id.btnConectar);
        this.estados = findViewById(R.id.textEstados);
    }

   public void probar(){
        this.conectarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estados.setText("Click!!!");
            }
        });
   }
}
