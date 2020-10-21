package com.example.eventos_java_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.time.LocalDate;
import com.example.eventos_java_mobile.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;

    private boolean edicao = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");

        carregarEvento();
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextData = findViewById(R.id.editText_data);
            EditText editTextLocal = findViewById(R.id.editText_local);

            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData());
            editTextLocal.setText(evento.getLocal());
            edicao = true;
            id = evento.getId();
        }

    }

    /*private void validaCampos() { // PODEMOS CRIAR UM VALIDA CAMPOS, QUEM SABE SEJA MELHOR.
    }*/

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v){

        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);


        String nome = editTextNome.getText().toString();

        /*if (nome.isEmpty() ){ // Identifica que o capo esta vazio, porém temos que colocar essa informação no PLAINTEXT do cadastro.
            Toast.makeText(CadastroEventoActivity.this, "É preciso informar o nome", Toast.LENGTH_LONG).show();
        }*/

        String data = editTextData.getText().toString(); // LocalDate data = LocalDate.parse(editTextData.getText().toString());

        /*if (data.isEmpty() ){
            Toast.makeText(CadastroEventoActivity.this, "É preciso informar a data", Toast.LENGTH_LONG).show();
        }*/

        String local = editTextLocal.getText().toString();

        /*if (local.isEmpty() ){
            Toast.makeText(CadastroEventoActivity.this, "É preciso informar o local", Toast.LENGTH_LONG).show();
        }*/

        Evento evento = new Evento(id, nome, data, local);
        Intent intent = new Intent();

        if (edicao) {
            intent.putExtra("eventoEditado", evento);
            setResult(RESULT_CODE_EVENTO_EDITADO, intent);

        } else {
            intent.putExtra("novoEvento", evento);
            setResult(RESULT_CODE_NOVO_EVENTO, intent);
        }


        finish();
    }
}