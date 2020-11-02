package com.example.eventos_java_mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.eventos_java_mobile.database.entity.EventoEntity;
import com.example.eventos_java_mobile.database.entity.LocalEntity;
import com.example.eventos_java_mobile.modelo.Evento;
import com.example.eventos_java_mobile.modelo.Local;

import java.util.ArrayList;
import java.util.List;

public class LocalDAO {

    private final String SQL_LISTAR_TODOS_LOCAIS = "SELECT * FROM " + LocalEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public LocalDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvarLocal(Local local) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.TABLE_COLUMN_NAME_NOME, local.getNome());
        contentValues.put(LocalEntity.TABLE_COLUMN_NAME_BAIRRO, local.getBairro());
        contentValues.put(LocalEntity.TABLE_COLUMN_NAME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.TABLE_COLUMN_NAME_CAPACIDADE_PUBLICO, local.getCapacidadePublico());
        if (local.getId() > 0 ){
            return dbGateway.getDatabase().update(LocalEntity.TABLE_NAME,
                    contentValues,
                    LocalEntity._ID + "=?",
                    new String[]{String.valueOf(local.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(LocalEntity.TABLE_NAME,
                null, contentValues) > 0;
    }

    public boolean excluirLocal(Local local) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.TABLE_COLUMN_NAME_NOME, local.getNome());
        contentValues.put(LocalEntity.TABLE_COLUMN_NAME_BAIRRO, local.getBairro());
        contentValues.put(LocalEntity.TABLE_COLUMN_NAME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.TABLE_COLUMN_NAME_CAPACIDADE_PUBLICO, local.getCapacidadePublico());
        return dbGateway.getDatabase().delete(LocalEntity.TABLE_NAME,
                LocalEntity._ID + "=?",
                new String[]{String.valueOf(local.getId())}) == local.getId();
    }

    public List<Local> listarLocais () {
        List<Local> locais = new ArrayList();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS_LOCAIS, null);
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
