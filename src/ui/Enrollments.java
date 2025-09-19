package ui;

import Enums.Subjects;
import models.Professor;
import models.Student;
import models.Subject;
import services.Studentservices;
import utils.JOptionpanelUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enrollments {
    private final Professor professor;
    private final Studentservices studentservices;
    private final List<String> assigned = new ArrayList<>();

    public Enrollments(Professor professor, Studentservices studentservices) {
        this.professor = professor;
        this.studentservices = studentservices;
    }

    public void start() {
        String[] options = {"Teach a subject", "Show the students", "Exit"};
        String opti;
        do {
            opti = JOptionpanelUtils.InputOptionList(
                    "See " + professor.getName() + "'s assigned courses",
                    "Enrollments menu",
                    options
            );

            if(opti == null){return;}

            switch (opti) {
                case "Teach a subject":
                    assignSubject();
                    break;
                case "Show the students":
                    showStudents();
                    break;
            }
        } while (!opti.equalsIgnoreCase("Exit"));
    }

    private void assignSubject() {
        while (true) {
            String name = JOptionpanelUtils.InputOptionList(
                    "Select the subject:",
                    "Subjects available",
                    Arrays.stream(Subjects.values())
                            .map(Enum::name)
                            .toArray(String[]::new)
            );

            boolean exists = professor.getSubjects().stream()
                    .anyMatch(s -> s.getName().equalsIgnoreCase(name));

            if (exists) {
                JOptionpanelUtils.ShowMessage("You're already assigned to this subject");
                continue;
            }

            assigned.add(name);
            Subject subject = new Subject(name, professor.getClassroom());
            professor.addSubject(subject);
            return;
        }
    }

    private void showStudents() {
        if (assigned.isEmpty()) {
            JOptionpanelUtils.ShowMessage("This professor has no assigned subjects yet.");
            return;
        }

        String subjectOpti = JOptionpanelUtils.InputOptionList(
                "Select one of your subjects",
                "Students registered",
                assigned.toArray(new String[0])
        );

        List<Student> totalStudents = studentservices.ShowStudents();
        List<Student> subjectStudents = new ArrayList<>();

        for (Student s : totalStudents) {
            for (Subject sub : s.getsubjects()) {
                if (sub.getName().equalsIgnoreCase(subjectOpti)) {
                    subjectStudents.add(s);
                }
            }
        }

        StringBuilder totalstudents = new StringBuilder();
        totalstudents.append("======== ").append(subjectOpti).append(" Students ========\n");
        totalstudents.append(String.format("%-20s | %-20s | %-10s\n", "Name", "Document", "Course"));
        totalstudents.append("================================================\n");

        for (Student s : subjectStudents) {
            totalstudents.append(String.format("%-20s | %-20d | %-10s\n",
                    s.getName(), s.getDocument(), s.getcourse()));
        }

        JOptionpanelUtils.messagemenu(totalstudents, subjectOpti + " Students");
    }
}
