package vasiliy;

import java.util.Random;

public class Player extends Thread implements Comparable<Player>{

    private int countOfWins;
    private int numberOfDice;
    final private Commentator commentator;
    final private Random random = new Random();
    final private static int AMOUNT_OF_SIDES = 6;

    public Player(String name, Commentator commentator, int numberOfDice){
        super(name);
        this.commentator = commentator;
        countOfWins = 0;
        this.numberOfDice = numberOfDice;
    }

    public void winIncrease(){
        ++countOfWins;
    }

    @Override
    public void run(){
        int points = 0;
        for(int i = 0; i < numberOfDice; i++){
            points += random.nextInt(AMOUNT_OF_SIDES) + 1;
        }
        synchronized(commentator){
            try{
                while(commentator.getNumberOfRoundsLeft() != 0){
                    if(commentator.getHowManyPlayersLast() > 1){
                        if(commentator.getCurrentMaxScore() != AMOUNT_OF_SIDES * numberOfDice){
                            commentator.writeResult(points, this);
                        }
                        else {
                            commentator.decreaseNumberOfPlayers();
                        }
                        System.out.println(getName() + "Waiting");
                        commentator.wait();
                    } else{
                        if(commentator.getCurrentMaxScore() != AMOUNT_OF_SIDES * numberOfDice){
                            commentator.writeResult(points, this);
                        }
                        else {
                            commentator.decreaseNumberOfPlayers();
                        }
                        System.out.println(getName() + "NotofiALL");
                        commentator.notifyAll();
                    }
                }
                if(commentator.getCurrentMaxScore() != AMOUNT_OF_SIDES * numberOfDice){
                    commentator.writeResult(points, this);
                }

            }
            catch(InterruptedException e){
                System.out.println("Something go wrong! Thread name: " + getName());
            }
        }
    }

    @Override
    public int compareTo(Player o){
        return countOfWins - o.countOfWins;
    }

    @Override
    public String toString(){
        return getName();
    }
}
