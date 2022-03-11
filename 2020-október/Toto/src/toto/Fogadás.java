package toto;

public class Fogadás {

    private int év;
    private int hét;
    private int sorszám;
    private int TDb;
    private int TNyer;
    private String eredmény;

    public Fogadás(String sor) {
        String[] darabok = sor.split(";");
        this.év = Integer.parseInt(darabok[0]);
        this.hét = Integer.parseInt(darabok[1]);
        this.sorszám = Integer.parseInt(darabok[2]);
        this.TDb = Integer.parseInt(darabok[3]);
        this.TNyer = Integer.parseInt(darabok[4]);
        this.eredmény = darabok[5];
    }
    
    public int getNyeremény() {
        return this.TDb * this.TNyer;
    }

    public int getÉv() {
        return év;
    }

    public int getHét() {
        return hét;
    }

    public int getSorszám() {
        return sorszám;
    }

    public int getTDb() {
        return TDb;
    }

    public int getTNyer() {
        return TNyer;
    }

    public String getEredmény() {
        return eredmény;
    }
    
    

    @Override
    public String toString() {
        return "Fogad\u00e1s{" + "\u00e9v=" + év + ", h\u00e9t=" + hét + ", sorsz\u00e1m=" + sorszám + ", TDb=" + TDb + ", TNyer=" + TNyer + ", eredm\u00e9ny=" + eredmény + '}';
    }

}
