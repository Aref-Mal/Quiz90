package entity;

/**
 * Representation of a quiz instance.
 */
public interface Quiz {

    /**
     * String representation of the list of trivia questions of the trivia quiz.
     * @return String representation of the list of trivia questions of the trivia quiz.
     */
    String getListOfQuestions();

    /**
     * Returns title of quiz.
     * @return string representation of quiz title
     */
    String getTitle();
}
