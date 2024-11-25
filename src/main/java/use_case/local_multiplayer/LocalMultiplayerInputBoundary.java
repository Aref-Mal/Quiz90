package use_case.local_multiplayer;

import use_case.quiz_generation.QuizGenerationInputData;

/**
 * Input Boundary for actions related to local multiplayer.
 */
public interface LocalMultiplayerInputBoundary {

    /**
     * Executes quiz generation for Local Multiplayer Use Case.
     * @param localMultiplayerInputData the input data for generating the quiz.
     */
    void execute(QuizGenerationInputData localMultiplayerInputData);

    /**
     * Executes action to switch to Local Multiplayer view.
     */
    void switchToLocalMultiplayerView();

    /**
     * Executes the action to switch to the Main Menu view.
     */
    void switchToMainMenuView();
}
