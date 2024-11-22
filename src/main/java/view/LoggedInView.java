package view;

import app.Constants;
import interface_adapter.access_quiz.AccessQuizController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.local_multiplayer.LocalMultiplayerController;
import interface_adapter.logout.LogoutController;
import interface_adapter.quiz_generation.QuizGenerationController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private LocalMultiplayerController localMultiplayerController;
    private AccessQuizController accessQuizController;
    private LogoutController logoutController;
    private QuizGenerationController quizGenerationController;

    private final JLabel username;

    private final JTextField sharedQuizKeyField = new JTextField(23);
    private final JLabel sharedQuizKeyErrorField = new JLabel();

    private final JButton logOut;

    private final JButton normalPlay;
    private final JButton playSharedQuiz;
    private final JButton localMultiplayer;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        loggedInViewModel.addPropertyChangeListener(this);

        final TitlePanel titlePanel = new TitlePanel(LoggedInViewModel.TITLE_LABEL);

        final JPanel currentPlayerPanel = new JPanel();
        final Box currentPlayerBox = Box.createHorizontalBox();
        currentPlayerPanel.setBackground(new Color(0, 71, 0));
        currentPlayerPanel.setPreferredSize(new Dimension(1200, 100));
        currentPlayerBox.setOpaque(true);
        currentPlayerBox.setBackground(Color.WHITE);
        currentPlayerBox.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 35));
        final JLabel player = new JLabel("Current Player: ");
        currentPlayerPanel.add(currentPlayerBox);
        currentPlayerBox.add(player);
        username = new JLabel();
        currentPlayerBox.add(username);

        final JPanel buttons0 = new JPanel();
        normalPlay = new JButton("Normal Play");
        buttons0.setBackground(new Color(0, 71, 171));

        buttons0.setLayout(new BoxLayout(buttons0, BoxLayout.X_AXIS));
        buttons0.add(Box.createHorizontalGlue());
        buttonsSizeHelper(normalPlay);
        buttons0.add(normalPlay);
        buttons0.add(Box.createHorizontalGlue());

        final JPanel buttons1 = new JPanel();
        sharedQuizKeyField.setText("Enter quiz key...");
        sharedQuizKeyField.setForeground(Color.GRAY);
        sharedQuizKeyField.setMaximumSize(new Dimension(300, 30));
        playSharedQuiz = new JButton("Play Shared Quiz");
        buttons1.setBackground(new Color(0, 71, 171));

        buttons1.setLayout(new BoxLayout(buttons1, BoxLayout.X_AXIS));
        buttons1.add(Box.createHorizontalGlue());
        buttons1.add(sharedQuizKeyField);
        buttons1.add(Box.createHorizontalStrut(200));
        buttonsSizeHelper(playSharedQuiz);
        buttons1.add(playSharedQuiz);
        buttons1.add(Box.createHorizontalGlue());

        final JPanel errorMessagePanel = new JPanel();
        sharedQuizKeyErrorField.setText("nothing");
        sharedQuizKeyErrorField.setMaximumSize(new Dimension(300, 30));
        errorMessagePanel.setBackground(new Color(0, 71, 171));
        errorMessagePanel.setLayout(new BoxLayout(errorMessagePanel, BoxLayout.X_AXIS));
        errorMessagePanel.add(sharedQuizKeyErrorField);

        final JPanel buttons2 = new JPanel();
        final JButton createdQuizzes = new JButton("My Created Quizzes");
        localMultiplayer = new JButton("Local Multiplayer");
        buttons2.setBackground(new Color(0, 71, 0));

        buttons2.setLayout(new BoxLayout(buttons2, BoxLayout.X_AXIS));
        buttons2.add(Box.createHorizontalGlue());
        buttonsSizeHelper(createdQuizzes);
        buttons2.add(createdQuizzes);
        buttons2.add(Box.createHorizontalStrut(200));
        buttonsSizeHelper(localMultiplayer);
        buttons2.add(localMultiplayer);
        buttons2.add(Box.createHorizontalGlue());

        final JPanel buttons3 = new JPanel();
        logOut = new JButton("Log Out");
        buttons3.setBackground(new Color(0, 71, 171));

        buttons3.setLayout(new BoxLayout(buttons3, BoxLayout.X_AXIS));
        buttons3.add(Box.createHorizontalGlue());
        buttonsSizeHelper(logOut);
        buttons3.add(logOut);
        buttons3.add(Box.createHorizontalGlue());

        sharedQuizKeyField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setQuizKey(sharedQuizKeyField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        sharedQuizKeyFieldUserInterfaceHelper();

        createdQuizzes.addActionListener(
                evt -> {
                    if (evt.getSource().equals(createdQuizzes)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

//                        TODO implement myCreatedQuizzesController
//                        this.myCreatedQuizzesController.execute();
                    }
                }
        );

        playSharedQuiz.addActionListener(
                evt -> {
                    // the "testing" check can be removed later if wanted, here for testing purposes
                    if (evt.getSource().equals(playSharedQuiz) || evt.getSource().equals("testing")) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        this.accessQuizController.execute(currentState.getQuizKey());
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                        final LoggedInState state = loggedInViewModel.getState();
                        final String user = state.getUsername();
                        logoutController.execute(user);
                    }
                }
        );

        normalPlay.addActionListener(evt -> {
            if (evt.getSource().equals(normalPlay)) {
                quizGenerationController.switchToQuizGenerationView();
            }
        });

        localMultiplayer.addActionListener(evt -> {
            if (evt.getSource().equals(localMultiplayer)) {
                localMultiplayerController.switchToLocalMultiplayerView();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalStrut(20));
        this.add(titlePanel);
        this.add(Box.createVerticalStrut(20));
        this.add(currentPlayerPanel);
        this.add(buttons0);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons1);
        this.add(Box.createVerticalStrut(20));
        this.add(errorMessagePanel);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons2);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons3);
        this.add(Box.createVerticalStrut(20));
        this.add(Box.createVerticalGlue());

        this.setBackground(Constants.BGCOLOUR);
    }

    private void sharedQuizKeyFieldUserInterfaceHelper() {
        sharedQuizKeyField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (sharedQuizKeyField.getText().equals("Enter quiz key...")) {
                    sharedQuizKeyField.setText("");
                    // Set text color when typing
                    sharedQuizKeyField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore placeholder text if the field is empty
                if (sharedQuizKeyField.getText().isEmpty()) {
                    sharedQuizKeyField.setText("Enter quiz key...");
                    // Set placeholder text color
                    sharedQuizKeyField.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void buttonsSizeHelper(JButton jbutton) {
        jbutton.setPreferredSize(new Dimension(250, 200));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName());
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("keyerror")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            sharedQuizKeyErrorField.setText(state.getQuizKeyError());
            System.out.println("success");
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setAccessQuizController(AccessQuizController accessQuizController) {
        this.accessQuizController = accessQuizController;
    }

    public void setQuizGenerationController(QuizGenerationController quizGenerationController) {
        this.quizGenerationController = quizGenerationController;
    }

    public void setLocalMultiplayerController(LocalMultiplayerController localMultiplayerController) {
        this.localMultiplayerController = localMultiplayerController;
    }

    // Main method to run and test the MainMenuView in a JFrame
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(Constants.FRAMEWIDTH, Constants.FRAMEHEIGHT));
        frame.setLocationRelativeTo(null);

        final LoggedInView loggedInView = new LoggedInView(new LoggedInViewModel());
        frame.add(loggedInView);
        frame.setVisible(true);
    }
}
