package mathematical;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOrequester;
import dao.DAOverification;

public class MathematicalStatsCalculator {
	
	   /**Retourne la somme d'heures travaillée par l'ensemble des employés dans une industrei
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     * @throws ClassNotFoundException 
     */
    public static String getSommeHeureEmployeEntreprise(int idEntreprise) throws ClassNotFoundException {
    	
        String resultStatement = null;
        try {
        	DAOrequester dr = new DAOrequester("db_tdd", "root", "");
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT SUM(nb_heure) AS somme FROM employe INNER JOIN industrie ON id_ind='"+idEntreprise+"'";
            resultStatement = dr.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    
    /**Retourne le nombre d'heures passés par tous les employés sur un mÃªme projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     * @throws ClassNotFoundException 
     */
    public static String getSommeHeureEmployeProjet(int idProj) throws ClassNotFoundException {
    	
        String resultStatement = null;
        try {
        	  
             DAOrequester dr = new DAOrequester("db_tdd", "root", "");
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT SUM(nb_heure) AS somme FROM projet INNER JOIN intermediaire ON fk_id_projet='"+idProj+"' INNER JOIN employe ON fk_id_emp = id_emp";
            resultStatement = dr.recupResultatRequete(requeteSelectionnee);

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
     * @throws ClassNotFoundException 
     */
    public static String getMoyenneHeureEmployeEntreprise(int idEntreprise) throws ClassNotFoundException {
    	
        String resultStatement = null;
        try {
        	  
             DAOrequester dr = new DAOrequester("db_tdd", "root", "");
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT AVG(nb_heure) AS somme FROM employe INNER JOIN industrie ON id_ind='"+idEntreprise+"'";
            resultStatement = dr.recupResultatRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste

            

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return resultStatement;
    }
    
    /**Retourne la moyenne d'heures passés par tous les employés sur un mÃªme projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public static String getMoyenneHeureEmployeProjet(int idProj) throws ClassNotFoundException, SQLException {
    	  
        
        String resultStatement = null;
        try {
        	 DAOrequester dr = new DAOrequester("db_tdd", "root", "");
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT AVG(nb_heure) AS somme FROM projet INNER JOIN intermediaire ON fk_id_projet='"+idProj+"' INNER JOIN employe ON fk_id_emp = id_emp";
            resultStatement = dr.recupResultatRequete(requeteSelectionnee);

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
     * @throws ClassNotFoundException 
     * @throws NumberFormatException 
     */
    public static String getVarianceHeureEmployeEntreprise(int idEntreprise) throws NumberFormatException, ClassNotFoundException {
    	
        String resultStatement = null;
        try {
        	  
             DAOrequester dr = new DAOrequester("db_tdd", "root", "");
            ArrayList<String> listeHeure;
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT nb_heure FROM employe INNER JOIN industrie ON id_ind='"+idEntreprise+"'";
            
            listeHeure = dr.remplirChampsRequete(requeteSelectionnee);
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
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public static String getVarianceHeureEmployeProjet(int idProj) throws ClassNotFoundException, SQLException {
    	
        String resultStatement = null;
         
        
        try {
        	DAOrequester dr = new DAOrequester("db_tdd", "root", "");
            ArrayList<String> listeHeure;
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "SELECT nb_heure FROM projet INNER JOIN intermediaire ON fk_id_projet='"+idProj+"' INNER JOIN employe ON fk_id_emp = id_emp";
            
            listeHeure = dr.remplirChampsRequete(requeteSelectionnee);
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
     * @throws ClassNotFoundException 
     * @throws NumberFormatException 
     */
    public static String getEcartTypeHeureEmployeEntreprise(int idEntreprise) throws NumberFormatException, ClassNotFoundException {
    	
        String resultStatement = null;
        resultStatement=Double.toString(Math.sqrt(Double.parseDouble(getVarianceHeureEmployeEntreprise(idEntreprise))));
        return resultStatement;
    }
    
    /**Retourne l'écart type d'heures travaillées par l'ensemble des employés sur un projet
     * 
     * @author Loic
     * @param idEntreprise
     * @return
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws NumberFormatException 
     */
    public static String getEcartTypeHeureEmployeProjet(int idProjet) throws NumberFormatException, ClassNotFoundException, SQLException {
    	
        String resultStatement = null;
        resultStatement=Double.toString(Math.sqrt(Double.parseDouble(getVarianceHeureEmployeProjet(idProjet))));
        return resultStatement;
    }
    
    /**Affiche les statistiques avancées pour une entreprise
     * 
     * @param idInd
     * @throws ClassNotFoundException
     * @throws SQLException
     * @author Loic
     */
    public static void superStatInd(int idInd) throws ClassNotFoundException, SQLException {
    	DAOverification verif = new DAOverification("db_tdd", "root", "");
        DAOrequester dr = new DAOrequester("db_tdd", "root", "");
        SalaireCalculator sal = new SalaireCalculator();
    	int nbreEmploye=Integer.parseInt(dr.recupResultatRequete("SELECT COUNT(id_emp) FROM employe INNER JOIN industrie ON (fk_id_ind=id_ind) WHERE id_ind="+idInd));
    	int nbreEmployeM=Integer.parseInt(dr.recupResultatRequete("SELECT COUNT(id_emp) FROM employe INNER JOIN industrie ON (fk_id_ind=id_ind) WHERE (sexe='M') AND id_ind="+idInd));
    	int nbreEmployeF=Integer.parseInt(dr.recupResultatRequete("SELECT COUNT(id_emp) FROM employe INNER JOIN industrie ON (fk_id_ind=id_ind) WHERE (sexe='F')AND id_ind="+idInd));
    
    	System.out.println("Votre entreprise compte : "+nbreEmploye+" employés");
    	System.out.println("Parmis ces employés, vous comptez "+nbreEmployeM+" hommes ("+(((float)nbreEmployeM/(float)nbreEmploye)*100)+"%) et "+nbreEmployeF+" femmes ("+(((float)nbreEmployeF/(float)nbreEmploye)*100)+"%)");
    	
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
    			+" \n \t => Une différence de "+((float)sal.salaire_cond("employe", "sexe", "M", idInd)/(float)sal.autre_salaire_cond("employe", "sexe", "M", idInd))*100+"% que dans une autre entreprise"
    			+"\nUne femme touche en moyenne "+sal.salaire_cond("employe", "sexe", "F", idInd)+"  euros au sein de votre entreprise"
    			+" \n \t => Une différence de "+((float)sal.salaire_cond("employe", "sexe", "F", idInd)/(float)sal.autre_salaire_cond("employe", "sexe", "F", idInd))*100+"% que dans une autre entreprise"
    			+"\nIl ya une différence de "+((float)sal.salaire_cond("employe", "sexe", "M", idInd)/(float)sal.salaire_cond("employe", "sexe", "F", idInd))*100+"% entre le salaire d'un homme et d'une femme dans votre entreprise");
    	
    	
    }

}
