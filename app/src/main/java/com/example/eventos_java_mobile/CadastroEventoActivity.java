package com.example.eventos_java_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.time.LocalDate;

import com.example.eventos_java_mobile.database.EventoDAO;
import com.example.eventos_java_mobile.database.LocalDAO;
import com.example.eventos_java_mobile.modelo.Evento;
import com.example.eventos_java_mobile.modelo.Local;

public class CadastroEventoActivity extends AppCompatActivity {

    private int id = 0;
    private EditText editTextNome;
    private EditText editTextData;
    private Spinner spinnerLocais;
    private ArrayAdapter<Local> locaisAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("CADASTRO DE EVENTO");
        spinnerLocais = findViewById(R.id.spinner_locais);
        editTextNome = findViewById(R.id.editText_nome);
        editTextData = findViewById(R.id.editText_data);

        carregarLocais();
        carregarEvento();
    }

    private void carregarLocais(){
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        locaisAdapter = new ArrayAdapter<Local>(CadastroEventoActivity.this,
                android.R.layout.simple_spinner_item,
                localDAO.listarLocais());
        spinnerLocais.setAdapter(locaisAdapter);
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");

            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData());
            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocais.setSelection(posicaoLocal);
            id = evento.getId();
        }
    }

    //Método de Posição
    private int obterPosicaoLocal(Local local) {
        for (int posicao = 0; posicao < locaisAdapter.getCount(); posicao++) {
            if (locaisAdapter.getItem(posicao).getId() == local.getId()) {
                return posicao;
            }
        }
        return 0;
    }

    public void onClickExcluirEvento(View v) {
        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        Local local = (Local) spinnerLocais.getSelectedItem();

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

        Local local = (Local) spinnerLocais.getSelectedItem();

        //VALIDAÇÃO DE CAMPOS EVENTO:

        if (!nome.isEmpty() && !data.isEmpty() && local != null) {
            Evento evento = new Evento(id, nome, data, local);
            EventoDAO eventoDAO = new EventoDAO(getBaseContext());
            boolean salvou = eventoDAO.salvar(evento);

            if (salvou) {
                Toast.makeText(CadastroEventoActivity.this, "Evento salvo com sucesso!", Toast.LENGTH_LONG).show();
                finish();

            } else {
                Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(CadastroEventoActivity.this, "É preciso informar todos os campos!", Toast.LENGTH_LONG).show();
        }
    }
}
