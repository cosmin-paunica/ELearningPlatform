package structure.users;

public enum TeachingDegree {
    ASSISTANT,
    LECTURER,
    PROFESSOR;

    public static TeachingDegree fromString(String str) {
        if (str.toLowerCase().equals("assistant"))
            return TeachingDegree.ASSISTANT;
        if (str.toLowerCase().equals("lecturer"))
            return TeachingDegree.LECTURER;
        if (str.toLowerCase().equals("professor"))
            return TeachingDegree.PROFESSOR;
        return null;
    }
}
