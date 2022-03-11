package helsinki1952;

public class Helyezés {

    private int helyezés;
    private int darab; // csapat v sportoló
    private String sportág;
    private String versenyszám;

    public Helyezés(String sor) {
        String[] darabok = sor.split(" ");
        this.helyezés = Integer.parseInt(darabok[0]);
        this.darab = Integer.parseInt(darabok[1]);
        this.sportág = darabok[2];
        this.versenyszám = darabok[3];
    }

    public int getOlimpiaiPont() {
        int result = 0;
        int[] pontokért = {7, 5, 4, 3, 2, 1};
        if (this.helyezés <= 6) {
            return pontokért[this.helyezés - 1];
        }
        return result;
    }

    public String getSportág() {
        return sportág;
    }

    public int getDarab() {
        return darab;
    }

    public int getHelyezés() {
        return helyezés;
    }

    public String getVersenyszám() {
        return versenyszám;
    }

    @Override
    public String toString() {
        String sportág = this.sportág;
        if (sportág.equals("kajakkenu")) {
            sportág = "kajak-kenu";
        }
        return this.helyezés + " " + this.darab + " " + this.getOlimpiaiPont() + " " + sportág + " " + this.versenyszám;
    }

}
