package com.example.eventos_java_mobile.database.entity;

import android.provider.BaseColumns;
//*************//
//Classe que representa as colunas da tabela
//*************//

public class EventoEntity implements BaseColumns {

    private EventoEntity() {}

    public static final String TABLE_NAME = "eventos";
    public static final String COLUMN_NAME_NOME = "nome";
    public static final String COLUMN_NAME_DATA = "data";
    public static final String COLUMN_NAME_LOCAL = "local";


}
