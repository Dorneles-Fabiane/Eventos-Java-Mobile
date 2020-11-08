package com.example.eventos_java_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import com.example.eventos_java_mobile.database.DBGateway;
import com.example.eventos_java_mobile.database.DatabaseDBHelper;
import com.example.eventos_java_mobile.database.EventoDAO;
import com.example.eventos_java_mobile.database.entity.EventoEntity;
import com.example.eventos_java_mobile.modelo.Evento;
import com.example.eventos_java_mobile.modelo.Local;

public class MainActivity extends AppCompatActivity {

    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private EventoDAO eventoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PRÓXIMOS EVENTOS");
        listViewEventos = findViewById(R.id.listView_eventos);
        definirOnClickListenerListView();
        eventoDAO = new EventoDAO(getBaseContext());
    }

    @Override protected void onResume() {
        super.onResume();

        eventoDAO = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.listar());
        listViewEventos.setAdapter(adapterEventos);

        definirOnClickListenerListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem pesquisarEvento = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) pesquisarEvento.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                return false;
            }
            //Verifica mudança de texto quando usuario pesquisa
            @Override
            public boolean onQueryTextChange(String pesquisa) {
                pesquisa = pesquisa.toLowerCase(); //Transforma a letra/palavra digitada em LC

                adapterEventos = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        eventoDAO.pesquisarEvento(pesquisa));
                listViewEventos.setAdapter(adapterEventos);

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //FUNÇÃO PARA O LISTVIEW DEFINIR ONCLICK
    private void definirOnClickListenerListView() {
        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }


    public void onClickNovoEvento(View v) {
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intent);
    }

    public void onClickLocais(View v) {
        Intent intent = new Intent(MainActivity.this, ListarLocaisActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickPesquisarCidade(View v) {
        EditText et_pesquisarCidade = findViewById(R.id.editText_pesquisaCidade);
        String cidade = et_pesquisarCidade.getText().toString().toLowerCase();

        adapterEventos = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.pesquisarCidade(cidade));
        listViewEventos.setAdapter(adapterEventos);
    }

}