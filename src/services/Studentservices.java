package services;

import Interfaces.StudentInterface;
import models.Student;

import java.util.ArrayList;
import java.util.List;

public class Studentservices implements StudentInterface {

    private List<Student> students = new ArrayList<>();

    @Override
    public void CreateStudent(Student student) {
        students.add(student);
    }

    @Override
    public List<Student> ShowStudents() {
        return students;
    }

    @Override
    public Student SearchStudent(String name) {
        for (Student a : students) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void DeleteStudent(String name) {
        students.removeIf(a -> a.getName().equalsIgnoreCase(name));
    }
}
