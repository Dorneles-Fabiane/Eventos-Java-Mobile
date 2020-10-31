package com.example.eventos_java_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eventos_java_mobile.database.LocalDAO;
import com.example.eventos_java_mobile.modelo.Local;

public class ListarLocaisActivity extends AppCompatActivity {

    private ListView listViewLocais;
    private ArrayAdapter<Local> localAdaptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_locais);
        setTitle("Locais");

        listViewLocais = findViewById(R.id.listView_locais);
        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalDAO localDAO = new LocalDAO(getBaseContext());

        localAdaptar = new ArrayAdapter<>(ListarLocaisActivity.this,
                android.R.layout.simple_list_item_1,
                localDAO.listarLocais());
        listViewLocais.setAdapter(localAdaptar);
    }

    private void definirOnClickListenerListView() {
        listViewLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Local localClicado =localAdaptar.getItem(position);
                Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocalActivity.class);

                intent.putExtra("localEdicao", localClicado);
                startActivity(intent);
            }
        });
    }

    public void onClickNovoLocal(View v) {
        Intent intent = new Intent(ListarLocaisActivity.this, CadastroLocalActivity.class);
        startActivity(intent);
    }

    public void onClickEventos(View v) {
        Intent intent = new Intent(ListarLocaisActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}