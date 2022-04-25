import java.util.ArrayList;
import java.util.Arrays;

public class NoeudsSysteme {

    private final int idN;
    private static int idNoeuds = 0;
    private int capaMemoire;
    private ArrayList<Donnees> donnesStockees = new ArrayList<>();
    private ArrayList<NoeudsSysteme> noeudsAccessibles = new ArrayList<>();
    private ArrayList<Utilisateurs> utilisateursAccessibles = new ArrayList<>();
    private static Matrice matrice;
    private static ArrayList<NoeudsSysteme> listNoeuds = new ArrayList<>();

    // Constructor
    /**
     * On créé un noeud. Si c'est le premier créé, on initialise la matrice d'ajacence avec ce noeud.
     *
     */
    public NoeudsSysteme(int capaMemoire) {
        this.capaMemoire = capaMemoire;
        this.idN = idNoeuds;

        if (idNoeuds == 0) {
            matrice = new Matrice();
        }
        matrice.updateMatrice();
        listNoeuds.add(this);
        idNoeuds++;
    }

    // Getter
    public int getIdN() {
        return idN;
    }
    public static int getIdNoeuds(){ // Return the number of nodes created
        return idNoeuds;
    }
    public int getCapaMemoire() {
        return capaMemoire;
    }
    public ArrayList<Donnees> getDonnesStockees() {
        return donnesStockees;
    }
    public ArrayList<NoeudsSysteme> getNoeudsAccessibles() {
        return noeudsAccessibles;
    }
    public ArrayList<Utilisateurs> getUtilisateursAccessibles() {
        return utilisateursAccessibles;
    }
    public static ArrayList<NoeudsSysteme> getListNoeuds() {
        return listNoeuds;
    }

    // Setter
    public void setCapaMemoire(int capaMemoire) {
        this.capaMemoire = capaMemoire;
    }

    // List Management
    public void ajoutDonneesStockage(Donnees donnees){
        if (this.getCapaMemoire() >= donnees.getTaille()){
            this.getDonnesStockees().add(donnees);
            this.setCapaMemoire(this.getCapaMemoire() - donnees.getTaille());
            System.out.println("Donnée n°" + donnees.getIdD() + " placée dans le noeud n°" + this.getIdN());
        }
    }
    public void ajoutNoeudAccessible(NoeudsSysteme noeud, int poid){
        this.noeudsAccessibles.add(noeud);
        noeud.noeudsAccessibles.add(this);
        matrice.ajoutArc(this,noeud,poid);
    }
    public void ajoutUtilisateurAccessible(Utilisateurs user){
        this.utilisateursAccessibles.add(user);
    }

    static int plusCourt(double[] T, ArrayList<Integer> I){
        double minimum = Double.POSITIVE_INFINITY;
        int indice = 0;
        for(int i : I){
            if((T[i] < minimum) && T[i] >= 0){
                indice = i;
                minimum = T[i];
            }

        }
        return indice;
    }

    /* Cette méthode retourne un tableau contenant les distances minimales à chaque noeuds du système.
       L'indice des noeuds dans le tableau correspondant à leur id.
     */
    public double[] dijkstra(){
        int id = this.getIdN();
        double inf = Double.POSITIVE_INFINITY;
        ArrayList<ArrayList<Integer>> matAdj = Matrice.getMatriceAdjacence();
        int n = matAdj.size();
        double[] dist = new double[n];
        for(int i=0 ; i<n ; i++){
            if(i == id){
                dist[i] = 0;
            }
            else{
                dist[i] = inf;
            }
        }
        ArrayList<Integer> nonMarques = new ArrayList<>();
        for(int i=0 ; i<n ; i++) {
            nonMarques.add(i);
        }
        ArrayList<Integer> marques = new ArrayList<>();

        while(marques.size() < n){
            int i = plusCourt(dist,nonMarques);
            marques.add(i);
            nonMarques.remove(Integer.valueOf(i));
            for(int k : nonMarques) {
                if ((dist[i] + matAdj.get(i).get(k)) < dist[k]){
                    dist[k] = dist[i] + matAdj.get(i).get(k);
                }
            }
        }
        return dist;

    }

    /***
     * Méthode qui cherche le noeud où il restera le moins de capacité mémoire après le placement de la donnée
     * @param donnees : donnée à placer
     * @return Noeuds_systeme
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
        };
        return node;
    }

    /***
     * Cette méthode cherche à placer les données non pas en essayant de la placer au plus proche de l'utilisateur
     * mais en essayant d'optimiser l'espace de stockage. Elle place les plus grandes données d'abord dans le noeuds le plus petit possible.
     */
    public static void mkpProblem(){
        Donnees.decreasingSort();
        for( Donnees donnees  : Donnees.getListDonnees()){
            System.out.println(donnees);
            System.out.println(NoeudsSysteme.plusPetitEcart(donnees));
            NoeudsSysteme.plusPetitEcart(donnees).ajoutDonneesStockage(donnees);
        };
    }


    @Override
    public String toString() {
        ArrayList<Integer> nAcces = new ArrayList<>();
        for (NoeudsSysteme noeud : noeudsAccessibles){
            nAcces.add(noeud.getIdN());
        }
        ArrayList<Integer> uAcces = new ArrayList<>();
        for (Utilisateurs user : utilisateursAccessibles){
            uAcces.add(user.getIdU());
        }
        return "NoeudsSysteme{" +
                "idN=" + idN +
                ", capaMemoire=" + capaMemoire +
                ", donnesStockees=" + donnesStockees +
                ", noeudsAccessibles=" + nAcces +
                ", utilisateursAccessibles=" + uAcces +
                '}';
    }
}
