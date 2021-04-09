package structure.quizzes;

public abstract class Question {
    protected String questionText;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }
}
