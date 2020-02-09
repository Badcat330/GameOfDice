package hsealexglushko;

import java.util.ArrayList;
import java.util.Vector;

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

    void writeResult(int points, Player player){
        if(points > currentMaxScore){
            currentWinner = player;
            currentMaxScore = points;
        }

        if(howManyPlayersLast == 1){
            if(numberOfRoundsLeft != 1){
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

    void printRoundWinner(){
        currentWinner.winIncrease();
        System.out.println("We have winner! It is " + currentWinner +
                "! He got " + currentMaxScore + " points!\n Congratulations!");
        resultTable.sort(Player::compareTo);
        System.out.println("Our current leader is " + resultTable.get(0) + "!\n");
        currentMaxScore = 0;
        howManyPlayersLast = numberOfPlayers;
        --numberOfRoundsLeft;
    }

    void printMatchWinner(){
        currentWinner.winIncrease();
        resultTable.sort(Player::compareTo);
        System.out.println("All game win " + resultTable.get(0) + "! \n Congratulations! \n");
        printResultTable();
    }

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
