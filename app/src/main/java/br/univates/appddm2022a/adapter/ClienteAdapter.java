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

import br.univates.appddm2022a.CadastroActivity;
import br.univates.appddm2022a.LinguagensActivity;
import br.univates.appddm2022a.R;
import br.univates.appddm2022a.model.Cliente;
import br.univates.appddm2022a.model.Linguagem;


public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private final Context context;
    private final ArrayList<Cliente> elementos;

    public ClienteAdapter(Context context, ArrayList<Cliente> elementos){
        super(context, R.layout.item_lista_cliente, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{
            Cliente objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_cliente, parent, false);

            TextView nome = rowView.findViewById(R.id.lblNome_item_cliente);

            nome.setText(objeto.getNome());


            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tela = new Intent(context, CadastroActivity.class);
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