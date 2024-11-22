package use_case.access_quiz;

import entity.PlayerCreatedQuiz;

/**
 * Output data for the Access Quiz use case.
 */
public class AccessQuizOutputData {

    private final boolean useCaseFailed;
    private final PlayerCreatedQuiz quizObject;

    public AccessQuizOutputData(boolean useCaseFailed, PlayerCreatedQuiz quizObject) {
        this.useCaseFailed = useCaseFailed;
        this.quizObject = quizObject;
    }

    public String getQuizName() {
        return quizObject.getTitle();
    }

    public int getNumQuestions() {
        return quizObject.getNumQuestions();
    }

    public String getAuthor() {
        return quizObject.getAuthor();
    }

    public PlayerCreatedQuiz getQuizObject() {
        return quizObject;
    }
}
