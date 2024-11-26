package use_case.access_database;

import entity.PlayerQuizDatabase;

public class AccessDatabaseOutputData {
    private final boolean useCaseFailed;
    private final PlayerQuizDatabase quizDatabase;

    public AccessDatabaseOutputData(boolean useCaseFailed, PlayerQuizDatabase quizDatabase) {
        this.useCaseFailed = useCaseFailed;
        this.quizDatabase = quizDatabase;
    }

    public PlayerQuizDatabase getQuizDatabase() {
        return quizDatabase;
    }
}
