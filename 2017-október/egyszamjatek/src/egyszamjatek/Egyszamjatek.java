package egyszamjatek;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Egyszamjatek {

    public static void main(String[] args) {
        ArrayList<Játék> játékok = feltölt("egyszamjatek.txt");

        System.out.println("3. feladat: Játékosok száma: " + játékok.size());

        System.out.println("4. feladat: Fordulók száma: " + játékok.get(0).getFordulókSzáma());

        boolean találatEgyes = false;
        for (Játék játék : játékok) {
            if (játék.getFordulóTipp(0) == 1) {
                találatEgyes = true;
                break;
            }
        }
        System.out.println("5. feladat: Az első fordulóban " + (találatEgyes ? "volt" : "nem volt") + " egyes tipp!");

        int maxTipp = 0;
        for (Játék játék : játékok) {
            if (játék.getLegnagyobbTipp() > maxTipp) {
                maxTipp = játék.getLegnagyobbTipp();
            }
        }
        System.out.println("6. feladat: A legnagyobb tipp a fordulók során: " + maxTipp);

        Scanner beBill = new Scanner(System.in);
        int megadottSorszám = 1;
        System.out.print("7. feladat: Kérem a forduló sorszámát [1-10]: ");
        megadottSorszám = beBill.nextInt();
        if (!(megadottSorszám >= 1 && megadottSorszám <= 10)) {
            megadottSorszám = 1;
        }

        TreeSet<Integer> tippek = new TreeSet<>();
        for (Játék játék : játékok) {
            tippek.add(játék.getFordulóTipp(megadottSorszám - 1));
        }
        boolean találatNyertes = false;
        int nyertesTipp = 100;
        for (Integer tipp : tippek) {
            int előfordulás = 0;
            for (Játék játék : játékok) {
                if (játék.getFordulóTipp(megadottSorszám - 1) == tipp) {
                    előfordulás++;
                }
            }
            if (előfordulás == 1 && tipp < nyertesTipp) {
                nyertesTipp = tipp;
                találatNyertes = true;
                break;
            }
        }
        if (találatNyertes) {
            System.out.println("8. feladat: A nyertes tipp a megadott fordulóban: " + nyertesTipp);
        } else {
            System.out.println("8. feladat: Nem volt egyedi tipp a megadott fordulóban!");
        }

        String nyertesNév = "";
        for (Játék játék : játékok) {
            if (játék.getFordulóTipp(megadottSorszám - 1) == nyertesTipp) {
                nyertesNév = játék.getJátékos();
            }
        }
        if (találatNyertes) {
            System.out.println("9. feladat: A megadott forduló nyertese: " + nyertesNév);
        } else {
            System.out.println("9. feladat: Nem volt egyedi tipp a megadott fordulóban!");
        }

        if (találatNyertes) {
            try {
                RandomAccessFile kimenet = new RandomAccessFile("nyertes.txt", "rw");
                kimenet.setLength(0);
                kimenet.writeBytes("Forduló sorszáma: " + megadottSorszám + ".\r\n");
                kimenet.writeBytes("Nyertes tipp: " + nyertesTipp + "\r\n");
                kimenet.writeBytes("Nyertes Játékos " + nyertesNév + "\r\n");
                kimenet.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static ArrayList<Játék> feltölt(String állomány) {
        ArrayList<Játék> result = new ArrayList<>();
        try {
            RandomAccessFile bemenet = new RandomAccessFile(állomány, "r");
            while (bemenet.getFilePointer() < bemenet.length()) {
                result.add(new Játék(bemenet.readLine()));
            }
            bemenet.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
