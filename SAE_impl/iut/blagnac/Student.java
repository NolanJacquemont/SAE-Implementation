package iut.blagnac;

/**
 * @author Cédric-Alexandre Pascal
 * Cette classe sert à représenter un étudiant
 */

public class Student {
    //le prénom de l'étudiant
    private String firstName;
    // le nom de famille de l'étudiant
    private String lastName;
    // le nombre de passages
    private int nbrPassages = 0;
    
    public Student(String nom, String nomDeFamille){
        this.firstName = nom;
        this.lastName = nomDeFamille;
    }
    
    /**
     * @return le prénom et le nom de l'élève sous forme de chaîne de caractères
     */
    public String getFullName() {
        return (firstName + " " + lastName);
    }
    
    /**
     * @return le prénom et le nom de l'élève sous forme de chaîne de caractères
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @return le prénom et le nom de l'élève sous forme de chaîne de caractères
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * @return Le nombre de passages de l'élèves
     */
    public int getPassages(){
        return nbrPassages;
    }

    /**
     * incrémente le nombre de passages de l'élève
     */
    public void incrPassages(){
        nbrPassages++;
    }
}
