package egyszamjatek;

import java.util.ArrayList;
import java.util.TreeSet;

public class Játék {

    private String játékos;
    private ArrayList<Integer> fordulók = new ArrayList<>();

    public Játék(String sor) {
        String[] darabok = sor.split(" ");
        this.játékos = darabok[darabok.length - 1];
        for (int i = 0; i < darabok.length - 1; i++) {
            fordulók.add(Integer.parseInt(darabok[i]));
        }
    }

    public String getJátékos() {
        return játékos;
    }

    public int getLegnagyobbTipp() {
        TreeSet<Integer> tippek = new TreeSet<>();
        tippek.addAll(this.fordulók);
        return tippek.last();
    }

    public int getFordulóTipp(int forduló) {
        return this.fordulók.get(forduló);
    }

    public int getFordulókSzáma() {
        return this.fordulók.size();
    }

    @Override
    public String toString() {
        return "J\u00e1t\u00e9k{" + "j\u00e1t\u00e9kos=" + játékos + ", fordul\u00f3k=" + fordulók + '}';
    }

}
