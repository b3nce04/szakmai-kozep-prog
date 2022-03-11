package uzemanyag;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Uzemanyag {

    public static void main(String[] args) {
        ArrayList<Változás> változások = feltölt("uzemanyag.txt");

        System.out.println("3. feladat: Változások száma: " + változások.size());

        int minKülönbség = 1;
        for (Változás változás : változások) {
            if (változás.getKülönbség() < minKülönbség) {
                minKülönbség = változás.getKülönbség();
            }
        }
        System.out.println("4. feladat: A legkisebb különbség: " + minKülönbség);

        int előfordulás = 0;
        for (Változás változás : változások) {
            if (változás.getKülönbségEgyezik(minKülönbség)) {
                előfordulás++;
            }
        }
        System.out.println("5. feladat: A legkisebb különbség előfordulása: " + előfordulás);

        boolean találat = false;
        for (Változás változás : változások) {
            if (változás.getSzökőNapE() && getSzökőÉvE(változás.getDátumÉv())) {
                találat = true;
                break;
            }
        }
        System.out.println("6. feladat: " + (találat ? "Volt" : "Nem volt") + " változás szökőnapon!");

        try {
            RandomAccessFile kimenet = new RandomAccessFile("euro.txt", "rw");
            kimenet.setLength(0);
            for (Változás változás : változások) {
                kimenet.writeBytes(változás.toString() + "\r\n");
            }
            kimenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }

        Scanner beBill = new Scanner(System.in);
        int megadottÉv = 0;
        do {
            System.out.print("8. feladat: Kérem adja meg az évszámot [2011..2016]: ");
            megadottÉv = Integer.parseInt(beBill.nextLine());
        } while (!(megadottÉv >= 2011 && megadottÉv <= 2016));

        int leghoszabb = 0;
        for (int i = 1; i < változások.size(); i++) {
            if (változások.get(i).getAdottÉvE(megadottÉv)) {
                int előző = i - 1;
                int eltelt = árváltozásNapok(változások.get(i).getDátumÉv(), változások.get(előző).getDátumHónap(), változások.get(i).getDátumHónap(), változások.get(előző).getDátumNap(), változások.get(i).getDátumNap());
                if (eltelt > leghoszabb) {
                    leghoszabb = eltelt;
                }
            }
        }
        System.out.println("10. feladat: " + megadottÉv + " évben a leghosszabb időszak " + leghoszabb + " nap volt.");
    }

    private static ArrayList<Változás> feltölt(String állomány) {
        ArrayList<Változás> result = new ArrayList<>();
        try {
            RandomAccessFile bemenet = new RandomAccessFile(állomány, "r");
            while (bemenet.getFilePointer() < bemenet.length()) {
                result.add(new Változás(bemenet.readLine()));
            }
            bemenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return result;
    }

    private static boolean getSzökőÉvE(int megadottÉv) {
        return megadottÉv % 4 == 0;
    }

    private static int árváltozásNapok(int megadottÉv, int előzőHónap, int hónap, int előzőVáltozásNap, int aktuálisVáltozásNap) {
        int[] napokSzáma = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (getSzökőÉvE(megadottÉv)) {
            napokSzáma[1] = 29;
        }
        if (előzőHónap == hónap) {
            return aktuálisVáltozásNap - előzőVáltozásNap;
        } else {
            return napokSzáma[előzőHónap - 1] - előzőVáltozásNap + aktuálisVáltozásNap;
        }
    }

}
