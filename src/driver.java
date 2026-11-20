import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class driver {
    public static int playerMoney;
    public static int casinoMoney;
    public static int bet;

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
        deck.clear();
        createDeck();
        Collections.shuffle(deck);
        return deck;
        
    }
    public static ArrayList<cardDeck> shuffledDeck = shuffleDeck();
    

    public static void gameDriver() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the casino! Will you play fairly or cheat?");
        System.out.println("You can play Blackjack or Poker. Which one? \t1. Poker, \t2. Blackjack");
        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("Poker it is!");
        }
        else if (choice == 2) {
            System.out.println("Blackjack it is!");
            blackjackDriver();
        }
        else {
            System.out.println("Invalid input gng :sob");
            gameDriver();
        }

    }
    public static void blackjackDriver() {
        Scanner input = new Scanner(System.in);
        System.out.println("");
        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("So you choose to cheat ;). Don't get caught or you WILL lose all of your money and access into another casino");
        }
        else {
            System.out.println("tsktsktsk you choose to not cheat xd loser. ");

        }
    }
    public static void blackjack() {
        int totalValue = 0;
        boolean bust = false;


        /*
        In blackjack, the dealer follows fixed rules rather than choosing whether to hit or stand:

            The dealer draws cards on 16 or less.
            The dealer usually stands on hard 17 or higher.

        tstst
        */
        while (bust != true) {
            System.out.println("Yo");
        }

    }
    // universal deal
    public static void dealCards(int amount) {

    }
    // make dealer turn, input for blackjack or poker


    // main bs
    public static void main(String[]args) {
        //createDeck();
        System.out.println(shuffledDeck);
    } // makethe whole dek after this method
    
}
