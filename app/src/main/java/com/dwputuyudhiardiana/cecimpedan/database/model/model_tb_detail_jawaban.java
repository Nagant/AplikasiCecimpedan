package com.dwputuyudhiardiana.cecimpedan.database.model;

public class model_tb_detail_jawaban {

    private String soalDetail,jawabanDetail,jawabanstatusDetail,jawabanpemainDetail;

    public model_tb_detail_jawaban(String soalDetail, String jawabanDetail,String jawabanstatusDetail,String jawabanpemainDetail) {
        this.soalDetail = soalDetail;
        this.jawabanDetail = jawabanDetail;
        this.jawabanstatusDetail=jawabanstatusDetail;
        this.jawabanpemainDetail=jawabanpemainDetail;
    }

    public void setsoalDetail(String soalDetail) {
        this.soalDetail = soalDetail;
    }

    public void setjawabanDetail(String jawabanDetail) {
        this.jawabanDetail = jawabanDetail;
    }

    public void setjawabanstatusDetail(String jawabanstatusDetail) {
        this.jawabanstatusDetail = jawabanstatusDetail;
    }

    public void setjawabanpemainDetail(String jawabanpemainDetail) {
        this.jawabanpemainDetail = jawabanpemainDetail;
    }

    public String getsoalDetail() {
        return soalDetail;
    }
    public String getjawabanDetail() {
        return jawabanDetail;
    }

    public String getjawabanstatusDetail() {
        return jawabanstatusDetail;
    }

    public String getjawabanpemainDetail() {
        return jawabanpemainDetail;
    }
}
