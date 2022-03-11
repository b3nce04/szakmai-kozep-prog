package cbradio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Cbradio {

    public static void main(String[] args) {
        ArrayList<Bejegyzés> bejegyzések = feltölt("cb.txt");

        System.out.println("3. feladat: Bejegyzések száma: " + bejegyzések.size() + " db");

        boolean találat = false;
        for (Bejegyzés bejegyzés : bejegyzések) {
            if (bejegyzés.isAdásokSzámaEgyezik(4)) {
                találat = true;
                break;
            }
        }
        if (találat) {
            System.out.println("4. feladat: Volt négy adást indító sofőr.");
        } else {
            System.out.println("4. feladat: Nem volt négy adást indító sofőr.");
        }

        Scanner beBill = new Scanner(System.in);
        System.out.print("5. feladat: Kérek egy nevet: ");
        String megadottNév = beBill.nextLine();
        int alkalom = 0;
        for (Bejegyzés bejegyzés : bejegyzések) {
            if (bejegyzés.isNévEgyezik(megadottNév)) {
                alkalom += bejegyzés.getAdásokSzáma();
            }
        }
        if (alkalom == 0) {
            System.out.println("\tNincs ilyen nevű sofőr!");
        } else {
            System.out.println("\t" + megadottNév + " " + alkalom + "x használta a CB-rádiót.");
        }

        try {
            RandomAccessFile kimenet = new RandomAccessFile("cb2.txt", "rw");
            kimenet.setLength(0);
            kimenet.writeBytes("Kezdes;Nev;AdasDb\r\n");
            for (Bejegyzés bejegyzés : bejegyzések) {
                kimenet.writeBytes(AtszamolPercre(bejegyzés.getÓra(), bejegyzés.getPerc()) + ";" + bejegyzés.getBecenév() + ";" + bejegyzés.getAdásokSzáma() + "\r\n");
            }
            kimenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }

        TreeSet<String> sofőrök = new TreeSet<>();
        for (Bejegyzés bejegyzés : bejegyzések) {
            sofőrök.add(bejegyzés.getBecenév());
        }
        System.out.println("8. feladat: Sofőrök száma: " + sofőrök.size() + " fő");

        HashMap<String, Integer> sofőrHívásai = new HashMap<String, Integer>();
        for (String string : sofőrök) {
            sofőrHívásai.put(string, 0);
        }
        for (Bejegyzés bejegyzés : bejegyzések) {
            sofőrHívásai.put(bejegyzés.getBecenév(), sofőrHívásai.get(bejegyzés.getBecenév()) + bejegyzés.getAdásokSzáma());
        }
        int legtöbbHívásSzáma = 0;
        for (Integer value : sofőrHívásai.values()) {
            if (value > legtöbbHívásSzáma) {
                legtöbbHívásSzáma = value;
            }
        }
        System.out.println("9. feladat: Legtöbb adást indító sofőr");
        for (Object object : sofőrHívásai.keySet()) {
            if (sofőrHívásai.get(object) == legtöbbHívásSzáma) {
                System.out.println("\tNév: " + object);
                System.out.println("\tAdások száma: " + legtöbbHívásSzáma + " alkalom");
            }
        }
    }

    private static ArrayList<Bejegyzés> feltölt(String állomány) {
        ArrayList<Bejegyzés> result = new ArrayList<>();
        try {
            RandomAccessFile bemenet = new RandomAccessFile(állomány, "r");
            bemenet.readLine();
            while (bemenet.getFilePointer() < bemenet.length()) {
                result.add(new Bejegyzés(bemenet.readLine()));
            }
            bemenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return result;
    }

    public static int AtszamolPercre(int óra, int perc) {
        return óra * 60 + perc;
    }

}
