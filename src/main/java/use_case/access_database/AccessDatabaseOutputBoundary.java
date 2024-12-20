package use_case.access_database;

import entity.Quiz;
import entity.RetrievedQuiz;

/**
 * The output boundary for the Access database use case.
 */
public interface AccessDatabaseOutputBoundary {

    /**
     * Prepares sucess view for the access database use case.
     * @param data the output data
     */
    void prepareSuccessView(AccessDatabaseOutputData data);

    /**
     * Prepares the main menu view for returning to logged in view use case.
     */
    void switchToMainMenuView();

    /**
     * Prepares the playthrough view for playing a selected quiz.
     * @param quiz the selected quiz
     */
    void preparePlaythroughView(RetrievedQuiz quiz);

    /**
     * Executes the switch to create question view.
     * @param username the user logged in's username
     */
    void switchToCreateQuestionView(String username);

    void prepareFailView(String error);
}
