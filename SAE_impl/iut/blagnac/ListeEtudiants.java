package iut.blagnac;

import java.io.*;
import java.util.Scanner;
import iut.blagnac.Student;
import iut.blagnac.StudentManager;

public class ListeEtudiants {
    /** 
     * donne le nombre d’étudiants de la liste pfListe
     * @param   pfListe IN tableau contenant la liste d'étudiants nom, prenom,  
     * @return le nombre d’étudiants de la liste
     */
    public static int nbEtudiant( String pfListe[][]){
        return pfListe.length;
    }
    
    /** 
     * charge la liste des étudiants dans la classe StudentManager
     * @param   pfFileName IN le nom du fichier à lire
     * @param   le délimiteur de champs dans le fichier csv
     *
     */
    public static void loadStudents(String pfFileName, String pfDelimiter)throws FileNotFoundException{
        /* nombre  de lignes du fichier */
        BufferedReader read = new BufferedReader(new FileReader(pfFileName));
        int nbElt = 0;
        try
        {
            while (read.readLine() != null)
                nbElt++;
            read.close(); 
        }
         catch (IOException e)
        {
            e.printStackTrace();
        } 
            
        /* lecture du fichier */
        
        String line = "";
        
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(pfFileName));
            int cpt = 0;
            while ((line = reader.readLine()) != null)   //loops through every line until null found
            {
                String[] token = line.split(pfDelimiter);    // separate every token by comma
                Student etudiant = new Student(token[1], token[0]);
                StudentManager.ajouterEtudiant(etudiant);
                cpt ++;
            }
            reader.close(); 
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
