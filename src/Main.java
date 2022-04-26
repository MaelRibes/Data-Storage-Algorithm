public class Main {
    public static void main(String[] args) {

        // Création des données
        Donnees data_0 = new Donnees(100);
        Donnees data_1 = new Donnees(100);
        Donnees data_2 = new Donnees(300);
        Donnees data_3 = new Donnees(200);
        Donnees data_4 = new Donnees(250);
        Donnees data_5 = new Donnees(200);
        Donnees data_6 = new Donnees(600);
        Donnees data_7 = new Donnees(700);

        // Création des nœuds
        NoeudsSysteme node_0 = new NoeudsSysteme(400);
        NoeudsSysteme node_1 = new NoeudsSysteme(600);
        NoeudsSysteme node_2 = new NoeudsSysteme(300);
        NoeudsSysteme node_3 = new NoeudsSysteme(700);
        NoeudsSysteme node_4 = new NoeudsSysteme(300);


        // Création des utilisateurs et ajout de leurs données d'intérêt
        Utilisateurs user_0 = new Utilisateurs(node_0);
        user_0.ajoutDonneesInteret(data_0);

        Utilisateurs user_1 = new Utilisateurs(node_0);
        user_1.ajoutDonneesInteret(data_1);
        user_1.ajoutDonneesInteret(data_2);

        Utilisateurs user_2 = new Utilisateurs(node_0);
        user_2.ajoutDonneesInteret(data_3);
        user_2.ajoutDonneesInteret(data_5);

        Utilisateurs user_3 = new Utilisateurs(node_1);
        user_3.ajoutDonneesInteret(data_4);

        Utilisateurs user_4 = new Utilisateurs(node_2);
        user_4.ajoutDonneesInteret(data_6);

        Utilisateurs user_5 = new Utilisateurs(node_3);
        user_5.ajoutDonneesInteret(data_1);
        user_5.ajoutDonneesInteret(data_4);

        Utilisateurs user_6 = new Utilisateurs(node_2);
        user_6.ajoutDonneesInteret(data_7);

        // Création des arcs entre les nœuds
        node_0.ajoutNoeudAccessible(node_1,2);
        node_0.ajoutNoeudAccessible(node_2,4);
        node_0.ajoutNoeudAccessible(node_3,2);
        node_0.ajoutNoeudAccessible(node_4,4);

        node_1.ajoutNoeudAccessible(node_2,1);
        node_1.ajoutNoeudAccessible(node_3,3);
        node_1.ajoutNoeudAccessible(node_4,3);

        node_2.ajoutNoeudAccessible(node_3,2);
        node_2.ajoutNoeudAccessible(node_4,3);

        node_3.ajoutNoeudAccessible(node_4,1);

        /* Question 1 : Vérification du fonctionnement de la structure de données */
        /*
        System.out.println("Matrice d'adjacence du système :");
        System.out.println(Matrice.getMatriceAdjacence() + "\n");
        System.out.println("Utilisateurs :");
        for(Utilisateurs user : Utilisateurs.getListUtilisateurs()){
            System.out.println(user);
        }
        System.out.println("\n Noeuds :");
        for(NoeudsSysteme node : NoeudsSysteme.getListNoeuds()){
            System.out.println(node);
        }
        */


        /* Question 2 & 3 : Vérification de l'ajout des données au plus proche des utilisateurs
         * On remarque que cette technique de placement ne permet pas de placer efficacement les données.
         * Les données 6 et 7 devraient avoir la place n'être stockées
         */
        /*
        Donnees.triDecroissant(); // Si on rajoute un tri des données avant le placement, celui-ci devient deja plus efficace
        Donnees.placerToutesDonnees();
        System.out.println("\n Noeuds :");
        for(NoeudsSysteme node : NoeudsSysteme.getListNoeuds()){
            System.out.println(node);
        }
        */

        /* Question 4 : Vérification de l'ajout des données de manière efficace
         * On remarque que cette technique de placement permet de maximiser l'espace occupé dans le système.
         * Les données 0 et 1 ne rentrent pas car le système ne contient pas assez d'espace de stockage.
         * Problème ne vient pas de la méthode de stockage, mais du système lui-même.
         */
        /*
        Donnees.mkpProblem();
        System.out.println("\n Noeuds :");
        for(NoeudsSysteme node : NoeudsSysteme.getListNoeuds()){
            System.out.println(node);
        }
         */
    }
}
