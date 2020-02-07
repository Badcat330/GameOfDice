package vasiliy;

import java.util.TreeSet;
import java.util.Vector;

public class Commentator{
    private TreeSet<Player> resultTable = new TreeSet<>();
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

    void writeResult(int points, Player player){
        if(points > currentMaxScore){
            currentWinner = player;
            currentMaxScore = points;
        }
        // ToDo: Check for code style
        if(howManyPlayersLast == 1){
            if(numberOfRoundsLeft != 0){
                player.winIncrease();
                System.out.println("We have winner! It is " + currentWinner +
                        "! He got " + currentMaxScore + " points!\n Congratulations!");
                System.out.println("Our current leader is " + resultTable.first() + "!");
                currentMaxScore = 0;
                howManyPlayersLast = numberOfPlayers;
                --numberOfRoundsLeft;
            }
            else {
                player.winIncrease();
                System.out.println("We have a winner! " + resultTable.first() + "! \n Congratulations!");
                printResultTable();
            }

        }
        else {
            System.out.println("Player " + player + " got " + points + " points!");
            System.out.println("Current winner is " + currentWinner + " with " + currentMaxScore + " points!");
            --howManyPlayersLast;
        }
    }

    public void printResultTable(){
        System.out.println("Result table:");
        //TODO: Finish Result Table
    }
}
