package hu.flowacademy.Hearthstone.Model;


public class GameModel {
    private String userId;
    private String username;
    private Long socketUrl;
    private boolean won;

    public GameModel() {
    }

    public GameModel(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.won = false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSocketUrl() {
        return socketUrl;
    }

    public void setSocketUrl(Long socketUrl) {
        this.socketUrl = socketUrl;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
