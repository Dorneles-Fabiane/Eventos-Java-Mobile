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
import com.example.eventos_java_mobile.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    //FINAL não permite alterar a variável (CONSTANTE)
    private final int REQUEST_CODE_NOVO_EVENTO = 1;
    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int REQUEST_CODE_EDITAR_EVENTO = 2;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;

    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Cadastro de Eventos");

        listViewEventos = findViewById(R.id.listView_eventos);
        ArrayList<Evento> eventos = this.criarListaEventos();

        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_list_item_1, eventos);
        listViewEventos.setAdapter(adapterEventos);

        definirOnClickListenerListView();

    }

    @Override protected void onResume() {
        super.onResume();
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
                startActivityForResult(intent, REQUEST_CODE_EDITAR_EVENTO);

            }
        });
    }

    private ArrayList<Evento> criarListaEventos() {
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        //Não coloquei o "LocalDate.of" porque o aplicativo cai se aplico esse formato. Ao invés coloquei a DATA como STRING.
        eventos.add(new Evento("Reunião 01", /*LocalDate.of(2020, 8, 10),*/ "20/08/2020", "Florianópolis"));
        eventos.add(new Evento("Reuniao 02", /*LocalDate.of(2020, 5, 20),*/  "15/10/2020", "Curitiba"));
        eventos.add(new Evento("Reunião 03", /*LocalDate.of(2020, 3, 25), */ "25/05/2020", "São Paulo"));
        return eventos;
    }

    public void onClickNovoEvento(View v) {
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivityForResult(intent, REQUEST_CODE_NOVO_EVENTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_NOVO_EVENTO && resultCode == RESULT_CODE_NOVO_EVENTO) {
            Evento evento = (Evento) data.getExtras().getSerializable("novoEvento");
            evento.setId(++id);
            this.adapterEventos.add(evento);
        } else if (requestCode == REQUEST_CODE_EDITAR_EVENTO && resultCode == RESULT_CODE_EVENTO_EDITADO){
            Evento eventoEditado = (Evento) data.getExtras().getSerializable("eventoEditado");
            for (int i = 0; i < adapterEventos.getCount(); i++) {
                Evento evento = adapterEventos.getItem(i);
                if (evento.getId() == eventoEditado.getId()) {
                    adapterEventos.remove(evento);
                    adapterEventos.insert(eventoEditado, i);
                    break;
                }

            }
            Toast.makeText(MainActivity.this, "Evento Editado", Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}