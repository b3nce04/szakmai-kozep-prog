package uzemanyag;

public class Változás {

    private static double ÁRFOLYAM = 307.7;
    private String dátum;
    private int benzinÁr;
    private int gázolajÁr;

    public Változás(String dátum, int benzinÁr, int gázolajÁr) {
        this.dátum = dátum;
        this.benzinÁr = benzinÁr;
        this.gázolajÁr = gázolajÁr;
    }

    public Változás(String sor) {
        String[] darabok = sor.split(";");
        this.dátum = darabok[0];
        this.benzinÁr = Integer.parseInt(darabok[1]);
        this.gázolajÁr = Integer.parseInt(darabok[2]);
    }

    public double getBenzinÁrEuró() {
        return 1.0 * this.benzinÁr / ÁRFOLYAM;
    }

    public double getGázolajÁrEuró() {
        return 1.0 * this.gázolajÁr / ÁRFOLYAM;
    }

    public int getDátumÉv() {
        return Integer.parseInt(this.dátum.split("[.]")[0]);
    }

    public int getDátumHónap() {
        return Integer.parseInt(this.dátum.split("[.]")[1]);
    }

    public int getDátumNap() {
        return Integer.parseInt(this.dátum.split("[.]")[2]);
    }

    public boolean getSzökőNapE() {
        return (this.getDátumHónap() == 2 && this.getDátumNap() == 24);
    }

    public int getKülönbség() {
        return Math.abs(this.benzinÁr - this.gázolajÁr);
    }

    public boolean getKülönbségEgyezik(int megadott) {
        return this.getKülönbség() == Math.abs(megadott);
    }

    public boolean getAdottÉvE(int megadott) {
        return this.getDátumÉv() == megadott;
    }

    @Override
    public String toString() {
        return String.format("%s;%.2f;%.2f", this.dátum, this.getBenzinÁrEuró(), this.getGázolajÁrEuró());
    }

}
