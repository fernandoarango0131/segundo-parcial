package com.edu.uac.co.parcial_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserController {

    private BdUsuario db;

    public UserController(Context context) {
        this.db = new BdUsuario(context);
    }

    public void registerUser(Usuario usuario) {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(bdDef.ID_COL,usuario.documento );
            values.put(bdDef.NAME_COL, usuario.nombre);
            values.put(bdDef.STRATUM_COL, usuario.estrato);
            values.put(bdDef.WAGE_COL, usuario.salario);
            values.put(bdDef.EDUCATION_LEVEL_COL, usuario.nivelEducativo);

            database.insert(bdDef.TABLE_NAME, null, values);

        } catch (Exception e) {
            System.out.println("Insertar Error");
        }
    }

    public boolean findUser(String uId) {
        SQLiteDatabase database = db.getReadableDatabase();
        String[] args = {uId};

        Cursor c = database.query(bdDef.TABLE_NAME, null, "DOCUMENTO=?", args, null, null, null);

        if (c.getCount() > 0) {
            database.close();
            return true;
        } else {
            database.close();
            return false;
        }
    }

    public void updateUser(Usuario usuario) {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            String[] args = {usuario.documento};

            values.put(bdDef.ID_COL, usuario.documento);
            values.put(bdDef.NAME_COL, usuario.nombre);
            values.put(bdDef.STRATUM_COL, usuario.estrato);
            values.put(bdDef.WAGE_COL, usuario.salario);
            values.put(bdDef.EDUCATION_LEVEL_COL, usuario.nivelEducativo);

            database.update(bdDef.TABLE_NAME, values, "ID=?", args);
            database.close();

        } catch (Exception e) {
            System.out.println("Actualizar Error");
        }
    }

    public void deleteUser(Usuario usuario) {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            String[] args = {usuario.documento};
            database.delete(bdDef.TABLE_NAME, "ID=?", args);

        } catch (Exception e) {
            System.out.println("Eliminar Error");
        }
    }

    public Cursor allUsers() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor c = database.rawQuery("select DOCUMENTO as _id , NOMBRE, ESTRATO, SALARIO, NIVEL_EDUCACION from USUARIOS", null);
            return c;
        } catch (final Exception e) {
            System.out.println("Consulta Error");
            return null;
        }
    }
}
