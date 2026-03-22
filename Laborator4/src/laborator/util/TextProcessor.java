package laborator.util;

import laborator.model.Course;
import laborator.model.Student;
import laborator.model.Syllabus;
import laborator.record.GradeRecord;

public final class TextProcessor {

    private TextProcessor() {
    }

    public static String demonstrateStringImmutability(String text) {
        String original = text;
        text.toUpperCase();
        return original;
    }

    public static boolean compareLiteralStrings() {
        String s1 = "catalog";
        String s2 = "catalog";
        return s1.equals(s2);
//        return s1 == s2;
//        String s3 = new String("catalog");
//        return s1.equals(s3);
    }

    public static boolean compareHeapStrings() {
        String s1 = new String("catalog");
        String s2 = new String("catalog");
        return s1.equals(s2);
    }

    public static String describeStringMethods(String text) {
        if (text == null) {
            text = "";
        }

        String trimmed = text.trim();
        String firstThree = trimmed.length() >= 3 ? trimmed.substring(0, 3) : trimmed;
        boolean containsJava = trimmed.contains("Java");
        int length = trimmed.length();

        return "Text analizat: '" + trimmed + '\'' +
                "\nLungime: " + length +
                "\nPrimele 3 caractere: " + firstThree +
                "\nContine 'Java': " + containsJava;
    }

    public static String buildStudentReportWithStringBuilder(Student student, Course course, GradeRecord record) {
        StringBuilder sb = new StringBuilder();

        sb.append("=== RAPORT STUDENT ===\n");
        sb.append("ID: ").append(student.getId()).append('\n');
        sb.append("Nume: ").append(student.getFullName()).append('\n');
        sb.append("Email: ").append(student.getEmail()).append('\n');
        sb.append("An studiu: ").append(student.getYearOfStudy()).append('\n');
        sb.append("Curs: ").append(course.getCode()).append(" - ").append(course.getTitle()).append('\n');
        sb.append("Profesor coordonator: ").append(course.getCoordinatorName()).append('\n');
        sb.append("Nota: ").append(record.grade()).append('\n');
        sb.append("Promovat: ").append(record.isPromoted() ? "DA" : "NU").append('\n');

        return sb.toString();
    }

    public static String buildThreadSafeLog(String operation, String value) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[LOG] ");
        buffer.append(operation);
        buffer.append(" -> ");
        buffer.append(value);
        return buffer.toString();
    }

    public static String joinTopics(Syllabus syllabus) {
        StringBuilder sb = new StringBuilder();
        String[] topics = syllabus.getTopics();

        for (String topic : topics) {
            sb.append("- ").append(topic).append('\n');
        }

        return sb.toString();
    }

    public static String arrayToString(String sentence) {
        String[] words = extractWords(sentence);
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if (i < words.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    public static String[] extractWords(String sentence) {
        if (sentence == null || sentence.isBlank()) {
            return new String[0];
        }
        return sentence.trim().split("[ .,:;!?]+");
    }
}