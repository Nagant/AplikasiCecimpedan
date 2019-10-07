package com.dwputuyudhiardiana.cecimpedan.database.model;

public class model_tb_kamus {


    private int idKamus;
    private String katKamus;
    private String soalKamus;
    private String jawabanKamus;
    private String kuisKamus;

    public model_tb_kamus()
    {

    }

    public model_tb_kamus(String katKamus, String soalKamus, String jawabanKamus, String kuisKamus)
    {
        this.katKamus=katKamus;
        this.soalKamus=soalKamus;
        this.jawabanKamus=jawabanKamus;
        this.kuisKamus=kuisKamus;
    }

    public void setIdKamus(int idKamus) {
        this.idKamus = idKamus;
    }

    public void setKatKamus(String katKamus) {
        this.katKamus = katKamus;
    }

    public void setSoalKamus(String soalKamus) {
        this.soalKamus = soalKamus;
    }

    public void setJawabanKamus(String jawabanKamus) {
        this.jawabanKamus = jawabanKamus;
    }

    public void setKuisKamus(String kuisKamus) {
        this.kuisKamus = kuisKamus;
    }


    public int getIdKamus() {
        return idKamus;
    }

    public String getKatKamus() {
        return katKamus;
    }

    public String getSoalKamus() {
        return soalKamus;
    }

    public String getJawabanKamus() {
        return jawabanKamus;
    }

    public String getKuisKamus() {
        return kuisKamus;
    }

}