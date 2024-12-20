package use_case.access_quiz;

import entity.RetrievedQuiz;

/**
 * The output boundary for the Access Quiz Use Case.
 */
public interface AccessQuizOutputBoundary {

    /**
     * Prepares the success view for the Access Quiz use case.
     * @param outputData the output data
     */
    void prepareSuccessView(AccessQuizOutputData outputData);

    /**
     * Prepares the failure view for the Access Quiz use case.
     * @param errorMessage the message to display on error
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to Logged In View.
     */
    void switchToLoggedInView();

    /**
     * Play Accessed Quiz.
     * @param quizObject the quiz to be played
     */
    void playAccessedQuiz(RetrievedQuiz quizObject);
}
