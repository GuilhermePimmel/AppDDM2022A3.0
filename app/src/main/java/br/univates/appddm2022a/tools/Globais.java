package br.univates.appddm2022a.tools;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Globais {

    public static void exibirMensagem(Context context, String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }

    //Função para a classe Tools/Globais
    public static String converterData(String data, String formatoInicial, String formatoDesejado) {
        String wDataConvertida = "";
        try {
            java.text.DateFormat parser = new SimpleDateFormat(formatoInicial, Locale.getDefault());
            java.text.DateFormat formatter = new SimpleDateFormat(formatoDesejado, Locale.getDefault());

            wDataConvertida = formatter.format(parser.parse(data));

        } catch (java.text.ParseException ex) {
            System.out.println(ex.getMessage().toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
        } finally {
            return wDataConvertida;
        }
    }

}