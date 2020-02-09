package hsealexglushko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest{

    final static private int LEFT_BOUND = 2;
    final static private int RIGHT_BOUND = 5;

    @Test
    void checkArgsTest(){
        Integer number = Game.checkArgs(LEFT_BOUND, RIGHT_BOUND, "3");
        assertEquals(number, 3);
        number = Game.checkArgs(LEFT_BOUND, RIGHT_BOUND, "234");
        assertNull(number);
        number = Game.checkArgs(LEFT_BOUND, RIGHT_BOUND, "sdfasdf");
        assertNull(number);
    }
}