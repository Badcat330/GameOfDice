package hsealexglushko;

import java.util.Vector;

public class Game{

    final static private int LEFT_BOUND_PLAYERS = 2;
    final static private int RIGHT_BOUND_PLAYERS = 6;
    final static private int LEFT_BOUND_DICE = 2;
    final static private int RIGHT_BOUND_DICE = 5;
    final static private int LEFT_BOUND_ROUNDS = 1;
    final static private int RIGHT_BOUND_ROUNDS = 100;

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
        } catch(Exception e){
            System.out.println("Something go wrong " + e);
        }
    }
}
