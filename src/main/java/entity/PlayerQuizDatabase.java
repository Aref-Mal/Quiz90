
package entity;

import java.util.*;

/**
 * An implementation of the Database interface.
 * Entity that represents a quiz database attached to a Player user
 */
public class PlayerQuizDatabase implements Database {
    private User user;
    private Map<String, PlayerCreatedQuiz> quizMap;
    private Map<String, String> titleToKeyMap = new HashMap<>();

    public PlayerQuizDatabase(User user, Map<String, PlayerCreatedQuiz> quizMap) {
        this.user = user;
        this.quizMap = quizMap;
        this.titleToKeyMap = this.titleToKeyMapBuilder();
    }

    private Map<String, String> titleToKeyMapBuilder() {
        final Map<String, String> titleToKeyMap = new HashMap<>();
        final Iterator<String> keys = quizMap.keySet().iterator();
        for (PlayerCreatedQuiz quiz : quizMap.values()) {
            titleToKeyMap.put(quiz.getTitle(), keys.next());
        }
        return titleToKeyMap;
    }

    @Override
    public User getUser() {
        return user;
    }

    /**
     * Returns a quiz that matches the given key.
     * @param key of quiz
     * @return quiz object
     */
    @Override
    public Quiz getByKey(String key) {
        final Quiz quiz = quizMap.get(key);
        return quiz;
    }

    /**
     * Returns a map of quizzes that contains a substring title in their titles.
     * @param title substring to search for
     * @return map of quizzes keyed to their key
     */
    @Override
    public Map<String, PlayerCreatedQuiz> getByTitle(String title) {
        final Map<String, PlayerCreatedQuiz> quizzes = new HashMap<>();
        final String str = title.toLowerCase().replaceAll("\\s+", "");
        for (PlayerCreatedQuiz quiz : quizMap.values()) {
            final String quizTitle = quiz.getTitle().toLowerCase().replaceAll("\\s+", "");
            if (quizTitle.contains(str)) {
                final String key = titleToKeyMap.get(quizTitle);
                quizzes.put(key, quiz);
            }
        }
        return quizzes;
    }

    /**
     * Returns all quiz objects for a given user keyed to quiz key.
     * @return map of quiz objects
     */
    @Override
    public Map<String, PlayerCreatedQuiz> getAll() {
        return quizMap;
    }

    /**
     * Returns number of quizzes for a user.
     * @return number of quizzes
     */
    @Override
    public int getNumberOfItems() {
        return quizMap.size();
    }
}

