import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class driver {
    public static int playerMoney;
    public static int casinoMoney;
    public static int bet;

    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

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
        shuffleDeck();
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
        System.out.println("do you 1. want to cheat, or 2. no cheat be good boy");
        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("So you choose to cheat ;). Don't get caught or you WILL lose all of your money and access into another casino");
        }
        else {
            System.out.println("tsktsktsk you choose to not cheat xd loser. ");
            blackjack();
        }
    }
    public static void blackjack() {
        Scanner input = new Scanner(System.in);
        int totalValue = 0;
        boolean bust = false;


        /*
        In blackjack, the dealer follows fixed rules rather than choosing whether to hit or stand:

            The dealer draws cards on 16 or less.
            The dealer usually stands on hard 17 or higher.g

        tstst
        */
        System.out.println("Yo");
        dealCards(2);
        while (bust != true) {
            
            System.out.println("Your cards are: " + playerCards);
            totalValue = totalValue("player");
            System.out.println(BLUE+"Your cards values add up to " + totalValue + " Do you 1. Hit, 2. Stand, 3. Bet more" + ANSI_RESET);
            String choice = input.nextLine();


        }

    }
    // universal deal, also, make the playerCards and dealerCards
    public static ArrayList<cardDeck> playerCards = new ArrayList<>();
    public static ArrayList<cardDeck> dealerCards = new ArrayList<>();

    public static void dealCards(int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard();
            dealDrawCard();
        }
    }
    // make dealer turn, input for blackjack or poker
    //

    // draw card functions for both dealer and player
    public static void drawCard() {
        playerCards.add(shuffledDeck.get(0));
        shuffledDeck.remove(0);
    }
    public static void dealDrawCard() {
        dealerCards.add(shuffledDeck.get(0));
        shuffledDeck.remove(0);
    }
    public static int totalValue(String who) {
        int total = 0;
        int currentValue;

        if (who.equals("player")) {
            for (int i = 0; i < playerCards.size(); i++) {
                currentValue = playerCards.get(0).getValue();
                total +=currentValue;
            }
        }
        return total;
    }



    // main bs
    public static void main(String[]args) {
        gameDriver();
    } 
    
}
