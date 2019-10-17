/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonction;
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
        afficherLignes(employe);

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
