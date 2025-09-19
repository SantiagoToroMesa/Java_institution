package Interfaces;

import models.Student;

import java.util.List;

public interface StudentInterface {
    void CreateStudent(Student student);
    List<Student> ShowStudents();
    Student SearchStudent(String name);
    void DeleteStudent(String name);
}
