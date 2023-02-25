import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private String name;
    Player[] players;
    Card[] deck;
    public Game(String name){
        this.name=name;
        this.players =new Player[4];
        this.deck=new Card[52];
        initDeck();
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
public void setPlayersName(){
        for(int i=0;i<4;i++)
        {  System.out.println("enter player number "+ (i+1)+" name: ");
            Scanner input=new Scanner(System.in);
            String playerName=input.nextLine();
            players[i]=new Player(playerName);
        }
}
private void initDeck(){
        int index=0;
   for(CardType cardType: CardType.values()){
       for(int j=1;j<=13;j++){
           deck[index]=new Card(j,cardType);
           index++;
       }
   }
}
public void distributeDeck(){
      shuffleDeck();
      int start=0,end=13;
      for (int i=0;i< players.length;i++){

          players[i].setCards(Arrays.copyOfRange(deck,start,end));

          start=end;
          end=end+13;

      }
}
private void shuffleDeck(){
        Random rand=new Random();
for(int i=0;i<deck.length;i++){
    int r=i+rand.nextInt(52-i);
    Card temp=deck[r];
    deck[r]=deck[i];
    deck[i]=temp;
}
}
public void printInfo(){
        System.out.println("Game Name: "+ getName());
        for(int i=0;i< players.length;i++){
            System.out.println("Player number "+(i+1)+" name: "+ players[i].getName());
            players[i].showCards();
        }
}
}
