package ultrabalaton;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ultrabalaton {

    public static void main(String[] args) {
        ArrayList<Eredmény> eredmények = feltölt("ub2017egyeni.txt");
        System.out.println("3. feladat: Egyéni indulók: " + eredmények.size() + " fő");
        
        int összeg = 0;
        for (Eredmény eredmény : eredmények) {
            if (eredmény.nőiTeljesTáv()) {
                összeg++;
            }
        }
        System.out.println("4. feladat: Célba érkező npi sportolók: " + összeg + " fő");
        
        Scanner beBill = new Scanner(System.in);
        System.out.print("5. feladat: Kérem a sportoló nevét: ");
        String megadottNév = beBill.nextLine();
        int találtIndex = -1;
        for (int i = 0; i < eredmények.size(); i++) {
            if (eredmények.get(i).névEgyezikE(megadottNév)) {
                találtIndex = i;
                break;
            }
        }
        System.out.println("\tIndult egyéniben a sportoló? " + (találtIndex > -1 ? "Igen" : "Nem"));
        if (találtIndex > -1) {
            System.out.println("\tTeljesítette a teljes távot? " + (eredmények.get(találtIndex).teljesítetteE() ? "Igen" : "Nem"));
        }
        
        int darab = 0;
        double átlagIdő = 0;
        for (Eredmény eredmény : eredmények) {
            if (eredmény.isFérfi() && eredmény.teljesítetteE()) {
                darab++;
                átlagIdő += eredmény.IdőÓrában();
            }
        }
        System.out.println(String.format("7. feladat: Átlagos idő: %f óra", (átlagIdő / darab * 1.0)));
        
        System.out.println("8. feladat: Verseny győztesei");
        ArrayList<Eredmény> nők = new ArrayList<>();
        ArrayList<Eredmény> férfiak = new ArrayList<>();
        for (Eredmény eredmény : eredmények) {
            if (!eredmény.isFérfi()) {
                nők.add(eredmény);
            } else {
                férfiak.add(eredmény);
            }
        }
        Collections.sort(nők);
        Collections.sort(férfiak);
        System.out.println("\tNők: " + nők.get(0).toString());
        System.out.println("\tFérfiak: " + férfiak.get(0).toString());
    }

    private static ArrayList<Eredmény> feltölt(String állomány) {
        ArrayList<Eredmény> result = new ArrayList<>();
        try {
            RandomAccessFile bemenet = new RandomAccessFile(állomány, "r");
            bemenet.readLine();
            while (bemenet.getFilePointer() < bemenet.length()) {                
                result.add(new Eredmény(bemenet.readLine()));
            }
            bemenet.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return result;
    }

}