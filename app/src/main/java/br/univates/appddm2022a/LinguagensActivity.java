package br.univates.appddm2022a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import br.univates.appddm2022a.controller.LinguagemController;
import br.univates.appddm2022a.model.Linguagem;
import br.univates.appddm2022a.model.Nota;
import br.univates.appddm2022a.tools.Globais;


public class LinguagensActivity extends AppCompatActivity {

    CheckBox chbFavorito;
    EditText txtNome;
    EditText txtDescricao;
    Linguagem objeto;
    LinguagemController controller;
    Context context;
    Spinner spiNota;
    int id_linguagem;
    Button btnExcluir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linguagens);

        id_linguagem = 0;

        chbFavorito = findViewById(R.id.chbFavorito_linguagem);
        txtNome = findViewById(R.id.txtNome_linguagem);
        txtDescricao = findViewById(R.id.txtDescricao_linguagem);
        spiNota = findViewById(R.id.Spinner_linguagens);
        btnExcluir = findViewById(R.id.btnExcluir_linguagem);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new LinguagemController(context);
                controller.excluir(id_linguagem);
            }
        });



        context = LinguagensActivity.this;

        ArrayList<Nota> notas = new ArrayList<>();
        notas.add(new Nota(0, "Sem Nota"));
        notas.add(new Nota(1, "Nota 1"));
        notas.add(new Nota(2, "Nota 2"));
        notas.add(new Nota(3, "Nota 3"));
        notas.add(new Nota(4, "Nota 4"));
        notas.add(new Nota(5, "Nota 5"));


        ArrayAdapter<Nota> adapter_notas = new ArrayAdapter<>(context,
                android.R.layout.simple_expandable_list_item_1, notas);

        spiNota.setAdapter(adapter_notas);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            id_linguagem = extras.getInt("id",0);
            controller = new LinguagemController(context);
            objeto = controller.buscar(id_linguagem);
            if(objeto != null){
                txtNome.setText(objeto.getNome());
                txtDescricao.setText(objeto.getDescricao());
                if(objeto.getFavorito() == 1){
                    chbFavorito.setChecked(true);
                }else{
                    chbFavorito.setChecked(true);
                }

                //preenchendo spinner da nota
                for(int i = 0; i < spiNota.getAdapter().getCount(); i++){
                    Nota nota = (Nota) spiNota.getItemAtPosition(i);
                    if(nota.getId() == objeto.getNota()){
                        spiNota.setSelection(i);
                        break;
                    }
                }

            }

        }else{
            id_linguagem = 0;
        }

        if(id_linguagem == 0){
            btnExcluir.setVisibility(View.GONE);
            finish();
        }

    }

    //preenchendo o spinner
    //String[] notas = getResources().getStringArray(R.array.notas);




    //Funcao para inflar o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.action_ok:

                //SALVAR
                salvar();

            case R.id.action_cancel:

                finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar(){
        try{

            String nome = txtNome.getText().toString().trim();
            String descricao = txtDescricao.getText().toString().trim();

            String nota = spiNota.getSelectedItem().toString();

            if(!nome.equals("") && !descricao.equals("")) {

                if(nome.length() > 30){
                    Globais.exibirMensagem(context,
                            "O nome é muito grande, credo.");
                    return;
                }

                objeto = new Linguagem();
                objeto.setNome(nome);
                objeto.setDescricao(descricao);

                if(chbFavorito.isChecked()){
                    objeto.setFavorito(1);
                }else{
                    objeto.setFavorito(0);
                }

                controller = new LinguagemController(context);

                boolean retorno = false;
                if(id_linguagem == 0){
                    retorno = controller.incluir(objeto);
                }else{
                    objeto.setId(id_linguagem);
                    retorno = controller.alterar(objeto);
                }


                if(retorno){
                    Globais.exibirMensagem(context, "Sucesso");
                    finish();
                }

            }

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
}