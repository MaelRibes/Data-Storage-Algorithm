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
            System.out.println("Le meilleur emplacement de "+ donn);
            System.out.println(noeudAccessible);
            return noeudAccessible;
        }
        else {
            for (NoeudsSysteme noeud : noeudAccessible.getNoeudsAccessibles()){
                if (noeud.getCapaMemoire() > donnees.getTaille()){
                    System.out.println(noeud);
                    return noeud;
                }
        }
    }

    public void ajoutDonnees(){
        for (Donnees donnees : donnesInteret){
            boolean commu = true;
            if (noeudAccessible.getCapaMemoire() > donnees.getTaille()){
                noeudAccessible.getDonnesStockees().add(donnees);
                noeudAccessible.setCapaMemoire(noeudAccessible.getCapaMemoire() - donnees.getTaille());
                commu = false;
            }
            else {
                for (NoeudsSysteme noeud : noeudAccessible.getNoeudsAccessibles()){
                    if (noeud.getCapaMemoire() > donnees.getTaille() && commu){
                        noeud.getDonnesStockees().add(donnees);
                        noeud.setCapaMemoire(noeud.getCapaMemoire() - donnees.getTaille());
                        commu = false;
                    }
                }
            }
            if (commu){
                System.out.println("Pas assez d'espace dans le système pour stocker la donnée "+ donnees.getIdD());
            }
            
        }
    }
}
