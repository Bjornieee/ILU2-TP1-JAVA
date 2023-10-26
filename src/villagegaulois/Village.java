package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	//TODO V�rifier private public static non static
	private class Marche {
		private Etal[] etals;

		private Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}

		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (!etals[indiceEtal].isEtalOccupe()) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			} else {
				System.out.println("Cet étal est déja occupé");
			}
		}

		int trouverEtalLibre() {
			int indiceEtal = -1;
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					indiceEtal = i;
					break;
				}
			}
			return (indiceEtal);
		}

		Etal[] trouverEtals(String produit) {
			int tailleTab = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit))
					tailleTab++;
			}
			Etal[] etalsAvecProd = new Etal[tailleTab];
			if (tailleTab > 0) {
				for (int i = 0, indiceEtal = 0; i < etals.length; i++) {
					if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
						etalsAvecProd[indiceEtal] = etals[i];
						indiceEtal++;
					}
				}
			}
			return etalsAvecProd;

		}

		//TODO reprendre la boucle (done)
		Etal trouverVendeur(Gaulois gaulois) {
			Etal iVendeur;
			iVendeur = null;
			for (int i = 0; i < etals.length; i++) {
				Gaulois vendeur = etals[i].getVendeur();
				if (vendeur.equals(gaulois)) {
				iVendeur = etals[i];
				break;
				}
			}
			return (iVendeur);
		}

		public String afficherMarche() {
			int nbEtalOccupe = 0;
			StringBuilder chaine = new StringBuilder();
			for (int i = 0; i < marche.etals.length; i++) {
				if (marche.etals[i].isEtalOccupe()) {
					chaine.append(marche.etals[i].afficherEtal());
					chaine.append("\n");
					nbEtalOccupe++;
				}
			}
			int nbEtalLibre = marche.etals.length - nbEtalOccupe;
			chaine.append("Il reste " + nbEtalLibre + " étals non utilisés sur le marché. \n");
			return chaine.toString();

		}

	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le vendeur ");
		chaine.append(vendeur.getNom());
		chaine.append(" cherche un endroit pour vendre ");
		chaine.append(nbProduit);
		chaine.append(" ");
		chaine.append(produit);
		chaine.append(".\n");
		int iEtalLibre = marche.trouverEtalLibre();
		if (iEtalLibre != -1) {
			marche.etals[iEtalLibre].occuperEtal(vendeur, produit, nbProduit);
		} else {
			chaine.append("Il n'y a pas d'étal disponible");
		}
		return chaine.toString();
	}

	//TODO g�rer toutes les sorties
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des ");
		chaine.append(produit);
		chaine.append(" sont :\n");
		for (int i = 0; i < marche.etals.length; i++) {
			if (marche.etals[i].isEtalOccupe() && marche.etals[i].contientProduit(produit)) {
				chaine.append("- ");
				chaine.append(marche.etals[i].getVendeur().getNom());
				chaine.append("\n");
			}
		}
		return chaine.toString();
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		Etal etalVendeur;
		etalVendeur = marche.trouverVendeur(vendeur);
		return etalVendeur;
	}

	public String partirVendeur(Gaulois gaulois) {
		StringBuilder chaine = new StringBuilder();
		Etal iVendeur;
		iVendeur = marche.trouverVendeur(gaulois);
		chaine.append(iVendeur.libererEtal());
		return chaine.toString();
	}

	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village \"");
		chaine.append(nom);
		chaine.append("\" possède plusieurs étals :\n");
		for (int i = 0; i < marche.etals.length; i++)
			if (marche.etals[i].isEtalOccupe()) {
				chaine.append(marche.etals[i].afficherEtal());
			}
		return chaine.toString();
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (chef == null) {
			throw new VillageSansChefException("ce village n'as pas de chef");
		} else {
			if (nbVillageois < 1) {
				chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
			} else {
				chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
				for (int i = 0; i < nbVillageois; i++) {
					chaine.append("- " + villageois[i].getNom() + "\n");
				}
			}
		}
		return chaine.toString();
	}
}