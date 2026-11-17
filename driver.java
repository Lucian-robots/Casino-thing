import java.util.ArrayList;

public class driver {

    public static ArrayList<cardDeck> deck = new ArrayList<>();

    public static void createDeck() {
        for (int i = 1; i <= 10; i++) {
            cardDeck c1 = new cardDeck("RED", i);
            deck.add(c1);
        }
        System.out.println("ting");
    }

    public static void main(String[]args) {
        createDeck();

    }
}
