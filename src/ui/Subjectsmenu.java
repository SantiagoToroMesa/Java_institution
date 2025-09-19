package ui;

import Enums.Subjects;
import models.Student;
import models.Subject;
import utils.InputRequester;
import utils.JOptionpanelUtils;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Subjectsmenu {
    private Student student;

    public Subjectsmenu(Student student) {
        this.student = student;
    }

    public void start(){
        String [] options = {"Create subject", "Show subjects", "Calculate average", "Exit"};
        String option;

        do {
            option = JOptionpanelUtils.InputOptionList("Select your option", student.getName() + "'s " + " Subjects menu", options);

            if(option == null){return;}

            switch(option){

                case "Create subject":
                    createSubject();
                    break;

                case "Show subjects":
                    showSubjects();
                    break;

                case "Calculate average":
                    calculateAverage();
                    break;

            }
        }while(option != "Exit");
    }

    private void createSubject(){
        while(true) {
            String name = JOptionpanelUtils.InputOptionList(
                    "Select the subject:",
                    "Subjects available",
                    Arrays.stream(Subjects.values())
                            .map(Enum::name)
                            .toArray(String[]::new)
            );

            boolean exists = student.getsubjects().stream()
                    .anyMatch(s -> s.getName().equalsIgnoreCase(name));

            if (exists) {
                JOptionpanelUtils.ShowMessage("You're already registered in this subject");
                continue;
            }

            double note = InputRequester.requestDouble("Enter the subject note (0.0 - 5.0").orElse(0.0);
            if (note > 5.0 || note < 0.0) {
                JOptionpanelUtils.ShowMessage("The note has to be between 0.0 - 5.0");
                continue;
            }

            Subject subject = new Subject(note, name);
            student.addSubject(subject);
            return;
        }
    }

    private void showSubjects(){
        List<Subject> subjects = student.getsubjects();
        StringBuilder Ssubjects = new StringBuilder();
        if(!subjects.isEmpty()){
            Ssubjects.append("==== "+student.getName() + "'s " + "subjects menu" + " ====\n");
            Ssubjects.append(String.format("%-15s | %-10s | %-10s" , "name", "note", "status"));
            Ssubjects.append("\n===========================\n");
            for(Subject s : subjects){
                Ssubjects.append(String.format("%-15s | %-10.2f | %-10s",
                        s.getName(),
                        s.getNote(),
                        s.itsApproved() ? "Approved\n" : "Reproved\n"));
            }
        }else {
            Ssubjects.append("No subjects registered for " + student.getName() + ".");
        }

        JOptionpanelUtils.messagemenu(Ssubjects, student.getName() + "'s " + "subjects list");
    }

    private void calculateAverage(){
        if(!student.getsubjects().isEmpty()){
            JOptionpanelUtils.ShowMessage("Calculated average = " + student.calculateAverage());
        }else{
            JOptionpanelUtils.ShowMessage("First create the subjects");
        }

    }
}
