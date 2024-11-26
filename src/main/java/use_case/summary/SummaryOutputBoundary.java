package use_case.summary;

import entity.Quiz;
import kotlin.Pair;

import java.util.Map;

/**
 * Output Boundary for the Summary Use Case.
 */
public interface SummaryOutputBoundary {

    /**
     * Executes the action to switch to the Summary view.
     *
     * @param quiz The completed quiz containing the questions and answers.
     * @param numOfCorrectAnswers The number of correct answers achieved by the user.
     * @param playerInfo A map of player information.
     */
    void prepareSummaryView(Quiz quiz, int numOfCorrectAnswers, Map<Integer, Pair<String, Boolean>> playerInfo);

    /**
     * Prepares the view to switch to the Main Menu screen.
     */
    void switchToMainMenuView();
}