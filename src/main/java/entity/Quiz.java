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
     * String representation of the category of the trivia quiz.
     * @return String representation of the category of the trivia quiz.
     */
    String getCategory();
}
