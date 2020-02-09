package hsealexglushko;

import java.util.Vector;

/**
 * Class for starting threads and parsing arguments
 * @version 1.0 09 Feb 2020
 * @author Alex Glushko
 */
public class Game{

    //Bounds for args
    final static private int LEFT_BOUND_PLAYERS = 2;
    final static private int RIGHT_BOUND_PLAYERS = 6;
    final static private int LEFT_BOUND_DICE = 2;
    final static private int RIGHT_BOUND_DICE = 5;
    final static private int LEFT_BOUND_ROUNDS = 1;
    final static private int RIGHT_BOUND_ROUNDS = 100;

    /**
     * Check if string argument correct and we can convert it into Integer
     * else return null
     * @param leftBound of number
     * @param rightBound of number
     * @param mayBeNumber string that can be a number
     * @return number if everything okay else null
     */
    public static Integer checkArgs(int leftBound, int rightBound, String mayBeNumber){
        Integer number = null;

        try {
            number = Integer.parseInt(mayBeNumber);
        } catch (NumberFormatException e){
            return number;
        } catch(Exception e){
            System.out.println("Something go wrong " + e);
            return number;
        }

        if(number < leftBound || number > rightBound){
            return null;
        } else {
            return number;
        }
    }

    public static void main(String[] args){

        if(args.length < 3){
            System.out.println("Incorrect format of input.");
            return;
        }

        Integer numberOfPlayers = checkArgs(LEFT_BOUND_PLAYERS, RIGHT_BOUND_PLAYERS, args[0]);
        Integer numberOfDice = checkArgs(LEFT_BOUND_DICE, RIGHT_BOUND_DICE, args[1]);
        Integer numberOfRounds =checkArgs(LEFT_BOUND_ROUNDS, RIGHT_BOUND_ROUNDS, args[2]);

        if(numberOfDice == null || numberOfPlayers == null|| numberOfRounds == null){
            System.out.println("Incorrect format of input.");
            return;
        }

        //Create commentator and vector of players
        Vector<Player> players = new Vector<>();
        Commentator commentator = new Commentator(numberOfPlayers, numberOfRounds);
        for(int i = 0; i < numberOfPlayers; i++){
            players.add(new Player("Player " + i, commentator, numberOfDice));
        }
        //Add players to commentator
        commentator.addPlayers(players);

        //Start threads
        for(Player player : players){
            System.out.println(player.getName() + " start the game!");
            player.start();
        }
        System.out.println();

        //Join threads
        try{
            for(Player player : players){
                player.join();
            }
        } catch(InterruptedException e){
            System.out.println("Has been interrupted");
        } catch(Exception e){
            System.out.println("Something go wrong " + e);
        }
    }
}//Game
