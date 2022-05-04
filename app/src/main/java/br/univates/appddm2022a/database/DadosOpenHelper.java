package br.univates.appddm2022a.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.univates.appddm2022a.tools.Globais;


public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2; //versão do banco de dados
    private static final String NM_BANCO = "banco";
    private Context context;

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append(" ALTER TABLE ");
            sql.append(Tabelas.TB_LINGUAGENS);
            sql.append(" ADD COLUMN ");
            sql.append(" favorito BIT ");
            db.execSQL(sql.toString());

        }catch (SQLException ex){
            Log.e("ONUPGRADE",ex.getMessage());
        }catch (Exception ex) {
            Log.e("ONUPGRADE",ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{



        }catch (Exception ex){

        }
    }
}