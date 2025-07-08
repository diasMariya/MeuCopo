package com.mariaeduarda.meucopo.Controller;


import android.content.ContentValues;
import android.content.SharedPreferences;

import com.mariaeduarda.meucopo.Model.CopoModel;
import com.mariaeduarda.meucopo.Model.MeuCopDB;
import com.mariaeduarda.meucopo.View.MainActivity;

import java.util.List;


public class CoposController extends MeuCopDB {

        private CopoModel dados;
        private TelaListener tela;

        SharedPreferences preferences;
        SharedPreferences.Editor dadosPreferences;
        public static final String NOME_PREFERENCES = "pref_MeuCopo";

        public CoposController(MainActivity mainActivity){
            super(mainActivity);
            preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES, 0);
            dadosPreferences = preferences.edit();
        }

        public void salvar(CopoModel copoModel){
            ContentValues dados = new ContentValues();
            dadosPreferences.putInt("meta", copoModel.getMeta());
            dadosPreferences.putInt("tamanhoCopo", copoModel.getTamanhoCopo());
            dadosPreferences.putInt("consumo", copoModel.getConsumo());
            dadosPreferences.apply();

            dados.put("meta", copoModel.getMeta());
            dados.put("tamanhoCopo", copoModel.getTamanhoCopo());
            dados.put("consumo", copoModel.getConsumo());

            salvar("CopoModel", dados);
        }
        public List<CopoModel> getListaDados(){
            return listaDados();
        }

    public interface TelaListener {
            void atualizarProgresso(int ml, int meta, double percentual);
        }

        public CoposController(TelaListener tela) {
            this.dados = CopoModel.getInstancia();
            this.tela = tela;

        }

        public void beber() {
            dados.beberAgua();
            atualizarTela();
        }

        public void atualizarTela() {
            double percentual = (dados.getConsumo() * 100.0) / dados.getMeta();
            tela.atualizarProgresso(dados.getConsumo(),dados.getMeta(), percentual);
        }
    }


