package dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOverification extends DAOconnexion {

	public DAOverification(String nameDatabase, String loginDatabase, String passwordDatabase)
			throws SQLException, ClassNotFoundException {
		super(nameDatabase, loginDatabase, passwordDatabase);
		// TODO Auto-generated constructor stub
	}
	
	 /**
     * Methode qui verifie l'integrité de l'id en entrée pour la table donnée
     * @return 
     * @throws java.sql.SQLException
     * @author Loic
     */
	public boolean verifValiditeID(int id, String table) throws SQLException {
    	boolean validity=false;
    	
    	try {
    	innitConn();
    	stmt = conn.createStatement();
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
    	} finally{ 
    		closeConn();
    	}
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
    	try {
    	innitConn();
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
        
        finally{ 
        	closeConn();
    	}
    }

}
