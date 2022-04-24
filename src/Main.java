import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Donnees data_0 = new Donnees(100);
        Donnees data_1 = new Donnees(700);
        Donnees data_2 = new Donnees(200);
        Donnees data_3 = new Donnees(450);

        NoeudsSysteme node_0 = new NoeudsSysteme(500);
        NoeudsSysteme node_1 = new NoeudsSysteme(600);
        NoeudsSysteme node_2 = new NoeudsSysteme(200);
        NoeudsSysteme node_3 = new NoeudsSysteme(700);



        Utilisateurs user_0 = new Utilisateurs(node_0);
        user_0.ajoutDonneesInteret(data_0);
        user_0.ajoutDonneesInteret(data_2);

        Utilisateurs user_1 = new Utilisateurs(node_3);
        user_1.ajoutDonneesInteret(data_1);
        user_1.ajoutDonneesInteret(data_0);
        user_1.ajoutDonneesInteret(data_3);



        node_0.ajoutNoeudAccessible(node_1,2);
        node_0.ajoutNoeudAccessible(node_2,1);
        node_0.ajoutNoeudAccessible(node_3,3);
        node_1.ajoutNoeudAccessible(node_2,2);
        node_1.ajoutNoeudAccessible(node_3,1);
        node_2.ajoutNoeudAccessible(node_3,1);


        //System.out.println(Matrice.getMatriceAdjacence());

        //System.out.println(Arrays.toString(node_0.dijkstra()));
        //System.out.println(Arrays.toString(node_3.dijkstra()));
        //System.out.println(data_0.getUtilisateursInteret());

        Donnees.placerToutesDonnees();




    }
}
