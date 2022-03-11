package karacsonycli;

class NapiBevetel {

    private int nap;
    private int harangKesz;
    private int harangEladott;
    private int angyalkaKesz;
    private int angyalkaEladott;
    private int fenyofaKesz;
    private int fenyofaEladott;

    public NapiBevetel(String sor) {
        String[] s = sor.split(";");
        this.nap = Integer.parseInt(s[0]);
        this.harangKesz = Integer.parseInt(s[1]);
        this.harangEladott = Math.abs(Integer.parseInt(s[2]));
        this.angyalkaKesz = Integer.parseInt(s[3]);
        this.angyalkaEladott = Math.abs(Integer.parseInt(s[4]));
        this.fenyofaKesz = Integer.parseInt(s[5]);
        this.fenyofaEladott = Math.abs(Integer.parseInt(s[6]));
    }

    public int getNap() {
        return nap;
    }

    public boolean getAdottNapE(int megadott) {
        return nap == megadott;
    }

    public int getHarangKesz() {
        return harangKesz;
    }

    public int getHarangEladott() {
        return harangEladott;
    }

    public int getAngyalkaKesz() {
        return angyalkaKesz;
    }

    public int getAngyalkaEladott() {
        return angyalkaEladott;
    }

    public int getFenyofaKesz() {
        return fenyofaKesz;
    }

    public int getFenyofaEladott() {
        return fenyofaEladott;
    }

    public int getHarangKészleten() {
        return this.getHarangKesz() - this.getHarangEladott();
    }

    public int getAngyalKészleten() {
        return this.getAngyalkaKesz() - this.getAngyalkaEladott();
    }

    public int getFenyofaKészleten() {
        return this.getFenyofaKesz() - this.getFenyofaEladott();
    }

    public int napiBevetel() {
        return (harangEladott * 1000 + angyalkaEladott * 1350 + fenyofaEladott * 1500);
    }
}
