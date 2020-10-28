package com.example.eventos_java_mobile.database.contract;

//*************//
//A classe EventoEntity poderia estar dentro do EventoContract.
//A classe EventoContract irá fazer o gerenciamento.
//*************//

import com.example.eventos_java_mobile.database.entity.EventoEntity;

public class EventoContract {

    public static final String criarTabela(){
        return "CREATE TABLE " + EventoEntity.TABLE_NAME + " (" +
                EventoEntity._ID + " INTEGER PRIMARY KEY," +
                EventoEntity.COLUMN_NAME_NOME + " TEXT," +
                EventoEntity.COLUMN_NAME_DATA + " TEXT," +
                EventoEntity.COLUMN_NAME_LOCAL + " TEXT)";

    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }
}
