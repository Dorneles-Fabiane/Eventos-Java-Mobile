package com.example.eventos_java_mobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.eventos_java_mobile.database.contract.EventoContract;

//*************//
// Classe feita para ajudar a criar o Banco de Dados, fazer Updates e abrir as conexões com o banco para escritas e leituras.
//*************//

public class DatabaseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.evento";
    private static final int DATABASE_VERSION = 1; //Sempre que atualizar o banco, tem que mudar a versão.

    //Inicia o processo de conexão com o banco. Se não achar BD ele criará com o onCreate.
    public DatabaseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Se não existir BD, o onCreate irá criar.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EventoContract.criarTabela()); //Chamando o metodo de criação da tabela.
    }

    // Feito para atualizar o BD (Modificar dados da tabelas, inserir tabelas)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EventoContract.removerTabela());
        db.execSQL(EventoContract.criarTabela());
        //Ao fazer o Update é melhor chamar as funções de ALTER TABLE, a maneira atual remove toda a tabela.

    }
}
