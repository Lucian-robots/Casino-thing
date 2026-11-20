import java.util.ArrayList;
import java.util.Collections;

public class driver {

    public static ArrayList<cardDeck> deck = new ArrayList<>();
    
    //public static ArrayList<cardDeck> shuffledDeck = new ArrayList<>();
    
    public static ArrayList<cardDeck> createDeck() {
        int suit = 2;
        String suitString = "Hearts";
       
        for (int z = 0; z < 4; z++) {
            for (int i = 1; i <= 14; i++) {
                cardDeck c1 = new cardDeck(suitString, i);
                deck.add(c1);
            }
            if (suit == 2) {
                suitString = "Diamonds";
            }
            else if (suit == 3) {
                suitString = "Clubs";
            }
            else if (suit == 4) {
                suitString = "Spades";
            }
            suit++;
        }
        return deck;
    }

    
    public static ArrayList<cardDeck> shuffleDeck() {
        Collections.shuffle(deck);
        return deck;
    }
    public static ArrayList<cardDeck> shuffledDeck = shuffleDeck();
    
    
    public static void main(String[]args) {
        //createDeck();
        System.out.println(shuffledDeck);
    } // makethe whole dek after this method
    
}
