package com.example.eventos_java_mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.eventos_java_mobile.database.entity.LocalEntity;
import com.example.eventos_java_mobile.modelo.Local;

import java.util.ArrayList;
import java.util.List;

public class LocalDAO {

    private final static String LISTAR_TODOS = "SELECT * FROM " + LocalEntity.TABLE_NAME;
    private DBGateway dbgateway;

    public LocalDAO (Context context) { dbgateway = DBGateway.getInstance(context); }

    public boolean salvarLocal (Local local) {
        ContentValues values = new ContentValues();
        values.put(LocalEntity.TABLE_COLUMN_NAME_NOME, local.getNome());
        values.put(LocalEntity.TABLE_COLUMN_NAME_BAIRRO, local.getBairro());
        values.put(LocalEntity.TABLE_COLUMN_NAME_CIDADE, local.getCidade());
        values.put(LocalEntity.TABLE_COLUMN_NAME_CAPACIDADE_PUBLICO, local.getCapacidadePublico());

        if (local.getId() > 0) {
            return dbgateway.getDatabase().update(LocalEntity.TABLE_NAME,
                    values,
                    LocalEntity._ID + "=?",
                    new String[]{String.valueOf(local.getId())}) > 0;
        }
        return dbgateway.getDatabase().insert(LocalEntity.TABLE_NAME,
                null, values) > 0;
    }

    public List<Local> listarLocais () {
        List<Local> locais = new ArrayList();
        Cursor cursor = dbgateway.getDatabase().rawQuery(LISTAR_TODOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(LocalEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(LocalEntity.TABLE_COLUMN_NAME_NOME));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.TABLE_COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.TABLE_COLUMN_NAME_CIDADE));
            int capacidadePublico = cursor.getInt(cursor.getColumnIndex(LocalEntity.TABLE_COLUMN_NAME_CAPACIDADE_PUBLICO));
            locais.add(new Local(id, nome, bairro, cidade, capacidadePublico));
        }
        cursor.close();
        return locais;
    }
}
