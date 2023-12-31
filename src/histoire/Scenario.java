package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

import java.util.Scanner;

public class Scenario {

    public static void main(String[] args) {
        Village village = new Village("le village des irréductibles", 10, 5);
        Chef abraracourcix = new Chef("Abraracourcix", 10, village);
        village.setChef(abraracourcix);

        Druide druide = new Druide("Panoramix", 2, 5, 10);
        Gaulois obelix = new Gaulois("Obélix", 25);
        Gaulois asterix = new Gaulois("Astérix", 8);
        Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
        Gaulois bonemine = new Gaulois("Bonemine", 7);

        village.ajouterHabitant(bonemine);
        village.ajouterHabitant(assurancetourix);
        village.ajouterHabitant(asterix);
        village.ajouterHabitant(obelix);
        village.ajouterHabitant(druide);
        village.ajouterHabitant(abraracourcix);
        try {
            village.afficherVillageois();
        } catch (VillageSansChefException e) {
            System.out.println("Entrez le nom du chef :\n");
            Scanner sc = new Scanner(System.in);
            String nom = sc.nextLine();
            System.out.println("Entrez la force du chef :\n");
            int force = sc.nextInt();
            Chef chef = new Chef(nom, force, village);
            village.setChef(chef);
            village.afficherVillageois();
        }
        String fleurs = "fleurs";
        System.out.println(village.installerVendeur(bonemine, fleurs, 20));
        System.out.println(village.installerVendeur(assurancetourix, "lyres", 5));
        System.out.println(village.installerVendeur(obelix, "menhirs", 2));
        System.out.println(village.installerVendeur(druide, fleurs, 10));

        System.out.println(village.rechercherVendeursProduit(fleurs));
        Etal etalFleur = village.rechercherEtal(bonemine);
        try {
            System.out.println(etalFleur.acheterProduit(-2, abraracourcix));
            System.out.println(etalFleur.acheterProduit(15, obelix));
            System.out.println(etalFleur.acheterProduit(15, assurancetourix));
        } catch (IllegalStateException e) {
            System.out.println("L'étal n'est pas occupé");
        } catch (IllegalArgumentException e) {
            System.out.println("Les gaulois n'ont pas pu acheter une quantité négative de fleurs, ilsn'ont donc pas acheté de fleurs");
        }
        System.out.println(village.partirVendeur(bonemine));
        System.out.println(village.afficherMarche());
    }

}




