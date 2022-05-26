package br.univates.appddm2022a;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.santalu.maskara.widget.MaskEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.univates.appddm2022a.controller.ClienteController;
import br.univates.appddm2022a.controller.LinguagemController;
import br.univates.appddm2022a.model.Cliente;
import br.univates.appddm2022a.model.Linguagem;
import br.univates.appddm2022a.tools.Globais;

public class CadastroActivity extends AppCompatActivity {

    EditText txtNome, txtData;
    MaskEditText txtTelefone;
    int id_cliente;
    Context context;
    Cliente objeto;
    ClienteController controller;
    Button btnExluir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        id_cliente = 0;

        txtTelefone = findViewById(R.id.txtTelefone_cliente);
        txtData = findViewById(R.id.txtDataNasc_cliente);
        txtNome = findViewById(R.id.txtNome_cliente);
        btnExluir = findViewById(R.id.btnExcluir_cliente);

        context = CadastroActivity.this;

        btnExluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new ClienteController(context);
                boolean retorno = controller.excluir(objeto);
                if (retorno) {
                    finish();
                }else{
                    Globais.exibirMensagem(context,"erro ao excluir");
                }
            }
        });

        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendario = Calendar.getInstance();

                Date data;

                try{
                    if(txtData.getText().toString().equals("")){
                        calendario = Calendar.getInstance();
                    }else{
                        String dtStart = txtData.getText().toString();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            data = format.parse(dtStart);
                            calendario.setTime(data);

                        } catch (ParseException e) {
                            e.printStackTrace();
                            calendario = Calendar.getInstance();
                        }
                    }

                    int ano = calendario.get(Calendar.YEAR);
                    int mes = calendario.get(Calendar.MONTH);
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);

                    new DatePickerDialog(context, android.R.style.Widget_DeviceDefault_DatePicker, mDateSetListener, ano, mes, dia).show();

                }catch (Exception ex){
                    calendario = Calendar.getInstance();
                    Globais.exibirMensagem(context, "Erro");
                }







            }
        });


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
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String data = String.format(String.format("%02d", dayOfMonth)) + "/"+ (String.format("%02d", monthOfYear + 1)) + "/" + (String.format("%02d", year));

            txtData.setText(data);
        }
    };
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

            String data = txtData.getText().toString().trim();
            String nome = txtNome.getText().toString().trim();
            String telefone = txtTelefone.getUnMasked().trim();

            String data_formatada = Globais.converterData(data, "dd/MM/yyyy", "yyyy-MM=dd");

                if(nome.length() > 70){
                    Globais.exibirMensagem(context,
                            "O nome é muito grande, credo.");
                    return;
                }

                objeto = new Cliente();
                objeto.setNome(nome);
                objeto.setData(data_formatada);
                objeto.setTelefone(telefone);

                controller = new ClienteController(context);

                boolean retorno = false;
                if(id_cliente == 0){
                    retorno = controller.incluir(objeto);
                }else{
                    objeto.setId(id_cliente);
                    retorno = controller.alterar(objeto);
                }


                if(retorno){
                    Globais.exibirMensagem(context, "Sucesso");
                    finish();
                }
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
    }

