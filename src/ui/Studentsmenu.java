package ui;

import models.Student;
import services.Studentservices;
import utils.InputRequester;
import utils.JOptionpanelUtils;
import javax.swing.*;
import java.awt.*;

import java.util.List;

public class Studentsmenu {
    Studentservices services = new Studentservices();
    public void start(){
        String [] opciones = {"Create student", "Show students" , "Select student", "Delete student", "Exit"};
        String option;
        do {
            option = JOptionpanelUtils.InputOptionList("Welcome to the students managments system \nwhat do you want to do today?", "Students menu", opciones);
            switch (option){
                case "Create student":
                    createStudent();
                    break;

                case "Show students":
                    showStudents();
                    break;

                case "Select student":
                    selectStudent();
                    break;

                case "Delete student":
                    deleteStudent();
                    break;
            }

        }while(option != "Exit");
    }

    private void createStudent(){
        String name = InputRequester.requestString("Enter the student's name: ");
        int age = InputRequester.requestInteger("Enter the student's age: ");
        int document = InputRequester.requestInteger("Enter the student's document: ");
        String email = InputRequester.requestString("Enter the student's email: ");
        String course = InputRequester.requestString("Enter the student's course");
        Student student = new Student(name,age,document,email,course);
        services.createStudent(student);
        System.out.println(student.mostrarinformacion());
    }

    private void showStudents(){
        List<Student> institution = services.showStudents();
        StringBuilder students = new StringBuilder();

        students.append("====================== Institution ======================\n");
        students.append(String.format("%-15s | %-5s | %-15s | %-30s | %-5s\n",
                "Name", "Age", "Document", "Email", "course"));
        students.append("====================================================\n");
        for(Student s : institution){
            students.append(String.format("%-15s | %-5d | %-15d | %-30s | %-10s\n",
                    s.getName(), s.getAge(), s.getDocument(), s.getEmail(), s.getcourse()));
        }
        JOptionpanelUtils.messagemenu(students, "Students list");
    }

    private void deleteStudent() {
        // String builder
        List<Student> institution = services.showStudents();
        StringBuilder students = new StringBuilder();
        students.append("================ Students ================\n");
        students.append(String.format("%-15s | %-5s | %-10s\n",
                "Name", "Age", "Course"));
        students.append("==========================================\n");
        for (Student s : institution) {
            students.append(String.format("%-15s | %-5d | %-10s\n",
                    s.getName(), s.getAge(), s.getcourse()));
        }

        JTextArea textArea = new JTextArea(students.toString(), 10, 40);
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12)); // fuente de ancho fijo
        JScrollPane scrollPane = new JScrollPane(textArea);

        String name = (String) JOptionPane.showInputDialog(
                null,
                new Object[]{scrollPane, "Enter the student's name that you want to delete:"},
                "Students list",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );

        if (name != null && !name.trim().isEmpty()) {
            services.deleteStudent(name.trim());
        }
    }

    private void selectStudent(){
        Student selectOne;
        // String builder
        List<Student> institution = services.showStudents();
        StringBuilder students = new StringBuilder();
        students.append("================ Students ================\n");
        students.append(String.format("%-15s | %-5s | %-10s\n",
                "Name", "Age", "Course"));
        students.append("==========================================\n");
        for (Student s : institution) {
            students.append(String.format("%-15s | %-5d | %-10s\n",
                    s.getName(), s.getAge(), s.getcourse()));
        }

        JTextArea textArea = new JTextArea(students.toString(), 10, 40);
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12)); // fuente de ancho fijo
        JScrollPane scrollPane = new JScrollPane(textArea);

        String name = (String) JOptionPane.showInputDialog(
                null,
                new Object[]{scrollPane, "Enter the student's name that you want to select:"},
                "Students list",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );

        if (name != null && !name.trim().isEmpty()) {
            selectOne = services.searchStudent(name);
            if(selectOne == null){
                JOptionpanelUtils.ShowMessage("Error founding " + name + "\ndont exits in the students list");
                return;
            }
            new Subjectsmenu(selectOne).start();
        }
    }

}
