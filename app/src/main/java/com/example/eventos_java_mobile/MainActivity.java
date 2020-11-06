package com.example.eventos_java_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

import com.example.eventos_java_mobile.database.DBGateway;
import com.example.eventos_java_mobile.database.DatabaseDBHelper;
import com.example.eventos_java_mobile.database.EventoDAO;
import com.example.eventos_java_mobile.database.entity.EventoEntity;
import com.example.eventos_java_mobile.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PRÓXIMOS EVENTOS");
        listViewEventos = findViewById(R.id.listView_eventos);
        definirOnClickListenerListView();
    }

    @Override protected void onResume() {
        super.onResume();

        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.listar());
        listViewEventos.setAdapter(adapterEventos);

        definirOnClickListenerListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<Evento> novaLista = new ArrayList<>();
                List<Evento> novaLista2 = eventoDAO.listar();
                for (Evento evento : novaLista2) {
                    String nome = evento.getNome().toLowerCase();
                    if(nome.contains(newText)){
                        novaLista.add(evento);
                    }
                }
                adapterEventos = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        novaLista);
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



}