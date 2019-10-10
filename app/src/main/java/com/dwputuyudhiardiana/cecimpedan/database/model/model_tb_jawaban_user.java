package com.dwputuyudhiardiana.cecimpedan.database.model;

public class model_tb_jawaban_user {

    private String tanggalbermainUser,namapemainanUser,nilaiUser,totaljawabanbenarUser,detailjawabanUser;

    public model_tb_jawaban_user()
    {

    }

    public model_tb_jawaban_user(String tanggalbermainUser, String namapemainanUser, String nilaiUser, String totaljawabanbenarUser, String detailjawabanUser)
    {
        this.tanggalbermainUser = tanggalbermainUser;
        this.namapemainanUser = namapemainanUser;
        this.nilaiUser = nilaiUser;
        this.totaljawabanbenarUser = totaljawabanbenarUser;
        this.detailjawabanUser = detailjawabanUser;
    }

    public void settanggalbermainUser(String tanggalbermainUser) {
        this.tanggalbermainUser = tanggalbermainUser;
    }

    public void setnamajenispermainanUser(String namajenispermainanUser) {
        this.tanggalbermainUser = namajenispermainanUser;
    }

    public void setnilaiUserUser(String nilaiUser) {
        this.nilaiUser = nilaiUser;
    }

    public void settotaljawabanbenarUser(String totaljawabanbenarUser) {
        this.totaljawabanbenarUser = totaljawabanbenarUser;
    }

    public void setdetailjawabanUser(String detailjawabanUser) {
        this.detailjawabanUser = detailjawabanUser;
    }

    public String gettanggalbermainUser() {
        return tanggalbermainUser;
    }

    public String getnamapemainanUser() {
        return namapemainanUser;
    }

    public String getnilaiUserUser() {
        return nilaiUser;
    }

    public String gettotaljawabanbenarUser() {
        return totaljawabanbenarUser;
    }

    public String getdetailjawabanUser() {
        return detailjawabanUser;
    }

}
