package ui;

import services.Professorservices;
import services.Studentservices;
import utils.JOptionpanelUtils;

public class PrincipalMenu {
    private final Professorservices professorservices;
    private final Studentservices studentservices;

    public PrincipalMenu(Studentservices studentservices, Professorservices professorservices){
        this.studentservices = studentservices;
        this.professorservices = professorservices;
    }
    public void start(){
        String[] options = {"Students", "Professors", "Exit"};
        String opti = " ";
        do {
            opti = JOptionpanelUtils.InputOptionList("Welcome to the riwi institute\nTotal students: " +
                    studentservices.ShowStudents().size() + "\nTotal professors: " +
                    professorservices.ShowProfessor().size() + "\nSelect your option:"
                    , "Principal menu", options);

            if(opti == null){return;}

            switch(opti){
                case "Students":
                    new Studentsmenu(studentservices).start();
                    break;
                case "Professors":
                    new Professorsmenu(studentservices,professorservices).start();
                    break;
            }

        }while(!opti.equalsIgnoreCase("Exit"));


    }
}
