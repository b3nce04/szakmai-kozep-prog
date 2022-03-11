package orvosinobeldijasok;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class OrvosiNobeldijasok {

    public static void main(String[] args) {
        ArrayList<Díj> díjak = feltölt("orvosi_nobeldijak.txt");

        System.out.println("3. feladat: Díjazottak száma: " + díjak.size() + " fő");

        int legnagyobbÉv = 0;
        for (Díj díj : díjak) {
            if (díj.getÉv() > legnagyobbÉv) {
                legnagyobbÉv = díj.getÉv();
            }
        }
        System.out.println("4. feladat: Utolsó év: " + legnagyobbÉv);

        System.out.print("5.feladat: Kérem adja meg egy ország kódját: ");
        Scanner be = new Scanner(System.in);
        String megadott = be.nextLine().toUpperCase();
        int száma = 0;
        Díj melyik = new Díj();
        for (Díj díj : díjak) {
            if (díj.getAdottOrszágE(megadott)) {
                száma++;
                melyik = díj;
            }
        }
        if (száma == 0) {
            System.out.println("\tA megadott országból nem volt díjazott!");
        } else if (száma == 1) {
            System.out.println("\tA megadott ország díjazottja:");
            System.out.println("\tNév: " + melyik.getNév());
            System.out.println("\tÉv: " + melyik.getÉv());
            System.out.println("\tSz/H: " + melyik.getSzülHal());
        } else if (száma > 1) {
            System.out.println("\tA megadott országból " + száma + " fő díjazott volt!");
        }
        
        System.out.println("6. Statisztika");
        TreeSet<String> országok = new TreeSet<>();
        for (Díj díj : díjak) {
            országok.add(díj.getOrszágKód());
        }
        for (String string : országok) {
            int szám = 0;
            for (Díj díj : díjak) {
                if (díj.getAdottOrszágE(string)) {
                    szám++;
                }
            }
            if (szám > 5) {
                System.out.println("\t"+string + " - " + szám + " fő");
            }
        }
        
        double átlag = 0;
        int ismet = 0;
        for (Díj díj : díjak) {
            Elethossz hossz = new Elethossz(díj.getSzülHal());
            if (hossz.ismertElethossz()) {
                átlag += hossz.elethosszEvekben();
                ismet++;
            }
        }
        System.out.println(String.format("A keresett átlag: %.1f év", átlag / ismet*1.0));
    }

    private static ArrayList<Díj> feltölt(String txt) {
        ArrayList<Díj> result = new ArrayList<>();
        List<String> sorok = new ArrayList<>();
        try {
            sorok = Files.readAllLines(Paths.get(txt), Charset.forName("utf-8"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sorok.remove(0);
        for (String elem : sorok) {
            result.add(new Díj(elem));
        }
        return result;
    }

}
