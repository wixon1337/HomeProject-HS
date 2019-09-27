package hu.flowacademy.Hearthstone;

import hu.flowacademy.Hearthstone.Model.Board;
import hu.flowacademy.Hearthstone.Model.Cards.Specific.ShroomBrewer;
import hu.flowacademy.Hearthstone.Model.Heroes.Priest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class CardTest {

    private static Priest p;
    private static Board board;

    @BeforeAll
    static void init() {
        p = new Priest();
        board = new Board();
    }

    @Test
    void aHeroShouldHave30Health() {
        assertEquals(p.getHealth(), 30);
        assertNotEquals(p.getHealth(), 1);
    }

    @Test
    void shroomBewerShouldHaveCorrectStats() {
        ShroomBrewer sb = new ShroomBrewer();
        assertEquals(sb.getHealth(), 4);
        assertEquals(sb.getAttack(), 4);
        assertEquals(sb.getCost(), 4);
        assertNotEquals(sb.getHealth(), 5);
        assertNotEquals(sb.getAttack(), 5);
        assertNotEquals(sb.getCost(), 5);
    }

    @Test
    void boardShouldWorkCorrectly() {
        ShroomBrewer sb = new ShroomBrewer();
        board.getPlayer1Hand().add(sb);
        assertTrue(board.getPlayer1Hand().contains(sb));
        board.getPlayer1Boardside().add(sb);
        board.getPlayer1Hand().remove(sb);
        assertFalse(board.getPlayer1Hand().contains(sb));
        assertTrue(board.getPlayer1Boardside().contains(sb));;

    }
}
