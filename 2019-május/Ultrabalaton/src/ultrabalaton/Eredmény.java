package ultrabalaton;

import java.util.Arrays;
import java.util.Objects;

public class Eredmény implements Comparable<Eredmény> {

    private String név;
    private int rajtszám;
    private String kategória;
    private int[] időeredmény = new int[3];
    private int teljesített; // százalékban

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.név);
        hash = 97 * hash + this.rajtszám;
        hash = 97 * hash + Objects.hashCode(this.kategória);
        hash = 97 * hash + Arrays.hashCode(this.időeredmény);
        hash = 97 * hash + this.teljesített;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Eredmény other = (Eredmény) obj;
        if (this.rajtszám != other.rajtszám) {
            return false;
        }
        if (this.teljesített != other.teljesített) {
            return false;
        }
        if (!Objects.equals(this.név, other.név)) {
            return false;
        }
        if (!Objects.equals(this.kategória, other.kategória)) {
            return false;
        }
        if (!Arrays.equals(this.időeredmény, other.időeredmény)) {
            return false;
        }
        return true;
    }

    public Eredmény(String sor) {
        String[] darabok = sor.split(";");
        this.név = darabok[0];
        this.rajtszám = Integer.parseInt(darabok[1]);
        this.kategória = darabok[2];
        String[] idők = darabok[3].split(":");
        for (int i = 0; i < idők.length; i++) {
            this.időeredmény[i] = Integer.parseInt(idők[i]);
        }
        this.teljesített = Integer.parseInt(darabok[4]);
    }

    public boolean isFérfi() {
        return this.kategória.equals("Ferfi");
    }

    public boolean nőiTeljesTáv() {
        return this.teljesített == 100 && !isFérfi();
    }

    public boolean névEgyezikE(String megadott) {
        return (this.név.toLowerCase()).equals(megadott.toLowerCase());
    }

    public boolean teljesítetteE() {
        return this.teljesített == 100;
    }

    public String getNév() {
        return név;
    }

    public int getRajtszám() {
        return rajtszám;
    }

    public String getKategória() {
        return kategória;
    }

    public int[] getIdőeredmény() {
        return időeredmény;
    }

    public int getTeljesített() {
        return teljesített;
    }

    public double IdőÓrában() {
        return this.időeredmény[0] + (1.0 * this.időeredmény[1] / 60) + (1.0 * this.időeredmény[2] / 3600);
    }

    @Override
    public String toString() {
        return this.név + " (" + this.rajtszám + ".) - " + this.időeredmény[0] + ":" + this.időeredmény[1] + this.időeredmény[2];
    }

    @Override
    public int compareTo(Eredmény másik) {
        int result = másik.teljesített - this.teljesített;
        if (result == 0) {
            result = (int) (Math.round(this.IdőÓrában()) - Math.round(másik.IdőÓrában()));
        }
        return result;
    }

}
