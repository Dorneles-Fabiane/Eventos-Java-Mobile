package com.example.eventos_java_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.time.LocalDate;
import java.util.ArrayList;

import com.example.eventos_java_mobile.database.EventoDAO;
import com.example.eventos_java_mobile.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private int id = 0;

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
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.listar());
        listViewEventos.setAdapter(adapterEventos);

        definirOnClickListenerListView();

          listViewEventos.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
              @Override public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                  contextMenu.add(Menu.NONE, 1, Menu.NONE, "Deletar");
              }
          });
    }

      @Override
      public boolean onContextItemSelected(MenuItem item) {
          AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                  item.getMenuInfo();
          int posicaoClicada = menuInfo.position;

          switch (item.getItemId()) {
              case 1: {
                  removeEventoNa(posicaoClicada);
                  break;
              }
          }

          return super.onContextItemSelected(item);
      }

    private void removeEventoNa(int posicaoClicada) {
          Evento evento = adapterEventos.getItem(posicaoClicada);

          adapterEventos.remove(evento);
          adapterEventos.notifyDataSetChanged();
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