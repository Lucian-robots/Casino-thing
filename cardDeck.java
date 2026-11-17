public class cardDeck {
    public String cardColor;
    public int cardValue;
    
    public cardDeck(String tempCardColor, int tempCardValue) {
        this.cardColor = tempCardColor;
        this.cardValue = tempCardValue;
    }
    public String getColor() {
        return cardColor;
    }
    public int getValue() {
        return cardValue;
    }
    
}
