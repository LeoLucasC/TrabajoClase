package com.example.proyectodeclase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyectodeclase.data.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Inicializar DatabaseHelper
        dbHelper = new DatabaseHelper(this);


        // Configurar el botón
        Button btnComienza = findViewById(R.id.btnComienza);
        btnComienza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar la conexión a la base de datos
                if (dbHelper.isDatabaseConnected()) {
                    // Mostrar Snackbar con el mensaje "Conexión exitosa"
                    Snackbar.make(v, "Conexión exitosa", Snackbar.LENGTH_SHORT).show();

                    // Navegar a la actividad Menu
                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    startActivity(intent);
                } else {
                    // Mostrar Snackbar con el mensaje "Error de conexión"
                    Snackbar.make(v, "Error de conexión a la base de datos", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();  // Cerrar la conexión a la base de datos
        super.onDestroy();
    }
}