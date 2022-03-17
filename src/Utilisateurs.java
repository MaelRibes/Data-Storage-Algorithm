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

    public NoeudsSysteme meilleurEmplacement(@NotNull Donnees donnees){
        if (noeudAccessible.getCapaMemoire() > donnees.getTaille()){
            System.out.println("Le meilleur emplacement de la donnée " + donnees.getIdD() +" est le noeud " + noeudAccessible.getIdN());
            return noeudAccessible;
        }
        else {
            for (NoeudsSysteme noeud : noeudAccessible.getNoeudsAccessibles()) {
                if (noeud.getCapaMemoire() > donnees.getTaille()) {
                    System.out.println("Le meilleur emplacement de la donnée " + donnees.getIdD() + " est le noeud " + noeud.getIdN());
                    return noeud;
                }
            }
        }
        return null;
    }

    public void ajoutDonneesInteretToStockage(){
        for (Donnees donnees : donnesInteret){
            NoeudsSysteme meilleurEmplacement;
            meilleurEmplacement = this.meilleurEmplacement(donnees);
            meilleurEmplacement.ajoutDonneesStockage(donnees);
        }
    }

    public void ajoutDonneesInteret(Donnees donnees){
        this.donnesInteret.add(donnees);
    }



}
