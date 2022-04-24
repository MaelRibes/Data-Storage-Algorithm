import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Utilisateurs {

    private final int idU;
    private static int idUtilisateurs;
    private ArrayList<Donnees> donnesInteret = new ArrayList<>();
    private NoeudsSysteme noeudAccessible;

    // Constructor
    public Utilisateurs(ArrayList<Donnees> donnesInteret, NoeudsSysteme noeudAccessible) {
        this.donnesInteret = donnesInteret;
        this.noeudAccessible = noeudAccessible;
        this.idU = idUtilisateurs;
        idUtilisateurs++;
    }
    public Utilisateurs(NoeudsSysteme noeudAccessible) {
        this.noeudAccessible = noeudAccessible;
        this.idU = idUtilisateurs;
        idUtilisateurs++;
    }

    // Getter
    public int getIdU() {
        return idU;
    }
    public static int getIdUtilisateurs() { // Return the number of utilisators created
        return idUtilisateurs;
    }
    public ArrayList<Donnees> getDonnesInteret() {
        return donnesInteret;
    }
    public NoeudsSysteme getNoeudAccessible() {
        return noeudAccessible;
    }

    @Override
    public String toString() {
        return "Utilisateurs{" +
                "idU=" + idU +
                ", donnesInteret=" + donnesInteret +
                ", noeudAccessible=" + noeudAccessible.getIdN() +
                '}';
    }


    public void ajoutDonneesInteretToStockage(){
        NoeudsSysteme meilleurEmplacement;
        for (Donnees donnees : donnesInteret){
            meilleurEmplacement = Donnees.meilleurEmplacement(donnees,this.getNoeudAccessible().dijkstra());
            if (meilleurEmplacement != null){
                meilleurEmplacement.ajoutDonneesStockage(donnees);
                System.out.println("Donnée n°" + donnees.getIdD() + " placée dans le noeud n°" + meilleurEmplacement.getIdN());
            }
        }
    }

    public void ajoutDonneesInteret(Donnees donnees){
        this.donnesInteret.add(donnees);
        donnees.getUtilisateursInteret().add(this);
    }




}
