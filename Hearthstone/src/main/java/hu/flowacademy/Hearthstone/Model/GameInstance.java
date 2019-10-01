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

    @Column
    private Long socketUrl;

    @Transient
    private Board board;

    @Column
    private boolean finished;

    @Column
    private boolean p1Turn;

    public GameInstance() {
    }

    public GameInstance(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.board = new Board();
        this.finished = false;
        this.p1Turn = true;
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

    public Long getSocketUrl() {
        return socketUrl;
    }

    public void setSocketUrl(Long socketUrl) {
        this.socketUrl = socketUrl;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isP1Turn() {
        return p1Turn;
    }

    public void setP1Turn(boolean p1Turn) {
        this.p1Turn = p1Turn;
    }
}
