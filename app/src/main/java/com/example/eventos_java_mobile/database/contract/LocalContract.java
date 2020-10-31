package com.example.eventos_java_mobile.database.contract;

import com.example.eventos_java_mobile.database.entity.LocalEntity;

public class LocalContract {

    private LocalContract() {}

    public static final String createTable() {
        return "CREATE TABLE " + LocalEntity.TABLE_NAME + " (" +
                LocalEntity._ID + " INT PRIMARY KEY," +
                LocalEntity.TABLE_COLUMN_NAME_NOME + " TEXT," +
                LocalEntity.TABLE_COLUMN_NAME_BAIRRO + " TEXT," +
                LocalEntity.TABLE_COLUMN_NAME_CIDADE + " TEXT," +
                LocalEntity.TABLE_COLUMN_NAME_CAPACIDADE_PUBLICO + " INT)";
    }

    public static final String dropTable() {
        return "DROP TABLE IF EXISTS " + LocalEntity.TABLE_NAME;
    }
}
