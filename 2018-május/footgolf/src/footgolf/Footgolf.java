package footgolf;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

public class Footgolf {

    public static void main(String[] args) {
        ArrayList<Versenyző> versenyzők = feltölt("fob2016.txt");

        System.out.println("3. feladat: Versenyzők száma: " + versenyzők.size());

        int nők = 0;
        for (Versenyző versenyző : versenyzők) {
            if (versenyző.getNőE()) {
                nők++;
            }
        }
        System.out.println(String.format("4. feladat: A női versenyzők aránya: %.2f%%", (1.0 * nők / versenyzők.size()) * 100));

        System.out.println("6. feladat: A bajnok női versenyző");
        int legtöbbIndex = 0;
        for (int i = 0; i < versenyzők.size(); i++) {
            Versenyző versenyző = versenyzők.get(i);
            if (versenyző.getNőE() && versenyző.getÖsszpontszám() > versenyzők.get(legtöbbIndex).getÖsszpontszám()) {
                legtöbbIndex = i;
            }
        }
        System.out.println("\tNév: " + versenyzők.get(legtöbbIndex).getNév());
        System.out.println("\tEgyesület: " + versenyzők.get(legtöbbIndex).getEgyesület());
        System.out.println("\tÖsszpont: " + versenyzők.get(legtöbbIndex).getÖsszpontszám());

        try {
            RandomAccessFile kimenet = new RandomAccessFile("osszpontFF.txt", "rw");
            kimenet.setLength(0);
            for (Versenyző versenyző : versenyzők) {
                if (!versenyző.getNőE()) {
                    kimenet.writeBytes(versenyző.getNév() + ";" + versenyző.getÖsszpontszám() + "\r\n");
                }
            }
            kimenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }

        System.out.println("8. feladat: Egyesület statisztika");
        HashMap<String, Integer> stat = new HashMap<>();
        for (Versenyző versenyző : versenyzők) {
            if (stat.containsKey(versenyző.getEgyesület())) {
                stat.replace(versenyző.getEgyesület(), stat.get(versenyző.getEgyesület()) + 1);
            } else {
                stat.put(versenyző.getEgyesület(), 1);
            }
        }
        for (String string : stat.keySet()) {
            if (stat.get(string) > 2 && !string.equals("n.a.")) {
                System.out.println("\t" + string + " - " + stat.get(string) + " fő");
            }
        }
    }

    private static ArrayList<Versenyző> feltölt(String állomány) {
        ArrayList<Versenyző> result = new ArrayList<>();
        try {
            RandomAccessFile bemenet = new RandomAccessFile(állomány, "r");
            while (bemenet.getFilePointer() < bemenet.length()) {
                result.add(new Versenyző(bemenet.readLine()));
            }
            bemenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return result;
    }

}
