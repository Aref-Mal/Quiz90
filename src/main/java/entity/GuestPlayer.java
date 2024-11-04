package entity;

/**
 * An implementation of the Player interface.
 * Entity that represents a Guest player.
 */
public class GuestPlayer implements Player {

    private final String username;
    // TODO Change this line depending on implementation of Guest user?
    //      Idk how it works with login API -> Albert look at probably
    // private final String password = null;
    private final int currentScore;

    public GuestPlayer() {
        this.username = "Guest";
        this.currentScore = 0;
    }

    @Override
    public String getUsername() {
        return "This is a Guest account";
    }

    @Override
    public String getPassword() {
        return "This is a guest player, it does not have a password.";
    }

    @Override
    public int getCurrentScore() {
        return "The current score of " + this.username + "is " + this.currentScore + ".";
    }

    @Override
    public int getHighScore() {
        System.out.print("Guest players do not have a high score.");
        return 0;

    }

}

