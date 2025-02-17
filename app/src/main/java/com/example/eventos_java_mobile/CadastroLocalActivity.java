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
    private EditText editTextDescricao;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextCapacidadePublico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        setTitle("CADASTRO DE LOCAL");

        editTextDescricao = findViewById(R.id.editText_nomeL);
        editTextBairro = findViewById(R.id.editText_bairro);
        editTextCidade = findViewById(R.id.editText_cidade);
        editTextCapacidadePublico  = findViewById(R.id.editText_capacidadePublico);

        carregarLocal();
    }

    private void carregarLocal() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("localEdicao") != null) {
            Local local = (Local) intent.getExtras().get("localEdicao");

            editTextDescricao.setText(local.getDescricao());
            editTextBairro.setText(local.getBairro());
            editTextCidade.setText(local.getCidade());
            editTextCapacidadePublico.setText(String.valueOf(local.getCapacidadePublico()));
            id = local.getId();
        }
    }

    public void onClickExcluirLocal(View v) {

        String descricao = editTextDescricao.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        int capacidadePublico = Integer.parseInt(editTextCapacidadePublico.getText().toString());

        Local local = new Local(id, descricao, bairro, cidade, capacidadePublico);
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
        String descricao = editTextDescricao.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String capacidadeString = editTextCapacidadePublico.getText().toString();

        //VALIDAÇÃO DE CAMPOS LOCAL:

        if (!descricao.isEmpty() && !bairro.isEmpty() && !cidade.isEmpty() && !capacidadeString.isEmpty()) {
            int capacidade = Integer.valueOf(capacidadeString);
            Local local = new Local(id, descricao, bairro, cidade, capacidade);
            LocalDAO localDAO = new LocalDAO(getBaseContext());

            boolean salvou = localDAO.salvarLocal(local);

            if (salvou) {
                Toast.makeText(CadastroLocalActivity.this, "Local salvo com sucesso!", Toast.LENGTH_LONG).show();
                finish();

            } else {
                Toast.makeText(CadastroLocalActivity.this, "Erro ao salvar!", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(CadastroLocalActivity.this, "É preciso informar todos os campos!", Toast.LENGTH_LONG).show();
        }
    }
}