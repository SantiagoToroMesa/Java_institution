package models;

import java.util.ArrayList;

public class Student extends Person {
    private String course;
    private ArrayList<Subject> subjects = new ArrayList<>();
    public Student(String name, int age, int document, String email, String course) {
        super(name, age, document, email);
        this.course = course;
    }

    public String getcourse() {
        return course;
    }

    public void setcourse(String course) {
        this.course = course;
    }

    public void addSubject(Subject subject){
        if(subjects.size() <5){
            subjects.add(subject);
        }
    }

    public ArrayList<Subject> getsubjects() {
        return subjects;
    }

    public double calculateAverage(){
        double sum = 0.0;
        double average;
        for(Subject s : subjects){
            sum += s.getNote();
        }
        average = sum / subjects.size();
        return average;
    }

    public void setsubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String mostrarinformacion(){
        return "name: " + getName() + " " +
                "age: " + getAge() + " " +
                "document: " + getDocument() + " " +
                "email: " + getEmail() + " " +
                "course: " + course;
    }
}
