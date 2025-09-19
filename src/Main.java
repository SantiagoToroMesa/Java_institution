import models.Student;
import models.Subject;
import services.Professorservices;
import services.Studentservices;
import ui.PrincipalMenu;
import ui.Professorsmenu;
import ui.Studentsmenu;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Studentservices studentservices = new Studentservices();
        Professorservices professorservices = new Professorservices();
        new PrincipalMenu(studentservices, professorservices).start();
    }

}
