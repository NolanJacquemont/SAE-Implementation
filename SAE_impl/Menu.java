import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import iut.blagnac.*;

/**
 * @author Nolan Jacquemont
 * 
 * Classe principale
 **/

public class Menu extends JFrame {
    private static JTextField text;
    private static JLabel label;
    
    /**
     * @return le nom du fichier csv dans le dossier (si plusieurs, premier dans l'ordre alphabétique), ou test.csv si aucun n'est trouvé
     */
    public static String getCSVFileName() {
        // On récupère le répertoire courant
        File dir = new File(".");
        // On récupère l'ensemble des fichiers du répertoire courant
        File[] directoryListing = dir.listFiles();
        // Pour chaque fichier, on vérifie si son nom termine par ".csv"
        for(File file : dir.listFiles()) {
            if(file.getName().endsWith(".csv")){
                return file.getName();  
             }
        }
        return "test.csv";
    }
    
    /**
     * Charge la liste des étudiants à partir du fichier csv présent dans le dossier
     */
    public static void loadStudents() {
        try {
            ListeEtudiants.loadStudents(getCSVFileName(), ",");
        } catch (Exception e) {  
            System.out.println("Erreur : "+ e.getMessage());
        }    
    }
    
    public static void main(String arguments[]) {
        loadStudents();
        appInterface();
    }
    
    /**
     * Permet d'afficher l'interface principale de l'application
     */
    public static void appInterface() {
        // Création de la fenêtre frame
        JFrame frame = new JFrame("Tirage au sort");
        
        // Création du titre
        JLabel titre = new JLabel("Tirage au sort :", JLabel.CENTER);
        titre.setFont(new Font(titre.getFont().getFamily(), Font.BOLD, 20));
        
        // Création d'un label (texte)
        label = new JLabel("Dernier élève tiré au sort : " + StudentManager.lastStudentName(), JLabel.CENTER);
        label.setFont(new Font(label.getFont().getFamily(), Font.ITALIC, 15));
        
        // Création du menu
        JMenuBar menu = new JMenuBar();
        // Création du sous-menu
        JMenu settingsTirage = new JMenu("Options de tirage");
        
        // Création des options du sous-menu
        JMenuItem afficherListeTirage = new JMenuItem("Afficher la liste du tirage");
        JMenuItem newTirage = new JMenuItem("Recommencer le tirage");
        
        // On ajoute l'option afficherListeTirage au sous-menu settingsTirage
        settingsTirage.add(afficherListeTirage);
        
        // Configuration de l'action lors d'un clic sur "Afficher la liste du tirage"
        afficherListeTirage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                appInterfaceTableau();
            }
        });
        
        // On ajoute l'option newTirage au sous-menu settingsTirage
        settingsTirage.add(newTirage);
        
        // Configuration de l'action lors d'un clic sur "Recommencer le tirage"
        newTirage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentManager.clearStudents();
                loadStudents();
                label.setText("Dernier élève tiré au sort : " + StudentManager.lastStudentName());
                text.setText("");
            }
        });
        
        // On ajoute le sous-menu settingsTirage au menu
        menu.add(settingsTirage);
        
        // Création du panel
        JPanel panel = new JPanel();
        
        // Création du champ de texte (correspond à l'endroit où sera affiché l'élève tiré au sort)
        text = new JTextField();
        text.setFont(new Font(text.getFont().getFamily(), Font.BOLD, 15));
        
        // Création du bouton "tirer un élève au sort"
        JButton btn1 = new JButton("Tirer un élève au sort");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Dernier élève tiré au sort : " + StudentManager.lastStudentName());
                Student nextStudent = StudentManager.nextStudent();
                text.setText(nextStudent.getFullName() + " qui a : " + nextStudent.getPassages() + " passages.");
            }
        });
        
        // On ajoute les boutons au frame
        panel.add(btn1);
        
        // On définit la grille de la fenêtre (5 de haut, 1 de large)
        frame.setLayout(new GridLayout(5, 1));
        
        // On ajoute les différents éléments à frame
        frame.add(menu);
        frame.add(titre);
        frame.add(label);
        frame.add(text);
        frame.add(panel);
        
        // Configuration de la fenêtre
        frame.pack();
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /**
     * Permet d'afficher l'interface de l'application contenant la liste des élèves tirés au sort
     */
    public static void appInterfaceTableau() {
        // Création de la fenêtre frame
        JFrame frame = new JFrame("Tirage au sort");
        
        // Création du titre
        JLabel titre = new JLabel("Liste des élèves tirés au sort :", JLabel.CENTER);
        titre.setFont(new Font(titre.getFont().getFamily(), Font.BOLD, 20));
        
        // Création du menu
        JMenuBar menu = new JMenuBar();
        // Création du sous-menu
        JMenu settingsTirage = new JMenu("Options de tirage");
        
        // Création des options du sous-menu
        JMenuItem continueTirage = new JMenuItem("Continuer le tirage actuel");
        JMenuItem newTirage = new JMenuItem("Recommencer le tirage");
        
        // On ajoute l'option continueTirage au sous-menu settingsTirage
        settingsTirage.add(continueTirage);
        
        // Configuration de l'action lors d'un clic sur "Continuer le tirage actuel"
        continueTirage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                appInterface();
            }
        });
        
        // On ajoute l'option afficherListeTirage au sous-menu settingsTirage
        settingsTirage.add(newTirage);
        
        // Configuration de l'action lors d'un clic sur "Afficher la liste du tirage"
        newTirage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentManager.clearStudents();
                loadStudents();
                frame.setVisible(false);
                appInterfaceTableau();
            }
        });
        
        // On ajoute l'option newTirage au sous-menu settingsTirage
        menu.add(settingsTirage);
        
        // On récupère la liste des étudiants
        ArrayList<Student> listeStudents = StudentManager.getStudents();
        
        // Création des colonnes du tableau
        String[] columns = new String[] {"Prénom", "Nom", "Nombre de passages"};
        
        // Création des données du tableau
        String[][] data = new String[listeStudents.size()][3];
        
        for (int i = 0; i < listeStudents.size(); i++) {
            data[i][0] = listeStudents.get(i).getFirstName();
            data[i][1] = listeStudents.get(i).getLastName();
            data[i][2] = String.valueOf(listeStudents.get(i).getPassages());
        }
        
        // Tri des données en fonction de l'ordre de passage
        Arrays.sort(data, new Comparator<String[]>() {
            public int compare(String[] s1, String[] s2) {
                String num1 = s1[2];
                String num2 = s2[2];
                return num2.compareTo(num1);
            }
        });
        
        // Création du tableau
        JTable table = new JTable(data, columns);
        
        // Création du scrollPane
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        // On ajoute les différents éléments au ContentPane de la fenêtre
        frame.getContentPane().add(menu,BorderLayout.PAGE_START);
        frame.getContentPane().add(titre,BorderLayout.CENTER);
        frame.getContentPane().add(scroll,BorderLayout.PAGE_END);
        
        // Configuration de la fenêtre
        frame.pack();
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);        
    }
       
}