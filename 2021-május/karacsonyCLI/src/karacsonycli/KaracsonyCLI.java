package karacsonycli;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class KaracsonyCLI {

    public static void main(String[] args) {
        ArrayList<NapiBevetel> munkák = feltölt("diszek.txt");
        
        int elkészültek = 0;
        for (NapiBevetel napiBevetel : munkák) {
            elkészültek += napiBevetel.getAngyalkaKesz() + napiBevetel.getFenyofaKesz() + napiBevetel.getHarangKesz();
        }
        System.out.println("4. feladat: Összesen " + elkészültek + " darab dísz készült.");
        System.out.println("");
        
        TreeSet<Integer> napok = new TreeSet<>();
        for (NapiBevetel napiBevetel : munkák) {
            napok.add(napiBevetel.getNap());
        }
        boolean volt = false;
        for (Integer nap : napok) {
            int naponElkészültek = 0;
            for (NapiBevetel napiBevetel : munkák) {
                if (napiBevetel.getAdottNapE(nap)) {
                    elkészültek += napiBevetel.getAngyalkaKesz() + napiBevetel.getFenyofaKesz() + napiBevetel.getHarangKesz();
                }
            }
            if (naponElkészültek == 0) {
                volt = true;
                break;
            }
        }
        if (volt) {
            System.out.println("5. feladat: Volt olyan nap, amikor egyetlen dísz sem készült.");
        } else {
            System.out.println("5. feladat: Nem volt olyan nap, amikor egyetlen dísz sem készült.");
        }
        System.out.println("");
        
        System.out.println("6.feladat:");
        Scanner scan = new Scanner(System.in);
        int bekért;
        do {            
            System.out.print("Adja meg a keresett napot [1 ... 40]: ");
            bekért = scan.nextInt();
        } while (!(bekért >= 1 && bekért <= 40));
        int harang = 0;
        int angyalka = 0;
        int fenyőfa = 0;
        for (NapiBevetel napiBevetel : munkák) {
            harang += napiBevetel.getHarangKészleten();
            angyalka += napiBevetel.getAngyalKészleten();
            fenyőfa += napiBevetel.getFenyofaKészleten();
            if (napiBevetel.getAdottNapE(bekért)) {
                break;
            }
        }
        System.out.println(String.format("\tA(z) %d. nap végén %d harang, %d angyalka és %d fenyőfa maradt készleten.", bekért, harang, angyalka, fenyőfa));
        System.out.println("");
        
        HashMap<String, Integer> névEladott = new HashMap<String, Integer>();
        névEladott.put("Harang", 0);
        névEladott.put("Angyalka", 0);
        névEladott.put("Fenyőfa", 0);
        for (NapiBevetel napiBevetel : munkák) {
            névEladott.replace("Harang", névEladott.get("Harang") + napiBevetel.getHarangEladott());
            névEladott.replace("Angyalka", névEladott.get("Angyalka") + napiBevetel.getAngyalkaEladott());
            névEladott.replace("Fenyőfa", névEladott.get("Fenyőfa") + napiBevetel.getFenyofaEladott());
        }
        int maxEladottMenny = 0;
        for (String elem : névEladott.keySet()) {
            if (névEladott.get(elem) > maxEladottMenny) {
                maxEladottMenny = névEladott.get(elem);
            }
        }
        System.out.println("7. feladat: Legtöbbet eladott dísz: " + maxEladottMenny+ " darab");
        for (String elem : névEladott.keySet()) {
            if (névEladott.get(elem) == maxEladottMenny) {
                System.out.println("\t" + elem);
            }
        }
        
        try {
            RandomAccessFile kimenet = new RandomAccessFile("bevetel.txt", "rw");
            kimenet.setLength(0);
            int darab = 0;
            for (NapiBevetel napiBevetel : munkák) {
                int bevétel = napiBevetel.napiBevetel();
                if (bevétel >= 10000) {
                    darab++;
                    kimenet.writeBytes(napiBevetel.getNap() + ";" + bevétel + "\r\n");
                }
            }
            kimenet.writeBytes(darab + " napon volt legalább 10000 Ft a bevétel.");
            kimenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    private static ArrayList<NapiBevetel> feltölt(String állomány) {
        ArrayList<NapiBevetel> result = new ArrayList<>();
        try {
            RandomAccessFile bemenet = new RandomAccessFile(állomány, "r");
            while (bemenet.getFilePointer() < bemenet.length()) {                
                result.add(new NapiBevetel(bemenet.readLine()));
            }
            bemenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return result;
    }

}