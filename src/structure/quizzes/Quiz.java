package structure.quizzes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Quiz {
    private LocalDateTime startDateTime;
    private LocalDateTime finishDateTime;
    ArrayList<Question> questions;

    public Quiz(LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        this.setStartDateTime(startDateTime);
        this.setFinishDateTime(finishDateTime);
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
}
