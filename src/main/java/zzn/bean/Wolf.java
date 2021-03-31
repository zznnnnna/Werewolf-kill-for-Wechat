package zzn.bean;

import java.util.List;

public class Wolf {

    private String faguan;
    private List<String> langren;
    private String yuyanjia;
    private String nvwu;
    private String qiubite;
    private String lieren;
    private String shouwei;
    private List<String> baichi;
    private List<String> chunmin;

    public Wolf() {

    }

    public Wolf(String faguan, List<String> langren, String yuyanjia, String nvwu, String qiubite, String lieren, String shouwei, List<String> baichi, List<String> chunmin) {
        this.faguan = faguan;
        this.langren = langren;
        this.yuyanjia = yuyanjia;
        this.nvwu = nvwu;
        this.qiubite = qiubite;
        this.lieren = lieren;
        this.shouwei = shouwei;
        this.baichi = baichi;
        this.chunmin = chunmin;
    }

    public String getFaguan() {
        return faguan;
    }

    public void setFaguan(String faguan) {
        this.faguan = faguan;
    }

    public List<String> getLangren() {
        return langren;
    }

    public void setLangren(List<String> langren) {
        this.langren = langren;
    }

    public String getYuyanjia() {
        return yuyanjia;
    }

    public void setYuyanjia(String yuyanjia) {
        this.yuyanjia = yuyanjia;
    }

    public String getNvwu() {
        return nvwu;
    }

    public void setNvwu(String nvwu) {
        this.nvwu = nvwu;
    }

    public String getQiubite() {
        return qiubite;
    }

    public void setQiubite(String qiubite) {
        this.qiubite = qiubite;
    }

    public String getLieren() {
        return lieren;
    }

    public void setLieren(String lieren) {
        this.lieren = lieren;
    }

    public String getShouwei() {
        return shouwei;
    }

    public void setShouwei(String shouwei) {
        this.shouwei = shouwei;
    }

    public List<String> getBaichi() {
        return baichi;
    }

    public void setBaichi(List<String> baichi) {
        this.baichi = baichi;
    }

    public List<String> getChunmin() {
        return chunmin;
    }

    public void setChunmin(List<String> chunmin) {
        this.chunmin = chunmin;
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "faguan='" + faguan + '\'' +
                ", langren=" + langren +
                ", yuyanjia='" + yuyanjia + '\'' +
                ", nvwu='" + nvwu + '\'' +
                ", qiubite='" + qiubite + '\'' +
                ", lieren='" + lieren + '\'' +
                ", shouwei='" + shouwei + '\'' +
                ", baichi=" + baichi +
                ", chunmin=" + chunmin +
                '}';
    }
}
