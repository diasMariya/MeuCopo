package com.mariaeduarda.meucopo.Model;

public class Registro {
        private String data;
        private int totalMl;

        public Registro(String data, int totalMl) {
            this.data = data;
            this.totalMl = totalMl;
        }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTotalMl() {
        return totalMl;
    }

    public void setTotalMl(int totalMl) {
        this.totalMl = totalMl;
    }
}
