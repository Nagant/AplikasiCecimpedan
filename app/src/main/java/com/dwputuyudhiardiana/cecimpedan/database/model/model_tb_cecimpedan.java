package com.dwputuyudhiardiana.cecimpedan.database.model;

public class model_tb_cecimpedan {


    private int idKuis;
    private String katKuis;
    private String soalKuis;
    private String jawabanKuis;
    private String ketKuis;
    private int totalKuis;

    public model_tb_cecimpedan() {

    }

    public model_tb_cecimpedan(String katKuis, String soalKuis, String jawabanKuis, String ketKuis, int totalKuis) {
        this.katKuis = katKuis;
        this.soalKuis = soalKuis;
        this.jawabanKuis = jawabanKuis;
        this.ketKuis = ketKuis;
        this.totalKuis = totalKuis;
    }

    public void setIdKuis(int idKuis) {
        this.idKuis = idKuis;
    }

    public void setKatKuis(String katKuis) {
        this.katKuis = katKuis;
    }

    public void setSoalKuis(String soalKuis) {
        this.soalKuis = soalKuis;
    }

    public void setJawabanKuis(String jawabanKuis) {
        this.jawabanKuis = jawabanKuis;
    }

    public void setKeteranganKuis(String ketKuis) {
        this.ketKuis = ketKuis;
    }

    public void setTotalKuis(int totalKuis) {
        this.totalKuis = totalKuis;
    }


    public int getIdKuis() {
        return idKuis;
    }

    public String getKatKuis() {
        return katKuis;
    }

    public String getSoalKuis() {
        return soalKuis;
    }

    public String getJawabanKuis() {
        return jawabanKuis;
    }

    public String getKeteranganKuis() {
        return ketKuis;
    }

    public int getTotalKuis() {
        return totalKuis;
    }

}

