package toto;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Toto {

    public static void main(String[] args) {
        ArrayList<Fogadás> fogadások = feltölt("toto.txt");

        System.out.println("3. feladat: Fordulók száma: " + fogadások.size() + " db");

        int telitalálatosDb = 0;
        for (Fogadás fogadás : fogadások) {
            telitalálatosDb += fogadás.getTDb();
        }
        System.out.println("4. feladat: Telitalálatos szelvények száma: " + telitalálatosDb);

        long átlagNyeremény = 0;
        for (Fogadás fogadás : fogadások) {
            átlagNyeremény += fogadás.getNyeremény();
        }
        System.out.println(String.format("5. feladat: Átlag: %d Ft", Math.round(átlagNyeremény / fogadások.size())));

        System.out.println("6. feladat:");
        int legkisebbIndex;
        int legnagyobbIndex = 0;
        for (int i = 0; i < fogadások.size(); i++) {
            if (fogadások.get(i).getTNyer() > fogadások.get(legnagyobbIndex).getTNyer()) {
                legnagyobbIndex = i;
            }
        }
        legkisebbIndex = legnagyobbIndex;
        for (int i = 0; i < fogadások.size(); i++) {
            if (fogadások.get(i).getTDb() != 0) {
                if (fogadások.get(i).getTNyer() < fogadások.get(legkisebbIndex).getTNyer()) {
                    legkisebbIndex = i;
                }
            }
        }
        System.out.println("\tLegnagyobb:");
        System.out.println("\tÉv: " + fogadások.get(legnagyobbIndex).getÉv());
        System.out.println("\tHét: " + fogadások.get(legnagyobbIndex).getHét() + ".");
        System.out.println("\tForduló: " + fogadások.get(legnagyobbIndex).getSorszám() + ".");
        System.out.println("\tTelitalálat: " + fogadások.get(legnagyobbIndex).getTDb() + " db");
        System.out.println("\tNyeremény: " + fogadások.get(legnagyobbIndex).getTNyer() + " Ft");
        System.out.println("\tEredmények: " + fogadások.get(legnagyobbIndex).getEredmény());
        System.out.println("");
        System.out.println("\tLegkisebb:");
        System.out.println("\tÉv: " + fogadások.get(legkisebbIndex).getÉv());
        System.out.println("\tHét: " + fogadások.get(legkisebbIndex).getHét() + ".");
        System.out.println("\tForduló: " + fogadások.get(legkisebbIndex).getSorszám() + ".");
        System.out.println("\tTelitalálat: " + fogadások.get(legkisebbIndex).getTDb() + " db");
        System.out.println("\tNyeremény: " + fogadások.get(legkisebbIndex).getTNyer() + " Ft");
        System.out.println("\tEredmények: " + fogadások.get(legkisebbIndex).getEredmény());
    
        boolean voltDöntetlenNélküli = false;
        for (Fogadás fogadás : fogadások) {
            EredmenyElemzo elemzo = new EredmenyElemzo(fogadás.getEredmény());
            if (!elemzo.memvoltDontetlenMerkozes()) {
                voltDöntetlenNélküli = true;
            }
        }
        String eredmény = voltDöntetlenNélküli ? "Volt döntetlen nélküli forduló" : "Nem volt döntetlen nélküli forduló";
        System.out.println("8. feladat: " + eredmény + "!");
    }

    private static ArrayList<Fogadás> feltölt(String állomány) {
        ArrayList<Fogadás> result = new ArrayList<>();
        try {
            RandomAccessFile bemenet = new RandomAccessFile(állomány, "r");
            bemenet.readLine();
            while (bemenet.getFilePointer() < bemenet.length()) {
                result.add(new Fogadás(bemenet.readLine()));
            }
            bemenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return result;
    }

}
