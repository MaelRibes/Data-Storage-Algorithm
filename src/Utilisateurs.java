import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Utilisateurs {

    private final int idU;
    private static int idUtilisateurs;
    private final ArrayList<Donnees> donnesInteret = new ArrayList<>();
    private final NoeudsSysteme noeudAccessible;
    private static final ArrayList<Utilisateurs> listUtilisateurs = new ArrayList<>();

    // Constructor
    public Utilisateurs(@NotNull NoeudsSysteme noeudAccessible) {
        this.noeudAccessible = noeudAccessible;
        this.idU = idUtilisateurs;
        idUtilisateurs++;
        noeudAccessible.ajoutUtilisateurAccessible(this);
        listUtilisateurs.add(this);
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
    public static ArrayList<Utilisateurs> getListUtilisateurs() {
        return listUtilisateurs;
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


    @Override
    public String toString() {
        ArrayList<Integer> dInteret = new ArrayList<>();
        for (Donnees donnee : donnesInteret){
            dInteret.add(donnee.getIdD());
        }
        return "Utilisateurs{" +
                "idU=" + idU +
                ", donnesInteret=" + dInteret +
                ", noeudAccessible=" + noeudAccessible.getIdN() +
                '}';
    }



}
