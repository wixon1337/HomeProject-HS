package hu.flowacademy.Hearthstone.Model;

import javax.persistence.*;

@Entity
@Table
public class GameInstance {

    @Column
    private String userId;

    @Id
    @Column
    private String username;

    @Transient
    private Board board;

    public GameInstance() {
    }

    public GameInstance(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.board = new Board();
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

    public Board getBoard() {
        return board;
    }
}
