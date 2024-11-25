package use_case.access_quiz;

import entity.PlayerCreatedQuestion;
import org.json.JSONObject;

import entity.PlayerCreatedQuiz;
import entity.PlayerCreatedQuizFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The Access Quiz Interactor.
 */
public class AccessQuizInteractor implements AccessQuizInputBoundary {

    private final AccessQuizUserDataAccessInterface customQuizDataAccessObject;
    private final AccessQuizOutputBoundary accessQuizPresenter;

    public AccessQuizInteractor(AccessQuizUserDataAccessInterface customQuizDataAccessObject,
                                AccessQuizOutputBoundary accessQuizPresenter) {
        this.customQuizDataAccessObject = customQuizDataAccessObject;
        this.accessQuizPresenter = accessQuizPresenter;
    }

    @Override
    public void execute(AccessQuizInputData accessQuizInputData) {

        final String key = accessQuizInputData.getKey();

        if (customQuizDataAccessObject.existsByKey(key)) {
            final PlayerCreatedQuizFactory quizFactory = new PlayerCreatedQuizFactory();
            final JSONObject quizData = customQuizDataAccessObject.getQuizFromKey(key);
            final PlayerCreatedQuiz quizObject = quizFactory.create(quizData, key);

            final AccessQuizOutputData accessQuizOutputData = new AccessQuizOutputData(
                    false, quizObject);

            accessQuizPresenter.prepareSuccessView(accessQuizOutputData);
            System.out.println("key exists");
        }
        // this is here for easy testing and can be removed later if wanted
        else if (key.equals("testing")) {
            final List<PlayerCreatedQuestion> temp2 = new ArrayList<>();
            final PlayerCreatedQuiz temp = new PlayerCreatedQuiz("title", temp2, "author");
            final AccessQuizOutputData accessQuizOutputData = new AccessQuizOutputData(
                    false, temp);

            accessQuizPresenter.prepareSuccessView(accessQuizOutputData);
        }
        else {
            System.out.println("key not exists");
            accessQuizPresenter.prepareFailView("There is no quiz with the key \"" + key + "\".");
        }
    }

    @Override
    public void switchToLoggedInView() {
        accessQuizPresenter.switchToLoggedInView();
    }
}
