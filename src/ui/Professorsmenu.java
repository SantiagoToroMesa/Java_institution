package ui;

import models.Professor;
import services.Professorservices;
import services.Studentservices;
import utils.InputRequester;
import utils.JOptionpanelUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Professorsmenu {
    private final Studentservices studentservices;
    private final Professorservices services;

    public Professorsmenu(Studentservices studentservices, Professorservices services) {
        this.studentservices = studentservices;
        this.services = services;
    }


    public void start(){
        String [] opciones = {"Create Professor", "Show Professors" , "Select Professor", "Delete Professor", "Exit"};
        String option;
        do {
            option = JOptionpanelUtils.InputOptionList("Welcome to the professors managments system \nwhat do you want to do today?", "professors menu", opciones);

            if(option == null){return;}

            switch (option){
                case "Create Professor":
                    createProfessor();
                    break;

                case "Show Professors":
                    showProfessors();
                    break;

                case "Select Professor":
                    selectProfessor();
                    break;

                case "Delete Professor":
                    break;
            }

        }while(option != "Exit");
    }
    
    private void createProfessor(){
        while(true){
        String name = InputRequester.requestString("Enter the professor's name: ");
        int age = InputRequester.requestInteger("Enter the professor's age: ");
        if(age > 100 | age < 18) {
            JOptionpanelUtils.ShowMessage("Age only can be between 18 - 100");
            continue;
        }
        int document = InputRequester.requestInteger("Enter the professor's document: ");
        String email = InputRequester.requestString("Enter the professor's email: ");
        String room = InputRequester.requestString("Enter the professor's room: ");
        
        Professor professor = new Professor(name,age,document,email,room);
        services.CreateProfessor(professor);
        return;
    }
        
}
    private void showProfessors(){
        List<Professor> institution = services.ShowProfessor();
        StringBuilder professors = new StringBuilder();

        professors.append("====================== Institution ======================\n");
        professors.append(String.format("%-15s | %-5s | %-15s | %-30s | %-10s\n",
                "Name", "Age", "Document", "Email", "Classroom"));
        professors.append("====================================================\n");
        for(Professor p : institution){
            professors.append(String.format("%-15s | %-5d | %-15d | %-30s | %-10s\n",
                    p.getName(), p.getAge(), p.getDocument(), p.getEmail(), p.getClassroom()));
        }
        JOptionpanelUtils.messagemenu(professors, "professors list");
    }
    
    private void selectProfessor(){
        Professor selectOne;
        // String builder
        List<Professor> institution = services.ShowProfessor();
        StringBuilder professors = new StringBuilder();
        professors.append("================ professors ================\n");
        professors.append(String.format("%-15s | %-5s | %-10s\n",
                "Name", "Age", "Course"));
        professors.append("==========================================\n");
        for (Professor s : institution) {
            professors.append(String.format("%-15s | %-5d | %-10s\n",
                    s.getName(), s.getAge(), s.getClassroom()));
        }

        JTextArea textArea = new JTextArea(professors.toString(), 10, 40);
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12)); // fuente de ancho fijo
        JScrollPane scrollPane = new JScrollPane(textArea);

        String name = (String) JOptionPane.showInputDialog(
                null,
                new Object[]{scrollPane, "Enter the professor's name that you want to select:"},
                "professors list",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );

        if (name != null && !name.trim().isEmpty()) {
            selectOne = services.SearchProfessor(name);
            if(selectOne == null){
                JOptionpanelUtils.ShowMessage("Error founding " + name + "\ndont exits in the professors list");
                return;
            }
            new Enrollments(selectOne, studentservices).start();
        }
    }
}
