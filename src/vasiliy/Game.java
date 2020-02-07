package vasiliy;

import java.util.Scanner;
import java.util.Vector;

public class Game{

    public static int readNumber(int leftBound, int rightBound){
        Scanner in = new Scanner(System.in);
        boolean flag = false;
        int number;
        String mayBeNumber;
        do{
            if(flag){
                System.out.println("Incorrect bounds!");
            }
            else{
                flag = true;
            }
            mayBeNumber = in.next();
            try {
                number = Integer.parseInt(mayBeNumber);
            }
            catch (NumberFormatException e){
                System.out.println("Incorrect format of input.");
                flag = false;
                number = 0;
            }
        }while(number < leftBound || number > rightBound);

        return number;
    }

    public static void main(String[] args){
        System.out.println("Input number of players: ");
        int numberOfPlayers = readNumber(2, 6);
        System.out.println("Input number of rounds: ");
        int numberOfRounds = readNumber(2, 5);
        System.out.println("Input number of dice: ");
        int numberOfDice = readNumber(1, 100);

        Vector<Player> players = new Vector<>();
        Commentator commentator = new Commentator(numberOfPlayers, numberOfRounds);
        for(int i = 0; i < numberOfPlayers; i++){
            players.add(new Player("Player " + i, commentator, numberOfDice));
        }
        commentator.addPlayers(players);
        for(Player player : players){
            System.out.println(player.getName() + " start the game!");
            player.start();
        }
        System.out.println();
        try{
            for(Player player : players){
                player.join();
            }
        } catch(InterruptedException e){
            System.out.println("Has been interrupted");
        }
    }
}
