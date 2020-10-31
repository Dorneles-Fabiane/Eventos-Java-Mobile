package com.example.eventos_java_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventos_java_mobile.database.LocalDAO;
import com.example.eventos_java_mobile.modelo.Local;

public class CadastroLocalActivity extends AppCompatActivity {

    private int id = 0;
    private EditText et_nome;
    private EditText et_bairro;
    private EditText et_cidade;
    private EditText et_capacidadePublico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("Cadastro de Local");

        et_nome = findViewById(R.id.editText_nomeL);
        et_bairro = findViewById(R.id.editText_bairro);
        et_cidade = findViewById(R.id.editText_cidade);
        et_capacidadePublico = findViewById(R.id.editText_capacidadePublico);

        carregarLocal();
    }

    private void carregarLocal() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null
            && intent.getExtras().get("localEdicao") != null) {
            Local local = (Local) intent.getExtras().get("localEdicao");
            et_nome.setText(local.getNome());
            et_bairro.setText(local.getBairro());
            et_cidade.setText(local.getCidade());
            et_capacidadePublico.setText(local.getCapacidadePublico());
            id = local.getId();
        }
    }

    public void onClickVoltarLocal(View v) {
        finish();
    }

    public void onClickSalvarLocal(View v) {
        String nome = et_nome.getText().toString();
        String bairro = et_bairro.getText().toString();
        String cidade = et_cidade.getText().toString();
        int capacidaPublico = Integer.parseInt(et_capacidadePublico.getText().toString());

        Local local = new Local(id, nome, bairro, cidade, capacidaPublico);
        LocalDAO localDAO = new LocalDAO(getBaseContext());

        boolean salvou = localDAO.salvarLocal(local);

        if (salvou) {
            Toast.makeText(CadastroLocalActivity.this, "Salvou com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(CadastroLocalActivity.this, "Erro ao salvar!", Toast.LENGTH_LONG).show();
        }

    }
}