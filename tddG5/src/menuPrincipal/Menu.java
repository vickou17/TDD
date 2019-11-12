/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuPrincipal;
import java.util.Scanner;

import dao.DAOrequester;
import dao.DAOverification;
import mathematical.MathematicalStatsCalculator;
import mathematical.SalaireCalculator;

import java.lang.Math;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Vick
 */
public class Menu {

    /**
     * @param args the command line arguments
     */


    private static Salaire sal = new Salaire();
    private static MathematicalStatsCalculator msc = new MathematicalStatsCalculator();
    private static DAOrequester dr = null;
    private static DAOverification verif= null;
    

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
    	DecimalFormat df = new DecimalFormat("########.00");
        DAOverification verif = new DAOverification("db_tdd", "root", "");
        DAOrequester dr = new DAOrequester("db_tdd", "root", "");
        
        
        Scanner scan = new Scanner(System.in);
        int i;
        int userChoice;
        do {
      
		
		System.out.println("\n \t Menu"
				+ "\n 1. Voir les données des employés d'une entreprise."
				+ "\n 2. Voir les données des employés pour un projet."
				+ "\n 3. Voir les informations sur le salaire"
				+ "\n 4. Comparer les entreprises selon des caractères précis."
				+" \n 5. Obtenir des statistiques avancés sur votre entreprise ou projet\n");
                System.out.print("Votre choix: ");
		  i=scan.nextInt();
		switch(i) {
		case 1: 
			System.out.println("Sélectionnez l'entreprise souhaitée (entrez l'id):\n");
			afficherLignes("Industrie");
			userChoice=scan.nextInt();
			//On vérifie que l'ID est bien présent dans la table
			if(verif.verifValiditeID(userChoice, "industrie")) {
				//On vérifie que des données sont bien présentes dans la table
				if(verif.verifDataInDB(userChoice, "industrie")) {
			System.out.println("\n Moyenne d'heure des employés pour l'entreprise :"+msc.getMoyenneHeureEmployeEntreprise(userChoice));
			System.out.println("\n Somme d'heure des employés pour l'entreprise : "+msc.getSommeHeureEmployeEntreprise(userChoice));
			System.out.println("\n Variance d'heure des employés pour l'entreprise :"+msc.getVarianceHeureEmployeEntreprise(userChoice));
			System.out.println("\n Ecart-Type d'heure des employés pour l'entreprise : "+msc.getEcartTypeHeureEmployeEntreprise(userChoice));
			}}
			break;
		
		case 2: 
			System.out.println("Séléctionnez le projet souhaité (entrez l'id) ");
			afficherLignes("Projet");
			userChoice=scan.nextInt();
			//On vérifie que l'ID est bien présent dans la table
			if(verif.verifValiditeID(userChoice, "projet")) {
				//On vérifie que des données sont bien présentes dans la table
				if(verif.verifDataInDB(userChoice, "projet")) {
					System.out.println(verif.verifDataInDB(userChoice, "projet"));
				System.out.println("\n Moyenne d'heure des employés pour le projet :"+msc.getMoyenneHeureEmployeProjet(userChoice));
				System.out.println("\n Somme d'heure des employés pour le projet : "+msc.getSommeHeureEmployeProjet(userChoice));
				System.out.println("\n Variance d'heure des employés pour le projet :"+msc.getVarianceHeureEmployeProjet(userChoice));
				System.out.println("\n Ecart-Type d'heure des employés pour le projet : "+msc.getEcartTypeHeureEmployeProjet(userChoice));
				}
			}
			
			break;
		
		case 3: 
			sal.menuSalaire();
			break;
			
		case 4 :
			int userChoice2;
			do {
				System.out.println("\n Comparer les différences de salaire selon :"
						+ "\n 1. Le statut dans l'entreprise."
						+ "\n 2. Le genre des employés.");
				userChoice2 = new Scanner(System.in).nextInt();
				ArrayList<Integer> listOfId = dr.listeIdTable("industrie");
				SalaireCalculator sal = new SalaireCalculator();
				switch (userChoice2){
					
					case 1 :
						System.out.println("Veuillez entrer le numéro du statut dont vous souhaitez faire la comparaison :"
								+ "\n 1. Stagiaire"
								+ "\n 2. Employe"
								+ "\n 3. Cadre");
						switch(new Scanner(System.in).nextInt()) {
						
							case 1:
								System.out.println("Comparaison des salaires selon le statut :");
								
								listOfId=dr.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+dr.nameInTable(listOfId.get(j), "industrie")
								+" offre comme salaire moyen a ses stagiaires : "+df.format(sal.salaire_cond("employe", "statut", "stagiaire",listOfId.get(j)))+" euros");
								}
								break;
								
							case 2:
								System.out.println("Comparaison des salaires selon le statut :");
								listOfId=dr.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+dr.nameInTable(listOfId.get(j), "industrie")
								+" offre comme salaire moyen a ses employes : "+df.format(sal.salaire_cond("employe", "statut", "employe",listOfId.get(j)))+" euros");
								}
								break;
								
							case 3: 
								System.out.println("Comparaison des salaires selon le statut :");
								listOfId=dr.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+dr.nameInTable(listOfId.get(j), "industrie")
								+" offre comme salaire moyen a ses cadres : "+df.format(sal.salaire_cond("employe", "statut", "cadre",listOfId.get(j)))+" euros");
								}
								break; 
								
							default :
								
								break;
						}
						break;
						
					case 2 :
						System.out.println("Sélectionnez le genre dont vous souhaitez comparer les salaires entre entreprises :"
								+ "\n 1. Hommes"
								+ "\n 2. Femmes");
						switch(new Scanner(System.in).nextInt()) {
							
							case 1:
								System.out.println("Comparaison des salaires selon le genre :");
								listOfId=dr.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+dr.nameInTable(listOfId.get(j), "industrie")
								+" offre comme salaire moyen a ses hommes : "+df.format(sal.salaire_cond("employe", "sexe", "M",listOfId.get(j)))+" euros");
								}
								break;
							
							case 2:
								System.out.println("Comparaison des salaires selon le genre :");
								listOfId=dr.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+dr.nameInTable(listOfId.get(j), "industrie")
								+" offre comme salaire moyen a ses femmes : "+df.format(sal.salaire_cond("employe", "sexe", "F",listOfId.get(j)))+" euros");
								}
								break;
								
							default:
								break;
						}
						
						
					default :
						break;
						
				}
			}while(userChoice2<3 && userChoice2>0);
			break;
		
		case 5 :
			System.out.println("Voulez-vous des statistiques avancées sur votre entreprise ou votre projet ?"
					+ "\n1. Entreprise."
					+ "\n2. Projet.");
			switch(new Scanner(System.in).nextInt()) {
			
			case 1: 
				ArrayList<Integer> listOfId = dr.listeIdTable("industrie");
				System.out.println("Veuillez sélectionnez votre entreprise :");
				for(int j=0; j<listOfId.size();j++) {
					System.out.println(listOfId.get(j)+". "+dr.nameInTable(listOfId.get(j), "industrie"));
				}
				int userChoice3;
				if(verif.verifValiditeID(userChoice3 = new Scanner(System.in).nextInt(), "industrie")) {
					msc.superStatInd(userChoice3);
					
				}
				
				break;
				
			case 2 :
				System.out.println("Non disponible");
				break;
				
			default:
				break;
				
			}
			break;
		
		default :
			System.out.println("Fin de l'exécution.");
			break;
    }
        }while(i<6 && i>0);
    }

    /**
     * Afficher les lignes de la table sélectionnée
     * @throws ClassNotFoundException 
     */
    public static void afficherLignes(String nomTable) throws ClassNotFoundException {

        try {
            DAOrequester dr = new DAOrequester("db_tdd", "root", "");
            ArrayList<String> liste;
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select * from " + nomTable + ";";
            liste = dr.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            String[] tab = new String[liste.size()];
            for(int i = 0; i < liste.size(); i++)
            {
                tab[i] = liste.get(i);
                //System.out.println(liste.get(i));
                System.out.println(tab[i]);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}