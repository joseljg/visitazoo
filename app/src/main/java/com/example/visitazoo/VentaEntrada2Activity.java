package com.example.visitazoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VentaEntrada2Activity extends AppCompatActivity {

    String dia;
    int cantidad;
    EditText edt_tipoe;
    TextView txt_precio_base;
    TextView txt_descuento_dia;
    TextView txt_descuento_grupal;
    TextView txt_iva;
    TextView txt_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_entrada2);
        Intent intent = getIntent();
        dia = intent.getStringExtra(MainActivity.EXTRA_DIA);
        cantidad= intent.getIntExtra(MainActivity.EXTRA_CANTIDAD, 0);
        edt_tipoe = (EditText) findViewById(R.id.edt_tipo_entrada);
        txt_precio_base = (TextView) findViewById(R.id.txt_precio_base);
        txt_iva= (TextView) findViewById(R.id.txt_iva);
        txt_descuento_dia = (TextView) findViewById(R.id.txt_descuento_dia);
        txt_descuento_grupal = (TextView) findViewById(R.id.txt_descuento_grupal);
        txt_total = (TextView) findViewById(R.id.txt_total);
    }

    public boolean validar_tipo_entrada(String tipoe)
    {
        tipoe = tipoe.toLowerCase();
        if(       tipoe.equalsIgnoreCase("general")
                ||tipoe.equalsIgnoreCase("jubilado")
                ||tipoe.equalsIgnoreCase("niño")) { return true; }
        else{ return false;}
    }

    public void comprar(View view) {
        double precio_base = 0.0;
        String tipoe = String.valueOf(edt_tipoe.getText());
        double precioe = 0.0;
        double descuento_dia = 0.0;
        double descuento_grupal = 0.0;
        if(validar_tipo_entrada(tipoe))
        {
            if(tipoe.equalsIgnoreCase("general")) { precioe = 5.0;}
            if(tipoe.equalsIgnoreCase("jubilado")) { precioe = 4.0;}
            if(tipoe.equalsIgnoreCase("niño")) { precioe = 3.0;}
            Log.i("precio", String.valueOf(precioe));
            precio_base = (precioe * cantidad);
            txt_precio_base.setText(String.valueOf(precio_base));
            if(dia.equalsIgnoreCase("lunes"))
            {
                descuento_dia = 1;
                txt_descuento_dia.setText(String.valueOf(descuento_dia));
            }
            if(cantidad >= 10)
            {
                descuento_grupal = precio_base * 0.10;
            }
            else if(cantidad >= 5)
            {
                descuento_grupal = precio_base * 0.05;
            }
            txt_descuento_grupal.setText(String.valueOf(descuento_grupal));
            double iva = precio_base * 0.21;
            txt_iva.setText(String.valueOf(iva));

            double total = precio_base + iva - descuento_dia -descuento_grupal;
            txt_total.setText(String.valueOf(total));
        }
        else
        {
            edt_tipoe.setError("elige un tipo de entrada correcto");
        }
    }
}