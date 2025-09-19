package services;

import Interfaces.ProfessorInterface;
import models.Professor;
import models.Student;
import models.Subject;

import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Professorservices implements ProfessorInterface {
    private List<Professor> professors = new ArrayList<>();


    @Override
    public void CreateProfessor(Professor professor) {
        professors.add(professor);
    }

    @Override
    public List<Professor> ShowProfessor() {
        return professors;
    }

    @Override
    public Professor SearchProfessor(String name) {
        return professors.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Override
    public void DeleteProfessor(String name) {
        professors.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }
}
