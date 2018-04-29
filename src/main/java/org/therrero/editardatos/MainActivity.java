package org.therrero.editardatos;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Calendar CalendarFechaNacimiento = Calendar.getInstance();
    private EditText campoNombre;
    private EditText campoTelefono;
    private EditText campoCorreo;
    private EditText campoDescripcion;
    private EditText campoFechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle parametros = getIntent().getExtras();

        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoFechaNacimiento = (EditText)findViewById(R.id.campo_fecha);
        campoTelefono = (EditText)findViewById(R.id.campo_telefono);
        campoCorreo = (EditText) findViewById(R.id.campo_correo);
        campoDescripcion = (EditText) findViewById(R.id.campo_descripcion);

        if (parametros!= null ){
            Contactos contacto = new Contactos(parametros.getString(getResources().getString(R.string.nombre)), parametros.getString(getResources().getString(R.string.fecha_de_nacimiento)), parametros.getString(getResources().getString(R.string.telefono)), parametros.getString(getResources().getString(R.string.email)), parametros.getString(getResources().getString(R.string.observaciones)));
            campoNombre.setText(contacto.getNombre());
            campoFechaNacimiento.setText(contacto.getFechaNacimiento());
            campoCorreo.setText(contacto.getEmail());
            campoDescripcion.setText(contacto.getDescripcion());
            campoTelefono.setText(contacto.getTelefono());
        }
        Button aceptar = (Button) findViewById(R.id.boton_aceptar);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                CalendarFechaNacimiento.set(Calendar.YEAR, year);
                CalendarFechaNacimiento.set(Calendar.MONTH, monthOfYear);
                CalendarFechaNacimiento.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        campoFechaNacimiento.setOnClickListener(new View.OnClickListener( ) {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, CalendarFechaNacimiento
                        .get(Calendar.YEAR), CalendarFechaNacimiento.get(Calendar.MONTH),
                        CalendarFechaNacimiento.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contactos contacto = new Contactos(campoNombre.getText().toString(),campoFechaNacimiento.getText().toString(),campoTelefono.getText().toString(), campoDescripcion.getText().toString(),campoCorreo.getText().toString());
                Intent intent = new Intent(MainActivity.this, ConfirmarDatos.class);
                intent.putExtra(getResources().getString(R.string.nombre),contacto.getNombre());
                intent.putExtra(getResources().getString(R.string.fecha_de_nacimiento),contacto.getFechaNacimiento());
                intent.putExtra(getResources().getString(R.string.telefono), contacto.getTelefono());
                intent.putExtra(getResources().getString(R.string.email),contacto.getEmail());
                intent.putExtra(getResources().getString(R.string.observaciones), contacto.getDescripcion());
                startActivity(intent);
                finish();
            }
        });

    }
    private void updateLabel() {
        String Formato = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(Formato);

        campoFechaNacimiento.setText(sdf.format(CalendarFechaNacimiento.getTime()));
    }
}
