import java.util.ArrayList;
import java.util.Arrays;

public class NoeudsSysteme {

    private final int idN;
    private static int idNoeuds = 0;
    private int capaMemoire;
    private final ArrayList<Donnees> donnesStockees = new ArrayList<>();
    private final ArrayList<NoeudsSysteme> noeudsAccessibles = new ArrayList<>();
    private final ArrayList<Utilisateurs> utilisateursAccessibles = new ArrayList<>();
    private static Matrice matrice;
    private static final ArrayList<NoeudsSysteme> listNoeuds = new ArrayList<>();

    // Constructor
    /**
     * On crée un nœud. Si c'est le premier créé, on initialise la matrice d'adjacence avec ce nœud.
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


    /*** Cette méthode recherche dans un tableau T l'indice de la valeur qui
    a la valeur minimale parmi une liste I d'indice. */
    static int plusCourt(double[] T, ArrayList<Integer> I){
        double minimum = Double.POSITIVE_INFINITY; // On initialise le minimum à l'infini
        int indice = 0;
        for(int i : I){ // Pour chaque indice dans I,
            if((T[i] < minimum) && T[i] >= 0){ // Si la valeur d'indice i dans T
                indice = i;                    // est inférieure au minimum et positive
                minimum = T[i];
            }
        }
        return indice;
    }


    /* Cette méthode retourne un tableau contenant les distances minimales à chaque nœud du système.
       L'indice des nœuds dans le tableau correspondant à leur id. */
    public double[] dijkstra(){
        // Initialisation des variables :
        int id = this.getIdN();
        double inf = Double.POSITIVE_INFINITY;
        ArrayList<ArrayList<Integer>> matAdj = Matrice.getMatriceAdjacence();
        int n = matAdj.size();

        // Création du tableau de distance et initialisation de celui-ci à avec l'infini
        double[] dist = new double[n];
        for(int i=0 ; i<n ; i++){
            if(i == id){
                dist[i] = 0;
            }
            else{
                dist[i] = inf;
            }
        }
        // Création de la liste des nœuds non marqués et remplissage avec les id de tous les nœuds
        ArrayList<Integer> nonMarques = new ArrayList<>();
        for(int i=0 ; i<n ; i++) {
            nonMarques.add(i);
        }
        // Création de la liste de nœuds marqués. Vide au début
        ArrayList<Integer> marques = new ArrayList<>();

        // Dijkstra :
        while(marques.size() < n){ // Tant que tous les nœuds ne sont pas marqués
            int i = plusCourt(dist,nonMarques); // i prend la valeur de l'id du nœud le plus proche non marqué
            marques.add(i); // On marque i
            nonMarques.remove(Integer.valueOf(i)); // et on le retire des non marqués.
            for(int k : nonMarques) { // Pour chaque nœud non marqué,
                // si la distance de i + le poids de l'arc i-k est inférieur à la distance de k,
                if ((dist[i] + matAdj.get(i).get(k)) < dist[k]){
                    dist[k] = dist[i] + matAdj.get(i).get(k); // k = distance de i + le poids de l'arc i-k
                }
            }
        }
        return dist;
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
        ArrayList<Integer> dAcces = new ArrayList<>();
        for (Donnees donnee : donnesStockees){
            dAcces.add(donnee.getIdD());
        }
        return "NoeudsSysteme{" +
                "idN=" + idN +
                ", capaMemoire=" + capaMemoire +
                ", donnesStockees=" + dAcces +
                ", noeudsAccessibles=" + nAcces +
                ", utilisateursAccessibles=" + uAcces +
                '}';
    }
}
