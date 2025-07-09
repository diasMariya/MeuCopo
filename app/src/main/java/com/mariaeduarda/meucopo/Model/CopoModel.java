package com.mariaeduarda.meucopo.Model;

import java.util.ArrayList;
import java.util.List;


public class CopoModel {

    private static CopoModel instancia;

    private int id;
    private int meta = 2000;
    private int tamanhoCopo = 200;
    private int consumo = 0;
    private List<Registro> historico = new ArrayList<>();

    public CopoModel(int id, int meta, int tamanhoCopo, int consumo) {
        this.id = id;
        this.meta = meta;
        this.tamanhoCopo = tamanhoCopo;
        this.consumo = consumo;
    }


    public CopoModel(MeuCopoDB meuCopoDB) {
        List<CopoModel> lista = meuCopoDB.listaDados();

        if (!lista.isEmpty()) {
            CopoModel ultimo = lista.get(lista.size() - 1);
            this.id = ultimo.getId();
            this.meta = ultimo.getMeta();
            this.tamanhoCopo = ultimo.getTamanhoCopo();
            this.consumo = ultimo.getConsumo();
        } else {
            this.meta = 2000;
            this.tamanhoCopo = 200;
            this.consumo = 0;
        }
    }
    public static CopoModel getInstancia(MeuCopoDB db) {
        if (instancia == null) {
            instancia = new CopoModel(db);
        }
        return instancia;
    }

    public static void setInstancia(CopoModel instancia) {
        CopoModel.instancia = instancia;
    }

    public void beberAgua() {
        consumo += tamanhoCopo;
    }

    public void registrarDia(String data) {
        historico.add(new Registro(data, consumo));
        consumo = 0;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public int getTamanhoCopo() {
        return tamanhoCopo;
    }

    public void setTamanhoCopo(int tamanhoCopo) {
        this.tamanhoCopo = tamanhoCopo;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public List<Registro> getHistorico() {
        return historico;
    }

    @Override
    public String toString() {
        return "CopoModel{" +
                "id=" + id +
                ", meta=" + meta +
                ", tamanhoCopo=" + tamanhoCopo +
                ", consumo=" + consumo +
                ", historico=" + historico +
                '}';
    }
}
