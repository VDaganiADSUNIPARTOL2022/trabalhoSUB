package br.unipar.trabalhosub.backend.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.unipar.trabalhosub.backend.entidade.Pais;
import br.unipar.trabalhosub.backend.helper.SQLiteDataHelper;

public class PaisDAO {

    //Variavel para abrir a conexão com BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase bancoDados;

    //Nome da tabela
    private String nomeTabela = "PAIS";

    //Nome das colunas da tabela
    private String[] colunas = {"CODIGO", "DESCRICAO"};

    private Context context;

    private static PaisDAO instancia;

    public static PaisDAO getInstancia(Context context){
        if(instancia == null){
            return instancia = new PaisDAO(context);
        }else{
            return instancia;
        }
    }

    private PaisDAO(Context context) {
        this.context = context;

        //Abrir uma conexão da BD
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR_BD",
                null, 1);
        //Carrega a BD e da permissão para escrever na tabela
        bancoDados = openHelper.getWritableDatabase();
    }

    public long insert(Pais obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigo());
            valores.put(colunas[1], obj.getDescricao());

            return bancoDados.insert(nomeTabela, null, valores);

        }catch (SQLException ex){
            Log.e("ERRO", "PaisDAO.insert(): "+ex.getMessage());
        }
        return 0;
    }

    public List<Pais> getAll() {
        List<Pais> lista = new ArrayList<>();
        try{
            Cursor cursor = bancoDados.query(nomeTabela, colunas,
                    null, null, null,
                    null, colunas[0]);

            //Verifica se é possível retornar o ponteiro para
            // a primeira posição do cursor
            if(cursor.moveToFirst()){
                do{
                    Pais pais = new Pais();
                    pais.setCodigo(cursor.getInt(0));
                    pais.setDescricao(cursor.getString(1));

                    lista.add(pais);

                }while (cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("ERRO", "PaisDAO.getAll(): "+ex.getMessage());
        }
        return lista;
    }
}
