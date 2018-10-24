package com.example.user.padel_pista;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView et1, et2,et3;
    private Button btnReserva, btnEntrada,btnSalida,btnFecha;
    private int dia,mes,anno,horas,minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(TextView)findViewById(R.id.et1);
        et2=(TextView)findViewById(R.id.et2);
        et3=(TextView)findViewById(R.id.et3);

        btnReserva=(Button) findViewById(R.id.btnReserva);
        btnEntrada=(Button) findViewById(R.id.btnEntrada);
        btnSalida=(Button) findViewById(R.id.btnSalida);
        btnFecha=(Button) findViewById(R.id.btnFecha);

        btnReserva.setOnClickListener(this);
        btnEntrada.setOnClickListener(this);
        btnSalida.setOnClickListener(this);
        btnFecha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        SimpleDateFormat sdf = new SimpleDateFormat("hh : mm");

        if(v==btnFecha){
            final Calendar c= Calendar.getInstance();
            dia= c.get(Calendar.DAY_OF_MONTH);
            mes= c.get(Calendar.MONTH);
            anno=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    et1.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },dia, mes, anno);
            datePickerDialog.show();
            datePickerDialog.updateDate(anno,mes,dia);

        }
        else if(v==btnEntrada){

            Calendar c= Calendar.getInstance();
            horas=c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int h, int m) {

                    et2.setText(h + " : " + m);
                }
            },horas,minutos,true);

            timePickerDialog.updateTime(horas,minutos);
            timePickerDialog.show();
        }
        else if(v==btnSalida) {
            Calendar c = Calendar.getInstance();
            horas = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int h, int m) {

                    et3.setText(h + " : " + m);
                }
            }, horas, minutos, true);
            timePickerDialog.updateTime(horas, minutos);
            timePickerDialog.show();

        }else if(v==btnReserva){

            Date horaEntrada= null;
            Date horaSalida=null;

            try {
                String hEntrada=(String) et2.getText();
                String hSalida=(String) et3.getText();

                horaEntrada = sdf.parse( hEntrada);
                horaSalida = sdf.parse( hSalida);

                if (horaEntrada.after(horaSalida)){
                    Toast.makeText(getApplicationContext(), "No se puede reservar una pista a esta hora", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Reservada el dia "+et1.getText() + ",Hora : "+et2.getText() + " Hora salida "+et3.getText() , Toast.LENGTH_LONG).show();
                }

            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(this, "Introduzca alguna fecha u Hora.", Toast.LENGTH_LONG).show();
            }

        }
    }
}