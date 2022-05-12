package br.univates.appddm2022a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.univates.appddm2022a.adapter.ClienteAdapter;
import br.univates.appddm2022a.adapter.LinguagemAdapter;
import br.univates.appddm2022a.controller.ClienteController;
import br.univates.appddm2022a.controller.LinguagemController;
import br.univates.appddm2022a.model.Cliente;
import br.univates.appddm2022a.model.Linguagem;
import br.univates.appddm2022a.tools.Globais;

public class ListaClientesActivity extends AppCompatActivity {

    Button btnNova;
    ListView ltvLista;
    ArrayList<Cliente> listagem;
    ClienteAdapter adapter;
    Context context;
    ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        context = ListaClientesActivity.this;

        btnNova = findViewById(R.id.btnAddCliente_clientes);
        ltvLista = findViewById(R.id.ltvLista_clientes);

        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CadastroActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista(){
        try {

            controller = new ClienteController(context);
            listagem = controller.lista();

            adapter = new ClienteAdapter(context, listagem);
            ltvLista.setAdapter(adapter);

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
}