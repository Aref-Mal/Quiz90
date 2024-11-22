package app;

import data_access.DBCustomQuizDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.access_quiz.AccessQuizController;
import interface_adapter.access_quiz.AccessQuizPresenter;
import interface_adapter.access_quiz.AccessedQuizInfoViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.local_multiplayer.LocalMultiplayerController;
import interface_adapter.local_multiplayer.LocalMultiplayerPresenter;
import interface_adapter.local_multiplayer.LocalMultiplayerViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.quiz_generation.QuizGenerationController;
import interface_adapter.quiz_generation.QuizGenerationPresenter;
import interface_adapter.quiz_generation.QuizGenerationViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.access_quiz.AccessQuizInputBoundary;
import use_case.access_quiz.AccessQuizInteractor;
import use_case.access_quiz.AccessQuizOutputBoundary;
import use_case.local_multiplayer.LocalMultiplayerInputBoundary;
import use_case.local_multiplayer.LocalMultiplayerInteractor;
import use_case.local_multiplayer.LocalMultiplayerOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.quiz_generation.QuizGenerationInputBoundary;
import use_case.quiz_generation.QuizGenerationInteractor;
import use_case.quiz_generation.QuizGenerationOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);
    private final DBCustomQuizDataAccessObject customQuizDataAccessObject = new DBCustomQuizDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private AccessedQuizInfoViewModel accessedQuizInfoViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private AccessedQuizInfoView accessedQuizInfoView;
    private QuizGenerationViewModel quizGenerationViewModel;
    private QuizGenerationView quizGenerationView;
    private LocalMultiplayerViewModel localMultiplayerViewModel;
    private LocalMultiplayerView localMultiplayerView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the QuizGeneration View to the application.
     * @return this builder
     */
    public AppBuilder addQuizGenerationView() {
        quizGenerationViewModel = new QuizGenerationViewModel();
        quizGenerationView = new QuizGenerationView(quizGenerationViewModel);
        cardPanel.add(quizGenerationView, quizGenerationView.getViewName());
        return this;
    }

    /**
     * Adds the Local Multiplayer View to the application.
     * @return this builder
     */
    public AppBuilder addLocalMultiplayerView() {
        localMultiplayerViewModel = new LocalMultiplayerViewModel();
        localMultiplayerView = new LocalMultiplayerView(localMultiplayerViewModel);
        cardPanel.add(localMultiplayerView, localMultiplayerView.getViewName());
        return this;
    }

    /**
     * Adds the Local Multiplayer Use Case to the application.
     */
    public AppBuilder addLocalMultiplayerUseCase() {
        loggedInViewModel = new LoggedInViewModel();
        final LocalMultiplayerOutputBoundary localMultiplayerPresenter = new LocalMultiplayerPresenter(
                viewManagerModel, localMultiplayerViewModel);

        final LocalMultiplayerInputBoundary localMultiplayerInteractor =
                new LocalMultiplayerInteractor(localMultiplayerPresenter);

        final LocalMultiplayerController localMultiplayerController =
                new LocalMultiplayerController(localMultiplayerInteractor);
        loggedInView.setLocalMultiplayerController(localMultiplayerController);
        localMultiplayerView.setLocalMultiplayerController(localMultiplayerController);
        return this;

    }

    /**
     * Adds the AccessedQuizInfo View to the application.
     * @return this builder
     */
    public AppBuilder addAccessedQuizInfoView() {
        accessedQuizInfoViewModel = new AccessedQuizInfoViewModel();
        accessedQuizInfoView = new AccessedQuizInfoView(accessedQuizInfoViewModel);
        cardPanel.add(accessedQuizInfoView, accessedQuizInfoView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loginViewModel, loggedInViewModel, signupViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Access Quiz Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAccessQuizUseCase() {
        final AccessQuizOutputBoundary accessQuizOutputBoundary = new AccessQuizPresenter(
                viewManagerModel, loggedInViewModel, accessedQuizInfoViewModel
        );

        final AccessQuizInputBoundary accessQuizInteractor = new AccessQuizInteractor(
                customQuizDataAccessObject, accessQuizOutputBoundary
        );

        final AccessQuizController accessQuizController = new AccessQuizController(accessQuizInteractor);
        loggedInView.setAccessQuizController(accessQuizController);
        accessedQuizInfoView.setAccessQuizController(accessQuizController);
        return this;
    }

    /**
     * Adds the Quiz Generation Use Case to the application.
     * @return this builder
     */
    public AppBuilder addQuizGenerationUseCase() {
        loggedInViewModel = new LoggedInViewModel();
        final QuizGenerationOutputBoundary quizGenerationPresenter =
                new QuizGenerationPresenter(viewManagerModel, quizGenerationViewModel, loggedInViewModel);

        final QuizGenerationInputBoundary quizGenerationInteractor =
                new QuizGenerationInteractor(quizGenerationPresenter);

        final QuizGenerationController quizGenerationController =
                new QuizGenerationController(quizGenerationInteractor);
        loggedInView.setQuizGenerationController(quizGenerationController);
        quizGenerationView.setQuizGenerationController(quizGenerationController);
        return this;

    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Quiz90 Trivia Game");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
