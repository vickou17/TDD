/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tddVickou;

import static tddVickou.Fonction.getMoyenneHeureEmployeProjet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;
import java.lang.reflect.Array; 

/**
 *
 * @author Vick
 */
public class Salaire {
    
    //Paramètres
    private static Fonction func = new Fonction();
    private static Connexion con = null;
    
    
    
    //Fonctions
    public void menuSalaire() throws SQLException, ClassNotFoundException{
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
                calcul_prime("employe");
                break;
            case 3:
                con = new Connexion("db_tdd","root","");
                ArrayList<String> affiche;
                String reque = "select * from industrie;";
                affiche = con.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                Scanner sc = new Scanner(System.in);
                int id = sc.nextInt();
                if(con.verifValiditeID(id, "industrie")&&con.verifDataInDB(id, "industrie")) {
                double result_moy = salaire_entreprise("employe", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise est de: " + result_moy + "€\n");
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
        con = new Connexion("db_tdd","root","");
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
                affiche = con.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                Scanner sc = new Scanner(System.in);
                id = sc.nextInt();
                if(con.verifValiditeID(id, "industrie")&&con.verifDataInDB(id, "industrie")) {
                double result_masculin = salaire_cond("employe","sexe", "M", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour un homme est de: " + result_masculin + "€\n");}
                break;
            case 2:
                affiche = con.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if(con.verifValiditeID(id, "industrie")&&con.verifDataInDB(id, "industrie")) {
                double result_feminin = salaire_cond("employe","sexe","F", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour une femme est de: " + result_feminin + "€\n");}
                
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
        con = new Connexion("db_tdd","root","");
        
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
                ArrayList<Double> result_employes = sal_employes("employe");
                break;
            case 3:
                ArrayList<String> affiche;
                String reqListe = "select id_emp, nom, prenom from employe;";
                affiche = con.remplirChampsRequete(reqListe);

                //Afficher les lignes de la requête sélectionnée
                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'id de l'employé: ");
                Scanner sc = new Scanner(System.in);
                int id = sc.nextInt();
                if(con.verifValiditeID(id, "employe")&&con.verifDataInDB(id, "employe")) {
                double result = sal_employe("employe", id);
                System.out.println("Salaire: " + result + " €/mois");}
                break;
        }
    }
    
    /**@author Vick
     * 
     * Menu permettant de choisir le salaire en fonction du statut
     */
    public void salaire_statut() throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
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
                affiche = con.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                Scanner sc = new Scanner(System.in);
                id = sc.nextInt();
                if(con.verifValiditeID(id, "industrie")&&con.verifDataInDB(id, "industrie")) {
                double result_cadre = salaire_cond("employe","statut", "Cadre", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour un cadre est de: " + result_cadre + "€\n");}
                break;
            case 2:
                affiche = con.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if(con.verifValiditeID(id, "industrie")&&con.verifDataInDB(id, "industrie")) {
                double result_emp = salaire_cond("employe","statut", "Employe", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour un employé est de: " + result_emp + "€\n");}
                break;
            case 3:
                affiche = con.remplirChampsRequete(reque);

                for(int i = 0; i < affiche.size(); i++)
                {
                    System.out.print(affiche.get(i)); 
                }
                System.out.println();

                System.out.print("Saisissez l'Id de l'entreprise: ");
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if(con.verifValiditeID(id, "industrie")&&con.verifDataInDB(id, "industrie")) {
                double result_stagiaire = salaire_cond("employe","statut", "Stagiaire", id);
                System.out.println("Le salaire mensuel moyen de l'entreprise pour un stagiaire est de: " + result_stagiaire + "€\n");}
                break;
        }
        
    }
    
    
    /**
     * Permet de calculer les primes en fonction du nombre d'heures travaillées et du statut
     * @author Vick
     * Ecriture de la fonction
     * 
     * @author Loic
     * Modification
     */
    public void calcul_prime(String nomTable) throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            double prime = 0;
            
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = con.remplirChampsRequete(req);

            // afficher les lignes de la requete selectionnee a partir de la liste
        
            for(int i = 0; i < heure.size(); i++)
            {
            	double surplusHoraire=0;
                
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }

                switch (statut.get(i)) {
                case "Stagiaire\n" :
                	surplusHoraire = Double.parseDouble(heure.get(i)) - 175;
                    if(surplusHoraire<=0){ prime = 0;}
                    else{
                       prime = surplusHoraire*(3.75);}
                    break;
                    
                case "Employe\n" :
                	 surplusHoraire = Double.parseDouble(heure.get(i)) - 200;
                     if(surplusHoraire<=0){ prime = 0;}
                     else{
                         prime = surplusHoraire*(7.93);
                     }
                     break;
                     
                case "Cadre\n" :
                	surplusHoraire = Double.parseDouble(heure.get(i)) - 225;
                    if(surplusHoraire<=0){ prime = 0;}
                    else{
                        prime = surplusHoraire*(9.13);
                    }
                    break;
                
                default : 
                	System.out.println("Erreur");
                	break;
            }
                System.out.println(liste.get(i) + "Prime: " + prime + "€/mois\n");
                
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    
    
    
    /**
     * Permet d'afficher les employés présents dans la BDD
     * 
     * @author Vick
     * Ecriture de la fonction
     * 
     * @author Loic
     * Modifications
     */
    public static ArrayList sal_employes(String nomTable) {
        ArrayList<Double> liste_sal;
        liste_sal = new ArrayList<Double>();
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            double sal = 0;
            
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = con.remplirChampsRequete(req);

            // afficher les lignes de la requete selectionnee a partir de la liste

            
            for(int i = 0; i < heure.size(); i++)
            {
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
            
      
                switch (statut.get(i)) {
                case "Stagiaire\n" :
                	sal = Double.parseDouble(heure.get(i))*3.75;
                        liste_sal.add(sal);
                    break;
                    
                case "Employe\n" :
                	 sal = Double.parseDouble(heure.get(i))*7.93;
                         liste_sal.add(sal);
                     break;
                     
                case "Cadre\n" :
                	sal = Double.parseDouble(heure.get(i))*9.13;
                        liste_sal.add(sal);
                    break;
                
                default : 
                	System.out.println("Erreur");
                	break;
            } 
                System.out.println(liste.get(i) + sal + "€/mois\n");
                
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return(liste_sal);
    }
    
    
    /**
     * Permet d'afficher le salaire d'un employé dans la BDD
     * 
     * @author Vick
     * Ecriture de la fonction
     * 
     * @author Loic
     * Modification
     */
    public static double sal_employe(String nomTable, int id) {
        double sal = 0;
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            
            
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + " where id_emp="+id+";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + " where id_emp="+id+";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + " where id_emp="+id+";";
            liste = con.remplirChampsRequete(req);

            
            for(int i = 0; i < heure.size(); i++)
            {
                
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
     
                
                switch (statut.get(i)) {
                case "Stagiaire\n" :
                	sal = Double.parseDouble(heure.get(i))*3.75;
                    break;
                    
                case "Employe\n" :
                	 sal = Double.parseDouble(heure.get(i))*7.93;
                     break;
                     
                case "Cadre\n" :
                	sal = Double.parseDouble(heure.get(i))*9.13;
                    break;
                
                default : 
                	System.out.println("Erreur");
                	break;
            }
                    System.out.print(liste.get(i));
                    
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return (sal);
        
    }



    /**
     * Permet d'afficher le salaire moyen dans une entreprise
     * 
     * @author Vick
     * Ecriture de la fonction
     * 
     * @author Loic
     * Modification
     */
    public double salaire_entreprise(String nomTable, int id) throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        double sal = 0, sal_moy = 0;
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            
          
            double n = 0;            
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + " where fk_id_ind="+id+";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable  + " where fk_id_ind="+id+";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            

            // afficher les lignes de la requete selectionnee a partir de la liste
            for(int i = 0; i < heure.size(); i++)
            {
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
         
               switch (statut.get(i)) {
                case "Stagiaire\n" :
                	sal += Double.parseDouble(heure.get(i))*3.75;
                	n+=1;
                    break;
                    
                case "Employe\n" :
                	 sal += Double.parseDouble(heure.get(i))*7.93;
                	 n+=1;
                     break;
                     
                case "Cadre\n" :
                	sal += Double.parseDouble(heure.get(i))*9.13;
                	n+=1;
                    break;
                
                default : 
                	System.out.println("Erreur");
                	break;
                
            }
            }
            sal_moy = (sal/n);
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return(sal_moy);
    }
    
    /**
     * Permet d'afficher le salaire moyen selon le genre ou le statut dans une entreprise
     * 
     *@author Vick
     *Ecriture de la fonction
     *@author Loic
     *Ré-ecriture
     */
    public double salaire_cond(String nomTable, String condColumnName, String condColumnInput, int id) throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        double sal=0, sal_moyen=0;
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            
            int n=0;
            
            String choice="WHERE "+condColumnName+"='"+condColumnInput+"' AND fk_id_ind ="+id;
            
            String requeteSelectionnee = "select nb_heure from " + nomTable + " "+choice+";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable+ " "+choice+";";
            statut = con.remplirChampsRequete(reqSelectionnee);

            
            for(int i = 0; i < heure.size(); i++)
            {

                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
                switch (statut.get(i)) {
                case "Stagiaire\n" :
                	sal += Double.parseDouble(heure.get(i))*3.75;
                	n+=1;
                    break;
                    
                case "Employe\n" :
                	 sal += Double.parseDouble(heure.get(i))*7.93;
                	 n+=1;
                     break;
                     
                case "Cadre\n" :
                	sal += Double.parseDouble(heure.get(i))*9.13;
                	n+=1;
                    break;
                
                default : 
                	System.out.println("Erreur");
                	break;
                
            }    
                
            }
            sal_moyen = (sal/n);
         
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return(sal_moyen);
    }
    
    

}