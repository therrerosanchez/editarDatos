package org.therrero.editardatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvFechaNacimiento;
    private TextView tvEmail;
    private TextView tvTelefono;
    private TextView tvDescripcion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_datos);
        Bundle parametros = getIntent().getExtras();

        Contactos contacto = new Contactos(parametros.getString(getResources().getString(R.string.nombre)),parametros.getString(getResources().getString(R.string.fecha_de_nacimiento)),parametros.getString(getResources().getString(R.string.telefono)), parametros.getString(getResources().getString(R.string.email)), parametros.getString(getResources().getString(R.string.observaciones)));
        tvNombre = (TextView) findViewById(R.id.contenidoNombre);
        tvTelefono = (TextView) findViewById(R.id.contenidoTelefono);
        tvEmail = (TextView) findViewById(R.id.contenidoEmail);
        tvFechaNacimiento = (TextView) findViewById(R.id.contenidoFechaNacimiento);
        tvDescripcion  = (TextView) findViewById(R.id.contenidoDescripcion);

        tvNombre.setText(contacto.getNombre());
        tvTelefono.setText(contacto.getTelefono());
        tvEmail.setText(contacto.getEmail());
        tvDescripcion.setText(contacto.getDescripcion());
        tvFechaNacimiento.setText(contacto.getFechaNacimiento());

        Button editar = (Button) findViewById(R.id.editarDatos);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmarDatos.this, MainActivity.class);
                intent.putExtra(getResources().getString(R.string.nombre),tvNombre.getText());
                intent.putExtra(getResources().getString(R.string.fecha_de_nacimiento),tvFechaNacimiento.getText());
                intent.putExtra(getResources().getString(R.string.telefono), tvTelefono.getText());
                intent.putExtra(getResources().getString(R.string.email),tvEmail.getText());
                intent.putExtra(getResources().getString(R.string.observaciones), tvDescripcion.getText());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(ConfirmarDatos.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
