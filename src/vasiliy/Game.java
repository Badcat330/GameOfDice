package vasiliy;

public class Game{

    public static void main(String[] args){
        Commentator commentator = new Commentator();
        commentator.start();
        try{
            commentator.join();
        }
        catch(InterruptedException e){

            System.out.printf("%s has been interrupted", commentator.getName());
        }

    }
}
