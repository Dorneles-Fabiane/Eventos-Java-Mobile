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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("CADASTRO DE LOCAL");

        carregarLocal();
    }

    private void carregarLocal() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("localEdicao") != null) {
            Local local = (Local) intent.getExtras().get("localEdicao");

            EditText editTextNomeL = findViewById(R.id.editText_nomeL);
            EditText editTextBairro = findViewById(R.id.editText_bairro);
            EditText editTextCidade = findViewById(R.id.editText_cidade);
            EditText editTextCapacidadePublico  = findViewById(R.id.editText_capacidadePublico);

            editTextNomeL.setText(local.getNome());
            editTextBairro.setText(local.getBairro());
            editTextCidade.setText(local.getCidade());
            editTextCapacidadePublico.setText(String.valueOf(local.getCapacidadePublico()));
            id = local.getId();
        }
    }

    public void onClickExcluirLocal(View v) {
        EditText editTextNomeL = findViewById(R.id.editText_nomeL);
        EditText editTextBairro = findViewById(R.id.editText_bairro);
        EditText editTextCidade = findViewById(R.id.editText_cidade);
        EditText editTextCapacidadePublico = findViewById(R.id.editText_capacidadePublico);

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

        EditText editTextNomeL = findViewById(R.id.editText_nomeL);
        EditText editTextBairro = findViewById(R.id.editText_bairro);
        EditText editTextCidade = findViewById(R.id.editText_cidade);
        EditText editTextCapacidade = findViewById(R.id.editText_capacidadePublico);

        String nome = editTextNomeL.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        int capacidaPublico = Integer.parseInt(editTextCapacidade.getText().toString());


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

        if (capacidaPublico < 0 && capacidaPublico == 0){
            editTextCapacidade.setError("* É preciso informar uma capacidade valida");
            editTextCapacidade.requestFocus();
            return;
        }

        Local local = new Local(id, nome, bairro, cidade, capacidaPublico);
        LocalDAO localDAO = new LocalDAO(getBaseContext());

        boolean salvou = localDAO.salvarLocal(local);
        boolean deletou = localDAO.excluirLocal(local);

        if (salvou) {
            Toast.makeText(CadastroLocalActivity.this, "Local salvo com sucesso!", Toast.LENGTH_LONG).show();
            finish();

        } else if (deletou) {
            Toast.makeText(CadastroLocalActivity.this, "Local deletado com sucesso!", Toast.LENGTH_LONG).show();
            finish();

        }else {
            Toast.makeText(CadastroLocalActivity.this, "Erro ao salvar!", Toast.LENGTH_LONG).show();
        }

    }
}