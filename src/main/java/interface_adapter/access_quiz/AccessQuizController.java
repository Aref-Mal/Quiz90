package interface_adapter.access_quiz;

import use_case.access_quiz.AccessQuizInputBoundary;
import use_case.access_quiz.AccessQuizInputData;

/**
 * The controller for the Access Quiz Use Case.
 */
public class AccessQuizController {

    private final AccessQuizInputBoundary accessQuizUseCaseInteractor;

    public AccessQuizController(AccessQuizInputBoundary accessQuizUseCaseInteractor) {
        this.accessQuizUseCaseInteractor = accessQuizUseCaseInteractor;
    }

    /**
     * Executes the Access Quiz Use Case.
     * @param key the created quiz key
     */
    public void execute(String key) {
        final AccessQuizInputData accessQuizInputData = new AccessQuizInputData(key);

        accessQuizUseCaseInteractor.execute(accessQuizInputData);
    }

    /**
     * Executes the Switch to Logged In View use case.
     */
    public void switchToLoggedInView() {
        accessQuizUseCaseInteractor.switchToLoggedInView();
    }
}
