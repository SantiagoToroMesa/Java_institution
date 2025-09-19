package models;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Person{
    private List<Subject>subjects = new ArrayList<>();
    private String Classroom;

    public Professor(String name, int age, int document, String email, String Classroom) {
        super(name, age, document, email);
        this.Classroom = Classroom;
    }
    public void addSubject(Subject subject){
        subjects.add(subject);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getClassroom() {
        return Classroom;
    }

    public void setClassroom(String Classroom) {
        Classroom = Classroom;
    }
}
