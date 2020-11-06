package com.example.eventos_java_mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.eventos_java_mobile.database.entity.EventoEntity;
import com.example.eventos_java_mobile.database.entity.LocalEntity;
import com.example.eventos_java_mobile.modelo.Evento;
import com.example.eventos_java_mobile.modelo.Local;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS_EVENTOS = "SELECT eventos._id, nome, data, idlocal, descricao, bairro, cidade, capacidadePublico FROM " + EventoEntity.TABLE_NAME +
            " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + EventoEntity.COLUMN_NAME_ID_LOCAL +
            " = " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID;
    private DBGateway dbGateway;

    public EventoDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getData());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_LOCAL, evento.getLocal().getId());

        if (evento.getId() > 0) {
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME, contentValues, EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME, null, contentValues) > 0;
    }

    public boolean excluirEvento(Evento evento) {
        ContentValues values = new ContentValues();
        values.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        values.put(EventoEntity.COLUMN_NAME_DATA, evento.getData());
        values.put(EventoEntity.COLUMN_NAME_ID_LOCAL, evento.getLocal().getId());

        return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME,
                EventoEntity._ID + "=?",
                new String[]{String.valueOf(evento.getId())}) == evento.getId();
    }

    public List<Evento> listar() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS_EVENTOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            int idLocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_LOCAL));
            String descricao = cursor.getString(cursor.getColumnIndex(LocalEntity.TABLE_COLUMN_NAME_DESCRICAO));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.TABLE_COLUMN_NAME_BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.TABLE_COLUMN_NAME_CIDADE));
            int capacidadePublico = cursor.getInt(cursor.getColumnIndex(LocalEntity.TABLE_COLUMN_NAME_CAPACIDADE_PUBLICO));

            Local local = new Local(idLocal, descricao, bairro, cidade, capacidadePublico);
            eventos.add(new Evento(id, nome, data, local));
        }
        cursor.close();
        return eventos;
    }
}
