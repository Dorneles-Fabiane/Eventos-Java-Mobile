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
    private EditText editTextNomeL;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextCapacidadePublico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("CADASTRO DE LOCAL");

        editTextNomeL = findViewById(R.id.editText_nomeL);
        editTextBairro = findViewById(R.id.editText_bairro);
        editTextCidade = findViewById(R.id.editText_cidade);
        editTextCapacidadePublico  = findViewById(R.id.editText_capacidadePublico);

        carregarLocal();
    }

    private void carregarLocal() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("localEdicao") != null) {
            Local local = (Local) intent.getExtras().get("localEdicao");

            editTextNomeL.setText(local.getNome());
            editTextBairro.setText(local.getBairro());
            editTextCidade.setText(local.getCidade());
            editTextCapacidadePublico.setText(String.valueOf(local.getCapacidadePublico()));
            id = local.getId();
        }
    }

    public void onClickExcluirLocal(View v) {

        String nome = editTextNomeL.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        int capacidadePublico = Integer.parseInt(editTextCapacidadePublico.getText().toString());

        Local local = new Local(id, nome, bairro, cidade, capacidadePublico);
        LocalDAO localDAO = new LocalDAO(getBaseContext());

        boolean deletou = localDAO.excluirLocal(local);
        if(deletou) {
            Toast.makeText(CadastroLocalActivity.this, "Local excluido com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(CadastroLocalActivity.this, "Erro ao apagar", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickVoltarLocal(View v) {
        finish();
    }

    public void onClickSalvarLocal(View v) {
        String nome = editTextNomeL.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        int capacidaPublico = Integer.parseInt(editTextCapacidadePublico.getText().toString());


        // VALIDAÇÃO DE CAMPOS LOCAL:

        if (nome.isEmpty()){
            editTextNomeL.setError("* É preciso informar o nome");
            editTextNomeL.requestFocus();
            return;
        }

        if (bairro.isEmpty()){
            editTextBairro.setError("* É preciso informar um Bairro");
            editTextBairro.requestFocus();
            return;
        }

        if (cidade.isEmpty()){
            editTextCidade.setError("* É preciso informar uma Cidade");
            editTextCidade.requestFocus();
            return;
        }

        if (capacidaPublico < 0 || capacidaPublico == 0){
            editTextCapacidadePublico.setError("* É preciso informar uma capacidade valida");
            editTextCapacidadePublico.requestFocus();
            return;
        }

        Local local = new Local(id, nome, bairro, cidade, capacidaPublico);
        LocalDAO localDAO = new LocalDAO(getBaseContext());

        boolean salvou = localDAO.salvarLocal(local);

        if (salvou) {
            Toast.makeText(CadastroLocalActivity.this, "Local salvo com sucesso!", Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(CadastroLocalActivity.this, "Erro ao salvar!", Toast.LENGTH_LONG).show();
        }

    }
}