package villagegaulois;

import personnages.Gaulois;

public class Etal {
    private Gaulois vendeur;
    private String produit;
    private int quantiteDebutMarche;
    private int quantite;
    private boolean etalOccupe = false;

    public boolean isEtalOccupe() {
        return etalOccupe;
    }

    public Gaulois getVendeur() {
        return vendeur;
    }

    public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
        this.vendeur = vendeur;
        this.produit = produit;
        this.quantite = quantite;
        quantiteDebutMarche = quantite;
        etalOccupe = true;
    }

    public String libererEtal() {
        etalOccupe = false;
        StringBuilder chaine = new StringBuilder();
        chaine.append("Le vendeur ");
        try {
            chaine.append(vendeur.getNom());
            chaine.append(" quitte son étal ");
            int produitVendu = quantiteDebutMarche - quantite;
            if (produitVendu > 0) {
                chaine.append(
                        "il a vendu " + produitVendu + " parmi " + quantiteDebutMarche +" " + produit + ".\n");
            } else {
                chaine.append("il n'a malheureusement rien vendu.\n");
            }
        } catch (NullPointerException e) {
            chaine.append("n'est pas installé sur le marché\n");
        }
        return chaine.toString();
    }

    public String afficherEtal() {
        StringBuilder chaine = new StringBuilder();
        if (etalOccupe) {
            chaine.append("L'étal de ");
            chaine.append(vendeur.getNom());
            chaine.append(" est garni de ");
            chaine.append(quantite);
            chaine.append(" ");
            chaine.append(produit);
            chaine.append("\n");
        } else chaine.append("L'étal est libre");
        return chaine.toString();
    }

    public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {

        if (quantiteAcheter < 1) {
            throw new IllegalArgumentException("la quantité doit être positive");
        }
        if (!isEtalOccupe()) {
            throw new IllegalStateException("l'étal n'est pas occupé");
        }
        try {
            StringBuilder chaine = new StringBuilder();
            chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
                    + " " + produit + " à " + vendeur.getNom());
            if (quantite == 0) {
                chaine.append(", malheureusement il n'y en a plus !");
                quantiteAcheter = 0;
            }
            if (quantiteAcheter > quantite) {
                chaine.append(", comme il n'y en a plus que " + quantite + ", "
                        + acheteur.getNom() + " vide l'étal de "
                        + vendeur.getNom() + ".\n");
                quantiteAcheter = quantite;
                quantite = 0;
            }
            if (quantite != 0) {
                quantite -= quantiteAcheter;
                chaine.append(". " + acheteur.getNom()
                        + ", est ravi de tout trouver sur l'étal de "
                        + vendeur.getNom() + "\n");
            }
            return chaine.toString();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public boolean contientProduit(String produit) {
        return this.produit.equals(produit);
    }

}
