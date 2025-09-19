package Interfaces;

import models.Professor;
import models.Student;

import java.util.List;

public interface ProfessorInterface {
    void CreateProfessor(Professor professor);
    List<Professor> ShowProfessor();
    Professor SearchProfessor(String name);
    void DeleteProfessor(String name);
}
