package com.mariaeduarda.meucopo.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MeuCopoDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "MeuCopo_db";
    private static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;

    public MeuCopoDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTableRegistro = "CREATE TABLE IF NOT EXISTS Registro (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "data DATE, " +
                "totalMl INTEGER)";
        sqLiteDatabase.execSQL(sqlTableRegistro);

        String sqlTableCopo = "CREATE TABLE IF NOT EXISTS Copo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "meta INTEGER, " +
                "tamanhoCopo INTEGER, " +
                "consumo INTEGER)";
        sqLiteDatabase.execSQL(sqlTableCopo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    public void salvar(String table, ContentValues dados) {
        db.insert(table, null, dados);
    }
    public void atualizar(String tabela, ContentValues valores, String whereClause, String[] whereArgs) {
        db.update(tabela, valores, whereClause, whereArgs);
    }

    public List<CopoModel> listaDados() {
        List<CopoModel> lista = new ArrayList<>();

        String querySQL = "SELECT * FROM Copo";
        cursor = db.rawQuery(querySQL, null);

        if (cursor.moveToFirst()) {
            do {
                CopoModel registro = new CopoModel(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3)
                );
                lista.add(registro);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }

}
