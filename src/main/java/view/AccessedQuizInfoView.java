package view;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Quiz;
import interface_adapter.access_quiz.AccessQuizController;
import interface_adapter.access_quiz.AccessedQuizInfoState;
import interface_adapter.access_quiz.AccessedQuizInfoViewModel;

/**
 * The view for when a quiz has been accessed and its information must be shown.
 */
public class AccessedQuizInfoView extends JPanel implements PropertyChangeListener {

    private Quiz quizObject;

    private final JLabel quizName;
    private final JLabel quizAuthor;
    private final JLabel quizNumQuestions;
    private final JButton back;
    private final JButton play;
    private AccessQuizController accessQuizController;

    public AccessedQuizInfoView(AccessedQuizInfoViewModel accessedQuizInfoViewModel) {
        accessedQuizInfoViewModel.addPropertyChangeListener(this);

        final TitlePanel titlePanel = new TitlePanel(AccessedQuizInfoViewModel.TITLE_LABEL);

        final JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setPreferredSize(new Dimension(1200, 100));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        final Box nameBox = Box.createHorizontalBox();
        nameBox.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 35));
        final Box authorBox = Box.createHorizontalBox();
        authorBox.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 35));
        final Box numBox = Box.createHorizontalBox();
        numBox.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 35));
        final JLabel quizNameLabel = new JLabel("Quiz Name: ");
        final JLabel quizAuthorLabel = new JLabel("Author: ");
        final JLabel quizNumQuestionsLabel = new JLabel("Number of Questions: ");
        quizName = new JLabel();
        quizAuthor = new JLabel();
        quizNumQuestions = new JLabel();
        infoPanel.add(nameBox);
        infoPanel.add(authorBox);
        infoPanel.add(numBox);
        nameBox.add(quizNameLabel);
        nameBox.add(quizName);
        authorBox.add(quizAuthorLabel);
        authorBox.add(quizAuthor);
        numBox.add(quizNumQuestionsLabel);
        numBox.add(quizNumQuestions);

        final JPanel buttons0 = new JPanel();
        back = new JButton("Back to Main Menu");
        play = new JButton("Play Quiz");
        buttons0.setBackground(new Color(116, 130, 82));
        buttons0.setLayout(new BoxLayout(buttons0, BoxLayout.X_AXIS));
        buttons0.add(Box.createHorizontalGlue());
        buttons0.add(play);
        buttons0.add(back);
        buttons0.add(Box.createHorizontalGlue());

        play.addActionListener(
                // below is temp code, quizObject should be passed somewhere to be played
                evt -> {
                    if (evt.getSource().equals(play)) {
                        if (quizObject.getListOfQuestions().equals("")) {
                            System.out.println("play button pressed. empty List of questions (null)");
                        }
                        else {
                            System.out.println("play button pressed. " + quizObject.getListOfQuestions());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalStrut(20));
        this.add(titlePanel);
        this.add(Box.createVerticalStrut(20));
        this.add(infoPanel);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons0);
        this.add(Box.createVerticalStrut(20));
        this.add(Box.createVerticalGlue());
        this.setBackground(new Color(255, 200, 70));

        backActionListener();
    }

    public String getViewName() {
        return "accessed quiz info";
    }

    private void backActionListener() {
        back.addActionListener(evt -> accessQuizController.switchToLoggedInView());
    }

    public void setAccessQuizController(AccessQuizController accessQuizController) {
        this.accessQuizController = accessQuizController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AccessedQuizInfoState state = (AccessedQuizInfoState) evt.getNewValue();
        quizName.setText(state.getQuizName());
        quizAuthor.setText(state.getAuthor());
        quizNumQuestions.setText(String.valueOf(state.getNumQuestions()));
        quizObject = state.getQuizObject();
    }
}
