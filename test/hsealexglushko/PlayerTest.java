package hsealexglushko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest{
    final static private int DEFAULT_NUMBER_OF_DICE = 3;
    final static private int DEFAULT_NUMBER_OF_PLAYERS = 4;
    final static private int DEFAULT_NUMBER_OF_ROUNDS = 2;

    @Test
    void getSetTests(){
        Player player = new Player("NewPlayer",
                                    new Commentator(DEFAULT_NUMBER_OF_PLAYERS,DEFAULT_NUMBER_OF_ROUNDS),
                                    DEFAULT_NUMBER_OF_DICE);
        assertEquals(player.getCountOfWins(), 0);
        player.winIncrease();
        assertEquals(player.getCountOfWins(), 1);
    }

    @Test
    void getPointTest(){
        Player player = new Player("NewPlayer",
                new Commentator(DEFAULT_NUMBER_OF_PLAYERS,DEFAULT_NUMBER_OF_ROUNDS),
                DEFAULT_NUMBER_OF_DICE);
        int point  = player.getPoints();
        assertTrue(point > 0 && point <= DEFAULT_NUMBER_OF_DICE * 6);
    }
}