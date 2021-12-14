package dsw.gerudok.app.repository;

public class Sadrzaj {
    private String name;
    private String sadrzaj;
    private String tip;
    public Sadrzaj(String name,String tip, String sadrzaj){
        this.name = name;
        this.sadrzaj = sadrzaj;
        this.tip = tip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}