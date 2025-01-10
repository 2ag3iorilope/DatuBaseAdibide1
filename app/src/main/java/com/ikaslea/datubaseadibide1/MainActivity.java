package com.ikaslea.datubaseadibide1;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    EditText kodeatxt;
    EditText izenatxt;
    Button ezabatubtn;
    Button eguenratubutton;
    Button insertarbtn;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kodeatxt = findViewById(R.id.editTextText);
        izenatxt = findViewById(R.id.editTextText2);
        ezabatubtn = findViewById(R.id.button3);
        insertarbtn = findViewById(R.id.button);
        eguenratubutton = findViewById(R.id.button2);

        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1); // Versión 1
        db = usdbh.getWritableDatabase();


        if (db != null) {

            for (int i = 1; i <= 5; i++) {

                int codigo = i;
                String nombre = "Usuario" + i;

                db.execSQL("INSERT INTO Usuarios (codigo, nombre) " +
                        "VALUES (" + codigo + ", '" + nombre + "')");
            }


            insertarbtn.setOnClickListener(v -> {
                String codigo = kodeatxt.getText().toString();
                String nombre = izenatxt.getText().toString();
                if (!codigo.isEmpty() && !nombre.isEmpty()) {
                    db.execSQL("INSERT INTO Usuarios (codigo, nombre) VALUES (" +
                            codigo + ", '" + nombre + "')");
                }
            });


            eguenratubutton.setOnClickListener(v -> {
                String codigo = kodeatxt.getText().toString();
                String nombre = izenatxt.getText().toString();
                if (!codigo.isEmpty() && !nombre.isEmpty()) {
                    db.execSQL("UPDATE Usuarios SET nombre = '" + nombre + "' WHERE codigo = " + codigo);
                }
            });


            ezabatubtn.setOnClickListener(v -> {
                String codigo = kodeatxt.getText().toString();
                if (!codigo.isEmpty()) {
                    db.execSQL("DELETE FROM Usuarios WHERE codigo = " + codigo);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}