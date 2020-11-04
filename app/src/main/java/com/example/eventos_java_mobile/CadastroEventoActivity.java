package com.example.eventos_java_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.time.LocalDate;

import com.example.eventos_java_mobile.database.EventoDAO;
import com.example.eventos_java_mobile.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;
    private EditText editTextNome;
    private EditText editTextData;
    private EditText editTextLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("CADASTRO DE EVENTO");

        editTextNome = findViewById(R.id.editText_nome);
        editTextData = findViewById(R.id.editText_data);
        editTextLocal = findViewById(R.id.editText_local);

        carregarEvento();
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");

            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData());
            editTextLocal.setText(evento.getLocal());
            id = evento.getId();
        }
    }

    public void onClickExcluirEvento(View v) {
        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());

        boolean deletou = eventoDAO.excluirEvento(evento);
        if(deletou) {
            Toast.makeText(CadastroEventoActivity.this, "Evento excluido com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(CadastroEventoActivity.this, "Erro ao apagar", Toast.LENGTH_LONG).show();
        }
    }
    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        //VALIDAÇÃO DE CAMPOS EVENTO:

        if (nome.isEmpty() ) {
            editTextNome.setError("* É preciso informar o nome");
            editTextNome.requestFocus();
            return;
        }

        if (data.isEmpty() ) {
            editTextData.setError("* É preciso informar a data");
            editTextData.requestFocus();
            return;
        }

        if (local.isEmpty() ) {
            editTextLocal.setError("* É preciso informar o local");
            editTextLocal.requestFocus();
            return;
        }

        Evento evento = new Evento(id,nome, data, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean salvou = eventoDAO.salvar(evento);

        if (salvou) {
            Toast.makeText(CadastroEventoActivity.this, "Evento salvo com sucesso!", Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }

}
