package br.univates.appddm2022a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.univates.appddm2022a.adapter.LinguagemAdapter;
import br.univates.appddm2022a.controller.LinguagemController;
import br.univates.appddm2022a.model.Linguagem;
import br.univates.appddm2022a.tools.Globais;


public class ListaLinguagensActivity extends AppCompatActivity {

    Button btnNova;
    ListView ltvLista;
    ArrayList<Linguagem> listagem;
    LinguagemAdapter adapter;
    Context context;
    LinguagemController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_linguagens);
        context = ListaLinguagensActivity.this;

        btnNova = findViewById(R.id.btnAddLinguagem_linguagens);
        ltvLista = findViewById(R.id.ltvLista_linguagem);

        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LinguagensActivity.class);
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

            controller = new LinguagemController(context);
            listagem = controller.lista();

            adapter = new LinguagemAdapter(context, listagem);
            ltvLista.setAdapter(adapter);

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
}