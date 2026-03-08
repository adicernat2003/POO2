package lab.model;

/**
 * Clasa din acelasi pachet cu Student, utila pentru a demonstra accesul default/package-private.
 */
public class PackageInspector {
    public void moveStudentToGroup(Student student, String newGroup) {
        // permis deoarece este in acelasi pachet
        student.changeGroup(newGroup);
    }
}
