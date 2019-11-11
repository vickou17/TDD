/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tddVickou;

/*
 * 
 * Librairies importées
 */
import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * Connexion a votre BDD locale ou Ã  distance sur le serveur de l'ECE via le tunnel SSH
 * 
 * @author segado
 */
public class Connexion {

    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    private Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    /**
     * ArrayList public pour les tables
     */
    public ArrayList<String> tables = new ArrayList<>();
    /**
     * ArrayList public pour les requÃªtes de sélection
     */
    public ArrayList<String> requetes = new ArrayList<>();
    /**
     * ArrayList public pour les requÃªtes de MAJ
     */
    public ArrayList<String> requetesMaj = new ArrayList<>();

    /**
     * Constructeur avec 3 paramÃ¨tres : nom, login et password de la BDD locale
     *
     * @param nameDatabase
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost/" + nameDatabase;

        //création d'une connexion JDBC Ã  la base 
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }

    
    /**
     * Méthode qui ajoute la table en parametre dans son ArrayList
     *
     * @param table
     */
    public void ajouterTable(String table) {
        tables.add(table);
    }

    /**
     * Méthode qui ajoute la requete de selection en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * Méthode qui ajoute la requete de MAJ en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }

    /**
     * Méthode qui retourne l'ArrayList des champs de la table en parametre
     *
     * @param table
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsTable(String table) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        // ajouter un "\n" Ã  la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }

    
    public ArrayList remplirChampsTable1(String table, String nom) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table + "where" + nom + "= 'MAAF'");

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        // ajouter un "\n" Ã  la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }
    
    
    
    
    
    /**
     * Methode qui retourne l'ArrayList des champs de la requete en parametre
     * @param requete
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsRequete(String requete) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<String>();

        // tant qu'il reste une ligne 
        while (rset.next()) {
            String champs;
            champs = rset.getString(1); // ajouter premier champ

            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset.getString(i + 1);
            }

            // ajouter un "\n" Ã  la ligne des champs
            champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
        }

        // Retourner l'ArrayList
        return liste;
    }
    
    /** Retourne le résultat de la requÃªte unique
     * 
     * @author Loic
     * @param requete
     * @return
     * @throws SQLException
     */
    public String recupResultatRequete(String requete) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);
        
        String resultStatement=null;

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        rset.next();
        resultStatement=rset.getString(1);
        
        // tant qu'il reste une ligne 
        

        // Retourner l'ArrayList
        return resultStatement;
    }
    
    /**
     * Methode qui verifie l'integrité de l'id en entrée pour la table donnée
     * @return 
     * @throws java.sql.SQLException
     * @author Loic
     */
    public boolean verifValiditeID(int id, String table) throws SQLException {
    	boolean validity=false;
    	
        // récupération de l'ordre de la requete
    	switch(table) {
    	
    	case "industrie":
        rset = stmt.executeQuery("SELECT id_ind FROM industrie");
        break;
        
    	case "projet":
        rset = stmt.executeQuery("SELECT id_projet FROM projet");
        break;
        
    	case "employe":
    	rset = stmt.executeQuery("SELECT id_emp FROM employe");
    	break;
        
        
    	}
        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();


        // tant qu'il reste une ligne 
        while (rset.next()) {
       
            if(Integer.parseInt(rset.getString(1))==id) {
            	validity=true;
            }
        }
        if(!validity) {
        	System.out.println("Erreur, veuillez entrer un id valide.");
        }

        // Retourner l'ArrayList
        return validity;
    }
    
    /**
     * Methode qui verifie l'integrité de l'id en entrée pour la table donnée
     * @return 
     * @throws java.sql.SQLException
     * @author Loic
     */
    public boolean verifDataInDB(int id, String table) throws SQLException {
    	boolean validity=false;
        // récupération de l'ordre de la requete
    	switch(table) {
    	
    	case "industrie":
        rset = stmt.executeQuery("SELECT id_emp FROM (employe)"
        		+ "WHERE (FK_id_ind="+id+");");
        break;
        
    	case "projet":
        rset = stmt.executeQuery("SELECT fk_id_projet FROM intermediaire "
        		+ "WHERE fk_id_projet="+id+";");
        break;
        
    	case "employe":
    	rset = stmt.executeQuery("SELECT id_emp FROM employe WHERE id_emp="+id+";");
    	break;
        
        
    	}
        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();


        // tant qu'il reste une ligne 
        if(rset.next()) {

        	return true;
        } else {
        	System.out.println("Erreur veuillez entrer des valeurs dans la BDD.");
        }

        // Retourner l'ArrayList
        return validity;
    }
    
    /**
     * Methode qui compte le nombre d'élèments dans une table de la BDD
     * @return 
     * @throws java.sql.SQLException
     * @author Loic
     */
    public int countElementInDB(String table) throws SQLException {
    	int number=0;
        // récupération de l'ordre de la requete
    	switch(table) {
    	
    	case "industrie":
        rset = stmt.executeQuery("SELECT COUNT(id_ind) FROM (industrie)");
        System.out.println("OK");
        break;
        
    	case "projet":
    		rset = stmt.executeQuery("SELECT COUNT(id_projet) FROM (projet)");
    		 System.out.println("OK");
        break;
        
    	case "employe":
    	rset = stmt.executeQuery("SELECT COUNT(id_emp )FROM employe");
    	 System.out.println("OK");
    	break;
    	
    	default:
    		break;
  
    	}
        // récupération du résultat de l'ordre
    	rsetMeta = rset.getMetaData();

        rset.next();
        number= rset.getInt(1);

        // tant qu'il reste une ligne 
       

        // Retourner l'ArrayList
        return number;
    }
    
    /**
     * Methode qui compte le nombre d'élèments dans une table de la BDD
     * @return 
     * @throws java.sql.SQLException
     * @author Loic
     */
    public int countElementInDBWithCond(String table) throws SQLException {
    	int number=0;
        // récupération de l'ordre de la requete
    	switch(table) {
    	
    	case "industrie":
        rset = stmt.executeQuery("SELECT COUNT(id_ind) FROM (industrie)");
        System.out.println("OK");
        break;
        
    	case "projet":
    		rset = stmt.executeQuery("SELECT COUNT(id_projet) FROM (projet)");
    		 System.out.println("OK");
        break;
        
    	case "employe":
    	rset = stmt.executeQuery("SELECT COUNT(id_emp )FROM employe");
    	 System.out.println("OK");
    	break;
    	
    	default:
    		break;
  
    	}
        // récupération du résultat de l'ordre
    	rsetMeta = rset.getMetaData();

        rset.next();
        number= rset.getInt(1);

        // tant qu'il reste une ligne 
       

        // Retourner l'ArrayList
        return number;
    }
    
    /**
     * Renvoi la liste des ID d'une table
     * @return 
     * @throws java.sql.SQLException
     * @author Loic
     */
    public ArrayList<Integer> listeIdTable(String table) throws SQLException {
    	ArrayList<Integer> listeID = new ArrayList<Integer>();
        // récupération de l'ordre de la requete
    	switch(table) {
    	
    	case "industrie":
        rset = stmt.executeQuery("SELECT id_ind FROM industrie");
        break;
        
    	case "projet":
    		rset = stmt.executeQuery("SELECT id_projet FROM (projet)");
        break;
        
    	case "employe":
    	rset = stmt.executeQuery("SELECT id_emp FROM employe");
    	break;
    	
    	default:
    		break;
  
    	}
        // récupération du résultat de l'ordre
    	rsetMeta = rset.getMetaData();

    	while(rset.next()) {
        listeID.add(rset.getInt(1));
    	}

        // tant qu'il reste une ligne 
       

        // Retourner l'ArrayList
        return listeID;
    }
    
    /**
     * Renvoi le nom en fonction de l'ID d'une table
     * @return 
     * @throws java.sql.SQLException
     * @author Loic
     */
    public String nameInTable(int id, String table) throws SQLException {
    	String nameOfElement;
        // récupération de l'ordre de la requete
    	switch(table) {
    	
    	case "industrie":
        rset = stmt.executeQuery("SELECT nom_ind FROM industrie WHERE id_ind="+id);
        break;
        
    	case "projet":
    		rset = stmt.executeQuery("SELECT nom_projet FROM (projet) WHERE id_projet="+id);
        break;
        
    	case "employe":
    	rset = stmt.executeQuery("SELECT nom FROM employe where id_emp="+id);
    	break;
    	
    	default:
    		break;
  
    	}
        // récupération du résultat de l'ordre
    	rsetMeta = rset.getMetaData();
    	rset.next();
        nameOfElement=rset.getString(1);
    	

        // tant qu'il reste une ligne 
       

        // Retourner l'ArrayList
        return nameOfElement;
    }
    
 
    
    

    /**
     * Méthode qui execute une requete de MAJ en parametre
     * @param requeteMaj
     * @throws java.sql.SQLException
     */
    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }

    void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}