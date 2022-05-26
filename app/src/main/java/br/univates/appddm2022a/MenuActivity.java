package br.univates.appddm2022a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends AppCompatActivity {

    Button btnClientes, btnLinguagens;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        context = MenuActivity.this;

        btnClientes = findViewById(R.id.btnTelaClientes_menu);
        btnLinguagens = findViewById(R.id.btnTelaLinguagens_menu);

        btnLinguagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListaLinguagensActivity.class);
                startActivity(intent);
            }
        });

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}