import java.util.ArrayList;

public class Matrice {


    private static ArrayList<ArrayList<Integer>> matriceAdjacence = new ArrayList<>();

    /**
     * Cette méthode est appelée dans le constructeur de la classe NoeudsSysteme
     * et permet de mettre à jour la taille de la matrice et de l'initialiser avec des 0
     **/

    public void updateMatrice(){
        int n = NoeudsSysteme.getIdNoeuds()+1;

        if (matriceAdjacence.isEmpty()){
            matriceAdjacence.add(new ArrayList<>());
            matriceAdjacence.get(0).add(0);
        }

        for (int i = 0 ; i < n-1 ; i++) {
            matriceAdjacence.get(i).add(0);
        }

        if (n > 1){
            matriceAdjacence.add(new ArrayList<>());
            for(int i = 0 ; i < n ; i++) {
                matriceAdjacence.get(n-1).add(0);
            }
        }
    }

    public void ajoutArc(NoeudsSysteme n1, NoeudsSysteme n2, int poid){
        int id1 = n1.getIdN();
        int id2 = n2.getIdN();
        matriceAdjacence.get(id1).set(id2,poid);
        matriceAdjacence.get(id2).set(id1,poid);
    }

    public int getPoid(NoeudsSysteme n1, NoeudsSysteme n2){
        int id1, id2;
        id1 = n1.getIdN();
        id2 = n2.getIdN();
        return matriceAdjacence.get(id1).get(id2);
    }

    public static ArrayList<ArrayList<Integer>> getMatriceAdjacence() {
        return matriceAdjacence;
    }

}







