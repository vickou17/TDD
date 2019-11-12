/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuPrincipal;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import dao.DAOconnexion;
import dao.DAOrequester;
import dao.DAOverification;
import mathematical.SalaireCalculator;

import java.util.Arrays;
import java.lang.*;
import java.lang.reflect.Array; 

/**
 *
 * @author Vick
 */
public class Salaire {
    
    //Paramètres
    private static Menu func = new Menu();
    private static DAOconnexion con = null;
    private static DAOverification verif = null;
    private static DAOrequester dr = null;
    DecimalFormat df = new DecimalFormat("########.00");
    SalaireCalculator sc = new SalaireCalculator();
    
    
    //Fonctions
    public void menuSalaire() throws SQLException, ClassNotFoundException{
    	 DAOverification verif = new DAOverification("db_tdd", "root", "");
         DAOrequester dr = new DAOrequester("db_tdd", "root", "");
         
        System.out.println("1. Retour au menu principal");
        System.out.println("2. Calcul des primes par employé");
        System.out.println("3. Salaire mensuel moyen par entreprise");
        System.out.println("4. Salaire mensuel moyen par sexe");
        System.out.println("5. Salaire mensuel moyen par statut");
        System.out.println("6. Salaire par employé\n");
        System.out.print("Votre choix: ");
         
        Scanner scan = new Scanner(System.in);
        int choix = 0;
        choix = scan.nextInt();
        switch(choix){
            case 1:
                func.main(null);
                break;
            case 2:
                sc.calcul_prime("employe");
                break;
            case 3:
                
                ArrayList<String> affiche;
                String reque = "select * from industrie;";
                affiche = dr.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                Scanner scan1 = new Scanner(System.in);
                int id = scan1.nextInt();
                if(verif.verifValiditeID(id, "industrie")&&verif.verifDataInDB(id, "industrie")) {
                double result_moy = sc.salaire_entreprise("employe", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise est de: " + df.format(result_moy) + "€\n");
                }
                break;
            case 4:
                salaire_sexe();
                break;
            case 5:
                salaire_statut();
                break;
            case 6:
                salaire_employe();
                break;
                
            default :
            	break;
        }
    }
    
    public void salaire_sexe() throws SQLException, ClassNotFoundException {
    	
    	 DAOverification verif = new DAOverification("db_tdd", "root", "");
         DAOrequester dr = new DAOrequester("db_tdd", "root", "");
         
        int choix = 0, id = 0;
        ArrayList<String> affiche;
        String reque = "select * from industrie;";
        
        System.out.println("1. Masculin");
        System.out.println("2. Féminin\n");
        System.out.print("Votre choix: ");
        Scanner scan = new Scanner(System.in);
        choix = scan.nextInt();
        
        switch(choix){
            case 1:
                affiche = dr.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                Scanner scan2 = new Scanner(System.in);
                id = scan2.nextInt();
                if(verif.verifValiditeID(id, "industrie")&&verif.verifDataInDB(id, "industrie")) {
                double result_masculin = sc.salaire_cond("employe","sexe", "M", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour un homme est de: " + df.format(result_masculin) + "€\n");}
                break;
            case 2:
                affiche = dr.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                scan2 = new Scanner(System.in);
                id = scan2.nextInt();
                if(verif.verifValiditeID(id, "industrie")&&verif.verifDataInDB(id, "industrie")) {
                double result_feminin = sc.salaire_cond("employe","sexe","F", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour une femme est de: " + df.format(result_feminin) + "€\n");}
                
                break;
                
            default :
            	System.out.println("Erreur, retour au menu précèdent.");
            	break;
        }
        
    }
    
    
    
    
    /**
     *Menu permettant de choisir le salaire pour un employe ou tous les employés
     */
    public void salaire_employe() throws SQLException, ClassNotFoundException {
         
    	 DAOverification verif = new DAOverification("db_tdd", "root", "");
         DAOrequester dr = new DAOrequester("db_tdd", "root", "");
        
        System.out.println("1. Retour au menu Salaire");
        System.out.println("2. Salaire de tous les employés");
        System.out.println("3. Salaire d'un seul employé\n");
        System.out.print("Votre choix: ");
        
        Scanner scan = new Scanner(System.in);
        int choix = 0;
        choix = scan.nextInt();
        switch(choix){
            case 1:
                menuSalaire();
                break;
            case 2:
                ArrayList<Double> result_employes = sc.sal_employes("employe");
                break;
            case 3:
                ArrayList<String> affiche;
                String reqListe = "select id_emp, nom, prenom from employe;";
                affiche = dr.remplirChampsRequete(reqListe);

                //Afficher les lignes de la requête sélectionnée
                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'id de l'employé: ");
                Scanner scan2 = new Scanner(System.in);
                int id = scan2.nextInt();
                if(verif.verifValiditeID(id, "employe")&&verif.verifDataInDB(id, "employe")) {
                double result = sc.sal_employe("employe", id);
                System.out.println("Salaire: " + df.format(result) + " €/mois");}
                break;
        }
    }
    
    /**@author Vick
     * 
     * Menu permettant de choisir le salaire en fonction du statut
     */
    public void salaire_statut() throws SQLException, ClassNotFoundException {
         
    	 DAOverification verif = new DAOverification("db_tdd", "root", "");
         DAOrequester dr = new DAOrequester("db_tdd", "root", "");
        int choix = 0, id = 0;
        ArrayList<String> affiche;
        String reque = "select * from industrie;";
        
        System.out.println("1. Cadre");
        System.out.println("2. Employe");
        System.out.println("3. Stagiaire\n");
        System.out.print("Votre choix: ");
        Scanner scan = new Scanner(System.in);
        choix = scan.nextInt();
        
        switch(choix){
            case 1:
                affiche = dr.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                Scanner scan1 = new Scanner(System.in);
                id = scan1.nextInt();
                if(verif.verifValiditeID(id, "industrie")&&verif.verifDataInDB(id, "industrie")) {
                double result_cadre = sc.salaire_cond("employe","statut", "Cadre", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour un cadre est de: " + df.format(result_cadre) + "€\n");}
                break;
            case 2:
                affiche = dr.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                scan1 = new Scanner(System.in);
                id = scan1.nextInt();
                if(verif.verifValiditeID(id, "industrie")&&verif.verifDataInDB(id, "industrie")) {
                double result_emp = sc.salaire_cond("employe","statut", "Employe", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour un employé est de: " + df.format(result_emp) + "€\n");}
                break;
            case 3:
                affiche = dr.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                scan1 = new Scanner(System.in);
                id = scan1.nextInt();
                if(verif.verifValiditeID(id, "industrie")&&verif.verifDataInDB(id, "industrie")) {
                double result_stagiaire = sc.salaire_cond("employe","statut", "Stagiaire", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour un stagiaire est de: " + df.format(result_stagiaire) + "€\n");}
                break;
        }
        
    }
    
    
  

}