package hu.flowacademy.Hearthstone.Model;


public class GameModel {
    private String username;
    private Board board;
    private boolean p1Turn;

    public GameModel(String username, Board board) {
        this.username = username;
        this.board = board;
        this.p1Turn = true;
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

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isP1Turn() {
        return p1Turn;
    }

    public void setP1Turn(boolean p1Turn) {
        this.p1Turn = p1Turn;
    }
}
