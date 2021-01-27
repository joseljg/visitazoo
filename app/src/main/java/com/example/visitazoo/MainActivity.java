package com.example.visitazoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_DIA = "com.example.visitazoo.dia";
    public static final String EXTRA_CANTIDAD = "com.example.visitazoo.cantidad";
    int cantidad_entradasd = 15;
    TextView txt_entradasd;
    EditText edt_cantidade;
    EditText edt_dia_semana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_entradasd = (TextView) findViewById(R.id.txt_cantidad_entradasd);
        txt_entradasd.setText(String.valueOf(cantidad_entradasd));
        edt_cantidade = (EditText) findViewById(R.id.edt_cantidade);
        edt_dia_semana = (EditText) findViewById(R.id.edt_dia_semana);
    }

    public boolean validar_disponibilidad_entradas(int cantidad)
    {
        if(cantidad <= 0) { return false; }
        else if(cantidad <= cantidad_entradasd) { return true; }
        else{ return false;}
    }

    public boolean validar_dia_semana(String dia)
    {
        dia = dia.toLowerCase();
        if(       dia.equalsIgnoreCase("lunes")
                ||dia.equalsIgnoreCase("martes")
                ||dia.equalsIgnoreCase("miercoles")
                ||dia.equalsIgnoreCase("jueves")
                ||dia.equalsIgnoreCase("viernes")
                ||dia.equalsIgnoreCase("sabado")
                ||dia.equalsIgnoreCase("domingo")) { return true; }
        else{ return false;}
    }


    public void siguiente(View view) {
        int cantidad = 0;
        boolean error = false;
        try {
            cantidad = Integer.valueOf(String.valueOf(edt_cantidade.getText()));
        }
        catch(Exception e){
            edt_cantidade.setError("debes introducir un numero");
            error = true;
        }
        if(!validar_disponibilidad_entradas(cantidad))
        {
            edt_cantidade.setError("No hay suficientes entradas disponibles");
            error = true;
        }
        String dia = String.valueOf(edt_dia_semana.getText());
        if(!validar_dia_semana(dia))
        {
            edt_dia_semana.setError("introduce un día de la semana correcto");
            error = true;
        }
        if(error == false) {
            cantidad_entradasd = cantidad_entradasd - cantidad;
            txt_entradasd.setText(String.valueOf(cantidad_entradasd));
            Intent intent = new Intent(this, VentaEntrada2Activity.class);
            intent.putExtra(EXTRA_DIA, dia);
            intent.putExtra(EXTRA_CANTIDAD, cantidad);
            startActivity(intent);
        }
    }
}