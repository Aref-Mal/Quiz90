package view;

import app.Constants;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

// TODO import statements in the right order
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jetbrains.annotations.NotNull;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements PropertyChangeListener {

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private SignupController signupController;

    public SignupView(SignupViewModel signupViewModel) {
        signupViewModel.addPropertyChangeListener(this);

        final TitlePanel titlePanel = new TitlePanel(SignupViewModel.TITLE_LABEL);
        final JPanel infoPanel = infoPanelHelper();

        final ButtonPanel buttonPanel = new ButtonPanel();
        final CustomButton signUp = new CustomButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttonPanel.add(signUp);

        final ButtonPanel buttonPanel1 = new ButtonPanel();
        buttonPanel1.setLayout(new BoxLayout(buttonPanel1, BoxLayout.LINE_AXIS));
        final CustomButton toLogin = new CustomButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
        final CustomButton exit = new CustomButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        buttonPanel1.add(toLogin);
        buttonPanel1.add(horizontalSpacer());
        buttonPanel1.add(exit);

        actionAndDocumentListeners(signupViewModel, exit, signUp, toLogin);

        assembleFinalPanel(titlePanel, infoPanel, buttonPanel, buttonPanel1);
    }

    @NotNull
    private JPanel infoPanelHelper() {
        final JPanel infoPanel = new JPanel();
        final LabelTextPanel usernameInfo = usernameInfoHelper();
        final LabelTextPanel passwordInfo = passwordInfoHelper(SignupViewModel.PASSWORD_LABEL, passwordInputField);
        final LabelTextPanel repeatPasswordInfo = passwordInfoHelper(
                SignupViewModel.REPEAT_PASSWORD_LABEL, repeatPasswordInputField);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Constants.BGCOLOUR);
        infoPanel.add(usernameInfo);
        infoPanel.add(verticalSpacer());
        infoPanel.add(passwordInfo);
        infoPanel.add(verticalSpacer());
        infoPanel.add(repeatPasswordInfo);
        return infoPanel;
    }

    @NotNull
    private LabelTextPanel usernameInfoHelper() {
        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        usernameInfo.setMaximumSize(new Dimension(Constants.TEXTPANELWIDTH, Constants.TEXTPANELHEIGHT));
        usernameInfo.setBackground(Constants.BGCOLOUR);
        return usernameInfo;
    }

    @NotNull
    private LabelTextPanel passwordInfoHelper(String passwordLabel, JPasswordField tempPasswordInputField) {
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(passwordLabel), tempPasswordInputField);
        passwordInfo.setBackground(Constants.BGCOLOUR);
        passwordInfo.setMaximumSize(new Dimension(Constants.TEXTPANELWIDTH, Constants.TEXTPANELHEIGHT));
        return passwordInfo;
    }

    private void actionAndDocumentListeners(SignupViewModel tempSignupViewModel,
                                            CustomButton exit, CustomButton signUp, CustomButton toLogin) {
        usernameFieldListener(tempSignupViewModel);
        passwordFieldListener(tempSignupViewModel);
        repeatPasswordFieldListener(tempSignupViewModel);
        signupActionListener(tempSignupViewModel, signUp);
        loginActionListener(toLogin);
        exitActionListener(exit);
    }

    private void usernameFieldListener(SignupViewModel tempSignupViewModel) {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = tempSignupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                tempSignupViewModel.setState(currentState);
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
    }

    private void passwordFieldListener(SignupViewModel tempSignupViewModel) {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = tempSignupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                tempSignupViewModel.setState(currentState);
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
    }
    
    private void repeatPasswordFieldListener(SignupViewModel tempSignupViewModel) {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = tempSignupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                tempSignupViewModel.setState(currentState);
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
    }

    private void signupActionListener(SignupViewModel tempSignupViewModel, CustomButton signUp) {
        signUp.addActionListener(evt -> {
            if (evt.getSource().equals(signUp)) {
                final SignupState currentState = tempSignupViewModel.getState();
                signupController.execute(
                        currentState.getUsername(),
                        currentState.getPassword(),
                        currentState.getRepeatPassword()
                );
            }
        });
    }

    private void loginActionListener(CustomButton toLogin) {
        toLogin.addActionListener(
                evt -> signupController.switchToLoginView()
        );
    }

    private static void exitActionListener(CustomButton exit) {
        exit.addActionListener(evt -> System.exit(0));
    }

    private void assembleFinalPanel(TitlePanel titlePanel, JPanel infoPanel, ButtonPanel buttonPanel,
                                    ButtonPanel buttonPanel1) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(verticalSpacer());
        this.add(titlePanel);
        this.add(Box.createVerticalGlue());
        this.add(infoPanel);
        this.add(Box.createVerticalGlue());
        this.add(verticalSpacer());
        this.add(buttonPanel);
        this.add(verticalSpacer());
        this.add(buttonPanel1);
        this.add(verticalSpacer());
        this.add(verticalSpacer());
        this.add(Box.createVerticalGlue());
        this.setBackground(Constants.BGCOLOUR);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    private Component horizontalSpacer() {
        return Box.createHorizontalStrut(Constants.STRUTSMALLSPACER);
    }

    private Component verticalSpacer() {
        return Box.createVerticalStrut(Constants.STRUTSMALLSPACER);
    }

    public String getViewName() {
        return "sign up";
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}
