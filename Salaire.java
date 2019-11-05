/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonction;

import static fonction.Fonction.getMoyenneHeureEmployeProjet;
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
                salaire_entreprise("employe");
                break;
            case 4:
                salaire_sexe();
                break;
            case 5:
                salaire_statut();
                break;
            case 6:
                salaire_employé();
                break;
        }
    }
    
    
    
    
    
    public void salaire_employé() throws SQLException, ClassNotFoundException {
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
                sal_employés("employe");
                break;
            case 3:
                sal_employé("employe");
                break;
        }
    }
    
    
    /**
     * Afficher les lignes de la table sélectionnée
     */
    public void calcul_prime(String nomTable) throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            double prime = 0;
            int comp1,comp2 = 0;
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = con.remplirChampsRequete(req);

            // afficher les lignes de la requete selectionnee a partir de la liste
            String[] array = new String[heure.size()]; // Liste
            double[] tab = new double[heure.size()]; //Nombre d'heures
            double[] tab3 = new double[heure.size()]; // Nombre d'heures supplémentaires
            String[] tab2 = new String[heure.size()]; // Statut
            
            for(int i = 0; i < heure.size(); i++)
            {
                array[i] = liste.get(i);
                
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
                tab[i] = st.nextDouble();
                //System.out.println(tab[i]);
                
                //tab[i] = Array.getDouble(heure, i);
                tab2[i] = statut.get(i);
                comp1 = tab2[i].compareTo("Stagiaire");
                if(comp1 <= 1){
                    tab3[i] = tab[i] - 35;
                    if(tab3[i]<=0){ prime = 0;}
                    else{
                        prime = tab3[i]*20;
                    }
                    
                }
                comp2 = tab2[i].compareTo("Cadre");
                if(comp2 <= 1){
                    tab3[i] = tab[i] - 45;
                    if(tab3[i]<=0){ prime = 0;}
                    else{
                        prime = tab3[i]*80;
                    }
                }
                comp1 = tab2[i].compareTo("Employe");
                if(comp1 <= 1){
                    tab3[i] = tab[i] - 40;
                    if(tab3[i]<=0){ prime = 0;}
                    else{
                        prime = tab3[i]*(62.5);
                    }
                }
                
                //System.out.println(liste.get(i));
                System.out.println(array[i] + "Prime: " + prime + "€/mois\n");
                
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    
    
    
    /**
     * Afficher les lignes de la table sélectionnée
     */
    public static void sal_employés(String nomTable) {

        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            double sal = 0;
            int comp1,comp2 = 0;
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = con.remplirChampsRequete(req);

            // afficher les lignes de la requete selectionnee a partir de la liste
            String[] array = new String[heure.size()]; // Liste
            double[] tab = new double[heure.size()]; //Nombre d'heure
            String[] tab2 = new String[heure.size()]; // Statut
            
            for(int i = 0; i < heure.size(); i++)
            {
                array[i] = liste.get(i);
                
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
                tab[i] = st.nextDouble();
                
                tab2[i] = statut.get(i);
                comp1 = tab2[i].compareTo("Stagiaire");
                if((comp1 == 1) || (comp1 == 0)){
                    sal = tab[i]*20;
                }
                comp2 = tab2[i].compareTo("Cadre");
                if((comp2 == 1) || (comp2 == 0)){
                    sal = tab[i]*80;
                }
                comp1 = tab2[i].compareTo("Employe");
                if((comp1 == 1) || (comp1 == 0)){
                    sal = tab[i]*(62.5);
                }
                
                
                System.out.println(array[i] + sal + "€/mois\n");
                
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    
    /**
     * Afficher les lignes de la table sélectionnée
     */
    public static void sal_employé(String nomTable) {

        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            ArrayList<String> affiche;
            ArrayList<String> num_id;
            
            double sal = 0;
            int comp1,comp2 = 0;
            int id;
            
            String reqListe = "select id_emp, nom, prenom from " + nomTable + ";";
            affiche = con.remplirChampsRequete(reqListe);

            //Afficher les lignes de la requête sélectionnée
            String[] aff = new String[affiche.size()]; // Affichage liste

            for(int i = 0; i < affiche.size(); i++)
            {
                System.out.print(affiche.get(i)); 
            }
            System.out.println();
            
            System.out.print("Saisissez l'id de l'employé: ");
            Scanner scan = new Scanner(System.in);
            id = scan.nextInt();
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = con.remplirChampsRequete(req);
            
            String requ = "select id_emp from " + nomTable + ";";
            num_id = con.remplirChampsRequete(requ);

            // afficher les lignes de la requête sélectionnée a partir de la liste
            String[] array = new String[heure.size()]; // Liste
            double[] tab = new double[heure.size()]; // Nombre d'heure
            String[] tab2 = new String[heure.size()]; // Statut
            int[] tab3 = new int[heure.size()]; // Statut
            
            for(int i = 0; i < heure.size(); i++)
            {
                array[i] = liste.get(i);
                
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
                tab[i] = st.nextDouble();
                //System.out.println(tab[i]);
                
                Scanner str = new Scanner(num_id.get(i));
                while (!str.hasNextInt())
                {
                    str.next();
                }
                tab3[i] = str.nextInt();
                
                tab2[i] = statut.get(i);
                comp1 = tab2[i].compareTo("Stagiaire");
                if((comp1 == 1) || (comp1 == 0)){
                    sal = tab[i]*20;
                }
                comp2 = tab2[i].compareTo("Cadre");
                if((comp2 == 1) || (comp2 == 0)){
                    sal = tab[i]*80;
                }
                comp1 = tab2[i].compareTo("Employe");
                if((comp1 == 1) || (comp1 == 0)){
                    sal = tab[i]*(62.5);
                }
                if(tab3[i] == id){
                    System.out.println(array[i] + sal + "€/mois\n");
                }
                
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }



    /**
     * Afficher les lignes de la table sélectionnée
     */
    public void salaire_entreprise(String nomTable) throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            ArrayList<String> ind;
            ArrayList<String> affiche;
            
            int comp1,comp2 = 0;
            double sal = 0;
            double n = 0;
            
            String reque = "select * from industrie;";
            affiche = con.remplirChampsRequete(reque);
            
            for(int i = 0; i < affiche.size(); i++)
            {
                System.out.print(affiche.get(i)); 
            }
            System.out.println();
            
            System.out.print("Saisissez l'Id de l'entreprise: ");
            Scanner scan = new Scanner(System.in);
            int id = scan.nextInt();
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = con.remplirChampsRequete(req);
            
            String requ = "select FK_id_ind from " + nomTable + ";";
            ind = con.remplirChampsRequete(requ);

            // afficher les lignes de la requete selectionnee a partir de la liste
            String[] array = new String[heure.size()]; // Liste
            double[] tab = new double[heure.size()]; //Nombre d'heures
            String[] tab2 = new String[heure.size()]; // Statut
            int[] tab3 = new int[heure.size()]; // Id
            
            for(int i = 0; i < heure.size(); i++)
            {
                array[i] = liste.get(i);
                
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
                tab[i] = st.nextDouble();
                
                Scanner str = new Scanner(ind.get(i));
                while (!str.hasNextInt())
                {
                    str.next();
                }
                tab3[i] = str.nextInt();
                
                
                tab2[i] = statut.get(i);
                comp1 = tab2[i].compareTo("Stagiaire");
                if(((comp1 == 1) || (comp1 == 0)) && (tab3[i] == id)){
                    sal += tab[i]*20;
                    n += 1;
                }
                comp2 = tab2[i].compareTo("Cadre");
                if(((comp2 == 1) || (comp2 == 0)) && (tab3[i] == id)){
                    sal += tab[i]*80;
                    n += 1;
                }
                comp1 = tab2[i].compareTo("Employe");
                if(((comp1 == 1) || (comp1 == 0)) && (tab3[i] == id)){
                    sal += tab[i]*(62.5);
                    n += 1;
                }
                
            }
            System.out.println("Le salaire mensuel moyen de l'entreprise est de: " + (sal/n) + "€\n");
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    
    
    /**
     * Afficher les lignes de la table sélectionnée
     */
    public void salaire_sexe() throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        int choix = 0;
        
        System.out.println("1. Masculin");
        System.out.println("2. Féminin\n");
        System.out.print("Votre choix: ");
        Scanner scan = new Scanner(System.in);
        choix = scan.nextInt();
        
        switch(choix){
            case 1:
                salaire_masculin("employe");
                break;
            case 2:
                salaire_feminin("employe");
                break;
        }
        
    }
    
    
    
    
    
    /**
     * Afficher les lignes de la table sélectionnée
     */
    public void salaire_masculin(String nomTable) throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            ArrayList<String> ind;
            ArrayList<String> affiche;
            ArrayList<String> sex;
            
            int comp1,comp2,comp3 = 0;
            double sal = 0;
            double n = 0;
            
            String reque = "select * from industrie;";
            affiche = con.remplirChampsRequete(reque);
            
            for(int i = 0; i < affiche.size(); i++)
            {
                System.out.print(affiche.get(i)); 
            }
            System.out.println();
            
            System.out.print("Saisissez l'Id de l'entreprise: ");
            Scanner scan = new Scanner(System.in);
            int id = scan.nextInt();
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = con.remplirChampsRequete(req);
            
            String requ = "select FK_id_ind from " + nomTable + ";";
            ind = con.remplirChampsRequete(requ);
            
            String reqSex = "select sexe from " + nomTable + ";";
            sex = con.remplirChampsRequete(reqSex);

            // afficher les lignes de la requete selectionnee a partir de la liste
            String[] array = new String[heure.size()]; // Liste
            double[] tab = new double[heure.size()]; //Nombre d'heures
            String[] tab2 = new String[heure.size()]; // Statut
            int[] tab3 = new int[heure.size()]; // Id
            String[] tab4 = new String[heure.size()]; // Sexe
            
            for(int i = 0; i < heure.size(); i++)
            {
                array[i] = liste.get(i);
                
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
                tab[i] = st.nextDouble();
                
                Scanner str = new Scanner(ind.get(i));
                while (!str.hasNextInt())
                {
                    str.next();
                }
                tab3[i] = str.nextInt();
                
                
                //System.out.println(tab[i]);
                
                //tab[i] = Array.getDouble(heure, i);
                tab2[i] = statut.get(i);
                tab4[i] = sex.get(i);
                comp1 = tab2[i].compareTo("Stagiaire");
                comp3 = tab4[i].compareTo("M");
                System.out.println(comp3);
                if(((comp1 == 1) || (comp1 == 0)) && (tab3[i] == id) && ((comp3 == 0) || (comp3 == 1))){
                    sal += tab[i]*20;
                    n += 1;
                    //System.out.println(comp1);
                }
                comp2 = tab2[i].compareTo("Cadre");
                if(((comp2 == 1) || (comp2 == 0)) && (tab3[i] == id) && ((comp3 == 0) || (comp3 == 1))){
                    sal += tab[i]*80;
                    n += 1;
                }
                comp1 = tab2[i].compareTo("Employe");
                if(((comp1 == 1) || (comp1 == 0)) && (tab3[i] == id) && ((comp3 == 0) || (comp3 == 1))){
                    sal += tab[i]*(62.5);
                    n += 1;
                }
                
            }
            System.out.println("Le salaire mensuel moyen de l'entreprise chez les hommes est de: " + (sal/n) + "€\n");
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    
    /**
     * Afficher les lignes de la table sélectionnée
     */
    public void salaire_feminin(String nomTable) throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            ArrayList<String> ind;
            ArrayList<String> affiche;
            ArrayList<String> sex;
            
            int comp1,comp2,comp3 = 0;
            double sal = 0;
            double n = 0;
            
            String reque = "select * from industrie;";
            affiche = con.remplirChampsRequete(reque);
            
            for(int i = 0; i < affiche.size(); i++)
            {
                System.out.print(affiche.get(i)); 
            }
            System.out.println();
            
            System.out.print("Saisissez l'Id de l'entreprise: ");
            Scanner scan = new Scanner(System.in);
            int id = scan.nextInt();
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = con.remplirChampsRequete(req);
            
            String requ = "select FK_id_ind from " + nomTable + ";";
            ind = con.remplirChampsRequete(requ);
            
            String reqSex = "select sexe from " + nomTable + ";";
            sex = con.remplirChampsRequete(reqSex);

            // afficher les lignes de la requete selectionnee a partir de la liste
            String[] array = new String[heure.size()]; // Liste
            double[] tab = new double[heure.size()]; //Nombre d'heures
            String[] tab2 = new String[heure.size()]; // Statut
            int[] tab3 = new int[heure.size()]; // Id
            String[] tab4 = new String[heure.size()]; // Sexe
            
            for(int i = 0; i < heure.size(); i++)
            {
                array[i] = liste.get(i);
                
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
                tab[i] = st.nextDouble();
                
                Scanner str = new Scanner(ind.get(i));
                while (!str.hasNextInt())
                {
                    str.next();
                }
                tab3[i] = str.nextInt();
                
               
                tab2[i] = statut.get(i);
                tab4[i] = sex.get(i);
                comp1 = tab2[i].compareTo("Stagiaire");
                comp3 = tab4[i].compareTo("F");
                System.out.println(comp3);
                if(((comp1 == 1) || (comp1 == 0)) && (tab3[i] == id) && ((comp3 == 0) || (comp3 == 1))){
                    sal += tab[i]*20;
                    n += 1;
                }
                comp2 = tab2[i].compareTo("Cadre");
                if(((comp2 == 1) || (comp2 == 0)) && (tab3[i] == id) && ((comp3 == 0) || (comp3 == 1))){
                    sal += tab[i]*80;
                    n += 1;
                }
                comp1 = tab2[i].compareTo("Employe");
                if(((comp1 == 1) || (comp1 == 0)) && (tab3[i] == id) && ((comp3 == 0) || (comp3 == 1))){
                    sal += tab[i]*(62.5);
                    n += 1;
                }
                
                
            }
            System.out.println("Le salaire mensuel moyen de l'entreprise chez les femmes est de: " + (sal/n) + "€\n");
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    
    
    /**
     * Afficher les lignes de la table sélectionnée
     */
    public void salaire_statut() throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        int choix = 0;
        
        System.out.println("1. Cadre");
        System.out.println("2. Employe");
        System.out.println("3. Stagiaire\n");
        System.out.print("Votre choix: ");
        Scanner scan = new Scanner(System.in);
        choix = scan.nextInt();
        
        switch(choix){
            case 1:
                salaire_stat("employe", "Cadre");
                break;
            case 2:
                salaire_stat("employe", "Employe");
                break;
            case 3:
                salaire_stat("employe", "Stagiaire");
                break;
        }
        
    }
    
    
    /**
     * Afficher les lignes de la table sélectionnée
     */
    public void salaire_stat(String nomTable, String statuts) throws SQLException, ClassNotFoundException {
        con = new Connexion("db_tdd","root","");
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            ArrayList<String> ind;
            ArrayList<String> affiche;
            
            int comp1,comp2 = 0;
            double sal_stagiaire = 0, sal_emp = 0, sal_cadre = 0;
            double ns = 0, ne = 0, nc = 0;
            
            String reque = "select * from industrie;";
            affiche = con.remplirChampsRequete(reque);
            
            for(int i = 0; i < affiche.size(); i++)
            {
                System.out.print(affiche.get(i)); 
            }
            System.out.println();
            
            System.out.print("Saisissez l'Id de l'entreprise: ");
            Scanner scan = new Scanner(System.in);
            int id = scan.nextInt();
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = con.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = con.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = con.remplirChampsRequete(req);
            
            String requ = "select FK_id_ind from " + nomTable + ";";
            ind = con.remplirChampsRequete(requ);

            // afficher les lignes de la requete selectionnee a partir de la liste
            String[] array = new String[heure.size()]; // Liste
            double[] tab = new double[heure.size()]; //Nombre d'heures
            String[] tab2 = new String[heure.size()]; // Statut
            int[] tab3 = new int[heure.size()]; // Id
            
            for(int i = 0; i < heure.size(); i++)
            {
                array[i] = liste.get(i);
                
                Scanner st = new Scanner(heure.get(i));
                while (!st.hasNextDouble())
                {
                    st.next();
                }
                tab[i] = st.nextDouble();
                
                Scanner str = new Scanner(ind.get(i));
                while (!str.hasNextInt())
                {
                    str.next();
                }
                tab3[i] = str.nextInt();
                
                
                //System.out.println(tab[i]);
                
                //tab[i] = Array.getDouble(heure, i);
                tab2[i] = statut.get(i);
                comp1 = tab2[i].compareTo("Stagiaire");
                if(((comp1 == 1) || (comp1 == 0)) && (tab3[i] == id)){
                    sal_stagiaire += tab[i]*20;
                    ns += 1;
                    //System.out.println(comp1);
                }
                comp2 = tab2[i].compareTo("Cadre");
                if(((comp2 == 1) || (comp2 == 0)) && (tab3[i] == id)){
                    sal_cadre += tab[i]*80;
                    nc += 1;
                }
                comp1 = tab2[i].compareTo("Employe");
                if(((comp1 == 1) || (comp1 == 0)) && (tab3[i] == id)){
                    sal_emp += tab[i]*(62.5);
                    ne += 1;
                }
                
                
            }
            if((statuts.compareTo("Stagiaire") == 0) || (statuts.compareTo("Stagiaire") == 1)){
                System.out.println("Le salaire mensuel moyen d'un " + statuts + "dans l'entreprise  est de: " + (sal_stagiaire/ns) + "€\n");
            }
            else if((statuts.compareTo("Employe") == 0) || (statuts.compareTo("Employe") == 1)){
                System.out.println("Le salaire mensuel moyen d'un " + statuts + "dans l'entreprise  est de: " + (sal_emp/ne) + "€\n");
            }
            else if((statuts.compareTo("Cadre") == 0) || (statuts.compareTo("Cadre") == 1)){
                System.out.println("Le salaire mensuel moyen d'un " + statuts + "dans l'entreprise  est de: " + (sal_cadre/nc) + "€\n");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
}
