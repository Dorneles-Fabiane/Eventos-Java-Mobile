package com.example.eventos_java_mobile.database;

//*************//
//DBGateway padrão de projeto
//*************//

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    //Padrão de projeto Singleton
    private static DBGateway dbGateway;

    private SQLiteDatabase db; //Conexão aberta com o banco. Remover e inserir dados.

    //Vai verificar se já existe uma instância dbGateway criada
    public static DBGateway getInstance(Context context) {
        if (dbGateway == null){
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    //Construtor
    private DBGateway (Context context) {
        DatabaseDBHelper dbHelper = new DatabaseDBHelper(context);
        db = dbHelper.getWritableDatabase(); // Conexão para escrita e leitura de dados.
    }

    //Retorno
    public SQLiteDatabase getDatabase(){
        return db;
    }
}
