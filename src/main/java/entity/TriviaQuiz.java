package entity;

import java.util.List;

/**
 * Representation of a trivia quiz of questions pulled from the API.
 */
public class TriviaQuiz implements Quiz {
    private final List<TriviaQuestion> listOfQuestions;
    private final String category;

    public TriviaQuiz(String category) {
        this.listOfQuestions = listOfQuestionsMaker();
        this.category = category;
    }

    /**
     * String representation of the list of questions of this trivia quiz.
     * @return String representation of the list of questions of this trivia quiz.
     */
    public String getListOfQuestions() {
        final StringBuilder result = new StringBuilder();
        for (TriviaQuestion question : this.listOfQuestions) {
            result.append(question).append("\n");
        }
        return result.toString();
    }

    public String getCategory() {
        return category;
    }

    private List<TriviaQuestion> listOfQuestionsMaker() {
        // TODO this needs implementation based on API
        return null;
    }
}
