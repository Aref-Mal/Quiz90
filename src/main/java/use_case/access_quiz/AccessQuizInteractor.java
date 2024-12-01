package use_case.access_quiz;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import entity.PlayerCreatedQuestion;
import entity.RetrievedQuiz;
import entity.RetrievedQuizFactory;

/**
 * The Access Quiz Interactor.
 */
public class AccessQuizInteractor implements AccessQuizInputBoundary {

    private final AccessQuizDataAccessInterface customQuizDataAccessObject;
    private final AccessQuizOutputBoundary accessQuizPresenter;

    public AccessQuizInteractor(AccessQuizDataAccessInterface customQuizDataAccessObject,
                                AccessQuizOutputBoundary accessQuizPresenter) {
        this.customQuizDataAccessObject = customQuizDataAccessObject;
        this.accessQuizPresenter = accessQuizPresenter;
    }

    @Override
    public void execute(AccessQuizInputData accessQuizInputData) {

        final String key = accessQuizInputData.getKey();

        if (customQuizDataAccessObject.existsByKey(key)) {
            final RetrievedQuizFactory quizFactory = new RetrievedQuizFactory();
            final JSONObject quizData = customQuizDataAccessObject.getQuizFromKey(key);
            final RetrievedQuiz quizObject = quizFactory.create(quizData, key);

            final AccessQuizOutputData accessQuizOutputData = new AccessQuizOutputData(
                    false, quizObject);

            accessQuizPresenter.prepareSuccessView(accessQuizOutputData);
        }
        // this is here for easy testing and can be removed later if wanted
        else if ("testing".equals(key)) {
            final List<PlayerCreatedQuestion> temp2 = new ArrayList<>();
            final RetrievedQuiz temp = new RetrievedQuiz("title", temp2, "author");
            final AccessQuizOutputData accessQuizOutputData = new AccessQuizOutputData(
                    false, temp);

            accessQuizPresenter.prepareSuccessView(accessQuizOutputData);
        }
        else {
            accessQuizPresenter.prepareFailView("There is no quiz with the key \"" + key + "\".");
        }
    }

    @Override
    public void switchToLoggedInView() {
        accessQuizPresenter.switchToLoggedInView();
    }

    @Override
    public void playAccessedQuiz(RetrievedQuiz quizObject) {
        accessQuizPresenter.playAccessedQuiz(quizObject);
    }
}
