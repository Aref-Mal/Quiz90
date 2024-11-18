package interface_adapter.logged_in;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private String username = "";

    private String password = "";

    private String quizKey = "";
    private String quizKeyError;

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        password = copy.password;
        this.quizKey = copy.quizKey;
        quizKeyError = copy.quizKeyError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getQuizKey() {
        return quizKey;
    }

    public void setQuizKey(String quizKey) {
        this.quizKey = quizKey;
    }

    public void setQuizKeyError(String quizKeyError) {
        this.quizKeyError = quizKeyError;
    }

    public String getQuizKeyError() {
        return quizKeyError;
    }
}
