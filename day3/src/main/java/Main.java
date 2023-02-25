import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       System.out.println("Enter your game name: ");
        Scanner input=new Scanner(System.in);
        String gameName=input.nextLine();
        Game game = new Game(gameName);
        game.setPlayersName();
        game.distributeDeck();
        game.printInfo();

    }
}