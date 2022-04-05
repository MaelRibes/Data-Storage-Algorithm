import java.util.ArrayList;

public class NoeudsSysteme {

    private final int idN;
    private static int idNoeuds = 0;
    private int capaMemoire;
    private ArrayList<Donnees> donnesStockees = new ArrayList<>();
    private ArrayList<NoeudsSysteme> noeudsAccessibles = new ArrayList<>();
    private ArrayList<Utilisateurs> utilisateursAccessibles = new ArrayList<>();
    static Matrice matrice;

    // Constructor
    /**
     * On créé un noeud. Si c'est le premier créé, on initialise la matrice d'ajacence avec ce noeud.
     *
     */
    public NoeudsSysteme(int capaMemoire, ArrayList<Donnees> donnesStockees, ArrayList<NoeudsSysteme> noeudsAccessibles, ArrayList<Utilisateurs> utilisateursAccessibles) {
        this.capaMemoire = capaMemoire;
        this.donnesStockees = donnesStockees;
        this.noeudsAccessibles = noeudsAccessibles;
        this.utilisateursAccessibles = utilisateursAccessibles;
        this.idN = idNoeuds;
        if (idNoeuds == 0) {
            matrice = new Matrice();
        }
        matrice.updateMatrice();
        idNoeuds++;
    }


    public NoeudsSysteme(int capaMemoire) {
        this.capaMemoire = capaMemoire;
        this.idN = idNoeuds;

        if (idNoeuds == 0) {
            matrice = new Matrice();
        }
        matrice.updateMatrice();
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

    // Setter
    public void setCapaMemoire(int capaMemoire) {
        this.capaMemoire = capaMemoire;
    }

    // List Management
    public void ajoutDonneesStockage(Donnees donnees){
        if (this.getCapaMemoire() > donnees.getTaille()){
            this.getDonnesStockees().add(donnees);
            this.setCapaMemoire(this.getCapaMemoire() - donnees.getTaille());
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
    public void retraitNoeudAccessible(NoeudsSysteme noeud){
        this.noeudsAccessibles.remove(noeud);
        noeud.noeudsAccessibles.remove(this);
    }
    public void retraitUtilisateurAccessible(Utilisateurs user){
        this.utilisateursAccessibles.remove(user);
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
