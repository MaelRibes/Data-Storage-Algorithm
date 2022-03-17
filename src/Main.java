public class Main {
    public static void main(String[] args) {
        Donnees data_1 = new Donnees(100);
        Donnees data_2 = new Donnees(200);
        Donnees data_3 = new Donnees(200);
        Donnees data_4 = new Donnees(200);

        NoeudsSysteme node_1 = new NoeudsSysteme(400);
        NoeudsSysteme node_2 = new NoeudsSysteme(500);

        node_1.ajoutDonneesStockage(data_1);
        node_2.ajoutDonneesStockage(data_2);
        node_1.ajoutNoeudAccessible(node_2);




        Utilisateurs user_1 = new Utilisateurs(node_1);
        user_1.ajoutDonneesInteret(data_3);
        user_1.ajoutDonneesInteret(data_4);
        node_1.ajoutUtilisateurAccessible(user_1);

        System.out.println(node_1);

        user_1.ajoutDonneesInteretToStockage();
        System.out.println(node_1);
        System.out.println(node_2);

    }
}
