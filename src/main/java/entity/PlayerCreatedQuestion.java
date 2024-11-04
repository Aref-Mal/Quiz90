package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the question interface.
 * Entity that represents a question object which was pulled from the Trivia API.
 */
public class PlayerCreatedQuestion implements Question {
    private final String questionText;
    private final List<String> answerOptions = new ArrayList<>();
    private final String correctAnswer;
    private final String category;
    private final String difficulty;
    private final String uniqueKey;

    public PlayerCreatedQuestion(String questionText, List<String> answerOptions, String correctAnswer,
                                 String category, String difficulty, String uniqueKey) {
        this.questionText = questionText;
        this.answerOptions.addAll(answerOptions);
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;
        this.uniqueKey = uniqueKey;
    }

    @Override
    public String getQuestionText() {
        return this.questionText;
    }

    @Override
    public String getAnswerOptions() {
        final StringBuilder result = new StringBuilder();
        for (String option : this.answerOptions) {
            result.append(option).append("\n");
        }
        return result.toString();
    }

    @Override
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public String getDifficulty() {
        return this.difficulty;
    }

    public String getUniqueKey() {
        return this.uniqueKey;
    }
}
