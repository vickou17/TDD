/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tddVickou;
import tddVickou.Salaire;
import java.util.Scanner;
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
public class Fonction {

    /**
     * @param args the command line arguments
     */

    private static Connexion con = null;
    private static Salaire sal = new Salaire();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
    	DecimalFormat df = new DecimalFormat("########.00");
        con = new Connexion("db_tdd","root","");
        
        Scanner scan = new Scanner(System.in);
        int i;
        int userChoice;
        do {
      
		
		System.out.println("\n \t Menu"
				+ "\n 1. Voir les donn�es des employ�s d'une entreprise."
				+ "\n 2. Voir les donn�es des employ�s pour un projet."
				+ "\n 3. Voir les informations sur le salaire"
				+ "\n 4. Comparer les entreprises selon des caract�res pr�cis."
				+" \n 5. Obtenir des statistiques avanc�s sur votre entreprise ou projet\n");
                System.out.print("Votre choix: ");
		  i=scan.nextInt();
		switch(i) {
		case 1: 
			System.out.println("S�lectionnez l'entreprise souhait�e (entrez l'id):\n");
			afficherLignes("Industrie");
			userChoice=scan.nextInt();
			//On v�rifie que l'ID est bien pr�sent dans la table
			if(con.verifValiditeID(userChoice, "industrie")) {
				//On v�rifie que des donn�es sont bien pr�sentes dans la table
				if(con.verifDataInDB(userChoice, "industrie")) {
					System.out.println("Ok");
			System.out.println("\n Moyenne d'heure des employ�s pour l'entreprise :"+getMoyenneHeureEmployeEntreprise(userChoice));
			System.out.println("\n Somme d'heure des employ�s pour l'entreprise : "+getSommeHeureEmployeEntreprise(userChoice));
			System.out.println("\n Variance d'heure des employ�s pour l'entreprise :"+getVarianceHeureEmployeEntreprise(userChoice));
			System.out.println("\n Ecart-Type d'heure des employ�s pour l'entreprise : "+getEcartTypeHeureEmployeEntreprise(userChoice));
			}}
			break;
		
		case 2: 
			System.out.println("S�l�ctionnez le projet souhait� (entrez l'id) ");
			afficherLignes("Projet");
			userChoice=scan.nextInt();
			//On v�rifie que l'ID est bien pr�sent dans la table
			if(con.verifValiditeID(userChoice, "projet")) {
				//On v�rifie que des donn�es sont bien pr�sentes dans la table
				if(con.verifDataInDB(userChoice, "projet")) {
					System.out.println(con.verifDataInDB(userChoice, "projet"));
				System.out.println("\n Moyenne d'heure des employ�s pour le projet :"+getMoyenneHeureEmployeProjet(userChoice));
				System.out.println("\n Somme d'heure des employ�s pour le projet : "+getSommeHeureEmployeProjet(userChoice));
				System.out.println("\n Variance d'heure des employ�s pour le projet :"+getVarianceHeureEmployeProjet(userChoice));
				System.out.println("\n Ecart-Type d'heure des employ�s pour le projet : "+getEcartTypeHeureEmployeProjet(userChoice));
				}
			}
			
			break;
		
		case 3: 
			sal.menuSalaire();
			break;
			
		case 4 :
			
			
			int userChoice2;
			do {
				System.out.println("\n Comparer les diff�rences de salaire selon :"
						+ "\n 1. Le statut dans l'entreprise."
						+ "\n 2. Le genre des employ�s.");
				userChoice2 = new Scanner(System.in).nextInt();
				ArrayList<Integer> listOfId = con.listeIdTable("industrie");
				Salaire sal = new Salaire();
				switch (userChoice2){
					
					case 1 :
						System.out.println("Veuillez entrer le num�ro du statut dont vous souhaitez faire la comparaison :"
								+ "\n 1. Stagiaire"
								+ "\n 2. Employe"
								+ "\n 3. Cadre");
						switch(new Scanner(System.in).nextInt()) {
						
							case 1:
								System.out.println("Comparaison des salaires selon le statut :");
								
								listOfId=con.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+con.nameInTable(listOfId.get(j), "industrie")
								+" offre comme salaire moyen a ses stagiaires : "+df.format(sal.salaire_cond("employe", "statut", "stagiaire",listOfId.get(j)))+" euros");
								}
								break;
								
							case 2:
								System.out.println("Comparaison des salaires selon le statut :");
								listOfId=con.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+con.nameInTable(listOfId.get(j), "industrie")
								+" offre comme salaire moyen a ses employes : "+df.format(sal.salaire_cond("employe", "statut", "employe",listOfId.get(j)))+" euros");
								}
								break;
								
							case 3: 
								System.out.println("Comparaison des salaires selon le statut :");
								listOfId=con.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+con.nameInTable(listOfId.get(j), "industrie")
								+" offre comme salaire moyen a ses cadres : "+df.format(sal.salaire_cond("employe", "statut", "cadre",listOfId.get(j)))+" euros");
								}
								break; 
								
							default :
								
								break;
						}
						break;
						
					case 2 :
						System.out.println("S�lectionnez le genre dont vous souhaitez comparer les salaires entre entreprises :"
								+ "\n 1. Hommes"
								+ "\n 2. Femmes");
						switch(new Scanner(System.in).nextInt()) {
							
							case 1:
								System.out.println("Comparaison des salaires selon le genre :");
								listOfId=con.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+con.nameInTable(listOfId.get(j), "industrie")
								+" offre comme salaire moyen a ses hommes : "+df.format(sal.salaire_cond("employe", "sexe", "M",listOfId.get(j)))+" euros");
								}
								break;
							
							case 2:
								System.out.println("Comparaison des salaires selon le genre :");
								listOfId=con.listeIdTable("industrie");
								for(int j=0;j<listOfId.size();j++) {
								System.out.println("L'entreprise : "+con.nameInTable(listOfId.get(j), "industrie")
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
			System.out.println("Voulez-vous des statistiques avanc�es sur votre entreprise ou votre projet ?"
					+ "\n1. Entreprise."
					+ "\n2. Projet.");
			switch(new Scanner(System.in).nextInt()) {
			
			case 1: 
				ArrayList<Integer> listOfId = con.listeIdTable("industrie");
				System.out.println("Veuillez s�lectionnez votre entreprise :");
				for(int j=0; j<listOfId.size();j++) {
					System.out.println(listOfId.get(j)+". "+con.nameInTable(listOfId.get(j), "industrie"));
				}
				int userChoice3;
				if(con.verifValiditeID(userChoice3 = new Scanner(System.in).nextInt(), "industrie")) {
					superStatInd(userChoice3);
					
				}
				
				break;
				
			case 2 :
				break;
				
			default:
				break;
				
			}
			break;
		
		default :
			System.out.println("Fin de l'ex�cution.");
			break;
    }
        }while(i<6 && i>0);
    }

    /**
     * Afficher les lignes de la table s�lectionn�e
     */
    public static void afficherLignes(String nomTable) {

        try {
            ArrayList<String> liste;
            // recuperer la liste de la table s�lectionn�e
            String requeteSelectionnee = "select * from " + nomTable + ";";
            liste = con.remplirChampsRequete(requeteSelectionnee);

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
    
    /**Retourne la somme d'heures travaill�e par l'ensemble des employ�s dans une industrei
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getSommeHeureEmployeEntreprise(int idEntreprise) {
    	
        String resultStatement = null;
        try {
        
            // recuperer la liste de la table s�lectionn�e
            String requeteSelectionnee = "SELECT SUM(nb_heure) AS somme FROM employe INNER JOIN industrie ON id_ind='"+idEntreprise+"'";
            resultStatement = con.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    
    /**Retourne le nombre d'heures pass�s par tous les employ�s sur un même projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getSommeHeureEmployeProjet(int idProj) {
    	
        String resultStatement = null;
        try {
        
            // recuperer la liste de la table s�lectionn�e
            String requeteSelectionnee = "SELECT SUM(nb_heure) AS somme FROM projet INNER JOIN intermediaire ON fk_id_projet='"+idProj+"' INNER JOIN employe ON fk_id_emp = id_emp";
            resultStatement = con.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    /**Retourne la moyenne d'heures travaill�e par l'ensemble des employ�s dans une industrei
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getMoyenneHeureEmployeEntreprise(int idEntreprise) {
    	
        String resultStatement = null;
        try {
        
            // recuperer la liste de la table s�lectionn�e
            String requeteSelectionnee = "SELECT AVG(nb_heure) AS somme FROM employe INNER JOIN industrie ON id_ind='"+idEntreprise+"'";
            resultStatement = con.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    /**Retourne la moyenne d'heures pass�s par tous les employ�s sur un même projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getMoyenneHeureEmployeProjet(int idProj) {
    	
        String resultStatement = null;
        try {
        
            // recuperer la liste de la table s�lectionn�e
            String requeteSelectionnee = "SELECT AVG(nb_heure) AS somme FROM projet INNER JOIN intermediaire ON fk_id_projet='"+idProj+"' INNER JOIN employe ON fk_id_emp = id_emp";
            resultStatement = con.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    /**Retourne la variance d'heures travaill�es par l'ensemble des employ�s dans une industrie
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getVarianceHeureEmployeEntreprise(int idEntreprise) {
    	
        String resultStatement = null;
        try {
            ArrayList<String> listeHeure;
            // recuperer la liste de la table s�lectionn�e
            String requeteSelectionnee = "SELECT nb_heure FROM employe INNER JOIN industrie ON id_ind='"+idEntreprise+"'";
            
            listeHeure = con.remplirChampsRequete(requeteSelectionnee);
            double somme=0;
            double moyenneHeure = Double.parseDouble(getMoyenneHeureEmployeEntreprise(idEntreprise));
            // afficher les lignes de la requete selectionnee a partir de la liste

            for(int i = 0; i < listeHeure.size(); i++)
            {
                somme = Math.pow((Double.parseDouble(listeHeure.get(i))-moyenneHeure),2)+somme;
            }
            
            resultStatement=Double.toString(somme/listeHeure.size());

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    /**Retourne la variance d'heures travaill�es par l'ensemble des employ�s sur un projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getVarianceHeureEmployeProjet(int idProj) {
    	
        String resultStatement = null;
        try {
            ArrayList<String> listeHeure;
            // recuperer la liste de la table s�lectionn�e
            String requeteSelectionnee = "SELECT nb_heure FROM projet INNER JOIN intermediaire ON fk_id_projet='"+idProj+"' INNER JOIN employe ON fk_id_emp = id_emp";
            
            listeHeure = con.remplirChampsRequete(requeteSelectionnee);
            double somme=0;
            double moyenneHeure = Double.parseDouble(getMoyenneHeureEmployeProjet(idProj));
            // afficher les lignes de la requete selectionnee a partir de la liste

            for(int i = 0; i < listeHeure.size(); i++)
            {
                somme = Math.pow((Double.parseDouble(listeHeure.get(i))-moyenneHeure),2)+somme;
            }
            
            resultStatement=Double.toString(somme/listeHeure.size());

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    /**Retourne l'�cart type d'heures travaill�es par l'ensemble des employ�s dans une industrie
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getEcartTypeHeureEmployeEntreprise(int idEntreprise) {
    	
        String resultStatement = null;
        resultStatement=Double.toString(Math.sqrt(Double.parseDouble(getVarianceHeureEmployeEntreprise(idEntreprise))));
        return resultStatement;
    }
    
    /**Retourne l'�cart type d'heures travaill�es par l'ensemble des employ�s sur un projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getEcartTypeHeureEmployeProjet(int idProjet) {
    	
        String resultStatement = null;
        resultStatement=Double.toString(Math.sqrt(Double.parseDouble(getVarianceHeureEmployeProjet(idProjet))));
        return resultStatement;
    }
    
    /**Affiche les statistiques avanc�es pour une entreprise
     * 
     * @param idInd
     * @throws ClassNotFoundException
     * @throws SQLException
     * @author Loic
     */
    public static void superStatInd(int idInd) throws ClassNotFoundException, SQLException {
    	int nbreEmploye=Integer.parseInt(con.recupResultatRequete("SELECT COUNT(id_emp) FROM employe INNER JOIN industrie ON (fk_id_ind=id_ind) WHERE id_ind="+idInd));
    	int nbreEmployeM=Integer.parseInt(con.recupResultatRequete("SELECT COUNT(id_emp) FROM employe INNER JOIN industrie ON (fk_id_ind=id_ind) WHERE (sexe='M') AND id_ind="+idInd));
    	int nbreEmployeF=Integer.parseInt(con.recupResultatRequete("SELECT COUNT(id_emp) FROM employe INNER JOIN industrie ON (fk_id_ind=id_ind) WHERE (sexe='F')AND id_ind="+idInd));
    
    	System.out.println("Votre entreprise compte : "+nbreEmploye+" employ�s");
    	System.out.println("Parmis ces employ�s, vous comptez "+nbreEmployeM+" hommes ("+(((float)nbreEmployeM/(float)nbreEmploye)*100)+"%) et "+nbreEmployeF+" femmes ("+(((float)nbreEmployeF/(float)nbreEmploye)*100)+"%)");
    	
    	System.out.println("\t ........................... \t");
    	System.out.println("Salaire moyen au sein de l'entreprise :"+sal.salaire_entreprise("employe", idInd));
    	System.out.println("Salaire par statut : "
    			+ "\n 1. Stagiaire : "+sal.salaire_cond("employe", "statut", "stagiaire", idInd)
    			+" \n \t => Un stagiaire touche en moyenne "+((float)+sal.salaire_cond("employe", "statut", "stagiaire", idInd)/(float)sal.autre_salaire_cond("employe", "statut", "stagiaire", idInd))*100+"% que dans une autre entreprise"
    			+" \n 2. Employe :"+sal.salaire_cond("employe", "statut", "employe", idInd )+" euros"
    			+" \n \t => Un employe touche en moyenne "+((float)+sal.salaire_cond("employe", "statut", "employe", idInd)/(float)sal.autre_salaire_cond("employe", "statut", "employe", idInd))*100+"% que dans une autre entreprise"
    			+" \n 3. Cadre : "+ sal.salaire_cond("employe", "statut", "cadre", idInd)+" euros"
    			+" \n \t => Un cadre touche en moyenne "+((float)+sal.salaire_cond("employe", "statut", "cadre", idInd)/(float)sal.autre_salaire_cond("employe", "statut", "cadre", idInd))*100+"% que dans une autre entreprise");
    	
    	System.out.println("\t ........................... \t");	
    	System.out.println("Un homme touche en moyenne "+sal.salaire_cond("employe", "sexe", "M", idInd)+" euros au sein de votre entreprise"
    			+" \n \t => Une diff�rence de "+((float)sal.salaire_cond("employe", "sexe", "M", idInd)/(float)sal.autre_salaire_cond("employe", "sexe", "M", idInd))*100+"% que dans une autre entreprise"
    			+"\nUne femme touche en moyenne "+sal.salaire_cond("employe", "sexe", "F", idInd)+"  euros au sein de votre entreprise"
    			+" \n \t => Une diff�rence de "+((float)sal.salaire_cond("employe", "sexe", "F", idInd)/(float)sal.autre_salaire_cond("employe", "sexe", "F", idInd))*100+"% que dans une autre entreprise"
    			+"\nIl ya une diff�rence de "+((float)sal.salaire_cond("employe", "sexe", "M", idInd)/(float)sal.salaire_cond("employe", "sexe", "F", idInd))*100+"% entre le salaire d'un homme et d'une femme dans votre entreprise");
    	
    	
    }
}