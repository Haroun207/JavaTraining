public class Player {
    private String name;
    int score;
    Card[] cards;
Player(String s){
    this.name=s;
    score=0;
    cards=new Card[13];
}
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Card[] getCards() {
        return cards;
    }
    public void showCards(){
        for(int i=0;i< cards.length;i++){
            System.out.println("card number: "+cards[i].getNumber()+" card type: "+cards[i].getType());
        }
    }
}
