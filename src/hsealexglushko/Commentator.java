package hsealexglushko;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Class for implementing commentator, it print results
 * and contain all useful information
 * @version 1.0 09 Feb 2020
 * @author Alex Glushko
 */
public class Commentator{

    private ArrayList<Player> resultTable = new ArrayList<>();
    private int numberOfPlayers;
    private int numberOfRoundsLeft = 2;
    private int currentMaxScore;
    private Player currentWinner;
    private int howManyPlayersLast;

    public Commentator(int numberOfPlayers, int numberOfRoundsLeft){
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfRoundsLeft = numberOfRoundsLeft;
        currentMaxScore = 0;
        howManyPlayersLast = numberOfPlayers;
    }

    ArrayList<Player> getResultTable(){
        return resultTable;
    }

    public int getNumberOfRoundsLeft(){
        return numberOfRoundsLeft;
    }

    public int getHowManyPlayersLast(){
        return howManyPlayersLast;
    }

    public int getCurrentMaxScore(){
        return currentMaxScore;
    }

    void decreaseNumberOfPlayers(){
        --howManyPlayersLast;
    }

    void addPlayers(Vector<Player> players)
    {
        resultTable.addAll(players);
    }

    /**
     * Print results and collect information about current winner
     * @param points number of points player get
     * @param player who get this points
     */
    void writeResult(int points, Player player){
        if(points > currentMaxScore){
            currentWinner = player;
            currentMaxScore = points;
        }

        if(howManyPlayersLast == 1){
            currentWinner.winIncrease();
            resultTable.sort(Player::compareTo);
            if(!isSomeOneWin()){
                printRoundWinner();
            } else {
                printMatchWinner();
            }
        } else {
            System.out.println("Player " + player + " got " + points + " points!");
            System.out.println("Current winner is " + currentWinner + " with " + currentMaxScore + " points!\n");
            --howManyPlayersLast;
        }
    }

    /**
     * Check if someone have enough wins for end the game
     * @return if game is ready to finish
     */
    boolean isSomeOneWin(){
        return numberOfRoundsLeft == resultTable.get(0).getCountOfWins();
    }

    /**
     * Print result of round
     */
    void printRoundWinner(){
        System.out.println("We have winner! It is " + currentWinner +
                "! He got " + currentMaxScore + " points!\n Congratulations!");
        System.out.println("Our current leader is " + resultTable.get(0) + "!\n");
        currentMaxScore = 0;
        howManyPlayersLast = numberOfPlayers;
    }

    /**
     * print result of match
     */
    void printMatchWinner(){
        System.out.println("All game win " + resultTable.get(0) + "! \n Congratulations! \n");
        printResultTable();
    }

    /**
     * print table of players
     */
    public void printResultTable(){
        System.out.println("Result table:");
        int i = 1;
        resultTable.sort(Player::compareTo);
        for(Player player : resultTable){
            System.out.println(i + " " + player.getName() + " - " + player.getCountOfWins());
            ++i;
        }
    }
} //class Commentator
