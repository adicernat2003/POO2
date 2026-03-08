package lab.inheritance;

import lab.model.Student;

/**
 * Demonstreaza accesul la un camp protected din alt pachet prin mostenire.
 */
public class GraduateStudent extends Student {
    private String dissertationTitle;

    public GraduateStudent(int studentId, String name, int yearOfStudy, String dissertationTitle) {
        super(studentId, name, yearOfStudy);
        this.dissertationTitle = dissertationTitle;
    }

    public void advanceResearchYear() {
        // acces permis pentru ca yearOfStudy este protected
        this.yearOfStudy++;
    }

    public void printResearchStatus() {
        System.out.println("Masterand/doctorand: " + getName() +
                " | an=" + yearOfStudy +
                " | lucrare='" + dissertationTitle + "'");
    }
}
