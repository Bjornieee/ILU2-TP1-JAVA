package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	private int nbEtal;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.nbEtal = nbEtal;
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	private static class Marche {
		private Etal[] etals;

		private Marche(int taille) {
			etals = new Etal[taille];
		}

		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (etals[indiceEtal].isEtalOccupe() == false) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			} else {
				System.out.println("Cet étal est déja occupé");
			}
		}

		int trouverEtalLibre() {
			int iEtal = -1;
			for (int i = 0; (etals[i].isEtalOccupe()) && (i < etals.length); i++) {
				if (etals[i].isEtalOccupe() == false)
					iEtal = i;
			}
			return (iEtal);
		}

		Etal[] trouverEtals(String produit) {
			int tailleTab = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) tailleTab++;
			}
			Etal[] etalsAvecProd = new Etal[tailleTab];
			switch (tailleTab){
				case 0: break;
				default:{
					int indiceEtal = 0;
					for (int i = 0; i < etals.length; i++) {
						if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
							etalsAvecProd[indiceEtal] = etals[i];
							indiceEtal++;

						}
					}
				}
			}
			return etalsAvecProd;


		}

		Etal trouverVendeur(Gaulois gaulois) {
			Etal iVendeur = new Etal();
			iVendeur = null;
			for (int i = 0; (etals[i].getVendeur() != gaulois) && (i < etals.length); i++) {
				if (etals[i].getVendeur() == gaulois)
					iVendeur = etals[i];
			}
			return (iVendeur);
		}

		void afficherMarche() {
			int nbEtalOccupe = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					etals[i].afficherEtal();
					nbEtalOccupe++;
				}
				if (etals.length - 1 > nbEtalOccupe) {
					StringBuilder chaine = new StringBuilder();
					int nbEtalLibre = etals.length - nbEtalOccupe;
					chaine.append("Il reste" + nbEtalLibre + "étals non utilisés sur le marché. \n");
				}
			}
		}
	}

	public string installerVendeur(Gaulois vendeur, String produit,int nbProduit){
		StringBuilder chaine = new StringBuilder();
		chaine append("Le vendeur" + vendeur + "cherche un endroit pour vendre" + nbProduit + produit + ".\n");
		int iEtalLibre = marche.trouverEtalLibre();
		etals[iEtalLibre].occuperEtal(vendeur,produit,nbProduit);
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
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}