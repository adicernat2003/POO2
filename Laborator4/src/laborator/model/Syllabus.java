package laborator.model;

public class Syllabus {
    private String description;
    private String[] topics;

    public Syllabus(String description, String[] topics) {
        this.description = description;
        this.topics = copyArray(topics);
    }

    public Syllabus(Syllabus other) {
        this.description = other.description;
        this.topics = copyArray(other.topics);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTopics() {
        return copyArray(topics);
    }

    public void setTopics(String[] topics) {
        this.topics = copyArray(topics);
    }

    public void addTopic(String topic) {
        if (topic == null || topic.isBlank()) {
            throw new IllegalArgumentException("Topicul nu poate fi gol.");
        }

        String[] newTopics = new String[topics.length + 1];
        for (int i = 0; i < topics.length; i++) {
            newTopics[i] = topics[i];
        }
        newTopics[topics.length] = topic;
        topics = newTopics;
    }

    private String[] copyArray(String[] source) {
        if (source == null) {
            return new String[0];
        }

        String[] copy = new String[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i]; // shallow copy - copiază referința, nu obiectul
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Syllabus{" +
                "description='" + description + '\'' +
                ", topics=[");

        for (int i = 0; i < topics.length; i++) {
            result.append(topics[i]);
            if (i < topics.length - 1) {
                result.append(", ");
            }
        }

        result.append("]}");
        return result.toString();
    }
}