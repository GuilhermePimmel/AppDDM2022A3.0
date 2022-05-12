package br.univates.appddm2022a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import br.univates.appddm2022a.controller.ClienteController;
import br.univates.appddm2022a.controller.LinguagemController;
import br.univates.appddm2022a.model.Cliente;

public class CadastroActivity extends AppCompatActivity {

    EditText txtNome;
    int id_cliente;
    Context context;
    Cliente objeto;
    ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        id_cliente = 0;

        txtNome = findViewById(R.id.txtNome_cliente);
        context = CadastroActivity.this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_cliente = extras.getInt("id", 0);
            controller = new ClienteController(context);
            objeto = controller.buscar(id_cliente);
            if (objeto != null) {
                txtNome.setText(objeto.getNome());

            }
        }
    }
}
