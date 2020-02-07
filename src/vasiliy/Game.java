package vasiliy;

import java.util.Vector;

public class Game{

    public static void main(String[] args){
        Vector<Player> players = new Vector<>();
        Commentator commentator = new Commentator(2, 4);
        players.add(new Player("First thred", commentator, 2));
        players.add(new Player("Second thred", commentator, 2));
        commentator.addPlayers(players);
        for(Player player : players){
            System.out.println("Player " + player.getName() + " start the game!");
            player.start();
        }
        try{
            for(Player player : players){
                player.join();
            }
        } catch(InterruptedException e){
            System.out.println("Has been interrupted");
        }
    }
}
