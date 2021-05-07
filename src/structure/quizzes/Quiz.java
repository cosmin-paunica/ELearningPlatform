package structure.quizzes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Quiz {
    private int id;
    private LocalDateTime startDateTime;
    private LocalDateTime finishDateTime;
    ArrayList<Question> questions;

    public Quiz(int id, LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        this.id = id;
        this.setStartDateTime(startDateTime);
        this.setFinishDateTime(finishDateTime);
        this.questions = new ArrayList<>();
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = LocalDateTime.of(
                startDateTime.getYear(),
                startDateTime.getMonth(),
                startDateTime.getDayOfMonth(),
                startDateTime.getHour(),
                startDateTime.getMinute()
        );
    }

    public void setFinishDateTime(LocalDateTime finishDateTime) {
        this.finishDateTime = LocalDateTime.of(
                finishDateTime.getYear(),
                finishDateTime.getMonth(),
                finishDateTime.getDayOfMonth(),
                finishDateTime.getHour(),
                finishDateTime.getMinute()
        );
    }

    public void addQuestion(Question newQuestion) {
        this.questions.add(newQuestion);
    }

    public void shuffleQuestions() {
        Collections.shuffle(this.questions);
    }

    @Override
    public String toString() {
        StringBuilder strBldr = new StringBuilder();
        int nextQuestionNumber = 1;
        for (Question question : this.questions) {
            strBldr.append(Integer.toString(nextQuestionNumber++))
                    .append(": ")
                    .append(question.toString())
                    .append("\n");
        }
        return strBldr.toString();
    }
}
