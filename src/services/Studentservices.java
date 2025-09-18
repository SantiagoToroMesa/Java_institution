package services;

import models.Student;

import java.util.ArrayList;
import java.util.List;

public class Studentservices {

    private List<Student> students = new ArrayList<>();

    public void createStudent(Student student){
        students.add(student);
    }

    public List<Student> showStudents(){
        return students;
    }

    public Student searchStudent(String name) {
        for (Student a : students) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public void deleteStudent(String nombre){
        students.removeIf(a -> a.getName().equalsIgnoreCase(nombre));
    }

}
