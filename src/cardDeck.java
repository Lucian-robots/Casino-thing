public class cardDeck {
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public boolean ting;
    
    public String cardSuit;
    public int cardValue;
    public String tenPlusString;
    
    public cardDeck(String tempCardColor, int tempCardValue) {
        this.cardSuit = tempCardColor;
        this.cardValue = tempCardValue;
    }
    public String getColor() {
        return cardSuit;
    }
    public int getValue() {
        return cardValue;
    }
    public static void print(String thing) {
        System.out.println(thing);
    }
    @Override 
    public String toString() {
        
        // tenPlusString is only to not print Diamond, 14 but instead, Diamond King
        
        if (cardValue == 11) {
            tenPlusString = "Jack";
            ting = true;
        }
        else if (cardValue == 12) {
            tenPlusString = "Queen";
            ting = true;
        }
        else if (cardValue == 13) {
            tenPlusString = "King";
            ting = true;
        }
        else if (/*cardValue == 1 ||*/ cardValue == 14) {
            tenPlusString = "Ace";
            ting = true;
        }
        
        if (ting == false) {
            if (cardSuit.equals("Diamonds") || cardSuit.equals("Hearts")) {
                return ANSI_UNDERLINE+ cardValue +ANSI_RESET + " of " +RED+ cardSuit +ANSI_RESET;
            }
            else {
                return ANSI_UNDERLINE+ cardValue +ANSI_RESET + " of " + cardSuit +ANSI_RESET;
            }
        }
        else {
            if (cardSuit.equals("Diamonds") || cardSuit.equals("Hearts")) {
                return ANSI_UNDERLINE+ tenPlusString +ANSI_RESET + " of " +RED+ cardSuit +ANSI_RESET;
            }
            else {
                return ANSI_UNDERLINE+ tenPlusString +ANSI_RESET + " of " +RED+ cardSuit +ANSI_RESET;
            }
        }
    }
}
