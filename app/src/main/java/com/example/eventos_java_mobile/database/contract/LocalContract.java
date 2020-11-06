package com.example.eventos_java_mobile.database.contract;
import com.example.eventos_java_mobile.database.entity.LocalEntity;

public class LocalContract {

    private LocalContract() {}

    public static final String criarTabelaLocal() {
        return "CREATE TABLE " + LocalEntity.TABLE_NAME + " (" +
                LocalEntity._ID + " INTEGER PRIMARY KEY," +
                LocalEntity.TABLE_COLUMN_NAME_DESCRICAO + " TEXT," +
                LocalEntity.TABLE_COLUMN_NAME_BAIRRO + " TEXT," +
                LocalEntity.TABLE_COLUMN_NAME_CIDADE + " TEXT," +
                LocalEntity.TABLE_COLUMN_NAME_CAPACIDADE_PUBLICO + " INTEGER)";
    }

    public static final String removerTabelaLocal() {
        return "DROP TABLE IF EXISTS " + LocalEntity.TABLE_NAME;
    }
}
