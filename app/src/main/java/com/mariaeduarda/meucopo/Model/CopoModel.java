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
        this.consumo = (consumo);
    }

    public CopoModel(MeuCopDB meuCopDB) {
    }


    public static CopoModel getInstancia() {
            if (instancia == null) {
            }
            return instancia;
        }

        public void beberAgua() {
            consumo += tamanhoCopo;
        }

        public void registrarDia(String data) {
            historico.add(new Registro(data, consumo));
            consumo = 0;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsumo() {
            return consumo;
        }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
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
            public List<Registro> getHistorico() {
            return historico;
        }

        public static void setInstancia(CopoModel instancia) {
            CopoModel.instancia = instancia;
        }

    @Override
    public String toString() {
        return "CopoModel{" +
                "meta=" + meta +
                ", tamanhoCopo=" + tamanhoCopo +
                ", consumo=" + consumo +
                ", historico=" + historico +
                '}';
    }
}

