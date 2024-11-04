package entity;

import java.util.List;

/**
 * Representation of a player created quiz.
 */
public class PlayerCreatedQuiz {
    private final List<PlayerCreatedQuestion> listOfQuestions;
    private final String category;
    private final String uniqueKey = "";

    public PlayerCreatedQuiz(String category) {
        this.listOfQuestions = listOfQuestionsMaker();
        this.category = category;
        // TODO assign uniqueKey uniquely/randomly (?)
    }

    /**
     * String representation of the list of questions of this trivia quiz.
     * @return String representation of the list of questions of this trivia quiz.
     */
    public String getListOfQuestions() {
        final StringBuilder result = new StringBuilder();
        for (PlayerCreatedQuestion question : this.listOfQuestions) {
            result.append(question).append("\n");
        }
        return result.toString();
    }

    public String getCategory() {
        return this.category;
    }

    public String getUniqueKey() {
        return this.uniqueKey;
    }

    private List<PlayerCreatedQuestion> listOfQuestionsMaker() {
        // TODO this needs implementation based on API
        return null;
    }
}
