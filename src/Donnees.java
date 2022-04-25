import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Donnees {

    private final int idD;
    private final int taille;
    private static int idDonnes;
    private ArrayList<Utilisateurs> utilisateursInteret = new ArrayList<>();
    private static ArrayList<Donnees> listDonnees = new ArrayList<>();


    // Constructor
    public Donnees(int taille) {
        this.taille = taille;
        this.idD = idDonnes;
        idDonnes++;
        listDonnees.add(this);
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
    public ArrayList<Utilisateurs> getUtilisateursInteret() {
        return utilisateursInteret;
    }
    public static ArrayList<Donnees> getListDonnees() {
        return listDonnees;
    }

    public static NoeudsSysteme meilleurEmplacement(@NotNull Donnees donnees, double[] distances) {
        ArrayList<Double> arrayDistances = new ArrayList<>();
        for (double dist : distances) {
            arrayDistances.add(dist);
        }
        NoeudsSysteme noeudCourant;
        NoeudsSysteme noeudChoisi = null;
        while (arrayDistances.size() > 0) {
            int indice = minimumTableau(distances);
            noeudCourant = NoeudsSysteme.getListNoeuds().get(indice);
            if (noeudCourant.getCapaMemoire() >= donnees.getTaille()) {
                noeudChoisi = noeudCourant;
                return noeudChoisi;
            } else {
                arrayDistances.remove(indice);
                distances[indice] = Double.POSITIVE_INFINITY;
            }
        }
        System.out.println("Pas de place pour la donnée n°" + donnees.getIdD());
        return noeudChoisi;
    }

    // Retourne l'indice de la valeur minimale d'un tableau
    public static int minimumTableau(double[] tab){
        double min = Double.MAX_VALUE;
        int indice = 0;
        for(int i=0 ; i<tab.length ; i++){
            if(tab[i]<min){
                indice = i;
                min = tab[i];
            }
        }
        return indice;
    }

    public static double[] sumTableau(double[] t1, double[] t2){
        double[] newTab = new double[t1.length];
        for(int i=0 ; i<t1.length ; i++){
            newTab[i] = t1[i] + t2[i];
            }
        return newTab;
    }

    public static void placerToutesDonnees() {
        NoeudsSysteme meilleurEmplacement;
        Utilisateurs utilisateurs;
        double[] tabDist = new double[NoeudsSysteme.getIdNoeuds()];
        for (Donnees donnees : listDonnees) {
            if (donnees.getUtilisateursInteret().size() == 0){
                System.out.println("La donnée n°" + donnees.getIdD()+ " n'est demandée par aucun utilisateur : impossible de la placer");
            }
            else if (donnees.getUtilisateursInteret().size() == 1) {
                utilisateurs = donnees.getUtilisateursInteret().get(0);
                tabDist = utilisateurs.getNoeudAccessible().dijkstra();
            }
            else if (donnees.getUtilisateursInteret().size() > 1) {
                for (Utilisateurs utilisateur : donnees.getUtilisateursInteret()) {
                    tabDist = sumTableau(tabDist,utilisateur.getNoeudAccessible().dijkstra());
                }

            }
            meilleurEmplacement = meilleurEmplacement(donnees,tabDist);
            if (meilleurEmplacement != null) {
                meilleurEmplacement.ajoutDonneesStockage(donnees);
            }

        }
    }

    /***
     * Méthode triant la liste des données par ordre decroissant de taille.
     */
    public static void decreasingSort(){
        Donnees data1 = null;
        Donnees data2 = null;
        for(int j = 0 ; j < Donnees.getIdDonnes();j++){
            for(int i = 0 ; i < Donnees.getIdDonnes()-1;i++){
                if(Donnees.listDonnees.get(i).getTaille() < Donnees.listDonnees.get(i + 1).getTaille()){
                    data1 = Donnees.listDonnees.get(i);
                    data2 = Donnees.listDonnees.get(i+1);
                    Donnees.listDonnees.set(i,data2);
                    Donnees.listDonnees.set(i+1,data1);
                }
            };
        }
    }

    @Override
    public String toString() {
        return "Donnees{" +
                "idD=" + idD +
                ", taille=" + taille +
                '}';
    }

    public ArrayList<NoeudsSysteme> getListNoeudsPotentiels() {
        ArrayList<NoeudsSysteme> noeudsPotentiels = new ArrayList<>();
        ArrayList<NoeudsSysteme> allNoeuds = NoeudsSysteme.getListNoeuds();
        for (NoeudsSysteme n : allNoeuds) {
            if (n.getCapaMemoire() >= this.getTaille()) {
                noeudsPotentiels.add(n);
            }
        }
        return noeudsPotentiels;
    }

}
