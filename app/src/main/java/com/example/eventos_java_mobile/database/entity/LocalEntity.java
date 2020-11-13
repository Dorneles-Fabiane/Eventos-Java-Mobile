package com.example.eventos_java_mobile.database.entity;

import android.provider.BaseColumns;

public final class LocalEntity implements BaseColumns {

    private LocalEntity() {}

    public static final String TABLE_NAME = "local";
    public static final String COLUMN_NAME_DESCRICAO = "descricao";
    public static final String COLUMN_NAME_BAIRRO = "bairro";
    public static final String COLUMN_NAME_CIDADE = "cidade";
    public static final String COLUMN_NAME_CAPACIDADE_PUBLICO = "capacidadePublico";

}
