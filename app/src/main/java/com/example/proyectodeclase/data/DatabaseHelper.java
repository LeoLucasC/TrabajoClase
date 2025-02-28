package com.example.proyectodeclase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "Lucasbd.db";

    // Versión de la base de datos (debes incrementarla si cambias el esquema)
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla y columnas
    private static final String TABLE_CONTACTOS = "Contactos";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NOMBRE = "NOMBRE";
    private static final String COLUMN_NUMERO = "NUMERO";

    // Sentencia SQL para crear la tabla
    private static final String CREATE_TABLE_CONTACTOS =
            "CREATE TABLE " + TABLE_CONTACTOS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOMBRE + " TEXT,"
                    + COLUMN_NUMERO + " TEXT"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla Contactos si no existe
        db.execSQL(CREATE_TABLE_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar la tabla si existe y crearla de nuevo
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTOS);
        onCreate(db);
    }

    // Método para verificar la conexión a la base de datos
    public boolean isDatabaseConnected() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db != null && db.isOpen();
    }
}