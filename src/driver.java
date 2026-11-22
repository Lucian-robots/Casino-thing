import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class driver {
    public static int playerMoney = 500;
    public static int casinoMoney;
    public static int betPub;
    

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
            pokerDriver();
        }
        else if (choice == 2) {
            System.out.println("Blackjack it is!");
            blackjackDriver();
            int i = 0;
            while (i != 2) {
                System.out.println("Do you want to play blackjack again? 1. Yes, 2. No");
                i = input.nextInt();
                if (i == 1) {
                    blackjackDriver();   
                }
            }
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
        dealerCards.clear();
        playerCards.clear();
        Scanner input = new Scanner(System.in);
        int totalValue = 0;
        boolean bust = false;

        //System.out.println("Yo");
        dealCards(2);
        
        System.out.println("The dealers top card is a " + dealerCards.get(0));
        System.out.println("Your cards are " + playerCards);
        System.out.println("Time to bet! Minimum bet is $50 and you have $" + playerMoney + ". How much are you going to bet?");
        betPub = input.nextInt();
        while (betPub < 50) {
            System.out.println("Input was invalid. Pick another");
            betPub = input.nextInt();
            playerMoney -=betPub;
        }
        while (bust != true) {
            totalValue = totalValue("player", "blackjack");
            if (totalValue == 21) {
                System.out.println("Blackjack!");
                bust = false;
                break;
            }
            else if (totalValue > 21) {
                System.out.println("Bust!");
                bust = true;
                break;
            }
            System.out.println("Your cards are: " + playerCards);
            
            System.out.println(BLUE+"Your cards values add up to " + totalValue + " Do you 1. Hit, 2. Stand, 3. Bet more" + ANSI_RESET);
            int choice = input.nextInt();
            if (choice == 1) {
                drawCard();
                System.out.println("You hit and get a " + playerCards.get(0));
            }
            else if (choice == 2) {
                System.out.println("You stand at " + totalValue);
                break;
            }
            else if (choice == 3) {
                System.out.println("How much will you add to your bet?");
                int bet = input.nextInt();
                betPub = bet + betPub;
            }
            else {
                System.out.println("Input was invalid");
            }
        } // checlk who won, check who busted
        if (bust == true) {
            System.out.println("You busted at " + totalValue + ". Now it's the dealers turn");
            dealerTurn("blackjack");
        }
        else {
            System.out.println("It's the dealers turn");
            dealerTurn("blackjack");
        }
        // after dealer turn, check who won and the bet they made. 
        if (bust == true) {
            System.out.println(RED+"Your value was " + totalValue + " and Dealers was: " + dealCardTotal+ANSI_RESET);
            System.out.println("Dealer won because u busted!\nYou lost your money!");
            playerMoney-=betPub;
        }
        else if (dealCardTotal > 21) {
            //System.out.println("Dealer busted! You win!");
            playerMoney += 2*betPub;
            System.out.println("Dealer busted! You win! You got " + 2*betPub + " dollars from that!");
        }
        else if (totalValue > dealCardTotal) {
            playerMoney += betPub*2;
            System.out.println(RED+"Your value was " + totalValue + " and Dealers was: " + dealCardTotal+ANSI_RESET);
            System.out.println("You won! You get your double your bet back! It is: " + 2*betPub + " back to you!");          
        }
        else if (totalValue == dealCardTotal) {
            playerMoney -= betPub;
            System.out.println(RED+"Your value was " + totalValue + " and Dealers was: " + dealCardTotal +ANSI_RESET);
            System.out.println("You tied in value but the dealer wins in a tie! You lost " + betPub + " dollars"); 
        }
        else if (dealCardTotal > totalValue) {
            System.out.println(RED+"Your value was " + totalValue + " and Dealers was: " + dealCardTotal+ANSI_RESET);
            System.out.println("You lost " + betPub + " dollars because you lost");     
        }
    }
     /*
    In blackjack, the dealer follows fixed rules rather than choosing whether to hit or stand:

        The dealer draws cards on 16 or less.
        The dealer usually stands on hard 17 or higher.

    */
    // make dealer turn, input for blackjack or poker
    public static int dealCardTotal;
    public static void dealerTurn(String game) {
        if (game.equals("blackjack")) {    
            System.out.println("The dealers top card is a " + dealerCards.get(0));
            dealCardTotal = totalValue("dealer", "blackjack");
            while (dealCardTotal <=16) {
                dealDrawCard();
                dealCardTotal = totalValue("dealer", "blackjack");
                System.out.println(dealCardTotal);
            }
            if (dealCardTotal == 21) {
                System.out.println("Dealer has blackjack!");
            }
            else if (dealCardTotal > 21) {
                System.out.println("Dealer busts!");
            }
            else {
                System.out.println("The dealer has to stand on " + dealCardTotal);
            }
        }
        else if (game.equals("poker")) {
            
            
        }
    }
    public static String pokerHandDetectionDealer() {
        //String hand = "";
        int matches = 0;
        for (int i = 0; i < playerCards.size(); i++) {
            /*
            0-1 0-2 0-3 0-4
            1-2 1-3 1-4
            2-3 2-4 
            3-4
            */
           // need to find which one will be the best
            for (int j = i+1; j < playerCards.size(); j++) {
                if (playerCards.get(i).isValueEqual(playerCards.get(j))) {
                    matches++;
                }
            }
        }

        // time to check for straughts, flush, and royal + straight flush
        int suitMatches = 0;
        for (int z = 0; z < playerCards.size(); z++) {          
            if (playerCards.get(0).isSuitEqual(playerCards.get(z))) {
             suitMatches++;
            }          
        }

        Collections.sort(playerCards,(card1, card2) -> Integer.compare(card1.getValue(), card2.getValue()));
        // make the playersCards in order of value, then check for a straight
        boolean isStraight = true;

        for (int x = 1; x < playerCards.size(); x++) {
            int previousValue = playerCards.get(x-1).getValue();
            int currentValue = playerCards.get(x).getValue();

            if (currentValue != previousValue +1) {
                isStraight = false;
                break;
            }
        }

        // CHECK HANBDS HERE
        if (matches == 1) {
            return "Pair";
        }
        else if (matches == 2) {
            return "2 Pair";
        }
        else if (matches == 3) {
            return "3 of a kind";
        }
        else if (matches == 4) {
            return "Hold";
        }
        else if (matches == 6) {
            return "Hold";
        }
        else if (isStraight == true && suitMatches == 5) {
            return "Hold";
        }
        else if (isStraight == true) {
            return "Hold";
        }
        else if (suitMatches == 5) {
            return "Hold";
        }
        else {
            return "Hold";
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
    

    // draw card functions for both dealer and player
    public static void drawCard() {
        playerCards.add(shuffledDeck.get(0));
        shuffledDeck.remove(0);
    }
    public static void dealDrawCard() {
        //System.out.println(shuffledDeck);
        dealerCards.add(shuffledDeck.get(0));
        shuffledDeck.remove(0);
    }
    public static int totalValue(String who, String game) {
        int total = 0;
        int currentValue =0;
        if (game.equals("blackjack")) {
            if (who.equals("player")) {
                for (int i = 0; i < playerCards.size(); i++) {
                    currentValue = playerCards.get(i).getValue();
                    if (currentValue >= 11) {
                        currentValue = 10;
                    }
                    else if (currentValue == 14) {
                        currentValue = 11;
                    }
                    total +=currentValue;
                }
                return total;
            }
            else if (who.equals("dealer")) {
                for (int i = 0; i < dealerCards.size(); i++) {
                    currentValue = dealerCards.get(i).getValue();
                    if (currentValue >= 11) {
                        currentValue = 11;
                    }
                    total +=currentValue;
                }
                return total;
            }
        }
        return total;
    }

    // poker here
    public static void pokerDriver() {
        ArrayList<cardDeck> discardCards = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to poker!");
        dealCards(5);
        
        System.out.println("Your cards are " + playerCards);
        System.out.println("How much do you want to bet? Minimum bet is $50, and you have $"+playerMoney);
        betPub = 0;
        betPub = input.nextInt();
        while (betPub < 50) {
            System.out.println("Not enough: enter new value above 50");
            betPub = input.nextInt();
        }
        System.out.println("The hands are 1. high card  2. 2 of a kind   3. 2 pair 4. 3 of a kind   5. straight   6. flush     7. full house    8. 4 of a kind   9. straight flush 10. Royal Flush");
        System.out.println("\nYour cards are " + playerCards +  ". You can discard up to 3 cards, and with an Ace you can discard 4 cards and get new ones, or discard none. How many will you discard / draw back? ");
        int discardAmount = input.nextInt();
        for (int i = 0; i < discardAmount; i++) {
            // ask which card to get rid of 
            System.out.println(playerCards+"\nEnter what number card you want to discard (0 - " + (playerCards.size() -1) + "):  ");
            int card = input.nextInt();
            discardCards.add(playerCards.remove(card));
        }
        if (discardAmount > 0) {
            for (int i = 0; i < discardCards.size(); i ++) {
                drawCard();
            }
        }
        System.out.println("Now your cards are " + playerCards);
        System.out.println(pokerHandDetection());
        // dealer turn
        dealerTurn("poker");
    }
    /* 
    public static void pokerCardsToDiscard() {
        char input = ' ';
        while (input != 'z') {
            System.out.println(playerCards);

        }
    }
    /*high card     2 of a kind   2 pair        3 of a kind   straight   flush         full house    4 of a kind   straight flushroyal flush
    */
    public static String pokerHandDetection() {
        //String hand = "";
        int matches = 0;
        for (int i = 0; i < playerCards.size(); i++) {
            /*
            0-1 0-2 0-3 0-4
            1-2 1-3 1-4
            2-3 2-4 
            3-4
            */
            for (int j = i+1; j < playerCards.size(); j++) {
                if (playerCards.get(i).isValueEqual(playerCards.get(j))) {
                    matches++;
                }
            }
        }

        // time to check for straughts, flush, and royal + straight flush
        int suitMatches = 0;
        for (int z = 0; z < playerCards.size(); z++) {          
            if (playerCards.get(0).isSuitEqual(playerCards.get(z))) {
             suitMatches++;
            }          
        }

        Collections.sort(playerCards,(card1, card2) -> Integer.compare(card1.getValue(), card2.getValue()));
        // make the playersCards in order of value, then check for a straight
        boolean isStraight = true;

        for (int x = 1; x < playerCards.size(); x++) {
            int previousValue = playerCards.get(x-1).getValue();
            int currentValue = playerCards.get(x).getValue();

            if (currentValue != previousValue +1) {
                isStraight = false;
                break;
            }
        }

        // CHECK HANBDS HERE
        if (matches == 1) {
            return "Pair";
        }
        else if (matches == 2) {
            return "2 Pair";
        }
        else if (matches == 3) {
            return "3 of a kind";
        }
        else if (matches == 4) {
            return "Full House";
        }
        else if (matches == 6) {
            return "4 of a kind";
        }
        else if (isStraight == true && suitMatches == 5) {
            return "Straigh Flush";
        }
        else if (isStraight == true) {
            return "Straight";
        }
        else if (suitMatches == 5) {
            return "Flush";
        }
        else {
            return "High Card";
        }
    }
    public static boolean containsCardPlayer(String cardType) {
        boolean contains = false;
        for (int i = 0; i < playerCards.size(); i++) {
            if (playerCards.get(i).getSuit().equals("cardType")) {
                contains = true;
            }
        }
        if (contains == true) {
            return true;
        }
        else {
            return false;
        }
    }
    // main bs
    public static void main(String[]args) {
        gameDriver();
    } 
    
}
