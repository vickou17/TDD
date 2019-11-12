package mathematical;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.DAOrequester;
import dao.DAOverification;

public class SalaireCalculator {
	
	
	public SalaireCalculator() {
		
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
         
    	 DAOverification verif = new DAOverification("db_tdd", "root", "");
         DAOrequester dr = new DAOrequester("db_tdd", "root", "");
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            double prime = 0;
            
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = dr.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = dr.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = dr.remplirChampsRequete(req);

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
                       prime = surplusHoraire*(3.75*1.25);}
                    break;
                    
                case "Employe\n" :
                	 surplusHoraire = Double.parseDouble(heure.get(i)) - 200;
                     if(surplusHoraire<=0){ prime = 0;}
                     else{
                         prime = surplusHoraire*(7.93*1.25);
                     }
                     break;
                     
                case "Cadre\n" :
                	surplusHoraire = Double.parseDouble(heure.get(i)) - 225;
                    if(surplusHoraire<=0){ prime = 0;}
                    else{
                        prime = surplusHoraire*(9.13*1.25);
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
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public static ArrayList sal_employes(String nomTable) throws ClassNotFoundException, SQLException {
    	 DAOverification verif = new DAOverification("db_tdd", "root", "");
         DAOrequester dr = new DAOrequester("db_tdd", "root", "");
        ArrayList<Double> liste_sal;
        liste_sal = new ArrayList<Double>();
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            double sal = 0;
            
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + ";";
            heure = dr.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + ";";
            statut = dr.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + ";";
            liste = dr.remplirChampsRequete(req);

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
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public static double sal_employe(String nomTable, int id) throws ClassNotFoundException, SQLException {
        double sal = 0;
        DAOverification verif = new DAOverification("db_tdd", "root", "");
        DAOrequester dr = new DAOrequester("db_tdd", "root", "");
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            ArrayList<String> liste;
            
            
            
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + " where id_emp="+id+";";
            heure = dr.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable + " where id_emp="+id+";";
            statut = dr.remplirChampsRequete(reqSelectionnee);
            
            String req = "select nom, prenom, statut from " + nomTable + " where id_emp="+id+";";
            liste = dr.remplirChampsRequete(req);

            
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
         
        double sal = 0, sal_moy = 0;
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            DAOverification verif = new DAOverification("db_tdd", "root", "");
            DAOrequester dr = new DAOrequester("db_tdd", "root", "");
            
          
            double n = 0;            
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select nb_heure from " + nomTable + " where fk_id_ind="+id+";";
            heure = dr.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable  + " where fk_id_ind="+id+";";
            statut = dr.remplirChampsRequete(reqSelectionnee);
            

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
         
        double sal=0, sal_moyen=0;
        DAOverification verif = new DAOverification("db_tdd", "root", "");
        DAOrequester dr = new DAOrequester("db_tdd", "root", "");
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            
            int n=0;
            
            String choice="WHERE "+condColumnName+"='"+condColumnInput+"' AND fk_id_ind ="+id;
            
            String requeteSelectionnee = "select nb_heure from " + nomTable + " "+choice+";";
            heure = dr.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable+ " "+choice+";";
            statut = dr.remplirChampsRequete(reqSelectionnee);

            
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
    
    /**
     * Permet d'afficher le salaire moyen selon le genre ou le statut dans les autres entreprises que celle sélectionée
     * 
     *@author Loic
     *Ecriture
     */
    public double autre_salaire_cond(String nomTable, String condColumnName, String condColumnInput, int id) throws SQLException, ClassNotFoundException {
         
        double sal=0, sal_moyen=0;
        DAOverification verif = new DAOverification("db_tdd", "root", "");
        DAOrequester dr = new DAOrequester("db_tdd", "root", "");
        
        try {
            ArrayList<String> heure;
            ArrayList<String> statut;
            
            int n=0;
            
            String choice="WHERE "+condColumnName+"='"+condColumnInput+"' AND fk_id_ind !="+id;
            
            String requeteSelectionnee = "select nb_heure from " + nomTable + " "+choice+";";
            heure = dr.remplirChampsRequete(requeteSelectionnee);
            
            String reqSelectionnee = "select statut from " + nomTable+ " "+choice+";";
            statut = dr.remplirChampsRequete(reqSelectionnee);

            
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
