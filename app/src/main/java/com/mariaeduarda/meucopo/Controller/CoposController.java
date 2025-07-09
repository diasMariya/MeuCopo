package com.mariaeduarda.meucopo.Controller;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.mariaeduarda.meucopo.Model.CopoModel;
import com.mariaeduarda.meucopo.Model.MeuCopoDB;


import java.util.List;


public class CoposController extends MeuCopoDB {

    private CopoModel dados;
    private TelaListener tela;

    public CoposController(Context context, TelaListener tela) {
        super(context);
        this.tela = tela;

        this.dados = CopoModel.getInstancia(this);
        //this.dados = new CopoModel();
    }

    public void salvarObj(CopoModel copoModel) {
        ContentValues valores = new ContentValues();
        valores.put("meta", copoModel.getMeta());
        valores.put("tamanhoCopo", copoModel.getTamanhoCopo());
        valores.put("consumo", copoModel.getConsumo());

        SQLiteDatabase db = getWritableDatabase();
        db.delete("Copo", null, null);

        salvar("Copo", valores);
    }

    public List<CopoModel> getListaDados() {
        return listaDados();
    }

    public interface TelaListener {
        void atualizarProgresso(int ml, int meta, double percentual);
    }
    public void beber() {
        dados.beberAgua();
        salvarObj(CopoModel.getInstancia(this));
        atualizarTela();
    }
    public void atualizarTela() {
        if (dados.getMeta() == 0) return;

        double percentual = (dados.getConsumo() * 100.0) / dados.getMeta();
        tela.atualizarProgresso(dados.getConsumo(), dados.getMeta(), percentual);
    }
    public List<CopoModel> mostrarTodosDados() {
        return getListaDados();
    }
//    public List<CopoModel> mostrarTodosContatos(){
//        List<CopoModel> copos = MeuCopoDB.listar();
//        return  copos;
//    }



}
