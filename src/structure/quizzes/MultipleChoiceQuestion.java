package structure.quizzes;

import java.util.ArrayList;
import java.util.Collections;

public class MultipleChoiceQuestion extends Question {
    protected ArrayList<MultipleChoiceAnswer> answerOptions;

    public MultipleChoiceQuestion(String questionText) {
        super(questionText);
        this.answerOptions = new ArrayList<>();
    }

    public ArrayList<MultipleChoiceAnswer> getAnswerOptions() {
        return answerOptions;
    }

    public void addAnswerOption(MultipleChoiceAnswer answerOption) {
        this.answerOptions.add(new MultipleChoiceAnswer(answerOption));
    }

    public void shuffleAnswers() {
        Collections.shuffle(this.answerOptions);
    }

    public String toString() {
        StringBuilder strBldr = new StringBuilder();
        strBldr.append(this.questionText).append("\n");
        char nextLetter = 'a';
        for (MultipleChoiceAnswer answer : this.answerOptions) {
            strBldr.append(nextLetter++)
                    .append(") ")
                    .append(answer.answerText)
                    .append("\n");
        }
        return strBldr.substring(0, strBldr.length() - 1);
    }
}
