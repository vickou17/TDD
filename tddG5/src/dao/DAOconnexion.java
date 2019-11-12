/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public abstract class DAOconnexion {

    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    protected Connection conn;
    protected Statement stmt;
    protected ResultSet rset;
    protected ResultSetMetaData rsetMeta;
    
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
    
    protected static String urlDatabase;
    protected static String loginDatabase;
    protected static String passwordDatabase;
    
    public DAOconnexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost/" + nameDatabase;
        
        this.loginDatabase=loginDatabase;
        this.passwordDatabase=passwordDatabase;
        this.urlDatabase=urlDatabase;

    }
    
    /**Iniate the connection to the DB
     * 
     * @throws SQLException
     */
    public void innitConn() throws SQLException {
    	 //création d'une connexion JDBC Ã  la base 
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }
    
    /**Close the connection to the DB
     * 
     * @throws SQLException
     */
    public void closeConn() throws SQLException {
    	conn.close();
    	stmt.close();
    }

    
}