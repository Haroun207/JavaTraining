public class Card {
    int number;
    CardType type;
Card(int number, CardType type){
    this.number=number;
    this.type=type;
}
    public void setNumber(int number) {
        this.number = number;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }
}
