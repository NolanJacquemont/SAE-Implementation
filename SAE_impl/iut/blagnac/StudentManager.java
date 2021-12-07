package iut.blagnac;

import java.util.*;

/**
 * @author James Robertson
 *
 * Cette classe permet de gérer l'ensemble des étudiants
 */
public class StudentManager {
    // On crée une liste vide qui peut contenir uniquement des objets de type Etudiant
    private static ArrayList<Student> students = new ArrayList<>();
    private static Student lastStudent;
    
    /**
     * Ajouter un etudiant à la liste des étudiants
     * @param etudiant : l'étudiant à ajouter
     */
    public static void ajouterEtudiant(Student etudiant) {
        students.add(etudiant);
    }
    
    /**
     * @return un étudiant aléatoire.
     */
    public static Student randomStudent(){
        // on choisit un index aléatoire pour récupérer un étudiant
        int index = (int)(Math.random() * students.size());
        Student student = students.get(index);
        lastStudent = student;
        student.incrPassages();
        return student;
    }
    
    /**
     * @return la liste des étudiants
     */
    public static ArrayList<Student> getStudents(){
        return students;
    }

    /**
     * @return S'il s'agit du premier tirage on tire un étudiant au hasard.
     * Sinon on tire l'étudiant qui a le moins de passages s'il ne s'agit pas du dernier à être passé
     * Autrement, il choisit le deuxième étudiant avec le moins de passages.
     */
    public static Student nextStudent() {

        // on modifie l'ordre de la liste de façon aléatoire
        Collections.shuffle(students);

        //on crée un étudiant résultat qui est le premier de la liste
        Student result = students.get(0);

        // On obtient un étudiant qui a le moins de passages et qui n'est pas le dernier a être intérrogé
        for(Student student : students ){
            if(student.getPassages()<result.getPassages() && student!=lastStudent){
                result = student;
            }
       
        }
        // si on a pas trouvé d'étudiant répondant à ce critère on enlève le critère du dernierEtudiant
        if(result==null) {
            for(Student student : students ){
                if(student.getPassages()<result.getPassages()){
                    result = student;
                }

            }
        }
        // on définit le dernier étudiant comme étant celui que l'on va retourner
        lastStudent = result;
        result.incrPassages();//on incrémente son nombre de passages

        return result;
    }
    
    /**
     * Remet à 0 la liste des étudiants
     */
    public static void clearStudents(){
        students.clear();
        lastStudent = null;
    }
    
    /**
     * @return le dernier étudiant tiré au sort
     */
    public static Student lastStudent() {
        return lastStudent;
    }
    
    /**
     * @return le nom du dernier étudiant tiré au sort, ou "Aucun" s'il n'existe pas
     */
    public static String lastStudentName() {
        String lastStudentName;
        if (lastStudent == null) {
            lastStudentName = "Aucun";
        }else {
            lastStudentName = lastStudent.getFullName();   
        }
        return lastStudentName;
    }
}

