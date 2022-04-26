import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Donnees {

    private final int idD;
    private final int taille;
    private static int idDonnes;
    private final ArrayList<Utilisateurs> utilisateursInteret = new ArrayList<>();
    private final static ArrayList<Donnees> listDonnees = new ArrayList<>();


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

    /***
     * @param donnees : donnée à placer
     * @param distances : tableau de distance
     * @return le nœud le plus proche où placer la donnée
     */
    public static NoeudsSysteme meilleurEmplacement(@NotNull Donnees donnees, double[] distances) {
        int cpt = 0;
        NoeudsSysteme noeudCourant;
        NoeudsSysteme noeudChoisi = null;
        while (distances.length > cpt) {
            int indice = minimumTableau(distances);
            noeudCourant = NoeudsSysteme.getListNoeuds().get(indice);
            if (noeudCourant.getCapaMemoire() >= donnees.getTaille()) {
                noeudChoisi = noeudCourant;
                return noeudChoisi;
            } else {
                distances[indice] = Double.POSITIVE_INFINITY;
            }
            cpt++;
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

    /***
     *  Cette méthode place toutes les données étant demandée par un ou plusieurs
     *  utilisateurs au plus proche de ceux-ci en prenant en compte le cas où plusieurs
     *  demandent la donnée.
     */
    public static void placerToutesDonnees() {
        NoeudsSysteme meilleurEmplacement;
        Utilisateurs utilisateurs;
        double[] tabDist = new double[NoeudsSysteme.getIdNoeuds()];
        for (Donnees donnees : listDonnees) {
            // si la donnée n'est demandée par aucun utilisateur, on passe.
            if (donnees.getUtilisateursInteret().size() == 0){
                System.out.println("La donnée n°" + donnees.getIdD() +
                " n'est demandée par aucun utilisateur : impossible de la placer");
            }
            // si demandée par 1 util., on récupère le tableau de distance de son noeudAccessible
            else if (donnees.getUtilisateursInteret().size() == 1) {
                utilisateurs = donnees.getUtilisateursInteret().get(0);
                tabDist = utilisateurs.getNoeudAccessible().dijkstra();
            }
            // si demandée par plusieurs utili,
            // on récupère le tableau de distance du noeudAccessible de chaque utilisateur
            // et on les somme pour obtenir un unique tableau.
            else if (donnees.getUtilisateursInteret().size() > 1) {
                for (Utilisateurs utilisateur : donnees.getUtilisateursInteret()) {
                    tabDist = sumTableau(tabDist,utilisateur.getNoeudAccessible().dijkstra());
                }
            }
            // On recherche le meilleur emplacement grace au tableau calculé précédemment
            meilleurEmplacement = meilleurEmplacement(donnees,tabDist);
            if (meilleurEmplacement != null) {
                meilleurEmplacement.ajoutDonneesStockage(donnees);
            }
        }
    }

    /***
     * Méthode triant la liste des données par ordre décroissant de taille.
     */
    public static void triDecroissant(){
        Donnees data1, data2;
        ArrayList<Donnees> listD = Donnees.listDonnees;
        for(int j = 0 ; j < Donnees.getIdDonnes();j++){
            for(int i = 0 ; i < Donnees.getIdDonnes()-1;i++){
                if(listD.get(i).getTaille() < listD.get(i + 1).getTaille()){
                    data1 = listD.get(i);
                    data2 = listD.get(i+1);
                    listD.set(i,data2);
                    listD.set(i+1,data1);
                }
            }
        }
    }

    /***
     * Méthode qui cherche le nœud où il restera le moins
     * de capacité mémoire après le placement de la donnée
     * @param donnees : donnée à placer
     * @return NoeudsSysteme
     */
    public static NoeudsSysteme plusPetitEcart(Donnees donnees){
        NoeudsSysteme node = null;
        int ecart = Integer.MAX_VALUE;
        int gap;
        for( NoeudsSysteme noeudsSysteme : NoeudsSysteme.getListNoeuds()){
            gap = noeudsSysteme.getCapaMemoire() - donnees.getTaille();
            if(gap < ecart && gap >= 0){
                node = noeudsSysteme;
                ecart = gap;
            }
        }
        return node;
    }

    /***
     * Cette méthode cherche à placer les données non pas en essayant de la placer au plus proche de l'utilisateur,
     * mais en essayant d'optimiser l'espace de stockage. Elle place les plus grandes données d'abord dans le nœuds le plus petit possible.
     */
    public static void mkpProblem(){
        triDecroissant();
        NoeudsSysteme nodeResult;
        for( Donnees donnees  : listDonnees){
            nodeResult = plusPetitEcart(donnees);
            if(nodeResult != null){
                nodeResult.ajoutDonneesStockage(donnees);
            }
            else{
                System.out.println("Pas de place pour la donnée n°" + donnees.getIdD());
            }
        }
    }

    @Override
    public String toString() {
        return "Donnees{" +
                "idD=" + idD +
                ", taille=" + taille +
                '}';
    }


}
