package vasiliy;

import java.util.TreeMap;
import java.util.TreeSet;

public class Commentator extends Thread{
    private TreeSet<Player> resultTable = new TreeSet<>();
    private int numberOfDice;
    private int numberOfPlayers;
    private int numberOfRoundsLeft = 2;
    private int currentMaxScore;
    private Player currentWinner;
    private int howManyPlayersLast;

    public int getNumberOfDice(){
        return numberOfDice;
    }

    void writeResult(int points, Player player){
        if(points > currentMaxScore){
            currentWinner = player;
            currentMaxScore = points;
        }
        // ToDo: Check for code style
        if(howManyPlayersLast == 0){
            if(numberOfRoundsLeft != 0){
                System.out.println("We have winner! It is " + currentWinner +
                        "! He got " + currentMaxScore + " points!\n Congratulations!");
                System.out.println("Our current leader is " + resultTable.first() + "!");
                player.winIncrease();
                currentMaxScore = 0;
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

    }


    @Override
    public void run(){
        resultTable.add(new Player("First thred", this));
        resultTable.add(new Player("Second thred", this));
        for(Player player : resultTable){
            player.start();
        }
    }
}
