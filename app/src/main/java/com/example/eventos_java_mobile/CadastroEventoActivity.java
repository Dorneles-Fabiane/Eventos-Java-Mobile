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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("CADASTRO DE EVENTO");
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
            id = evento.getId();
        }
    }

    public void onClickExcluirEvento(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

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
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        //VALIDAÇÃO DE CAMPOS EVENTO:


        Evento evento = new Evento(id,nome, data, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean salvou = eventoDAO.salvar(evento);
        boolean deletou = eventoDAO.excluirEvento(evento);

        if (salvou) {
            Toast.makeText(CadastroEventoActivity.this, "Evento salvo com sucesso!", Toast.LENGTH_LONG).show();
            finish();

        } else if (deletou) {
            Toast.makeText(CadastroEventoActivity.this, "Evento deletado com sucesso!", Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }

}
