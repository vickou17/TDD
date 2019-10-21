/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testTDD;
import java.util.Scanner;
import java.lang.Math;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    static double x = 0;


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here
        /*x=somme();
        System.out.println("La somme est:" + x);
        x=moyenne();
        System.out.println("La moyenne est:" + x);
        x=variance();
        System.out.println("La variance est:" + x);*/
    	

        con = new Connexion("db_tdd","root","");
        String employe = "employe";
        String result;
        
        //result=getSommeHeureEmployeEntreprise(1);
        //System.out.println(result);
        
        //result=getSommeHeureEmployeProjet(1);
        //System.out.println(result);
        
        //result=getMoyenneHeureEmployeEntreprise(1);
      	//System.out.println(result);
        
        //result=getMoyenneHeureEmployeProjet(1);
        //System.out.println(result);
        
        //result=getVarianceHeureEmployeEntreprise(1);
        //System.out.println(result);
        
        //result=getVarianceHeureEmployeProjet(1);
        //System.out.println(result);
        
        //result=getEcartTypeHeureEmployeEntreprise(1);
        //System.out.println(result);
        
        //result=getEcartTypeHeureEmployeProjet(1);
        //System.out.println(result);
        
        Scanner scan = new Scanner(System.in);
        int i;
        int userChoice;
        do {
      
		
		System.out.println("\n \t Menu"
				+ "\n 1. Voir les données des employés d'une entreprise."
				+ "\n 2. Voir les données des employés pour un projet.");
		  i=scan.nextInt();
		switch(i) {
		case 1: 
			System.out.println("Sélectionnez l'entreprise souhaitée (entrez l'id)");
			afficherLignes("Industrie");
			userChoice=scan.nextInt();
			if(con.verifValiditeID(userChoice, "industrie")) {
			System.out.println("\n Moyenne d'heure des employés pour l'entreprise :"+getMoyenneHeureEmployeEntreprise(userChoice));
			System.out.println("\n Somme d'heure des employés pour l'entreprise : "+getSommeHeureEmployeEntreprise(userChoice));
			System.out.println("\n Variance d'heure des employés pour l'entreprise :"+getVarianceHeureEmployeEntreprise(userChoice));
			System.out.println("\n Ecart-Type d'heure des employés pour l'entreprise : "+getEcartTypeHeureEmployeEntreprise(userChoice));
			} else {
				System.out.println("Erreur, veuillez entrer un ID valide");
			}
			break;
		
		case 2: 
			System.out.println("Séléctionnez le projet souhaité (entrez l'id) ");
			afficherLignes("Projet");
			userChoice=scan.nextInt();
			if(con.verifValiditeID(userChoice, "projet")) {
				System.out.println("\n Moyenne d'heure des employés pour le projet :"+getMoyenneHeureEmployeProjet(userChoice));
				System.out.println("\n Somme d'heure des employés pour le projet : "+getSommeHeureEmployeProjet(userChoice));
				System.out.println("\n Variance d'heure des employés pour le projet :"+getVarianceHeureEmployeProjet(userChoice));
				System.out.println("\n Ecart-Type d'heure des employés pour le projet : "+getEcartTypeHeureEmployeProjet(userChoice));
			} else {
				System.out.println("Erreur, veuillez entrer un ID valide.");
			}
			
			break;
		
		case 3: 
			break;
		
		case 4: 
			System.out.println("Ecart-type");
			break;
		
		case 5: 
			System.out.println("Maximum");
			break;
		
		case 6: 
			System.out.println("Minimum");
			break;
    }
        }while(i<6 && i>0);
    }

    /**
     * Afficher les lignes de la table sélectionnée
     */
    public static void afficherLignes(String nomTable) {

        try {
            ArrayList<String> liste;
            // recuperer la liste de la table sélectionnée
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
    
    /**Retourne la somme d'heures travaillée par l'ensemble des employés dans une industrei
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getSommeHeureEmployeEntreprise(int idEntreprise) {
    	
        String resultStatement = null;
        try {
        
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT SUM(nb_heure) AS somme FROM employe INNER JOIN industrie ON id_ind='"+idEntreprise+"'";
            resultStatement = con.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    
    /**Retourne le nombre d'heures passés par tous les employés sur un même projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getSommeHeureEmployeProjet(int idProj) {
    	
        String resultStatement = null;
        try {
        
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT SUM(nb_heure) AS somme FROM projet INNER JOIN intermediaire ON fk_id_projet='"+idProj+"' INNER JOIN employe ON fk_id_emp = id_emp";
            resultStatement = con.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    /**Retourne la moyenne d'heures travaillée par l'ensemble des employés dans une industrei
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getMoyenneHeureEmployeEntreprise(int idEntreprise) {
    	
        String resultStatement = null;
        try {
        
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT AVG(nb_heure) AS somme FROM employe INNER JOIN industrie ON id_ind='"+idEntreprise+"'";
            resultStatement = con.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    /**Retourne la moyenne d'heures passés par tous les employés sur un même projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getMoyenneHeureEmployeProjet(int idProj) {
    	
        String resultStatement = null;
        try {
        
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT AVG(nb_heure) AS somme FROM projet INNER JOIN intermediaire ON fk_id_projet='"+idProj+"' INNER JOIN employe ON fk_id_emp = id_emp";
            resultStatement = con.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    /**Retourne la variance d'heures travaillées par l'ensemble des employés dans une industrie
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getVarianceHeureEmployeEntreprise(int idEntreprise) {
    	
        String resultStatement = null;
        try {
            ArrayList<String> listeHeure;
            // recuperer la liste de la table sélectionnée
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
    
    /**Retourne la variance d'heures travaillées par l'ensemble des employés sur un projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     */
    public static String getVarianceHeureEmployeProjet(int idProj) {
    	
        String resultStatement = null;
        try {
            ArrayList<String> listeHeure;
            // recuperer la liste de la table sélectionnée
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
    
    /**Retourne l'écart type d'heures travaillées par l'ensemble des employés dans une industrie
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
    
    /**Retourne l'écart type d'heures travaillées par l'ensemble des employés sur un projet
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
    
    


    public static double somme(){
        System.out.println("Entrez le nombre de valeurs que vous souhaitez rentrer:");
        Scanner scan = new Scanner(System.in);
        double n = scan.nextDouble();
        double val = 0;
        int i = 0;
        while (i<n){
            System.out.println("Valeur n°:"+ (i+1));
            scan = new Scanner(System.in);
            val += scan.nextFloat();
            i+=1;
        }
        return((double)(val));
    }



    public static double moyenne(){
        System.out.println("Entrez le nombre de valeurs que vous souhaitez rentrer:");
        Scanner scan = new Scanner(System.in);
        double n = scan.nextDouble();
        double val = 0;
        int i = 0;
        while (i<n){
            System.out.println("Valeur n°:"+ (i+1) +"\n");
            scan = new Scanner(System.in);
            val += scan.nextDouble();
            i+=1;
        }
        return((double)(val/n));
    }


    public static double carre(double val){
        val*=val;
        return (val);
    }

    public static double variance(){
        double moy = 0;
        System.out.println("Entrez le nombre de valeurs que vous souhaitez rentrer:");
        Scanner scan = new Scanner(System.in);
        double n = scan.nextDouble();
        double val[]= null;
        double somme = 0;
        double resultat = 0;
        int i = 0;
        while (i<n){
            System.out.println("Valeur n°:"+ (i+1) +"\n");
            scan = new Scanner(System.in);
            val[i] = scan.nextDouble();
            somme += val[i];
            i+=1;
        }
        moy = (double)(somme/n);
        for (i=0;i<n;i++){
            resultat += carre((val[i]-moy));
        }
        return((double)(resultat/n));
    }


    public static double ecart_type(){
        return(Math.sqrt((double)variance()));
    }
}