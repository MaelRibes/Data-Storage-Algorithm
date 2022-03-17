public class Donnees {

    private final int idD;
    private final int taille;
    private static int idDonnes;


    // Constructor
    public Donnees(int taille) {
        this.taille = taille;
        this.idD = idDonnes;
        idDonnes++;
    }

    // Getter
    public int getIdD() {
        return idD;
    }
    public int getTaille() {
        return taille;
    }
    public static int getIdDonnes() { // Return the number of data created
        return idDonnes;
    }

    @Override
    public String toString() {
        return "Donnees{" +
                "idD=" + idD +
                ", taille=" + taille +
                '}';
    }
}
