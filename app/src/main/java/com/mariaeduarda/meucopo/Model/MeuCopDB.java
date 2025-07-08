package com.mariaeduarda.meucopo.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MeuCopDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "MeuCopo_db";
    private static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;

    public MeuCopDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlTableRegistro = "CREATE TABLE IF NOT EXISTS Registro (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "data  DATE, " +
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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void salvar(String table, ContentValues dados){db.insert(table,null, dados);}

    public List<CopoModel> listaDados(){
        List<CopoModel> lista = new ArrayList<>();

        CopoModel registro;
        String querySQL = "SELECT * FROM CopoModel";
        cursor = db.rawQuery(querySQL, null);

        if (cursor.moveToFirst()){
            do{
                registro = new CopoModel(this);
                registro.setId(cursor.getInt(0));
                registro.setMeta(cursor.getInt(1));
                registro.setTamanhoCopo(cursor.getInt(2));
                registro.setConsumo(cursor.getInt(3));
                lista.add(registro);
            }
            while (cursor.moveToFirst());

        }else {

        }
        return lista;
    }

}
