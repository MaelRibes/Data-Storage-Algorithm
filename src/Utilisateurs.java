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

    // Getter
    public int getIdU() {
        return idU;
    }
    // Return the number of utilisators created
    public static int getIdUtilisateurs() {
        return idUtilisateurs;
    }
    public ArrayList<Donnees> getDonnesInteret() {
        return donnesInteret;
    }
    public NoeudsSysteme getNoeudAccessible() {
        return noeudAccessible;
    }


    public NoeudsSysteme meilleurEmplacement(@NotNull Donnees donnees){
        if (noeudAccessible.getCapaMemoire() > donnees.getTaille()){
            System.out.println("Le meilleur emplacement de la donnée " + donnees.getIdD() +" est :");
            System.out.println(noeudAccessible);
            return noeudAccessible;
        }
        else {
            for (NoeudsSysteme noeud : noeudAccessible.getNoeudsAccessibles()) {
                if (noeud.getCapaMemoire() > donnees.getTaille()) {
                    System.out.println("Le meilleur emplacement de la donnée " + donnees.getIdD() + " est :");
                    System.out.println(noeudAccessible);
                    return noeud;
                }
            }
        }
        return null;
    }


}
