package entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Representation of the current game session of the program.
 */
public class GameSession {
    private final Player lastPlayed;
    private final List<Player> players;
    private final Quiz currentQuiz;
    private final Question currentQuestion;
    private final Queue turnBasedQueue;

    public GameSession(List<Player> players, Quiz currentQuiz, Question currentQuestion) {
        this.players = players;
        this.lastPlayed = this.players.get(0);
        this.currentQuiz = currentQuiz;
        this.currentQuestion = currentQuestion;
        this.turnBasedQueue = new LinkedList<>();
    }

    public Player getLastPlayed() {
        return this.lastPlayed;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Quiz getCurrentQuiz() {
        return this.currentQuiz;
    }

    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    public Queue getTurnBasedQueue() {
        return this.turnBasedQueue;
    }
}
