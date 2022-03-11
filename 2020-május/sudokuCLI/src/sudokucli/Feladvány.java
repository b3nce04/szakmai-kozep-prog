package sudokucli;

class Feladvány {

    public String kezdo;
    private int meret;

    public int getMeret() {
        return meret;
    }
    
    public boolean adottMéret(int megadott) {
        return this.meret == megadott;
    }

    public void setMeret(int ertek) {
        meret = ertek;
    }

    public Feladvány(String sor) {
        kezdo = sor;
        meret = (int) (Math.sqrt(sor.length()));
    }

    public void kirajzol() {
        for (int i = 0; i < kezdo.length(); i++) {
            if (kezdo.charAt(i) == '0') {
                System.out.print(".");
            } else {
                System.out.print(kezdo.charAt(i));
            }
            if (i % meret == meret - 1) {
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        return this.kezdo;
    }
    
    
}
