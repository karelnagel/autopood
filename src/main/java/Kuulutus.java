import java.util.Date;

public class Kuulutus {

    String mark;
    String mudel;
    int aasta;
    int hind;
    String link;
    public Kuulutus(String mark, String mudel, int aasta, int hind, String link) {
        this.mark = mark;
        this.mudel = mudel;
        this.aasta = aasta;
        this.hind = hind;
        this.link = link;
    }
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMudel() {
        return mudel;
    }

    public void setMudel(String mudel) {
        this.mudel = mudel;
    }

    public int getAasta() {
        return aasta;
    }

    public void setAasta(int aasta) {
        this.aasta = aasta;
    }

    public int getHind() {
        return hind;
    }

    public void setHind(int hind) {
        this.hind = hind;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
