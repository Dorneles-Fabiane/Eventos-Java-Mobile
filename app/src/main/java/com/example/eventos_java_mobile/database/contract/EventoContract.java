package com.example.eventos_java_mobile.database.contract;
import com.example.eventos_java_mobile.database.entity.EventoEntity;

public class EventoContract {

    public static final String criarTabelaEvento(){
        return "CREATE TABLE " + EventoEntity.TABLE_NAME + " (" +
                EventoEntity._ID + " INTEGER PRIMARY KEY," +
                EventoEntity.COLUMN_NAME_NOME + " TEXT," +
                EventoEntity.COLUMN_NAME_DATA + " TEXT," +
                EventoEntity.COLUMN_NAME_LOCAL + " TEXT)";

    }

    public static final String removerTabelaEvento() {
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }
}
