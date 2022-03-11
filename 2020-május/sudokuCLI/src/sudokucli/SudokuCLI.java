package sudokucli;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SudokuCLI {

    public static void main(String[] args) {
        ArrayList<Feladvány> feladványok = feltölt("feladvanyok.txt");
        
        System.out.println("3. feladat: Beolvasva " + feladványok.size() + " feladvány.");
        System.out.println("");
        
        Scanner scan = new Scanner(System.in);
        int méret = 0;
        do {            
            System.out.print("4. feladat: Kérem a feladvány méretét [4..9]: ");
            méret = Integer.parseInt(scan.nextLine());
        } while (!(méret >= 4 && méret <= 9));
        int darab = 0;
        ArrayList<Feladvány> kigyűjtöttek = new ArrayList<>();
        for (Feladvány feladvány : feladványok) {
            if (feladvány.getMeret() == méret) {
                darab++;
                kigyűjtöttek.add(feladvány);
            }
        }
        System.out.println(méret + "x" + méret + " feladványból " + darab + " darab van tárolva");
        System.out.println("");
        
        System.out.println("5. feladat: A kiválasztott feladvány:");
        Random random = new Random();
        Feladvány kiválasztott = kigyűjtöttek.get(random.nextInt(kigyűjtöttek.size()-1));
        System.out.println(kiválasztott.toString());
        System.out.println("");
        
        int töltött = 0;
        for (int i = 0; i < kiválasztott.toString().length(); i++) {
            char kar = kiválasztott.toString().charAt(i);
            if (kar != '0') {
                töltött++;
            }
        }
        System.out.println(String.format("6. feladat: A feladvány kitöltöttsége: %d%%", Math.round((1.0*töltött/kiválasztott.kezdo.length()) * 100)));
        System.out.println("");
        
        System.out.println("7. feladat: A feladvány kirajzolva");
        kiválasztott.kirajzol();
        System.out.println("");
        
        try {
            RandomAccessFile kimenet = new RandomAccessFile("sudoku"+méret+".txt", "rw");
            kimenet.setLength(0);
            for (Feladvány feladvány : kigyűjtöttek) {
                if (feladvány.adottMéret(méret)) {
                    kimenet.writeBytes(feladvány.toString()+"\r\n");
                }
            }
            kimenet.close();
            System.out.println("8. feladat: sudoku" + méret + ".txt állomány 24 darab feladvánnyal létrehozva");
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    private static ArrayList<Feladvány> feltölt(String állomány) {
        ArrayList<Feladvány> result = new ArrayList<>();
        try {
            RandomAccessFile bemenet = new RandomAccessFile(állomány, "r");
            while (bemenet.getFilePointer() < bemenet.length()) {                
                result.add(new Feladvány(bemenet.readLine()));
            }
            bemenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return result;
    }
}