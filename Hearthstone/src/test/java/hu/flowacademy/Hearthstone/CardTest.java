package hu.flowacademy.Hearthstone;

import hu.flowacademy.Hearthstone.Model.Board;
import hu.flowacademy.Hearthstone.Model.Cards.Card;
import hu.flowacademy.Hearthstone.Model.Cards.Specific.ShroomBrewer;
import hu.flowacademy.Hearthstone.Model.Cards.Specific.SimpleMinion;
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
        board.getPlayer1Boardside()[0] = (sb);
        board.getPlayer1Hand().remove(sb);
        assertFalse(board.getPlayer1Hand().contains(sb));
        assertSame(board.getPlayer1Boardside()[0], (sb));;

        System.out.println("sb id: " + board.getPlayer1Boardside()[0].getId());

    }

    @Test
    void counterShouldCountProperly() {
        SimpleMinion minion1 = new SimpleMinion(2,3,4,false,false,"Minion1");
        ShroomBrewer sb = new ShroomBrewer();
        assertEquals(2, Card.counter);
        SimpleMinion minion2 = new SimpleMinion(2,5,4, true,true, "Minion2");
        assertEquals(3, Card.counter);
        assertNotEquals(1, Card.counter);
/*        System.out.println("sb id:" + sb.getId());
        System.out.println("minion1 id:" + minion1.getId());*/
    }
}
