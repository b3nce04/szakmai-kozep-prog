package cbradio;

public class Bejegyzés {

    private int óra;
    private int perc;
    private int adásokSzáma;
    private String becenév;

    public Bejegyzés(String sor) {
        String[] darabok = sor.split(";");
        this.óra = Integer.parseInt(darabok[0]);
        this.perc = Integer.parseInt(darabok[1]);
        this.adásokSzáma = Integer.parseInt(darabok[2]);
        this.becenév = darabok[3];
    }

    public boolean isNévEgyezik(String megadott) {
        return (this.becenév.toLowerCase()).equals(megadott.toLowerCase());
    }

    public boolean isAdásokSzámaEgyezik(int megadott) {
        return this.adásokSzáma == megadott;
    }

    public int getÓra() {
        return óra;
    }

    public int getPerc() {
        return perc;
    }

    public String getBecenév() {
        return becenév;
    }

    public int getAdásokSzáma() {
        return adásokSzáma;
    }

    @Override
    public String toString() {
        return "Bejegyz\u00e9s{" + "\u00f3ra=" + óra + ", perc=" + perc + ", ad\u00e1sokSz\u00e1ma=" + adásokSzáma + ", becen\u00e9v=" + becenév + '}';
    }

}
