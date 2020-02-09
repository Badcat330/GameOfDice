package hsealexglushko;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CommentatorTest{

    final static private int DEFAULT_NUMBER_OF_DICE = 3;
    final static private int DEFAULT_NUMBER_OF_PLAYERS = 4;
    final static private int DEFAULT_NUMBER_OF_ROUNDS = 2;
    private static Vector<Player> players = new Vector<>();
    private static Commentator commentator = new Commentator(DEFAULT_NUMBER_OF_PLAYERS, DEFAULT_NUMBER_OF_ROUNDS);

    @BeforeAll
    static void beforeAll(){
        commentator = new Commentator(DEFAULT_NUMBER_OF_PLAYERS, DEFAULT_NUMBER_OF_ROUNDS);
        players.clear();
        players.add(new Player("Elena", commentator, DEFAULT_NUMBER_OF_DICE));
        players.add(new Player("Alex", commentator, DEFAULT_NUMBER_OF_DICE));
        players.add(new Player("Petr", commentator, DEFAULT_NUMBER_OF_DICE));
        players.add(new Player("Jone", commentator, DEFAULT_NUMBER_OF_DICE));
        commentator.addPlayers(players);
    }

    @Test
    void getTests(){
        beforeAll();
        assertEquals(commentator.getHowManyPlayersLast(), DEFAULT_NUMBER_OF_PLAYERS);
        assertEquals(commentator.getCurrentMaxScore(), 0);
        assertEquals(commentator.getNumberOfRoundsLeft(), DEFAULT_NUMBER_OF_ROUNDS);
    }

    @Test
    void addPlayersTest(){
        beforeAll();
        ArrayList<Player> resultTable = commentator.getResultTable();
        for(Player player : resultTable){
            assertTrue(players.contains(player));
        }
    }

    @Test
    void writeResultTest(){
        beforeAll();
        commentator.writeResult(12, players.get(0));
        assertEquals(commentator.getCurrentMaxScore(), 12);
        assertEquals(commentator.getHowManyPlayersLast(), DEFAULT_NUMBER_OF_PLAYERS - 1);
        commentator.writeResult(10, players.get(1));
        assertEquals(commentator.getCurrentMaxScore(), 12);
        assertEquals(commentator.getHowManyPlayersLast(), DEFAULT_NUMBER_OF_PLAYERS - 2);
        commentator.writeResult(15, players.get(2));
        assertEquals(commentator.getCurrentMaxScore(), 15);
        assertEquals(commentator.getHowManyPlayersLast(), DEFAULT_NUMBER_OF_PLAYERS - 3);
        commentator.writeResult(3, players.get(3));
        assertEquals(commentator.getCurrentMaxScore(), 0);
        assertEquals(commentator.getHowManyPlayersLast(), DEFAULT_NUMBER_OF_PLAYERS);
        assertEquals(commentator.getNumberOfRoundsLeft(), DEFAULT_NUMBER_OF_ROUNDS - 1);
    }

    @Test
    void decreaseTest(){
        beforeAll();
        commentator.decreaseNumberOfPlayers();
        assertEquals(commentator.getHowManyPlayersLast(), DEFAULT_NUMBER_OF_PLAYERS - 1);
    }
}