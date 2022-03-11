package footgolf;

import java.util.ArrayList;
import java.util.Collections;

public class Versenyző {

    private String név;
    private String kategória;
    private String egyesület;
    private int[] fordulóPontok = new int[8];

    public Versenyző(String név, String kategória, String egyesület, int[] fordulóPontok) {
        this.név = név;
        this.kategória = kategória;
        this.egyesület = egyesület;
        this.fordulóPontok = fordulóPontok;
    }

    public Versenyző(String sor) {
        String[] darabok = sor.split(";");
        this.név = darabok[0];
        this.kategória = darabok[1];
        this.egyesület = darabok[2];
        for (int i = 3; i < darabok.length; i++) {
            this.fordulóPontok[i - 3] = Integer.parseInt(darabok[i]);
        }
    }

    public String getNév() {
        return név;
    }

    public String getEgyesület() {
        return egyesület;
    }

    public boolean getNőE() {
        return this.kategória.equals("Noi");
    }

    public int getÖsszpontszám() {
        int result = 0;
        ArrayList<Integer> pontszámok = new ArrayList<>();
        for (int i : this.fordulóPontok) {
            pontszámok.add(i);
        }
        Collections.sort(pontszámok);
        for (int i = 2; i < pontszámok.size(); i++) {
            result += pontszámok.get(i);
        }
        if (pontszámok.get(0) != 0) {
            result += 10;
        }
        if (pontszámok.get(1) != 0) {
            result += 10;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Versenyz\u0151{" + "n\u00e9v=" + név + ", kateg\u00f3ria=" + kategória + ", egyes\u00fclet=" + egyesület + ", fordul\u00f3Pontok=" + fordulóPontok + '}';
    }

}
