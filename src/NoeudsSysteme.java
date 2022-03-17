import java.util.ArrayList;

public class NoeudsSysteme {

    private final int idN;
    private static int idNoeuds;
    private int capaMemoire;
    private ArrayList<Donnees> donnesStockees = new ArrayList<>();
    private ArrayList<NoeudsSysteme> noeudsAccessibles = new ArrayList<>();
    private ArrayList<Utilisateurs> utilisateursAccessibles = new ArrayList<>();

    // Constructor
    public NoeudsSysteme(int capaMemoire, ArrayList<Donnees> donnesStockees, ArrayList<NoeudsSysteme> noeudsAccessibles, ArrayList<Utilisateurs> utilisateursAccessibles) {
        this.capaMemoire = capaMemoire;
        this.donnesStockees = donnesStockees;
        this.noeudsAccessibles = noeudsAccessibles;
        this.utilisateursAccessibles = utilisateursAccessibles;
        this.idN = idNoeuds;
        idNoeuds++;
    }

    // Getter
    public int getIdN() {
        return idN;
    }
    // Return the number of nodes created
    public static int getIdNoeuds() {
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

    public void ajoutDonnees(Donnees donnees){
        if (this.getCapaMemoire() > donnees.getTaille()){
            this.getDonnesStockees().add(donnees);
            this.setCapaMemoire(this.getCapaMemoire() - donnees.getTaille());
        }
    }

}
