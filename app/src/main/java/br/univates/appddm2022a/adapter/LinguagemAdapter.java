package br.univates.appddm2022a.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.univates.appddm2022a.LinguagensActivity;
import br.univates.appddm2022a.R;
import br.univates.appddm2022a.model.Linguagem;


public class LinguagemAdapter extends ArrayAdapter<Linguagem> {

    private final Context context;
    private final ArrayList<Linguagem> elementos;

    public LinguagemAdapter(Context context, ArrayList<Linguagem> elementos){
        super(context, R.layout.item_lista_cliente, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{
            Linguagem objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_cliente, parent, false);

            TextView nome = rowView.findViewById(R.id.lblNome_item_linguagem);
            TextView descricao = rowView.findViewById(R.id.lblDescricao_item_linguagem);
            ImageView favorito = rowView.findViewById(R.id.imgFavorito_linguagem);


            nome.setText(objeto.getNome());
            descricao.setText(objeto.getDescricao());
            if(objeto.getFavorito() == 1){
                favorito.setVisibility(View.VISIBLE);
            }else{
                favorito.setVisibility(View.GONE);
            }


            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tela = new Intent(context, LinguagensActivity.class);
                    tela.putExtra("id", objeto.getId());
                    context.startActivity(tela);
                }
            });

            return rowView;

        }catch (Exception ex){
            Log.e("ERRO_ADAPTER", ex.getMessage());
            return null;
        }

    }
}