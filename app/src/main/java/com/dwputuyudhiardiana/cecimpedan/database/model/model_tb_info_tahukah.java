package com.dwputuyudhiardiana.cecimpedan.database.model;

public class model_tb_info_tahukah {


    private int idInfo;
    private String isiInfo;
    
    public model_tb_info_tahukah(String isiInfo)
    {
        this.isiInfo=isiInfo;
    }

    public void setIdInfo(int idInfo) {
        this.idInfo = idInfo;
    }

    public void setIsiInfo(String isiInfo) {
        this.isiInfo = isiInfo;
    }


    public int getIdInfo() {
        return idInfo;
    }


    public String getIsiInfo() {
        return isiInfo;
    }
}