package com.edu.uac.co.parcial_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button registrar, cancelar;
    private EditText doc, nombre, salario;
    private Spinner estratos, nivEdus;

    private Usuario usuario;
    private UserController usuarioC;

    ArrayAdapter<CharSequence> adaptadorEstratos, adaptadorNivEdus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrar = findViewById(R.id.aceptarReg);
        cancelar = findViewById(R.id.cancelar);
        doc = findViewById(R.id.doc);
        nombre = findViewById(R.id.nombre);
        estratos = findViewById(R.id.estrato);
        salario = findViewById(R.id.salario);
        nivEdus = findViewById(R.id.nivelEdu);

        usuarioC = new UserController(getApplicationContext());

        adaptadorEstratos = ArrayAdapter.createFromResource(this, R.array.estratos, android.R.layout.simple_spinner_dropdown_item);
        adaptadorNivEdus = ArrayAdapter.createFromResource(this, R.array.niveles, android.R.layout.simple_spinner_dropdown_item);

        adaptadorEstratos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptadorNivEdus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        estratos.setAdapter(adaptadorEstratos);
        nivEdus.setAdapter(adaptadorNivEdus);

        estratos.setOnItemSelectedListener(this);
        nivEdus.setOnItemSelectedListener(this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario(doc.getText().toString(),
                        nombre.getText().toString(),
                        estratos.getSelectedItem().toString(),
                        salario.getText().toString(),
                        nivEdus.getSelectedItem().toString()
                );

                Toast.makeText(getApplicationContext(), usuario.nombre, Toast.LENGTH_SHORT).show();

                if (!usuarioC.findUser(usuario.documento)) {
                    Log.d("Buscar", "Usuario no encontrado");
                    Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    usuarioC.registerUser(usuario);
                    clearForm();
                } else {
                    Log.d("Buscar", "Usuario Encontrado");
                    Toast.makeText(getApplicationContext(), "Usuario Previamente Registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(i);
                clearForm();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String txtEstrato, txtNivEdu;

        switch (parent.getId()) {
            case R.id.estrato:
                txtEstrato = parent.getItemAtPosition(position).toString();
                Log.i("Estrato", ""+txtEstrato);
                break;

            case R.id.nivelEdu:
                txtNivEdu = parent.getItemAtPosition(position).toString();
                Log.i("Nivel Educativo", ""+txtNivEdu);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void clearForm() {

        if (doc.length() > 0) {
            doc.setText(null);
        }
        if (nombre.length() > 0) {
            nombre.setText(null);
        }
        estratos.setSelection(0);

        if (salario.length() > 0) {
            salario.setText(null);
        }

        nivEdus.setSelection(0);
    }
}