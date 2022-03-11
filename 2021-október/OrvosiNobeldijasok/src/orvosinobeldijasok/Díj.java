package orvosinobeldijasok;

public class Díj {

    private int év;
    private String név;
    private String szülHal;
    private String országKód;

    public Díj() {
        this.év = 0;
        this.név = "";
        this.szülHal = "";
        this.országKód = "";
    }
    
    public Díj(String sor) {
        this.év = Integer.parseInt(sor.split(";")[0]);
        this.név = sor.split(";")[1];
        this.szülHal = sor.split(";")[2];
        this.országKód = sor.split(";")[3];
    }

    public int getÉv() {
        return év;
    }

    public String getNév() {
        return név;
    }

    public String getSzülHal() {
        return szülHal;
    }

    public String getOrszágKód() {
        return országKód;
    }
    
    public boolean getAdottOrszágE(String kód) {
        return this.országKód.equals(kód);
    }

    @Override
    public String toString() {
        return "D\u00edj{" + "\u00e9v=" + év + ", n\u00e9v=" + név + ", sz\u00fclHal=" + szülHal + ", orsz\u00e1gK\u00f3d=" + országKód + '}';
    }

}
