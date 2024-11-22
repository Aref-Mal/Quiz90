package interface_adapter.access_quiz;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.access_quiz.AccessQuizOutputBoundary;
import use_case.access_quiz.AccessQuizOutputData;

/**
 * The presenter for the Access Quiz Use Case.
 */
public class AccessQuizPresenter implements AccessQuizOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final AccessedQuizInfoViewModel accessedQuizInfoViewModel;

    public AccessQuizPresenter(ViewManagerModel viewManagerModel,
                               LoggedInViewModel loggedInViewModel,
                               AccessedQuizInfoViewModel accessedQuizInfoViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.accessedQuizInfoViewModel = accessedQuizInfoViewModel;
    }

    @Override
    public void prepareSuccessView(AccessQuizOutputData response) {
        final AccessedQuizInfoState accessedQuizInfoState = accessedQuizInfoViewModel.getState();

        accessedQuizInfoState.setQuizObject(response.getQuizObject());
        accessedQuizInfoState.setQuizName(response.getQuizName());
        accessedQuizInfoState.setNumQuestions(response.getNumQuestions());
        accessedQuizInfoState.setAuthor(response.getAuthor());

        this.accessedQuizInfoViewModel.setState(accessedQuizInfoState);
        this.accessedQuizInfoViewModel.firePropertyChanged();

        this.viewManagerModel.setState(accessedQuizInfoViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setQuizKeyError(error);
        loggedInViewModel.firePropertyChanged("keyError");
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
