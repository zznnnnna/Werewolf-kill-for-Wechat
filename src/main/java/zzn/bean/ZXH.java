package zzn.bean;

public class ZXH {
    private String Q;
    public ZXH( ) {
    }
    public ZXH(String q) {
        Q = q;
    }

    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q = q;
    }

    @Override
    public String toString() {
        return "ZXH{" +
                "Q='" + Q + '\'' +
                '}';
    }
}
