package helsinki1952;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Helsinki1952 {

    public static void main(String[] args) {
        ArrayList<Helyezés> helyezések = feltölt("helsinki.txt");
        
        System.out.println("3. feladat:");
        System.out.println("Pontszerző helyezések száma: " + helyezések.size());
        
        System.out.println("4. feladat:");
        String[] nevek = {"arany", "ezüst", "bronz"};
        int[] darabok = new int[3];
        for (int i = 0; i < darabok.length; i++) {
            int darab = 0;
            for (Helyezés helyezés : helyezések) {
                if (helyezés.getHelyezés() == i+1) {
                    darab += 1;
                }
            }
            darabok[i] = darab;
        }
        for (int i = 0; i < nevek.length; i++) {
            System.out.println(nagyKezdőbetű(nevek[i]) + ": " + darabok[i]);
        }
        
        System.out.println("5. feladat:");
        int pontokSzáma = 0;
        for (Helyezés helyezés : helyezések) {
            if (helyezés.getHelyezés() <= 6) {
                pontokSzáma += helyezés.getOlimpiaiPont();
            }
        }
        System.out.println("Olimpiai pontok száma: " + pontokSzáma);
        
        System.out.println("6. feladat");
        HashMap<String, Integer> sportágÉrem = new HashMap<>();
        for (Helyezés helyezés : helyezések) {
            if (!sportágÉrem.containsKey(helyezés.getSportág())) {
                sportágÉrem.put(helyezés.getSportág(), helyezés.getDarab());
            } else {
                sportágÉrem.put(helyezés.getSportág(), sportágÉrem.get(helyezés.getSportág()) + helyezés.getDarab());
            }
        }
        int legtöbb = 0;
        String sportág = "";
        for (String elem : sportágÉrem.keySet()) {
            if (sportágÉrem.get(elem) > legtöbb) {
                legtöbb = sportágÉrem.get(elem);
                sportág = elem;
            }
        }
        System.out.println(nagyKezdőbetű(sportág) + " sportágban szereztek több érmet");
        
        try {
            RandomAccessFile kimenet = new RandomAccessFile("helsinki2.txt", "rw");
            kimenet.setLength(0);
            for (Helyezés helyezés : helyezések) {
                kimenet.writeBytes(helyezés.toString() + "\r\n");
            }
            kimenet.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("8. feladat:");
        int legtöbbSportolóIndex = 0;
        for (int i = 0; i < helyezések.size(); i++) {
            if (helyezések.get(i).getDarab() > helyezések.get(legtöbbSportolóIndex).getDarab()) {
                legtöbbSportolóIndex = i;
            }
        }
        System.out.println("Helyezés: " + helyezések.get(legtöbbSportolóIndex).getHelyezés());
        System.out.println("Sportág: " + helyezések.get(legtöbbSportolóIndex).getSportág());
        System.out.println("Versenyszám: " + helyezések.get(legtöbbSportolóIndex).getVersenyszám());
        System.out.println("Sportolók száma: " + helyezések.get(legtöbbSportolóIndex).getDarab());
    }
    
    public static String nagyKezdőbetű(String bemenet) {
        return bemenet.toUpperCase().charAt(0) + bemenet.substring(1);
    }

    private static ArrayList<Helyezés> feltölt(String állomány) {
        ArrayList<Helyezés> result = new ArrayList<>();
        try {
            RandomAccessFile bemenet = new RandomAccessFile("helsinki.txt", "r");
            while (bemenet.getFilePointer() < bemenet.length()) {                
                result.add(new Helyezés(bemenet.readLine()));
            }
            bemenet.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}