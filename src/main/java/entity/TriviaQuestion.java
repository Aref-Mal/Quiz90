package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the question interface.
 * Entity that represents a question object which was pulled from the Trivia API.
 */
public class TriviaQuestion implements Question {
    private String questionText;
    // TODO easy to use arrayList but should probably used a fixed list size of 4
    private List<String> answerOptions = new ArrayList<>();
    private String correctAnswer;
    private String category;
    private String difficulty;

    public TriviaQuestion() {
        // TODO needs an implementation based on the API i have no clue how this will work
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
}
