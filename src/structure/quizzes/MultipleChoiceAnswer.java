package structure.quizzes;

public class MultipleChoiceAnswer {
    protected String answerText;
    protected boolean correctness;

    public MultipleChoiceAnswer(String answerText, boolean correctness) {
        this.answerText = answerText;
        this.correctness = correctness;
    }

    public MultipleChoiceAnswer(MultipleChoiceAnswer answer) {
        this.answerText = answer.answerText;
        this.correctness = answer.correctness;
    }

    public boolean isCorrect() {
        return this.correctness;
    }
}
